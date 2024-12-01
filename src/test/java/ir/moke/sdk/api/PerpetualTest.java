package ir.moke.sdk.api;

import ir.moke.coinex.Coinex;
import ir.moke.coinex.model.Response;
import ir.moke.coinex.model.enums.MarginMode;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderSide;
import ir.moke.coinex.model.enums.OrderType;
import ir.moke.coinex.model.request.CancelOrder;
import ir.moke.coinex.model.request.ClosePosition;
import ir.moke.coinex.model.request.Order;
import ir.moke.coinex.model.request.PositionLeverage;
import ir.moke.coinex.model.response.*;
import ir.moke.coinex.resource.Asset;
import ir.moke.coinex.resource.Perpetual;
import ir.moke.kafir.utils.JsonUtils;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class PerpetualTest {
    private static final String SYMBOL = "GOATUSDT";
    private static Perpetual perpetual;
    private static Asset asset;

    /**
     * Each request need to create new instance of api .
     * Why? timeout and update timestamp in interceptor
     */
    @BeforeEach
    public void createPerpetualApi() {
        perpetual = new Coinex.Builder()
                .setAccessId(Configuration.ACCESS_ID)
                .setConnectionTimeout(Duration.ofSeconds(5))
                .setSecretKey(Configuration.SECRET_KEY)
                .perpetual();

        asset = new Coinex.Builder()
                .setAccessId(Configuration.ACCESS_ID)
                .setConnectionTimeout(Duration.ofSeconds(5))
                .setSecretKey(Configuration.SECRET_KEY)
                .asset();
    }

    @Test
    @org.junit.jupiter.api.Order(0)
    public void market() {
        Response<List<MarketResponse>> response = perpetual.market(SYMBOL);
        Assertions.assertEquals(0, response.code());
        Assertions.assertEquals(1, response.data().size());
        Assertions.assertEquals(SYMBOL, response.data().getFirst().market());
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void ticker() {
        Response<List<TickerResponse>> response = perpetual.ticker("%s,DOGEUSDT".formatted(SYMBOL));
        Assertions.assertEquals(0, response.code());
        Assertions.assertEquals(2, response.data().size());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void checkLeverage() {
        Response<LeverageResponse> resp = perpetual.adjustPositionLeverage(new PositionLeverage(SYMBOL, MarketType.FUTURES, MarginMode.ISOLATED, 20));
        Assertions.assertNotNull(resp);
        Assertions.assertEquals(resp.data().leverage(), 20);

        // second time
        int leverage = 10;
        resp = perpetual.adjustPositionLeverage(new PositionLeverage(SYMBOL, MarketType.FUTURES, MarginMode.ISOLATED, leverage));
        Assertions.assertNotNull(resp);
        Assertions.assertEquals(resp.data().leverage(), leverage);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void checkOrders() {
        Response<List<OrderResponse>> orderResponseResponse = perpetual.pendingOrder(null, MarketType.FUTURES, null, null, null, null);
        Assertions.assertNotNull(orderResponseResponse);

        if (!orderResponseResponse.data().isEmpty()) {
            for (OrderResponse item : orderResponseResponse.data()) {
                Response<OrderResponse> response = perpetual.cancelOrder(new CancelOrder(item.market(), item.marketType(), item.orderId()));
                Assertions.assertEquals(response.code(), 0);
            }
        }
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void futureBalance() {
        Response<List<FutureBalanceResponse>> futuresBalanceResponse = asset.futuresBalance();
        Assertions.assertNotNull(futuresBalanceResponse);
        Assertions.assertEquals(0, futuresBalanceResponse.code());
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    public void spotBalance() {
        Response<List<SpotBalanceResponse>> spotBalanceResponse = asset.spotBalance();
        Assertions.assertNotNull(spotBalanceResponse);
        Assertions.assertEquals(0, spotBalanceResponse.code());
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    public void checkMarginMode() {
//        perpetual.adjustPositionMargin(new PositionMargin(SYMBOL,MarketType.FUTURES,))
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    public void limitOrder() {
        Response<List<TickerResponse>> tickerResponse = perpetual.ticker(SYMBOL);
        double markPrice = Double.parseDouble(tickerResponse.data().getFirst().markPrice());
        markPrice = markPrice - (15 * markPrice / 100);

        Response<List<MarketResponse>> marketResponse = perpetual.market(SYMBOL);
        String minAmount = marketResponse.data().getFirst().minAmount();

        Order order = new Order(SYMBOL, MarketType.FUTURES, OrderSide.BUY, OrderType.LIMIT, minAmount, String.valueOf(markPrice), null, false, null);
        Response<OrderResponse> response = perpetual.order(order);
        Assertions.assertEquals(0, response.code());
        Response<List<OrderResponse>> orderResponseResponse = perpetual.pendingOrder(null, MarketType.FUTURES, null, null, null, null);
        Assertions.assertFalse(orderResponseResponse.data().isEmpty());

        for (OrderResponse orderResponse : orderResponseResponse.data()) {
            Response<OrderResponse> cancelOrder = perpetual.cancelOrder(new CancelOrder(orderResponse.market(), orderResponse.marketType(), orderResponse.orderId()));
            Assertions.assertEquals(cancelOrder.code(), 0);
        }

        orderResponseResponse = perpetual.pendingOrder(null, MarketType.FUTURES, null, null, null, null);
        Assertions.assertTrue(orderResponseResponse.data().isEmpty());
    }

    @Test
    @org.junit.jupiter.api.Order(8)
    public void marketOrder() {
        // First check current open positions
        Response<List<PositionResponse>> positionResponse = perpetual.pendingPosition(SYMBOL, MarketType.FUTURES, null, null);
        Assertions.assertEquals(positionResponse.code(), 0);
        System.out.printf("Current open positions: %s%n", positionResponse.data().size());
        if (!positionResponse.data().isEmpty()) {
            for (PositionResponse pr : positionResponse.data()) {
                Response<OrderResponse> closeResponse = perpetual.closePosition(new ClosePosition(pr.market(), pr.marketType()));
                Assertions.assertEquals(closeResponse.code(), 0);
            }
        }

        Response<List<MarketResponse>> marketResponse = perpetual.market(SYMBOL);
        String minAmount = marketResponse.data().getFirst().minAmount();

        // Check available balance before enter position
        Response<List<FutureBalanceResponse>> beforeBalanceResponse = asset.futuresBalance();
        Assertions.assertEquals(0, beforeBalanceResponse.code());
        System.out.println(JsonUtils.toJson(beforeBalanceResponse.data()));

        // Enter market position
        Order order = new Order(SYMBOL, MarketType.FUTURES, OrderSide.BUY, minAmount);
        Response<OrderResponse> response = perpetual.order(order);
        System.out.println("Order : " + JsonUtils.toJson(response.data()));
        Assertions.assertEquals(0, response.code());

        // Check available balance after enter position
        Response<List<FutureBalanceResponse>> afterBalanceResponse = asset.futuresBalance();
        Assertions.assertEquals(0, afterBalanceResponse.code());
        System.out.println(JsonUtils.toJson(afterBalanceResponse.data()));

        // List orders should be empty
        Response<List<OrderResponse>> pendingOrderResponse = perpetual.pendingOrder(SYMBOL, MarketType.FUTURES, null, null, null, null);
        System.out.println("Pending Order : " + JsonUtils.toJson(pendingOrderResponse.data()));
        Assertions.assertTrue(pendingOrderResponse.data().isEmpty());

        // List current positions
        positionResponse = perpetual.pendingPosition(SYMBOL, MarketType.FUTURES, null, null);
        System.out.println("Position : " + JsonUtils.toJson(positionResponse.data()));
        Assertions.assertNotNull(positionResponse);
        Assertions.assertEquals(0, positionResponse.code());

        // Try to close position
        Response<OrderResponse> closePositionOrder = perpetual.closePosition(new ClosePosition(SYMBOL, MarketType.FUTURES));
        System.out.println("Close position : " + JsonUtils.toJson(closePositionOrder.data()));
        Assertions.assertNotNull(closePositionOrder);
        Assertions.assertEquals(closePositionOrder.code(), 0);

        // List position after close
        positionResponse = perpetual.pendingPosition(SYMBOL, MarketType.FUTURES, null, null);
        System.out.println("Position After Close : " + JsonUtils.toJson(positionResponse.data()));
        Assertions.assertNotNull(positionResponse);
        Assertions.assertTrue(positionResponse.data().isEmpty());
    }

    @Test
    @org.junit.jupiter.api.Order(9)
    public void positionHistory() {
        Response<List<PositionResponse>> response = perpetual.finishedPosition(SYMBOL, MarketType.FUTURES, null, null, 0, 1);
        Assertions.assertEquals(response.code(), 0);
    }
}

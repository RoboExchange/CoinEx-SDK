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
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class PerpetualTest {
    private static final String SYMBOL = "DOTUSDT";
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
        Response<List<TickerResponse>> response = perpetual.ticker("BTCUSDT,DOGEUSDT");
        Assertions.assertEquals(0, response.code());
        Assertions.assertEquals(2, response.data().size());
        Assertions.assertEquals(SYMBOL, response.data().getFirst().market());
        Assertions.assertEquals("DOGEUSDT", response.data().getLast().market());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void checkLeverage() {
        Response<LeverageResponse> resp = perpetual.adjustPositionLeverage(new PositionLeverage(SYMBOL, MarketType.FUTURES, MarginMode.ISOLATED, 10));
        Assertions.assertNotNull(resp);
        Assertions.assertEquals(resp.data().leverage(), 10);

        // second time
        resp = perpetual.adjustPositionLeverage(new PositionLeverage(SYMBOL, MarketType.FUTURES, MarginMode.ISOLATED, 1));
        Assertions.assertNotNull(resp);
        Assertions.assertEquals(resp.data().leverage(), 1);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void checkOrders() {
        Response<List<OrderResponse>> orderResponseResponse = perpetual.pendingOrder(null, MarketType.FUTURES, null, null, null, null);
        Assertions.assertNotNull(orderResponseResponse);
        Assertions.assertEquals(orderResponseResponse.data().size(), 0);
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
    public void limitOrder() {
        Order order = new Order(SYMBOL, MarketType.FUTURES, OrderSide.BUY, OrderType.LIMIT, "1", "5", null, false, null);
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
    @org.junit.jupiter.api.Order(7)
    public void marketOrder() {

        // Enter market position
        Order order = new Order(SYMBOL, MarketType.FUTURES, OrderSide.BUY, "1");
        Response<OrderResponse> response = perpetual.order(order);
        Assertions.assertEquals(0, response.code());

        // List orders should be empty
        Response<List<OrderResponse>> orderResponseResponse = perpetual.pendingOrder(SYMBOL, MarketType.FUTURES, null, null, null, null);
        Assertions.assertTrue(orderResponseResponse.data().isEmpty());

        // List current positions
        Response<List<PositionResponse>> positionResponse = perpetual.pendingPosition(SYMBOL, MarketType.FUTURES, null, null);
        Assertions.assertNotNull(positionResponse);
        Assertions.assertEquals(0, positionResponse.code());

        // Try to close position
        Response<OrderResponse> closePositionOrder = perpetual.closePosition(new ClosePosition(SYMBOL, MarketType.FUTURES, OrderType.MARKET));
        Assertions.assertNotNull(closePositionOrder);
        Assertions.assertEquals(closePositionOrder.code(), 0);

        // List position after close
        positionResponse = perpetual.pendingPosition(SYMBOL, MarketType.FUTURES, null, null);
        Assertions.assertNotNull(positionResponse);
        Assertions.assertTrue(positionResponse.data().isEmpty());
    }
}

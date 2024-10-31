package ir.moke.sdk.api;

import ir.moke.coinex.Coinex;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderSide;
import ir.moke.coinex.model.enums.OrderType;
import ir.moke.coinex.model.request.Order;
import ir.moke.coinex.model.response.TickerResponse;
import ir.moke.coinex.model.response.MarketResponse;
import ir.moke.coinex.model.response.OrderResponse;
import ir.moke.coinex.model.Response;
import ir.moke.coinex.resource.Perpetual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PerpetualTest {
    private static Perpetual perpetual;

    /**
     * Each request need to create new instance of api .
     * Why? timeout and update timestamp in interceptor
     */
    @BeforeEach
    public void createPerpetualApi() {
        perpetual = new Coinex.Builder()
                .setAccessId(Configuration.ACCESS_ID)
                .setSecretKey(Configuration.SECRET_KEY)
                .perpetual();
    }

    @Test
    public void market() {
        Response<List<MarketResponse>> response = perpetual.market("BTCUSDT");
        Assertions.assertEquals(0, response.getCode());
        Assertions.assertEquals(1, response.getData().size());
        Assertions.assertEquals("BTCUSDT", response.getData().getFirst().getMarket());
    }

    @Test
    public void ticker() {
        Response<List<TickerResponse>> response = perpetual.ticker("BTCUSDT,DOGEUSDT");
        Assertions.assertEquals(0, response.getCode());
        Assertions.assertEquals(2, response.getData().size());
        Assertions.assertEquals("BTCUSDT", response.getData().getFirst().getMarket());
        Assertions.assertEquals("DOGEUSDT", response.getData().getLast().getMarket());
    }

    @Test
    public void order() {
        Order order = new Order("BTCUSDT", MarketType.FUTURES, OrderSide.SELL, OrderType.MARKET, "1000");
        Response<OrderResponse> response = perpetual.order(order);
        System.out.println(response);
        Assertions.assertNotEquals(0, response.getCode());
    }

    @Test
    public void batchOrderStatus() {
        Response<List<Response<OrderResponse>>> batchOrderStatus = perpetual.batchOrderStatus("BTCUSDT", "13400");
    }
}

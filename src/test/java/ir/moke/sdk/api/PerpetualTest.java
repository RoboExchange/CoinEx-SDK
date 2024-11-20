package ir.moke.sdk.api;

import ir.moke.coinex.Coinex;
import ir.moke.coinex.model.Response;
import ir.moke.coinex.model.enums.MarginMode;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.request.PositionLeverage;
import ir.moke.coinex.model.response.MarketResponse;
import ir.moke.coinex.model.response.TickerResponse;
import ir.moke.coinex.resource.Perpetual;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
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
    @org.junit.jupiter.api.Order(0)
    public void market() {
        Response<List<MarketResponse>> response = perpetual.market("BTCUSDT");
        Assertions.assertEquals(0, response.code());
        Assertions.assertEquals(1, response.data().size());
        Assertions.assertEquals("BTCUSDT", response.data().getFirst().market());
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void ticker() {
        Response<List<TickerResponse>> response = perpetual.ticker("BTCUSDT,DOGEUSDT");
        Assertions.assertEquals(0, response.code());
        Assertions.assertEquals(2, response.data().size());
        Assertions.assertEquals("BTCUSDT", response.data().getFirst().market());
        Assertions.assertEquals("DOGEUSDT", response.data().getLast().market());
    }

    @Test
    public void checkLeverage() {
        PositionLeverage leverage = new PositionLeverage("BTCUSDT", MarketType.FUTURES, MarginMode.ISOLATED, 3);
        Response<String> resp = perpetual.adjustPositionLeverage(leverage);
        System.out.println(resp.code());
        System.out.println(resp.data());
    }

//    @Test
//    @org.junit.jupiter.api.Order(2)
//    public void order() {
//        Order order = new Order("BTCUSDT", MarketType.FUTURES, OrderSide.SELL, OrderType.MARKET, "1000");
//        Response<OrderResponse> response = perpetual.order(order);
//        System.out.println(response);
//        Assertions.assertNotEquals(0, response.code());
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(3)
//    public void batchOrderStatus() {
//        Response<List<Response<OrderResponse>>> batchOrderStatus = perpetual.batchOrderStatus("BTCUSDT", "13400");
//    }
}

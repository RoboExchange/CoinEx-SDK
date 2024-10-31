package ir.moke.coinex.resource;

import ir.moke.coinex.model.request.BatchOrder;
import ir.moke.coinex.model.request.Order;
import ir.moke.coinex.model.request.Ticker;
import ir.moke.kafir.annotation.GET;
import ir.moke.kafir.annotation.POST;
import ir.moke.kafir.annotation.QueryParameter;
import ir.moke.coinex.model.response.MarketResponse;
import ir.moke.coinex.model.response.OrderResponse;
import ir.moke.coinex.model.Response;

import java.util.List;

public interface Perpetual {

    @GET("/futures/market")
    Response<List<MarketResponse>> market(@QueryParameter("market") String market);

    @GET("/futures/ticker")
    Response<List<Ticker>> ticker(@QueryParameter("market") String market);

    @POST("/futures/order")
    Response<OrderResponse> order(Order order);

    @POST("/futures/batch-order")
    Response<List<Response<OrderResponse>>> order(BatchOrder batchOrder);

    @GET("/futures/batch-order-status")
    String batchOrderStatus(@QueryParameter("market") String market,@QueryParameter("order_ids") String orderIds);
}

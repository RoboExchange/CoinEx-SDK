package ir.moke.coinex.resource;

import ir.moke.coinex.model.Response;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderSide;
import ir.moke.coinex.model.request.*;
import ir.moke.coinex.model.response.*;
import ir.moke.kafir.annotation.GET;
import ir.moke.kafir.annotation.POST;
import ir.moke.kafir.annotation.QueryParameter;

import java.util.List;

public interface Perpetual {

    @GET("/futures/market")
    Response<List<MarketResponse>> market(@QueryParameter("market") String market);

    @GET("/futures/ticker")
    Response<List<TickerResponse>> ticker(@QueryParameter("market") String market);

    @POST("/futures/order")
    Response<OrderResponse> order(Order order);

    @POST("/futures/stop-order")
    Response<StopOrderResponse> stopOrder(StopOrder stopOrder);

    @POST("/futures/batch-order")
    Response<List<Response<OrderResponse>>> batchOrder(BatchOrder batchOrder);

    @POST("/futures/batch-stop-order")
    Response<List<Response<StopOrderResponse>>> batchStopOrder(BatchStopOrder batchStopOrder);

    @GET("/futures/order-status")
    Response<OrderResponse> orderStatus(@QueryParameter("market") String market, @QueryParameter("order_ids") String orderIds);

    @GET("/futures/batch-order-status")
    Response<List<Response<OrderResponse>>> batchOrderStatus(@QueryParameter("market") String market, @QueryParameter("order_ids") String orderIds);

    @GET("/futures/pending-order")
    Response<List<OrderResponse>> pendingOrder(@QueryParameter("market") String market,
                                               @QueryParameter("market_type") MarketType marketType,
                                               @QueryParameter("side") OrderSide side,
                                               @QueryParameter("client_id") String clientId,
                                               @QueryParameter("page") Integer page,
                                               @QueryParameter("limit") Integer limit);

    @GET("/futures/finished-order")
    Response<OrderResponse> finishedOrder(@QueryParameter("market") String market,
                                          @QueryParameter("market_type") MarketType marketType,
                                          @QueryParameter("side") OrderSide side,
                                          @QueryParameter("page") Integer page,
                                          @QueryParameter("limit") Integer limit);

    @GET("/futures/pending-stop-order")
    Response<OrderResponse> pendingStopOrder(@QueryParameter("market") String market,
                                             @QueryParameter("market_type") MarketType marketType,
                                             @QueryParameter("side") OrderSide side,
                                             @QueryParameter("client_id") String clientId,
                                             @QueryParameter("page") Integer page,
                                             @QueryParameter("limit") Integer limit);

    @GET("/futures/finished-stop-order")
    Response<OrderResponse> finishedStopOrder(@QueryParameter("market") String market,
                                              @QueryParameter("market_type") MarketType marketType,
                                              @QueryParameter("side") OrderSide side,
                                              @QueryParameter("page") Integer page,
                                              @QueryParameter("limit") Integer limit);

    @POST("/futures/modify-order")
    Response<OrderResponse> modifyOrder(ModifyOrder modifyOrder);

    @POST("/futures/modify-stop-order")
    Response<StopOrderResponse> modifyStopOrder(ModifyStopOrder modifyStopOrder);

    @POST("/futures/cancel-all-order")
    Response<Void> cancelAllOrder(CancelAllOrder cancelAllOrder);

    @POST("/futures/cancel-order")
    Response<OrderResponse> cancelOrder(CancelOrder cancelOrder);

    @POST("/futures/cancel-stop-order")
    Response<OrderResponse> cancelStopOrder(CancelStopOrder cancelStopOrder);

    @POST("/futures/close-position")
    Response<OrderResponse> closePosition(ClosePosition closePosition);

    @POST("/futures/adjust-position-margin")
    Response<PositionResponse> adjustPositionMargin(PositionMargin positionMargin);

    @POST("/futures/adjust-position-leverage")
    Response<LeverageResponse> adjustPositionLeverage(PositionLeverage leverage);

    @GET("/futures/pending-position")
    Response<List<PositionResponse>> pendingPosition(@QueryParameter("market") String market,
                                                     @QueryParameter("market_type") MarketType marketType,
                                                     @QueryParameter("page") Integer page,
                                                     @QueryParameter("limit") Integer limit);

    @POST("/futures/set-position-stop-loss")
    Response<PositionResponse> setPositionStopLoss(PositionStopLoss stopLoss);

    @POST("/futures/set-position-take-profit")
    Response<PositionResponse> setPositionTakeProfit(PositionTakeProfit takeProfit);

    @GET("/futures/finished-position")
    Response<List<PositionResponse>> finishedPosition(@QueryParameter("market") String market,
                                                      @QueryParameter("market_type") MarketType marketType,
                                                      @QueryParameter("start_time") Integer startTime, // timestamp
                                                      @QueryParameter("end_time") Integer endTime, // timestamp
                                                      @QueryParameter("page") Integer page,
                                                      @QueryParameter("limit") Integer limit);
}

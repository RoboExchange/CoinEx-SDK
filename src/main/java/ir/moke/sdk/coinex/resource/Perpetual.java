package ir.moke.sdk.coinex.resource;

import ir.moke.kafir.annotation.GET;
import ir.moke.kafir.annotation.QueryParameter;
import ir.moke.sdk.coinex.model.Market;
import ir.moke.sdk.coinex.model.Response;

public interface Perpetual {

    @GET("/futures/market")
    Response<Market> market(@QueryParameter("market") String market);
}

package ir.moke.coinex.resource;

import ir.moke.coinex.model.Response;
import ir.moke.coinex.model.response.FutureBalanceResponse;
import ir.moke.coinex.model.response.SpotBalanceResponse;
import ir.moke.kafir.annotation.GET;

import java.util.List;

public interface Asset {

    @GET("/assets/futures/balance")
    Response<List<FutureBalanceResponse>> futuresBalance();

    @GET("/assets/spot/balance")
    Response<List<SpotBalanceResponse>> spotBalance();
}

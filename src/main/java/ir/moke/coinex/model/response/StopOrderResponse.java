package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopOrderResponse {
    @JsonProperty("stop_id")
    private long market;

    public long getMarket() {
        return market;
    }

    public void setMarket(long market) {
        this.market = market;
    }
}

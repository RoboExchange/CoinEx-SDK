package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;

public record CancelStopOrderClientId(@JsonProperty("market") String market,
                                      @JsonProperty("market_type") MarketType marketType,
                                      @JsonProperty("client_id") String clientId) {
    public CancelStopOrderClientId(MarketType marketType, String clientId) {
        this(null, marketType, clientId);
    }
}

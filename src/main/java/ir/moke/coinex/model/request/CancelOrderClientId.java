package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;

public record CancelOrderClientId(@JsonProperty("market") String market,
                                  @JsonProperty("market_type") MarketType marketType,
                                  @JsonProperty("client_id") String clientId) {
    public CancelOrderClientId(MarketType marketType, String clientId) {
        this(null, marketType, clientId);
    }
}

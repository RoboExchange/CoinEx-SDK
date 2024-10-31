package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;

public record CancelStopOrder(@JsonProperty("market") String market,
                              @JsonProperty("market_type") MarketType marketType,
                              @JsonProperty("stop_id") String stopId) {
}

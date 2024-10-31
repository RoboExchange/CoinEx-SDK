package ir.moke.sdk.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;

public record RecordType(@JsonProperty("market") String market, @JsonProperty("market_type") MarketType marketType) {
}

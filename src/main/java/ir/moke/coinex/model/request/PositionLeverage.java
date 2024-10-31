package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarginMode;
import ir.moke.coinex.model.enums.MarketType;

public record PositionLeverage(@JsonProperty("market") String market,
                               @JsonProperty("market_type") MarketType marketType,
                               @JsonProperty("margin_mode") MarginMode marginMode,
                               @JsonProperty("leverage") Integer leverage) {
}

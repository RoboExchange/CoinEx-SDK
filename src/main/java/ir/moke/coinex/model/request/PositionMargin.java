package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderType;
import ir.moke.coinex.model.enums.StpMode;

public record PositionMargin(@JsonProperty("market") String market,
                             @JsonProperty("market_type") MarketType marketType,
                             @JsonProperty("amount") String amount) {
}

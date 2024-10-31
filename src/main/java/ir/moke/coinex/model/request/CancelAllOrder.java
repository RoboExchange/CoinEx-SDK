package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderSide;

public record CancelAllOrder(@JsonProperty("market") String market,
                             @JsonProperty("market_type") MarketType marketType,
                             @JsonProperty("side") OrderSide orderSide) {
}

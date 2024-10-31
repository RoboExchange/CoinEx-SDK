package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;

public record CancelOrder(@JsonProperty("market") String market,
                          @JsonProperty("market_type") MarketType marketType,
                          @JsonProperty("order_id") String orderId) {
}

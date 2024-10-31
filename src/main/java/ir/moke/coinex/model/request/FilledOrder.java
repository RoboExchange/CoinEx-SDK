package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderSide;

public record FilledOrder(@JsonProperty("market") String market,
                          @JsonProperty("market_type") MarketType marketType,
                          @JsonProperty("side") OrderSide side,
                          @JsonProperty("page") Integer page,
                          @JsonProperty("limit") Integer limit) {
    public FilledOrder(MarketType marketType) {
        this(null, marketType, null, null, null);
    }
}

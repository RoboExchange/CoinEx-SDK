package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderSide;

public record UnfilledOrder(@JsonProperty("market") String market,
                            @JsonProperty("market_type") MarketType marketType,
                            @JsonProperty("side") OrderSide side,
                            @JsonProperty("client_id") String clientId,
                            @JsonProperty("page") Integer page,
                            @JsonProperty("limit") Integer limit) {
    public UnfilledOrder(MarketType marketType) {
        this(null, marketType, null, null, null, null);
    }
}

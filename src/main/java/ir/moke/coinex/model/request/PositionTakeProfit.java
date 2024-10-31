package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.TakeProfitType;

public record PositionTakeProfit(@JsonProperty("market") String market,
                                 @JsonProperty("market_type") MarketType marketType,
                                 @JsonProperty("take_profit_type") TakeProfitType takeProfitType,
                                 @JsonProperty("take_profit_price") String takeProfitPrice) {
}

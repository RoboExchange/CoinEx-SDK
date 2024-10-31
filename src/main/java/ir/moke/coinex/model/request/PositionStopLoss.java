package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderType;
import ir.moke.coinex.model.enums.StopLossType;
import ir.moke.coinex.model.enums.StpMode;

public record PositionStopLoss(@JsonProperty("market") String market,
                               @JsonProperty("market_type") MarketType marketType,
                               @JsonProperty("stop_loss_type") StopLossType stopLossType,
                               @JsonProperty("stop_loss_price") String stopLossPrice) {
}

package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.*;

public record PositionResponse(
        @JsonProperty("position_id") String positionId,
        @JsonProperty("market") String market,
        @JsonProperty("market_type") MarketType marketType,
        @JsonProperty("side") OrderSide side,
        @JsonProperty("margin_mode") MarginMode marginMode,
        @JsonProperty("open_interest") String openInterest,
        @JsonProperty("close_avbl") String closeAvbl,
        @JsonProperty("ath_position_amount") String athPositionAmount,
        @JsonProperty("unrealized_pnl") String unrealizedPnl,
        @JsonProperty("realized_pnl") String realizedPnl,
        @JsonProperty("avg_entry_price") String avgEntryPrice,
        @JsonProperty("cml_position_value") String cmlPositionValue,
        @JsonProperty("max_position_value") String maxPositionValue,
        @JsonProperty("take_profit_price") String takeProfitPrice,
        @JsonProperty("stop_loss_price") String stopLossPrice,
        @JsonProperty("take_profit_type") TakeProfitType takeProfitType,
        @JsonProperty("stop_loss_type") StopLossType stopLossType,
        @JsonProperty("leverage") String leverage,
        @JsonProperty("margin_avbl") String marginAvbl,
        @JsonProperty("ath_margin_size") String athMarginSize,
        @JsonProperty("position_margin_rate") String positionMarginRate,
        @JsonProperty("maintenance_margin_rate") String maintenanceMarginRate,
        @JsonProperty("maintenance_margin_value") String maintenanceMarginValue,
        @JsonProperty("liq_price") String liqPrice,
        @JsonProperty("bkr_price") String bkrPrice,
        @JsonProperty("adl_level") Integer adlLevel,
        @JsonProperty("settle_price") String settlePrice,
        @JsonProperty("settle_value") String settleValue,
        @JsonProperty("created_at") Integer createdAt,
        @JsonProperty("updated_at") Integer updatedAt) {
}

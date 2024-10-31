package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarginMode;
import ir.moke.coinex.model.enums.MarketType;

public record MarginChangeHistoryResponse(
        @JsonProperty("market") String market,
        @JsonProperty("market_type") MarketType marketType,
        @JsonProperty("position_id") String positionId,
        @JsonProperty("margin_mode") MarginMode marginMode,
        @JsonProperty("leverage") String leverage,
        @JsonProperty("liq_price") String liqPrice,
        @JsonProperty("bkr_price") String bkrPrice,
        @JsonProperty("settle_price") String settlePrice,
        @JsonProperty("open_interest") String openInterest,
        @JsonProperty("margin_avbl") String marginAvbl,
        @JsonProperty("margin_change") String marginChange,
        @JsonProperty("created_at") Integer createdAt) {
}

package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderSide;
import ir.moke.coinex.model.enums.OrderStatus;

public record OrderResponse(@JsonProperty("order_id") Long orderId,
                            @JsonProperty("market") String market,
                            @JsonProperty("market_type") MarketType marketType,
                            @JsonProperty("side") OrderSide side,
                            @JsonProperty("type") String type,
                            @JsonProperty("amount") String amount,
                            @JsonProperty("price") String price,
                            @JsonProperty("unfilled_amount") String unfilledAmount,
                            @JsonProperty("filled_amount") String filledAmount,
                            @JsonProperty("filled_value") String filledValue,
                            @JsonProperty("client_id") String clientId,
                            @JsonProperty("fee") String fee,
                            @JsonProperty("fee_ccy") String feeCcy,
                            @JsonProperty("maker_fee_rate") String makerFeeRate,
                            @JsonProperty("taker_fee_rate") String takerFeeRate,
                            @JsonProperty("last_filled_amount") String lastFilledAmount,
                            @JsonProperty("last_filled_price") String lastFilledPrice,
                            @JsonProperty("realized_pnl") String realizedPnl,
                            @JsonProperty("created_at") String createdAt,
                            @JsonProperty("updated_at") String updatedAt,
                            @JsonProperty("status") OrderStatus status) {

}

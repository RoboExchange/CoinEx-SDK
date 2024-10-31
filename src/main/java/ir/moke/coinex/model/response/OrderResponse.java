package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponse {
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("market")
    private String market;
    @JsonProperty("market_type")
    private String marketType;
    @JsonProperty("side")
    private String side;
    @JsonProperty("type")
    private String type;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("price")
    private String price;
    @JsonProperty("unfilled_amount")
    private String unfilledAmount;
    @JsonProperty("filled_amount")
    private String filledAmount;
    @JsonProperty("filled_value")
    private String filledValue;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("fee")
    private String fee;
    @JsonProperty("fee_ccy")
    private String feeCcy;
    @JsonProperty("maker_fee_rate")
    private String makerFeeRate;
    @JsonProperty("taker_fee_rate")
    private String takerFeeRate;
    @JsonProperty("last_filled_amount")
    private String lastFilledAmount;
    @JsonProperty("last_filled_price")
    private String lastFilledPrice;
    @JsonProperty("realized_pnl")
    private String realizedPnl;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

    public String getOrderId() {
        return orderId;
    }

    public String getMarket() {
        return market;
    }

    public String getMarketType() {
        return marketType;
    }

    public String getSide() {
        return side;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getPrice() {
        return price;
    }

    public String getUnfilledAmount() {
        return unfilledAmount;
    }

    public String getFilledAmount() {
        return filledAmount;
    }

    public String getFilledValue() {
        return filledValue;
    }

    public String getClientId() {
        return clientId;
    }

    public String getFee() {
        return fee;
    }

    public String getFeeCcy() {
        return feeCcy;
    }

    public String getMakerFeeRate() {
        return makerFeeRate;
    }

    public String getTakerFeeRate() {
        return takerFeeRate;
    }

    public String getLastFilledAmount() {
        return lastFilledAmount;
    }

    public String getLastFilledPrice() {
        return lastFilledPrice;
    }

    public String getRealizedPnl() {
        return realizedPnl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

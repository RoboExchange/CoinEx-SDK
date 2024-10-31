package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.*;

public record StopOrder(@JsonProperty("market") String market,
                        @JsonProperty("market_type") MarketType marketType,
                        @JsonProperty("side") OrderSide side,
                        @JsonProperty("type") OrderType orderType,
                        @JsonProperty("amount") String amount,
                        @JsonProperty("price") String price,
                        @JsonProperty("trigger_price_type") TriggerPriceType triggerPriceType,
                        @JsonProperty("trigger_price") String triggerPrice,
                        @JsonProperty("client_id") String clientId,
                        @JsonProperty("is_hide") String isHide,
                        @JsonProperty("stp_mode") StpMode stpMode) {
    public StopOrder(String market, MarketType marketType, OrderSide side, OrderType orderType, String amount, TriggerPriceType triggerPriceType, String triggerPrice) {
        this(market, marketType, side, orderType, amount, null, triggerPriceType, triggerPrice, null, null, null);
    }
}

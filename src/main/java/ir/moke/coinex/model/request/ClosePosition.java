package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderType;
import ir.moke.coinex.model.enums.StpMode;

public record ClosePosition(@JsonProperty("market") String market,
                            @JsonProperty("market_type") MarketType marketType,
                            @JsonProperty("type") OrderType orderType,
                            @JsonProperty("amount") String amount,
                            @JsonProperty("price") String price,
                            @JsonProperty("client_id") String clientId,
                            @JsonProperty("is_hide") String isHide,
                            @JsonProperty("stp_mode") StpMode stpMode) {
    public ClosePosition(String market, MarketType marketType) {
        this(market, marketType, OrderType.MARKET, null, null, null, null, null);
    }
}

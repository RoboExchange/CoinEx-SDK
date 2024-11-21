package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.OrderSide;
import ir.moke.coinex.model.enums.OrderType;
import ir.moke.coinex.model.enums.StpMode;

public record Order(@JsonProperty("market") String market,
                    @JsonProperty("market_type") MarketType marketType,
                    @JsonProperty("side") OrderSide side,
                    @JsonProperty("type") OrderType orderType,
                    @JsonProperty("amount") String amount,
                    @JsonProperty("price") String price,
                    @JsonProperty("client_id") String clientId,
                    @JsonProperty("is_hide") Boolean isHide,
                    @JsonProperty("stp_mode") StpMode stpMode) {
    public Order(String market, MarketType marketType, OrderSide side, String amount) {
        this(market, marketType, side, OrderType.MARKET, amount, null, null, null, null);
    }
}

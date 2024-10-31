package ir.moke.sdk.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;

public class BaseClass {
    @JsonProperty("market")
    private String market;
    @JsonProperty("market_type")
    private MarketType marketType;

    public BaseClass() {
    }

    public BaseClass(String market, MarketType marketType) {
        this.market = market;
        this.marketType = marketType;
    }

    public String getMarket() {
        return market;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public static class InnerClass extends BaseClass {
        @JsonProperty("stop_id")
        private String orderId;

        public InnerClass() {
        }

        public InnerClass(String market, MarketType marketType, String orderId) {
            super(market, marketType);
            this.orderId = orderId;
        }

        public String getOrderId() {
            return orderId;
        }
    }
}

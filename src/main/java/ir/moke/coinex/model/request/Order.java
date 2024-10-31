package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarketType;
import ir.moke.coinex.model.enums.StpMode;
import ir.moke.coinex.model.enums.OrderSide;
import ir.moke.coinex.model.enums.OrderType;

public class Order {
    @JsonProperty("market")
    private String market;
    @JsonProperty("market_type")
    private MarketType marketType;
    @JsonProperty("side")
    private OrderSide side;
    @JsonProperty("type")
    private OrderType orderType;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("price")
    private String price;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("is_hide")
    private String isHide;
    @JsonProperty("stp_mode")
    private StpMode stpMode;

    public Order() {
    }

    public Order(String market, MarketType marketType, OrderSide side, OrderType orderType, String amount) {
        this.market = market;
        this.marketType = marketType;
        this.side = side;
        this.orderType = orderType;
        this.amount = amount;
    }

    public Order(String market, MarketType marketType, OrderSide side, OrderType orderType, String amount, String price, String clientId, String isHide, StpMode stpMode) {
        this.market = market;
        this.marketType = marketType;
        this.side = side;
        this.orderType = orderType;
        this.amount = amount;
        this.price = price;
        this.clientId = clientId;
        this.isHide = isHide;
        this.stpMode = stpMode;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public StpMode getStpMode() {
        return stpMode;
    }

    public void setStpMode(StpMode stpMode) {
        this.stpMode = stpMode;
    }
}

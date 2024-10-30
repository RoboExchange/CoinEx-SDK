package ir.moke.sdk.coinex.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Market {
    private String market;
    @JsonProperty("contract_type")
    private String contractType;
    @JsonProperty("taker_fee_rate")
    private String takerFeeRate;
    @JsonProperty("maker_fee_rate")
    private String makerFeeRate;
    @JsonProperty("min_amount")
    private String minAmount;
    @JsonProperty("base_ccy")
    private String baseCcy;
    @JsonProperty("quote_ccy")
    private String quoteCcy;
    @JsonProperty("base_ccy_precision")
    private int baseCcyPrecision;
    @JsonProperty("quote_ccy_precision")
    private int quoteCcyPrecision;
    @JsonProperty("tick_size")
    private String tickSize;
    @JsonProperty("is_market_available")
    private boolean isMarketAvailable;
    @JsonProperty("is_copy_trading_available")
    private boolean isCopyTradingAvailable;
    private List<String> leverage;
    @JsonProperty("open_interest_volume")
    private String openInterestVolume;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getTakerFeeRate() {
        return takerFeeRate;
    }

    public void setTakerFeeRate(String takerFeeRate) {
        this.takerFeeRate = takerFeeRate;
    }

    public String getMakerFeeRate() {
        return makerFeeRate;
    }

    public void setMakerFeeRate(String makerFeeRate) {
        this.makerFeeRate = makerFeeRate;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    public String getQuoteCcy() {
        return quoteCcy;
    }

    public void setQuoteCcy(String quoteCcy) {
        this.quoteCcy = quoteCcy;
    }

    public int getBaseCcyPrecision() {
        return baseCcyPrecision;
    }

    public void setBaseCcyPrecision(int baseCcyPrecision) {
        this.baseCcyPrecision = baseCcyPrecision;
    }

    public int getQuoteCcyPrecision() {
        return quoteCcyPrecision;
    }

    public void setQuoteCcyPrecision(int quoteCcyPrecision) {
        this.quoteCcyPrecision = quoteCcyPrecision;
    }

    public String getTickSize() {
        return tickSize;
    }

    public void setTickSize(String tickSize) {
        this.tickSize = tickSize;
    }

    public boolean isMarketAvailable() {
        return isMarketAvailable;
    }

    public void setMarketAvailable(boolean marketAvailable) {
        isMarketAvailable = marketAvailable;
    }

    public boolean isCopyTradingAvailable() {
        return isCopyTradingAvailable;
    }

    public void setCopyTradingAvailable(boolean copyTradingAvailable) {
        isCopyTradingAvailable = copyTradingAvailable;
    }

    public List<String> getLeverage() {
        return leverage;
    }

    public void setLeverage(List<String> leverage) {
        this.leverage = leverage;
    }

    public String getOpenInterestVolume() {
        return openInterestVolume;
    }

    public void setOpenInterestVolume(String openInterestVolume) {
        this.openInterestVolume = openInterestVolume;
    }
}

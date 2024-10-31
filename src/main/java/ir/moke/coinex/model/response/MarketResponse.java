package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MarketResponse {
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

    public String getContractType() {
        return contractType;
    }

    public String getTakerFeeRate() {
        return takerFeeRate;
    }

    public String getMakerFeeRate() {
        return makerFeeRate;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public String getQuoteCcy() {
        return quoteCcy;
    }

    public int getBaseCcyPrecision() {
        return baseCcyPrecision;
    }

    public int getQuoteCcyPrecision() {
        return quoteCcyPrecision;
    }

    public String getTickSize() {
        return tickSize;
    }

    public boolean isMarketAvailable() {
        return isMarketAvailable;
    }

    public boolean isCopyTradingAvailable() {
        return isCopyTradingAvailable;
    }

    public List<String> getLeverage() {
        return leverage;
    }

    public String getOpenInterestVolume() {
        return openInterestVolume;
    }
}

package ir.moke.coinex.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticker {
    private String market;
    private String last;
    private String open;
    private String close;
    private String high;
    private String low;
    private String volume;
    @JsonProperty("volume_sell")
    private String volumeSell;
    @JsonProperty("volume_buy")
    private String volumeBuy;
    private String value;
    @JsonProperty("index_price")
    private String indexPrice;
    @JsonProperty("mark_price")
    private String markPrice;
    private long period;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolumeSell() {
        return volumeSell;
    }

    public void setVolumeSell(String volumeSell) {
        this.volumeSell = volumeSell;
    }

    public String getVolumeBuy() {
        return volumeBuy;
    }

    public void setVolumeBuy(String volumeBuy) {
        this.volumeBuy = volumeBuy;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIndexPrice() {
        return indexPrice;
    }

    public void setIndexPrice(String indexPrice) {
        this.indexPrice = indexPrice;
    }

    public String getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(String markPrice) {
        this.markPrice = markPrice;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}

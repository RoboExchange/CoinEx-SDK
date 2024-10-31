package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TickerResponse(@JsonProperty("market") String market,
                             @JsonProperty("last") String last,
                             @JsonProperty("open") String open,
                             @JsonProperty("close") String close,
                             @JsonProperty("high") String high,
                             @JsonProperty("low") String low,
                             @JsonProperty("volume") String volume,
                             @JsonProperty("volume_sell") String volumeSell,
                             @JsonProperty("volume_buy") String volumeBuy,
                             @JsonProperty("value") String value,
                             @JsonProperty("index_price") String indexPrice,
                             @JsonProperty("mark_price") String markPrice,
                             @JsonProperty("period") long period) {

}

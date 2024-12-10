package ir.moke.coinex.ws;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MarketStatusSubscription(@JsonProperty("market") String market,
                                       @JsonProperty("last") String last,
                                       @JsonProperty("open") String open,
                                       @JsonProperty("close") String close,
                                       @JsonProperty("high") String high,
                                       @JsonProperty("low") String low,
                                       @JsonProperty("volume") String volume,
                                       @JsonProperty("value") String value,
                                       @JsonProperty("volume_sell") String volumeSell,
                                       @JsonProperty("volume_buy") String volumeBuy,
                                       @JsonProperty("insurance_fund_size") String insuranceFundSize,
                                       @JsonProperty("mark_price") String markPrice,
                                       @JsonProperty("index_price") String indexPrice,
                                       @JsonProperty("open_interest_size") String openInterestSize,
                                       @JsonProperty("latest_funding_rate") String latestFundingRate,
                                       @JsonProperty("next_funding_rate") String nextFundingRate,
                                       @JsonProperty("latest_funding_time") String latestFundingTime,
                                       @JsonProperty("next_funding_time") String nextFundingTime,
                                       @JsonProperty("period") String period) {

}

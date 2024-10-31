package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MarketResponse(@JsonProperty("market") String market,
                             @JsonProperty("contract_type") String contractType,
                             @JsonProperty("taker_fee_rate") String takerFeeRate,
                             @JsonProperty("maker_fee_rate") String makerFeeRate,
                             @JsonProperty("min_amount") String minAmount,
                             @JsonProperty("base_ccy") String baseCcy,
                             @JsonProperty("quote_ccy") String quoteCcy,
                             @JsonProperty("base_ccy_precision") Integer baseCcyPrecision,
                             @JsonProperty("quote_ccy_precision") Integer quoteCcyPrecision,
                             @JsonProperty("tick_size") String tickSize,
                             @JsonProperty("is_market_available") Boolean isMarketAvailable,
                             @JsonProperty("is_copy_trading_available") Boolean isCopyTradingAvailable,
                             @JsonProperty("leverage") List<String> leverage,
                             @JsonProperty("open_interest_volume") String openInterestVolume) {
}

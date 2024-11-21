package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FutureBalanceResponse(@JsonProperty("ccy") String currencyName,
                                    @JsonProperty("available") String available,
                                    @JsonProperty("frozen") String frozen,
                                    @JsonProperty("margin") String margin,
                                    @JsonProperty("transferrable") String transferrable,
                                    @JsonProperty("unrealized_pnl") String unrealizedPnl) {
}

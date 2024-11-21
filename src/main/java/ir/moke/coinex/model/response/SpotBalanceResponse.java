package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpotBalanceResponse(@JsonProperty("ccy") String currencyName,
                                  @JsonProperty("available") String available,
                                  @JsonProperty("frozen") String frozen) {
}

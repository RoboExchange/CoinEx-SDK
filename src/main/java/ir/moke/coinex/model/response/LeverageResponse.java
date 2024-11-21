package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.coinex.model.enums.MarginMode;

public record LeverageResponse(@JsonProperty("margin_mode") MarginMode marginMode,
                               @JsonProperty("leverage") Integer leverage) {
}

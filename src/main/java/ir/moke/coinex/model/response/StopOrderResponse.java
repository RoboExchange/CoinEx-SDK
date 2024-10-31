package ir.moke.coinex.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StopOrderResponse(@JsonProperty("stop_id") long stopId) {
}

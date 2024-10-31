package ir.moke.coinex.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Pagination(@JsonProperty("total") int total, @JsonProperty("has_next") boolean hasNext) {
}

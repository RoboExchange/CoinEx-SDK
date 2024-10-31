package ir.moke.coinex.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StpMode {
    CT("ct"),
    CM("cm"),
    BOTH("both");

    private final String value;

    StpMode(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

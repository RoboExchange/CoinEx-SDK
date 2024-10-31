package ir.moke.coinex.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MarginMode {
    ISOLATED("isolated"),
    CROSS("cross");

    private final String value;

    MarginMode(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

package ir.moke.coinex.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PositionSide {
    LONG("long"),
    SHORT("short");

    private final String value;

    PositionSide(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

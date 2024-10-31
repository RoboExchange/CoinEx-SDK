package ir.moke.coinex.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    OPEN("open"),
    PART_FILLED("part_filled"),
    FILLED("filled"),
    PART_CANCELED("part_canceled"),
    CANCELED("canceled");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

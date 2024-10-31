package ir.moke.coinex.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StopLossType {
    LAST_PRICE("latest_price"),
    MARKET_PRICE("mark_price");

    private final String value;

    StopLossType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

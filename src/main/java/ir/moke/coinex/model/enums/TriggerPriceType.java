package ir.moke.coinex.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TriggerPriceType {
    LAST_PRICE("latest_price"),
    MARK_PRICE("mark_price"),
    INDEX_PRICE("index_price");

    private final String value;

    TriggerPriceType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

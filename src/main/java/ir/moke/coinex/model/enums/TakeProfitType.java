package ir.moke.coinex.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TakeProfitType {
    LAST_PRICE("latest_price"),
    MARKET_PRICE("mark_price");

    private final String value;

    TakeProfitType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

package ir.moke.coinex.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderType {
    LIMIT("limit"),
    MARKET("market"),
    MAKER_ONLY("maker_only"),
    IOC("ioc"),
    FOK("fok");

    private final String value;

    OrderType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

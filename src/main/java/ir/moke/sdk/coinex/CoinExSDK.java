package ir.moke.sdk.coinex;

import ir.moke.kafir.http.Kafir;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CoinExSDK {

    private final String accessId;
    private final String secretKey;
    private final Duration connectionTimeout;
    private String baseUrl = "https://api.coinex.com/v2";

    public CoinExSDK(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.accessId = builder.accessId;
        this.secretKey = builder.secretKey;
        this.connectionTimeout = builder.connectionTimeout;
    }

    public static class Builder {
        private String baseUrl = "https://api.coinex.com/v2";
        private String accessId;
        private String secretKey;
        private Duration connectionTimeout;

        public String getBaseUrl() {
            return baseUrl;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public String getAccessId() {
            return accessId;
        }

        public Builder setAccessId(String accessId) {
            this.accessId = accessId;
            return this;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public Builder setSecretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public Duration getConnectionTimeout() {
            return connectionTimeout;
        }

        public Builder setConnectionTimeout(Duration connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public <T> T build(Class<T> clazz) {
            return new Kafir.KafirBuilder()
                    .setBaseUri(this.baseUrl)
                    .setInterceptor(new SignatureInterceptor(accessId, secretKey))
                    .build(clazz);
        }

        private Map<String, String> headers(String sign, long timestamp) {
            Map<String, String> map = new HashMap<>();
            map.put("X-COINEX-KEY", this.accessId);
            map.put("X-COINEX-SIGN", sign);
            map.put("X-COINEX-TIMESTAMP", String.valueOf(timestamp));
            return map;
        }
    }
}

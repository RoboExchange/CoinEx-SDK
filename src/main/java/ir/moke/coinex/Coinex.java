package ir.moke.coinex;

import ir.moke.coinex.resource.Perpetual;
import ir.moke.kafir.http.Kafir;

import java.time.Duration;

public class Coinex {
    private final String baseUrl;
    private final String accessId;
    private final String secretKey;
    private final Duration connectionTimeout;

    private Coinex(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.accessId = builder.accessId;
        this.secretKey = builder.secretKey;
        this.connectionTimeout = builder.connectionTimeout;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAccessId() {
        return accessId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Duration getConnectionTimeout() {
        return connectionTimeout;
    }

    public static class Builder {
        private String baseUrl = "https://api.coinex.com/v2";
        private String accessId;
        private String secretKey;
        private Duration connectionTimeout = Duration.ofSeconds(3600);

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setAccessId(String accessId) {
            this.accessId = accessId;
            return this;
        }

        public Builder setSecretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public Builder setConnectionTimeout(Duration connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public <T> T build(Class<T> clazz) {
            return new Kafir.KafirBuilder()
                    .setBaseUri(this.baseUrl)
                    .setConnectionTimeout(connectionTimeout)
                    .setInterceptor(new SignatureInterceptor(accessId, secretKey))
                    .build(clazz);
        }

        public Perpetual perpetual() {
            return build(Perpetual.class);
        }
    }
}

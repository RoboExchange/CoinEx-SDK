package ir.moke.coinex;

import ir.moke.coinex.resource.Asset;
import ir.moke.coinex.resource.Perpetual;
import ir.moke.coinex.ws.CoinexListener;
import ir.moke.coinex.ws.WebSocketHandler;
import ir.moke.kafir.http.Kafir;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Coinex {
    private final String baseUrl;
    private final String accessId;
    private final String secretKey;
    private final Duration connectionTimeout;
    private final String perpetualWebSocketUrl;

    private Coinex(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.accessId = builder.accessId;
        this.secretKey = builder.secretKey;
        this.connectionTimeout = builder.connectionTimeout;
        this.perpetualWebSocketUrl = builder.perpetualWebSocketUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPerpetualWebSocketUrl() {
        return perpetualWebSocketUrl;
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
        private String perpetualWebSocketUrl = "wss://socket.coinex.com/v2/futures";
        private String accessId;
        private String secretKey;
        private Duration connectionTimeout = Duration.ofSeconds(3600);

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setPerpetualWebSocketUrl(String perpetualWebSocketUrl) {
            this.perpetualWebSocketUrl = perpetualWebSocketUrl;
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

        public Asset asset() {
            return build(Asset.class);
        }

        public CompletableFuture<WebSocket> perpetualWebSocket(CoinexListener listener) {
            return HttpClient.newHttpClient()
                    .newWebSocketBuilder()
                    .buildAsync(URI.create(perpetualWebSocketUrl), new WebSocketHandler(listener));
        }
    }
}

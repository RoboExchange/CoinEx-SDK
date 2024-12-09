package ir.moke.coinex;

import ir.moke.kafir.http.HttpMethod;
import ir.moke.kafir.http.Interceptor;

import java.net.URI;
import java.net.http.HttpRequest;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.Flow;

public class SignatureInterceptor implements Interceptor {

    private final String accessId;
    private final String secretKey;

    public SignatureInterceptor(String accessId, String secretKey) {
        this.accessId = accessId;
        this.secretKey = secretKey;
    }

    @Override
    public HttpRequest intercept(HttpRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        HttpMethod httpMethod = HttpMethod.valueOf(request.method());

        URI uri = request.uri();
        String path = uri.getPath();
        String query = uri.getQuery();
        long timestamp = System.currentTimeMillis();
        String body = extractBody(request);

        String prepare_str = "%s%s%s%s%s".formatted(httpMethod.name(), path, query != null ? "?" + query : "", body, timestamp);
        String sign = CoinexUtils.generateSignature(secretKey, prepare_str);

        builder.header("X-COINEX-KEY", accessId);
        builder.header("X-COINEX-SIGN", sign);
        builder.header("X-COINEX-TIMESTAMP", String.valueOf(timestamp));

        switch (httpMethod) {
            case GET -> builder.GET();
            case POST -> request.bodyPublisher().ifPresentOrElse(builder::POST, () -> builder.POST(null));
            case PUT -> request.bodyPublisher().ifPresentOrElse(builder::PUT, () -> builder.PUT(null));
            case DELETE -> builder.DELETE();
        }

        return builder.uri(uri).build();
    }

    private String extractBody(HttpRequest request) {
        Optional<HttpRequest.BodyPublisher> bodyPublisher = request.bodyPublisher();
        StringBuilder sb = new StringBuilder();
        bodyPublisher.ifPresent(item -> item.subscribe(bodyPublisherSubscriber(sb)));
        return sb.toString();
    }

    private Flow.Subscriber<ByteBuffer> bodyPublisherSubscriber(StringBuilder sb) {
        return new Flow.Subscriber<>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(ByteBuffer item) {
                sb.append(StandardCharsets.UTF_8.decode(item));
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {

            }
        };
    }
}

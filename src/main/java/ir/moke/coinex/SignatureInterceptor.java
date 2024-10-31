package ir.moke.coinex;

import ir.moke.kafir.http.HttpMethod;
import ir.moke.kafir.http.Interceptor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
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
        String sign = generateSignature(secretKey, prepare_str);

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

    private String generateSignature(String secretKey, String data) {
        try {
            // Create a new secret key spec with the provided secret key and HMAC SHA-256 algorithm
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.ISO_8859_1), "HmacSHA256");

            // Initialize a Mac instance with HMAC SHA-256
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            // Generate the HMAC
            byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.ISO_8859_1));

            // Convert to hexadecimal string and make it lowercase
            StringBuilder hash = new StringBuilder();
            for (byte b : hmacBytes) hash.append(String.format("%02x", b));
            return hash.toString().toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

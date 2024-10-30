package ir.moke.sdk.coinex;

import ir.moke.kafir.http.HttpMethod;
import ir.moke.kafir.http.Interceptor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.http.HttpRequest;

public class SignatureInterceptor implements Interceptor {

    private final String accessId;
    private final String secretKey;

    public SignatureInterceptor(String accessId, String secretKey) {
        this.accessId = accessId;
        this.secretKey = secretKey;
    }

    private static String generateSignature(String secretKey, String data) {
        try {
            // Create HMAC-SHA256 key
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            // Sign the data
            byte[] hash = sha256_HMAC.doFinal(data.getBytes());

            // Convert to hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpRequest intercept(HttpRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        HttpMethod httpMethod = HttpMethod.valueOf(request.method());

        URI uri = request.uri();
        String path = uri.getPath();
        String query = uri.getQuery();
        long timestamp = System.currentTimeMillis();

        String sb = httpMethod +
                path +
                "?" +
                query +
                timestamp;

        String sign = generateSignature(secretKey, sb);

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
}

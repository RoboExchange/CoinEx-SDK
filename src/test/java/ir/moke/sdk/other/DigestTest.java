package ir.moke.sdk.other;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class DigestTest {
    public static void main(String[] args) {
        String secret_key = "96B9753D1929A1C6974F528E4089601EF15B6D52C767AF4B";
        String prepare_str = "GET/v2/futures/order1730319179183";

        System.out.println(generateSignature(secret_key,prepare_str));

    }

    private static String generateSignature(String secretKey, String data) {
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
}

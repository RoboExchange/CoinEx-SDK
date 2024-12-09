package ir.moke.coinex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class CoinexUtils {
    public static byte[] decompressGzip(ByteBuffer data) {
        byte[] buf = new byte[data.remaining()];
        data.get(buf);
        try {
            var stream = new GZIPInputStream(new ByteArrayInputStream(buf));
            return stream.readAllBytes();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public static String generateSignature(String secretKey, String data) {
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

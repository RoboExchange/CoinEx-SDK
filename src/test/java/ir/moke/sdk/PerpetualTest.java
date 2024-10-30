package ir.moke.sdk;

import ir.moke.sdk.coinex.CoinExSDK;
import ir.moke.sdk.coinex.resource.Perpetual;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class PerpetualTest {

    private static Perpetual perpetual ;
    public static void init() {
        Files.readAllLines(Path.of(".test_config"));
        perpetual = new CoinExSDK.Builder()
                .setAccessId(accessId)
                .setSecretKey(secretKey)
                .build(Perpetual.class);
    }

    @Test
    public void market() {

    }
}

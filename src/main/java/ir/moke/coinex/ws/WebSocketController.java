package ir.moke.coinex.ws;

import ir.moke.kafir.utils.JsonUtils;

import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;

public class WebSocketController {
    public static void subscribeStatus(CompletableFuture<WebSocket> webSocketCompletableFuture, String... markets) {
        String str = JsonUtils.toJson(markets);
        String message = "{\"method\": \"state.subscribe\", \"params\": {\"market_list\": %s}, \"id\": 1}".formatted(str);
        webSocketCompletableFuture.thenAccept(webSocket -> webSocket.sendText(message, true));
    }

    public static void unsubscribeStatus(CompletableFuture<WebSocket> webSocketCompletableFuture, String... markets) {
        String str = JsonUtils.toJson(markets);
        String message = "{\"method\": \"state.unsubscribe\", \"params\": {\"market_list\": %s}, \"id\": 1}".formatted(str);
        webSocketCompletableFuture.thenAccept(webSocket -> webSocket.sendText(message, true));
    }
}

package ir.moke.coinex.ws;

import ir.moke.coinex.CoinexUtils;

import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;

public record WebSocketHandler(CoinexListener listener) implements WebSocket.Listener {
    @Override
    public void onOpen(WebSocket webSocket) {
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
        byte[] bytes = CoinexUtils.decompressGzip(data);
        String message = new String(bytes, StandardCharsets.UTF_8);
        listener.onMessage(message);
        return WebSocket.Listener.super.onBinary(webSocket, data, last);
    }

}
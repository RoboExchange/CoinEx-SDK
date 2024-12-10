package ir.moke.coinex.ws;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ir.moke.coinex.CoinexUtils;
import ir.moke.kafir.utils.JsonUtils;

import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        try {
            JsonNode rootNode = JsonUtils.toObject(message, JsonNode.class);
            JsonNode dataNode = rootNode.get("data");
            JsonNode methodNode = rootNode.get("method");

            if (methodNode != null && !methodNode.isNull() && methodNode.textValue().equals("state.update")) {
                ArrayNode stateList = (ArrayNode) dataNode.get("state_list");
                Iterator<JsonNode> iterator = stateList.iterator();
                List<MarketStatusSubscription> list = new ArrayList<>();
                while (iterator.hasNext()) {
                    JsonNode item = iterator.next();
                    if (item != null && !item.isNull()) {
                        MarketStatusSubscription statusSubscription = JsonUtils.toObject(item.toString(), MarketStatusSubscription.class);
                        list.add(statusSubscription);
                    }
                }

                listener.onStatusSubscription(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebSocket.Listener.super.onBinary(webSocket, data, last);
    }

}
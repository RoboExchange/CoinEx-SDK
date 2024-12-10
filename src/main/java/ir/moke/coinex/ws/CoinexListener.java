package ir.moke.coinex.ws;

import java.util.List;

public interface CoinexListener {
    void onStatusSubscription(List<MarketStatusSubscription> subscription);
}

package ir.moke.coinex.model.request;

import java.util.List;

public record BatchStopOrder(List<StopOrder> orders) {
}

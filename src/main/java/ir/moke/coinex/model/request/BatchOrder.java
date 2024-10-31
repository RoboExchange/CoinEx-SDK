package ir.moke.coinex.model.request;

import java.util.List;

public class BatchOrder {
    private List<Order> orders;

    public BatchOrder() {
    }

    public BatchOrder(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

package org.example.repo;

import org.example.model.Order;

public interface OrderRepo {
    Order getOrderById(String order_id);

    void createOrder(String user_id, String product_id);

    void updateOrder(String order_id, String user_id, String product_id);

    void deleteOrder(String order_id);
}

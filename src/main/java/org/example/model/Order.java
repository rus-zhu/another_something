package org.example.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private int id;
    private LocalDateTime created_at;
    private int user_id;
    private int product_id;

    public Order() {
    }

    public Order(int id, LocalDateTime created_at, int user_id, int product_id) {
        this.id = id;
        this.created_at = created_at;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (user_id != order.user_id) return false;
        if (product_id != order.product_id) return false;
        return Objects.equals(created_at, order.created_at);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        result = 31 * result + user_id;
        result = 31 * result + product_id;
        return result;
    }
}

package org.example.repo.impl;

import org.example.model.Order;
import org.example.repo.OrderRepo;
import org.example.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepoImpl implements OrderRepo {
    @Override
    public Order getOrderById(String order_id) {
        try (Connection conn = DatabaseUtil.Connection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM t_order WHERE id = ?")) {
            st.setInt(1, Integer.parseInt(order_id));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int user_id = Integer.parseInt(rs.getString("user_id"));
                int product_id = Integer.parseInt(rs.getString("product_id"));
                return new Order(id, user_id, product_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createOrder(String user_id, String product_id) {
        try (Connection conn = DatabaseUtil.Connection();
             PreparedStatement st = conn.prepareStatement("INSERT INTO t_order (user_id, product_id) VALUES (?, ?)")) {
            st.setInt(1, Integer.parseInt(user_id));
            st.setInt(2, Integer.parseInt(product_id));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(String order_id, String user_id, String product_id) {
        try (Connection conn = DatabaseUtil.Connection();
             PreparedStatement st = conn.prepareStatement(
                     "UPDATE t_order SET user_id = ?, product_id = ? WHERE id = ?) VALUES (?, ?)")) {
            st.setInt(1, Integer.parseInt(user_id));
            st.setInt(2, Integer.parseInt(product_id));
            st.setInt(3, Integer.parseInt(order_id));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(String order_id) {
        try (Connection conn = DatabaseUtil.Connection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM t_order WHERE id = ?")) {
            st.setInt(1, Integer.parseInt(order_id));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

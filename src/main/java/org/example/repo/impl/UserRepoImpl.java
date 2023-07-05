package org.example.repo.impl;

import org.example.model.User;
import org.example.repo.UserRepo;
import org.example.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepoImpl implements UserRepo {
    @Override
    public User getUserById(String user_id) {
        try (Connection conn = DatabaseUtil.Connection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM t_user WHERE id = ?")) {
            st.setInt(1, Integer.parseInt(user_id));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                return new User(id, first_name, last_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createUser(String first_name, String last_name) {
        try (Connection conn = DatabaseUtil.Connection();
             PreparedStatement st = conn.prepareStatement("INSERT INTO t_user (first_name, last_name) VALUES (?, ?)")) {
            st.setString(1, first_name);
            st.setString(2, last_name);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(String user_id, String first_name, String last_name) {
        try (Connection conn = DatabaseUtil.Connection();
             PreparedStatement st = conn.prepareStatement("UPDATE t_user SET first_name = ?, last_name = ? WHERE id = ?) VALUES (?, ?)")) {
            st.setString(1, first_name);
            st.setString(2, last_name);
            st.setInt(3, Integer.parseInt(user_id));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String user_id) {
        try (Connection conn = DatabaseUtil.Connection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM t_user WHERE id = ?")) {
            st.setInt(1, Integer.parseInt(user_id));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package org.example.repo;

import org.example.model.User;

public interface UserRepo {
    User getUserById(String user_id);

    void createUser(String first_name, String last_name);

    void updateUser(String user_id, String first_name, String last_name);

    void deleteUser(String user_id);
}

package org.example.servlets;

import org.example.model.User;
import org.example.repo.UserRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserRepo userRepo;

    public UserServlet(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user_id = req.getParameter("id");
        User user = userRepo.getUserById(user_id);
        PrintWriter writer = resp.getWriter();
        writer.println("User - " + user.getId());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user_id = req.getParameter("id");
        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        PrintWriter writer = resp.getWriter();
        if (user_id != null) {
            userRepo.updateUser(user_id, first_name, last_name);
            writer.println("User id=" + user_id + " was updated");
        } else {
            userRepo.createUser(first_name, last_name);
            writer.println("User was created");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user_id = req.getParameter("id");
        userRepo.deleteUser(user_id);
        PrintWriter writer = resp.getWriter();
        writer.println("User id=" + user_id + " was deleted");
    }
}

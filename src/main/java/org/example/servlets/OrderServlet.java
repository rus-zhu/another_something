package org.example.servlets;

import org.example.model.Order;
import org.example.repo.OrderRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrderRepo orderRepo;

    public OrderServlet(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order_id = req.getParameter("id");
        Order order = orderRepo.getOrderById(order_id);
        PrintWriter writer = resp.getWriter();
        writer.println("Order - " + order.getId());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order_id = req.getParameter("id");
        String user_id = req.getParameter("user_id");
        String product_id = req.getParameter("product_id");
        PrintWriter writer = resp.getWriter();
        if (order_id != null) {
            orderRepo.updateOrder(order_id, user_id, product_id);
            writer.println("Order id=" + order_id + " was updated");
        } else {
            orderRepo.createOrder(user_id, product_id);
            writer.println("Order was created");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order_id = req.getParameter("id");
        orderRepo.deleteOrder(order_id);
        PrintWriter writer = resp.getWriter();
        writer.println("Order id=" + order_id + " was deleted");
    }
}

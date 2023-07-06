package org.example.servlets;

import org.example.model.Order;
import org.example.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private OrderRepo orderRepo;
    @Mock
    private PrintWriter printWriter;

    private OrderServlet orderServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderServlet = new OrderServlet(orderRepo);
    }

    @Test
    public void testDoGet() throws Exception {
        String order_id = "1";
        Order order = new Order(Integer.parseInt(order_id), 1, 1);

        when(request.getParameter("id")).thenReturn(order_id);
        when(orderRepo.getOrderById(order_id)).thenReturn(order);
        when(response.getWriter()).thenReturn(printWriter);

        orderServlet.doGet(request, response);

        verify(orderRepo).getOrderById(order_id);
        verify(response).getWriter();
        verify(printWriter).println("Order - " + order_id);
    }

    @Test
    public void testDoPost_CreateOrder() throws Exception {
        when(request.getParameter("id")).thenReturn(null);
        when(request.getParameter("user_id")).thenReturn("1");
        when(request.getParameter("product_id")).thenReturn("1");
        when(response.getWriter()).thenReturn(printWriter);

        orderServlet.doPost(request, response);

        verify(orderRepo).createOrder("1", "1");
        verify(response).getWriter();
        verify(printWriter).println("Order was created");
    }

    @Test
    public void testDoPost_UpdateOrder() throws Exception {
        String order_id = "1";
        Order old_order = new Order(Integer.parseInt(order_id), 1, 1);
        Order new_order = new Order(Integer.parseInt(order_id), 2, 2);
        when(orderRepo.getOrderById(order_id)).thenReturn(old_order);

        when(request.getParameter("id")).thenReturn(order_id);
        when(request.getParameter("user_id")).thenReturn(String.valueOf(new_order.getUser_id()));
        when(request.getParameter("product_id")).thenReturn(String.valueOf(new_order.getProduct_id()));
        when(response.getWriter()).thenReturn(printWriter);

        orderServlet.doPost(request, response);

        verify(orderRepo).updateOrder(order_id, String.valueOf(new_order.getUser_id()), String.valueOf(new_order.getProduct_id()));
        verify(response).getWriter();
        verify(printWriter).println("Order id=" + order_id + " was updated");
    }

    @Test
    public void testDoDelete() throws Exception {
        String order_id = "1";
        when(request.getParameter("id")).thenReturn(order_id);
        when(response.getWriter()).thenReturn(printWriter);

        orderServlet.doDelete(request, response);

        verify(orderRepo).deleteOrder(order_id);
        verify(response).getWriter();
        verify(printWriter).println("Order id=" + order_id + " was deleted");
    }

}
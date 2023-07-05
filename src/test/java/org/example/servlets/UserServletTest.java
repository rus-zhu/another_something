package org.example.servlets;

import org.example.model.User;
import org.example.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserRepo userRepo;
    @Mock
    private PrintWriter printWriter;

    private UserServlet userServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userServlet = new UserServlet(userRepo);
    }

    @Test
    public void testDoGet() throws Exception {
        String user_id = "1";
        User user = new User(Integer.parseInt(user_id), "John", "Doe");

        when(request.getParameter("id")).thenReturn(user_id);
        when(userRepo.getUserById(user_id)).thenReturn(user);
        when(response.getWriter()).thenReturn(printWriter);

        userServlet.doGet(request, response);

        verify(userRepo).getUserById(user_id);
        verify(response).getWriter();
        verify(printWriter).println("User - " + user_id);
    }

    @Test
    public void testDoPost_CreateUser() throws Exception {
        when(request.getParameter("id")).thenReturn(null);
        when(request.getParameter("first_name")).thenReturn("John");
        when(request.getParameter("last_name")).thenReturn("Doe");
        when(response.getWriter()).thenReturn(printWriter);

        userServlet.doPost(request, response);

        verify(userRepo).createUser("John", "Doe");
        verify(response).getWriter();
        verify(printWriter).println("User was created");
    }

    @Test
    public void testDoPost_UpdateUser() throws Exception {
        String user_id = "1";
        User old_user = new User(Integer.parseInt(user_id), "John", "Doe");
        User new_user = new User(Integer.parseInt(user_id), "Adam", "Niel");
        when(userRepo.getUserById(user_id)).thenReturn(old_user);

        when(request.getParameter("id")).thenReturn(user_id);
        when(request.getParameter("first_name")).thenReturn(new_user.getFirst_name());
        when(request.getParameter("last_name")).thenReturn(new_user.getLast_name());
        when(response.getWriter()).thenReturn(printWriter);

        userServlet.doPost(request, response);

        verify(userRepo).updateUser(user_id, new_user.getFirst_name(), new_user.getLast_name());
        verify(response).getWriter();
        verify(printWriter).println("User id=" + user_id + " was updated");
    }

    @Test
    public void testDoDelete() throws Exception {
        String user_id = "1";
        when(request.getParameter("id")).thenReturn(user_id);
        when(response.getWriter()).thenReturn(printWriter);

        userServlet.doDelete(request, response);

        verify(userRepo).deleteUser(user_id);
        verify(response).getWriter();
        verify(printWriter).println("User id=" + user_id + " was deleted");
    }
}
package servlet;

import accountCreate.SignupHandler;
import authentication.Authenticator;
import beans.Customer;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (request.getRequestURI().toLowerCase().equals("/signin")) {
            signInCustomer(
                    request.getParameter("email"),
                    request.getParameter("password"),
                    out
            );
        } else if(requestIs("/signUp", request)) {
            signUp(
                    request.getParameter("email"),
                    request.getParameter("password"),
                    out
            );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean requestIs(String requestURI, HttpServletRequest request) {
        return request.getRequestURI().equals(requestURI);
    }

    private void signInCustomer(String email, String password, PrintWriter out) {
        if (email != null && password != null){
            out.println(Authenticator.verifyCredentials(email, password).toString());
        } else {
            JSONObject response = new JSONObject()
                    .put("error_message", "SignIn parameters not inserted. (email, password)");
            out.println(response.put("error", true));
        }
    }

    private void signUp(String email, String password, PrintWriter out) {
        if (email != null && password != null){
            out.println(SignupHandler.signUp(new Customer(email, password))); //Considering whether to move all response creation on this page
        } else {
            JSONObject response = new JSONObject()
                    .put("error_message", "SignUp parameters not set. (email, password)");
            out.println(new JSONObject().put("error", true));
        }
    }
}

package com.example.cdione;

import java.io.*;

import jakarta.inject.Inject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import scopes.Account;
import scopes.RequestInfo;
import scopes.SessionInfo;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    @Inject
    private Calculator calculator ;


    @Inject
    private Account accountBean; // ApplicationScoped

    @Inject
    private RequestInfo requestBean;


    @Inject 
    private SessionInfo sessionBean;

    public void init() {
        message = "Hello Ahmad";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        
        PrintWriter out = response.getWriter();
        if(accountBean != null && requestBean != null && sessionBean != null) {
        	 out.println("<html><body>");
             out.println("<h2> Application : " + accountBean.getAppName() + "</h2>");
             out.println("<h2 style='color:red'> Application Bean: " + accountBean + "</h2>");
             out.println("<h2 style='color:blue'> Request Bean: " + requestBean + "</h2>");
             out.println("<h2 style='color:blue'> Request Bean: " + requestBean.getClientInfo() + "</h2>");
             out.println("<h2 style='color:green'> Session Bean: " + sessionBean + "</h2>");
             out.println("<h2 style='color:green'> Session Information: " + sessionBean.getSessionDetails() + "</h2>");
             out.println("<h1> the result is : " + calculator.add(100, 200) + "</h1>");
             out.println("</body></html>");
        } else {
        	out.println("<html><body>");
        	out.println("<h2> java.lang.NullPointerException: Cannot invoke \"packagetwo.RequestInfo.getClientInfo()\" because \"this.requestBean\" is null </h2>");
        	out.println("</body></html>");
        }
       
    }

    public void destroy() {
    }
}
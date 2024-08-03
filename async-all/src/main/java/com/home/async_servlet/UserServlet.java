package com.home.async_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.home.scheduled_task.UserScheduledBean;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.AsyncContext;


/**
 * http://localhost:8080/ahmad_asyncall/userServlet
 */
@WebServlet(name = "userServlet", urlPatterns = {"/userServlet"}, asyncSupported = true)
public class UserServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8833290289542567065L;

	@Inject
    private UserScheduledBean userBean;

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext ctx = req.startAsync();
        ctx.start(() -> {
            try (PrintWriter writer = ctx.getResponse().getWriter()) {
                writer.write(jsonb.toJson(userBean.getUser()));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            ctx.complete();
        });
    }

    @Override
    public void destroy() {
        try {
            jsonb.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}

package com.msdt.carrental.frontcontroller;

import java.io.IOException;



import com.msdt.carrental.frontcontroller.controller.Controller;
import com.msdt.carrental.frontcontroller.controller.ControllerFactory;
import com.msdt.carrental.frontcontroller.viewresolve.ViewResolver;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/car-rental")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTROLLER_NAME = "controller";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void processRequest(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		String controllerName = request.getParameter(CONTROLLER_NAME);

		ControllerFactory factory = new ControllerFactory();
		Controller controller = factory.getController(controllerName);
		ViewResolver resolver = controller.resolve(request, response);
		dispatch(request, response, resolver);
	}

	private void dispatch(final HttpServletRequest request, final HttpServletResponse response,
					final ViewResolver resolver) throws ServletException, IOException {

		String view = resolver.getView();
		switch (resolver.getResolveAction()) {
		case FORWARD:
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
			dispatcher.forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(view);
			break;

		default:
			break;
		}

	}

}

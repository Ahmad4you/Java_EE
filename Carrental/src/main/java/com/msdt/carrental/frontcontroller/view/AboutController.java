package com.msdt.carrental.frontcontroller.view;




import com.msdt.carrental.frontcontroller.controller.Controller;
import com.msdt.carrental.frontcontroller.viewresolve.ViewResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AboutController implements Controller {

	private static final String ABOUT_JSP = "/WEB-INF/pages/main/about.jsp";

	@Override
	public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
		ViewResolver resolver = new ViewResolver();

		resolver.forward(ABOUT_JSP);
		return resolver;
	}

	

	

}

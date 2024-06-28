package com.msdt.carrental.frontcontroller.view;


import com.msdt.carrental.frontcontroller.controller.Controller;
import com.msdt.carrental.frontcontroller.viewresolve.ViewResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NewsController implements Controller {

	private static final String NEWS_JSP = "/WEB-INF/pages/main/news.jsp";

//	@Override
//	public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
//		ViewResolver resolver = new ViewResolver();
//
//		resolver.forward(NEWS_JSP);
//		return resolver;
//	}

	@Override
	public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) {
		ViewResolver resolver = new ViewResolver();

		resolver.forward(NEWS_JSP);
		return resolver;
	}

}

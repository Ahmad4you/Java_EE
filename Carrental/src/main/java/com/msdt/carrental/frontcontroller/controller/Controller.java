package com.msdt.carrental.frontcontroller.controller;




import com.msdt.carrental.frontcontroller.viewresolve.ViewResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public interface Controller {
//	ViewResolver resolve(final HttpServletRequestWrapper request, final HttpServletResponse response);
	ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response);
}


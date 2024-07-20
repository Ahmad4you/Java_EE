/**
 * 
 */
package com.home.push;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.PushBuilder;

/**
 * 
 * @author Ahmad Alrefai
 */
@WebFilter(filterName = "JSFPushFilter", urlPatterns = {"*.xhtml"})
public class JSFPushFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpReq = (HttpServletRequest)request;
        PushBuilder builder =  httpReq.newPushBuilder();
        
        if (builder != null){
            // Push hier JSF-spezifischen Ressourcen
        	System.out.println("JSF PushBuilder is available");
            builder
                .path("jakarta.faces.resource/jsf.js.xhtml?ln=jakarta.faces")
                .path("jakarta.faces.resource/theme.css.xhtml?ln=primefaces-aristo")
                .push();            
            System.out.println("JSF resources pushed");
        }

        chain.doFilter(request, response);
    }
    
    
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
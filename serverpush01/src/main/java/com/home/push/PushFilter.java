/**
 * 
 */
package com.home.push;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.PushBuilder;



/**
 * ushFilter, der auf alle URL-Pfade ("/*") angewendet wird.
 * 
 * verwendet HTTP/2 Server Push, um bestimmte Ressourcen (ein Bild, CSS und JavaScript) proaktiv an den Client zu senden.
 * @author Ahmad Alrefai
 */
@WebFilter(filterName = "PushFilter", urlPatterns = {"/*"})
public class PushFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpReq = (HttpServletRequest)request;
        PushBuilder builder =  httpReq.newPushBuilder();
        
        if (builder != null){
        	System.out.println("PushBuilder is available");
            builder
                .path("resources/javaee-logo.png")
                .path("resources/style.css")
                .path("resources/functions.js")
                .push();            
            System.out.println("Resources pushed");
        } else {
        	
        	    System.out.println("PushBuilder is not available");
        }

        chain.doFilter(request, response);
        
    }

    
}

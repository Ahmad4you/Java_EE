/**
 * 
 */
package com.home.servlets;

import java.util.Arrays;
import java.util.HashSet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.CallerOnlyCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * 
 * @author Ahmad Alrefai
 */
@ApplicationScoped
public class AuthenticationMechanism implements HttpAuthenticationMechanism {
	
	/*
	 * erwartet ein CallerOnlyCredential Objekt.
	 * Wenn der Benutzer "user" ist, wird die Authentifizierung erfolgreich durchgeführt und dem Benutzer werden die Rollen "role1" und "role2" zugewiesen.
	 * 
	 */

	@Override
	public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
	    System.out.println("Validating request...");
	    if (httpMessageContext.isAuthenticationRequest()) {
	        System.out.println("Authentication request detected");
	        Credential credential = httpMessageContext.getAuthParameters().getCredential();
	        System.out.println("Credential: " + credential);
	        if (!(credential instanceof CallerOnlyCredential)) {
	            System.out.println("Invalid credential type");
	            throw new IllegalStateException("Invalid mechanism");
	        }

	        CallerOnlyCredential callerOnlyCredential = (CallerOnlyCredential) credential;
	        System.out.println("Caller: " + callerOnlyCredential.getCaller());

	        if ("user".equals(callerOnlyCredential.getCaller())) {
	            System.out.println("Valid user, notifying container");
//	            return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(Arrays.asList("role1","role2")));
	            return httpMessageContext.notifyContainerAboutLogin("user", new HashSet<>(Arrays.asList("role1", "role2")));
	        } else {
	            System.out.println("Invalid user");
	            throw new AuthenticationException();
	        }
	    }

	    System.out.println("Not an authentication request");
	    return httpMessageContext.doNothing();
	}
}

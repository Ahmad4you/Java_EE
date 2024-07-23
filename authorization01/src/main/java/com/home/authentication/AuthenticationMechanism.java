/**
 * 
 */
package com.home.authentication;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {

        if (httpMessageContext.isAuthenticationRequest()) {

            Credential credential = httpMessageContext.getAuthParameters().getCredential();
            if (!(credential instanceof CallerOnlyCredential)) {
                throw new IllegalStateException("Invalid mechanism");
            }

            CallerOnlyCredential callerOnlyCredential = (CallerOnlyCredential) credential;
            System.out.println("Authenticating user: " + callerOnlyCredential.getCaller());

            if (null == callerOnlyCredential.getCaller()) {
                throw new AuthenticationException();
            } else switch (callerOnlyCredential.getCaller()) {
                case "user1":
                	Set<String> roles = new HashSet<>(Arrays.asList(Roles.ROLE1));
                    System.out.println("Assigning roles to user1: " + roles);
                    
                    AuthenticationStatus status = null;
                    try {
                        status = httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), roles);
                    } catch (Exception e) {
                        System.err.println("Exception in notifyContainerAboutLogin: " + e.getMessage());
                        e.printStackTrace();
                    }
                    if (status == null) {
                        System.out.println("notifyContainerAboutLogin returned null");
                       
                        return AuthenticationStatus.SEND_FAILURE;
                    }
                    return status;
                case "user2":
                    return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(asList(Roles.ROLE2)));
                case "user3":
                    return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(asList(Roles.ROLE3)));
                default:
                    throw new AuthenticationException();
            }

        }

        return httpMessageContext.doNothing();
    }

}

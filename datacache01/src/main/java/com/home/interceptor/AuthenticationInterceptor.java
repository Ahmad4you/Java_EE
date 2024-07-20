/**
 * 
 */
package com.home.interceptor;

import datacache.UserCacheBean;
import jakarta.ejb.EJB;
import jakarta.interceptor.Interceptor;

/**
 * 
 * @author Ahmad Alrefai
 */
@Interceptor
//@Authenticated
public class AuthenticationInterceptor {

    @EJB
    private UserCacheBean userCache;

//    @AroundInvoke
//    public Object authenticate(InvocationContext context) throws Exception {
//        HttpServletRequest request = (HttpServletRequest) context.getParameters()[0];
//        String username = request.getHeader("X-Username");
//
//        boolean isAuthenticated = userCache.get().stream()
//                .anyMatch(user -> user.getUsername().equals(username) && user.isActive());
//
//        if (isAuthenticated) {
//            return context.proceed();
//        } else {
//            throw new SecurityException("User not authenticated");
//        }
//    }
}
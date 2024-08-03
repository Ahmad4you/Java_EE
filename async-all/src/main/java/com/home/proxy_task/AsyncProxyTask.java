/**
 * 
 */
package com.home.proxy_task;

import java.util.concurrent.Callable;

import com.home.model.User;

import jakarta.ejb.Stateless;



/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class AsyncProxyTask implements Callable<User>{

    @Override
    public User call() throws Exception {
        return new UserProxytaskBean().getUser();
    }

}
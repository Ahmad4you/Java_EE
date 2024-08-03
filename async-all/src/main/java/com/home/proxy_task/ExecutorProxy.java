/**
 * 
 */
package com.home.proxy_task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.home.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.enterprise.concurrent.ContextService;
import jakarta.enterprise.concurrent.ManagedThreadFactory;


/**
 * 
 * @author Ahmad Alrefai
 */
@Singleton
public class ExecutorProxy {

    @Resource(name = "LocalManagedThreadFactory")
    private ManagedThreadFactory factory;

    @Resource(name = "LocalContextService")
    private ContextService context;

    private ExecutorService executor;

    @PostConstruct
    public void init(){
        executor = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), factory);
    }
    
    @SuppressWarnings("unchecked")
	public Future<User> submit(Callable<User> task){
        Callable<User> ctxProxy = context.createContextualProxy(task, Callable.class);
        return executor.submit(ctxProxy);
    }
}

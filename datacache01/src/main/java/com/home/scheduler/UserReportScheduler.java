/**
 * 
 */
package com.home.scheduler;

import java.util.List;

import com.home.model.User;

import datacache.UserCacheBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;

/**
 * 
 * @author Ahmad Alrefai
 */
@Singleton
public class UserReportScheduler {

    @EJB
    private UserCacheBean userCache;

    @Schedule(hour = "1", minute = "0", second = "0", persistent = false)
    public void generateDailyReport() {
        List<User> users = userCache.get();
        int totalUsers = users.size();
//        int activeUsers = (int) users.stream().filter(User::isActive).count();

        // einen Report generieren und speichern oder versenden
        System.out.println("Daily User Report:");
        System.out.println("Total Users: " + totalUsers);
//        System.out.println("Active Users: " + activeUsers);
    }
}
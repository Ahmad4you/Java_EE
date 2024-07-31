/**
 * 
 */
package com.home.microprofile;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/**
 * 
 * @author Ahmad Alrefai
 */
@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {
	
	/*
	 * Diese Methode gibt eine HealthCheckResponse zurück, die angibt, ob die Anwendung betriebsbereit ist. 
	 * Die Betriebsbereitschaft wird zufällig bestimmt (isAcessible()).
	 * 
	 */
    
    @Override
    public HealthCheckResponse call() {
        if (isAcessible()){
            return HealthCheckResponse.up("I'm up and ready!");
        } else{
            return HealthCheckResponse.down("I'm up, but not ready...");
        }
    }
    
    private boolean isAcessible(){
        return new Random().nextBoolean();
    }
    
}

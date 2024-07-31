/**
 * 
 */
package com.home.microprofile;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/**
 * 
 * @author Ahmad Alrefai
 */
@Liveness
@ApplicationScoped
public class LivenessHealthCheck implements HealthCheck{
	
	/*
	 * Diese Methode gibt eine HealthCheckResponse zurück, die angibt, dass die Anwendung läuft ("I'm up!").
	 */

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("I'm up!");
    }
    
}

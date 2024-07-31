/**
 * 
 */
package com.home.health;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Beispiel, wie einen eigenen Readiness-Health-Check für eine externe API in einem MicroProfile-Projekt implementieren kann.
 * 
 * @author Ahmad Alrefai
 */

@Readiness
@ApplicationScoped
public class ExternalApiReadinessCheck implements HealthCheck {

//    private static final String API_URL = "https://api.example.com/health";
    private static final String API_URL = "http://localhost:8080/ahmad_address/api/userAddressService/get";

    @Override
    public HealthCheckResponse call() {
        return checkSomeExternalService() ?
            HealthCheckResponse.up("External API is ready") :
            HealthCheckResponse.down("External API is not ready");
    }

    private boolean checkSomeExternalService() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Timeout für die Verbindung
            connection.setReadTimeout(5000); // Timeout für das Lesen der Antwort

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) { // Überprüfen, ob der Statuscode 200 OK ist
                System.out.println("Externe API ist bereit.");
                return true;
            } else {
                System.out.println("Externe API ist nicht bereit. Antwortcode: " + responseCode);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
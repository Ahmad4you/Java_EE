/**
 * 
 */
package com.home.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import io.helidon.security.Security;
import io.helidon.security.SecurityContext;

/**
 * 
 * @author Ahmad Alrefai
 */

@ApplicationScoped
public class SecurityProducer {

	@Produces
    @ApplicationScoped
    public Security produceSecurity() {
        // Konfigurieren Sie hier Ihre Security-Instanz
        return Security.builder()
                .build();
    }

    @Produces
    @ApplicationScoped
    public SecurityContext produceSecurityContext(Security security) {
        return security.createContext("default-context");
    }
    
}
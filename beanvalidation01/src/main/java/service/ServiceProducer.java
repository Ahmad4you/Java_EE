/**
 * 
 */
package service;

import bcrypt.PasswordHasher;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

/**
 * 
 * @author Ahmad Alrefai
 */
@ApplicationScoped
public class ServiceProducer {
    @Produces
    public BenutzerService produceBenutzerService() {
        return new BenutzerService();
    }

    @Produces
    public PasswordHasher producePasswordHasher() {
        return new PasswordHasher();
    }
}
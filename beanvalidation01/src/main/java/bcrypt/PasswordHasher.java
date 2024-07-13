/**
 * 
 */
package bcrypt;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.ejb.Stateless;

/**
 * 
 * @author Ahmad Alrefai
 */

@Stateless
public class PasswordHasher {

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verify(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
}
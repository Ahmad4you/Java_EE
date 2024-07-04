/**
 * 
 */
package cdi.profile;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;

/**
 * 
 * @author Ahmad Alrefai
 */

@ApplicationScoped
@Profile(ProfileType.DATENSCHUTZ)
@Default
public class ImolDatenschutz implements UserProfile{

    @Override
    public ProfileType type() {
        System.out.println("User is datenschutz");
        return ProfileType.DATENSCHUTZ;
    }
    
}
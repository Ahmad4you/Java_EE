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
@Profile(ProfileType.DEFAULT)
@Default
public class ImplDefault implements UserProfile{

    @Override
    public ProfileType type() {
        System.out.println("User is default");
        return ProfileType.DEFAULT;
    }
    
}

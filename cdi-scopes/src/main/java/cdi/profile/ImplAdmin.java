package cdi.profile;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Ahmad Alrefai
 */

@ApplicationScoped
@Profile(ProfileType.ADMIN)
public class ImplAdmin implements UserProfile{

    @Override
    public ProfileType type() {
        System.out.println("User is admin");
        return ProfileType.ADMIN;
    }
    
}

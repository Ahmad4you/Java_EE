package cdi.profile;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;

/**
 *
 * @author Ahmad Alrefai
 */

@ApplicationScoped
@Profile(ProfileType.OPERATOR)
@Default
public class ImplOperator implements UserProfile{

    @Override
    public ProfileType type() {
        System.out.println("User is operator");
        return ProfileType.OPERATOR;
    }
    
}

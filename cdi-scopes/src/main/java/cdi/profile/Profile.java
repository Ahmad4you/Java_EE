package cdi.profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.inject.Qualifier;

/**
 * Es handelt sich um eine benutzerdefinierte Qualifier-Annotation! um verschiedene Profiltypen zu unterscheiden.
 * @Qualifier: wird verwendet, um Mehrdeutigkeiten bei der Dependency Injection aufzulösen, wenn mehrere Beans des gleichen Typs vorhanden sind.
 * @Target wo die @Profile Annotation verwendet werden kann. In diesem Fall kann sie auf Klassen (TYPE), Felder (FIELD), Methoden (METHOD) 
 * und Methodenparameter (PARAMETER) angewendet werden.
 * @interface Profile: Dies definiert eine neue Annotation namens Profile.
 * 
 * @author Ahmad Alrefai
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface Profile {
    ProfileType value();
}

//@Profile(ProfileType.ADMIN)
//public class AdminService {
//    // ...
//}


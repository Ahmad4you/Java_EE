/**
 * 
 */
package converter;

import java.util.logging.Logger;

import entity.User;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * 
 * Dieser Konverter wird in JSF verwendet, um User-Objekte in Strings umzuwandeln und umgekehrt. 
 * Dies ist nützlich, wenn man User-Objekte in JSF-Komponenten wie Dropdown-Listen 
 * oder Eingabefeldern verwenden möchten.
 * 
 * @author Ahmad Alrefai
 */

@FacesConverter(forClass = User.class)
public class UserConverter implements Converter<User> {
	private static final Logger logger = Logger.getLogger(UserConverter.class.getName());
	
	@Override
    public String getAsString(FacesContext context, UIComponent component, User user) {
        if (user == null) {
            return "";
        }
        
        return user.getName() + "|" + user.getEmail();
    }

    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        
        String[] parts = value.split("\\|");
        if (parts.length != 2) {
            return null;
        }
        
        User user = new User();
        user.setName(parts[0]);
        user.setEmail(parts[1]);
        logger.info("Converting string to object: " + value);
        return user;
    }
}

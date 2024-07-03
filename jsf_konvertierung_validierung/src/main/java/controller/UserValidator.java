/**
 * 
 */
package controller;

import java.util.regex.Pattern;

import entity.User;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 * 
 * @author Ahmad Alrefai
 */
@FacesValidator("userValidator")
public class UserValidator implements Validator<User> {

	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	@Override
	public void validate(FacesContext context, UIComponent component, User user) throws ValidatorException {
		if (user == null) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validierungsfehler",
					"Benutzer darf nicht null sein."));
		}

		// E-Mail-Validierung
		if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültiges E-Mail-Format",
					"Bitte geben Sie eine gültige E-Mail-Adresse ein."));
		}

		// Benutzername-Validierung
		if (user.getName() == null || user.getName().length() < 3) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültiger Benutzername",
					"Der Benutzername muss mindestens 3 Zeichen lang sein."));
		}

		// Passwort-Validierung
//        if (user.getPassword() == null || user.getPassword().length() < 8) {
//            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
//                "Unsicheres Passwort", "Das Passwort muss mindestens 8 Zeichen lang sein."));
//        }

        // Weitere Validierungen hier...
	}

//	 @Override
//	    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//	        // Validierungslogik hier
//	        if (/* Validierungsbedingung */) {
//	            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validierungsfehler", "Detaillierte Fehlermeldung"));
//	        }
//	    }
//	 

}

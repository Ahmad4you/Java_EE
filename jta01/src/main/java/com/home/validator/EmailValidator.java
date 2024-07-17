/**
 * 
 */
package com.home.validator;

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
@FacesValidator("emailValidator")
public class EmailValidator implements Validator<String> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
    	
        
        if (value == null || value.trim().isEmpty()) {
            return; // Lassen die Überprüfung auf Pflichtfeld dem required-Attribut
        }

        if (!value.matches(EMAIL_REGEX)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Ungültige E-Mail-Adresse", "Bitte geben Sie eine gültige E-Mail-Adresse ein."));
        }
    }
    
    
}

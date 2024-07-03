/**
 * 
 */
package converter;

import java.text.NumberFormat;
import java.text.ParseException;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

/**
 * 
 * @author Ahmad Alrefai
 */

@FacesConverter("numberConverter")
public class NumberConverter implements Converter {

	/*
	 * <h:inputText value="#{bean.amount}" converter="numberConverter" />
	 */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return NumberFormat.getInstance().parse(value);
        } catch (ParseException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Konvertierungsfehler", "Ungültiges Zahlenformat"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return NumberFormat.getInstance().format(value);
    }
}
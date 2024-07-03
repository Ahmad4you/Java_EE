/**
 * 
 */
package converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * 
 * @author Ahmad Alrefai
 */
@FacesConverter("booleanConverter")
public class BooleanConverter implements Converter {

	/*
	 * <h:selectBooleanCheckbox value="#{bean.active}" converter="booleanConverter" />
	 */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Boolean.valueOf(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
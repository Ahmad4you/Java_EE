/**
 * 
 */
package converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@FacesConverter("dateConverter")
public class DateConverter implements Converter {

    private static final String PATTERN = "dd.MM.yyyy";
    
    /**
     * <h:selectOneMenu value="#{bean.status}" converter="enumConverter">
     *  <f:selectItems value="#{bean.statusOptions}" />
     *  </h:selectOneMenu>
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Konvertierungsfehler", "Ungültiges Datumsformat"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
            return sdf.format((Date) value);
        }
        return value.toString();
    }
}

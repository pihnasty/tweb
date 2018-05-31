package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;

import static common.Constant.UiConstant.EMAIL;
import static common.ExpressionValidator.checkByRegularExpression;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
//            org.apache.commons.validator.routines.EmailValidator emailValidator = org.apache.commons.validator.routines.EmailValidator.getInstance(true);
//            if ( emailValidator.isValid((String) value))
//            {
//                System.out.println();
//            }
            int count = checkByRegularExpression("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", (String) value).size();
            if (count != 1) {
                ResourceBundle bundle = ResourceBundle.getBundle("nls.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
                throw new IllegalArgumentException(bundle.getString("invalid.email"));
            }
        } catch (IllegalArgumentException e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        context.getAttributes().put(EMAIL, value);
    }
}
//  1.context.getExternalContext().getRequestParameterMap().values().iterator().next()
//  2.https://www.programcreek.com/java-api-examples/?api=org.apache.commons.validator.routines.EmailValidator
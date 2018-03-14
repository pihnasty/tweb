package validators;

import db.Database;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.sql.*;
import java.util.ResourceBundle;

import static common.Constant.UiConstant.EMAIL;

@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        ResourceBundle bundle = ResourceBundle.getBundle("nls.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        String email = "";
        try {
            if (context.getAttributes().get(EMAIL) != null) {
                email = (String) context.getAttributes().get(EMAIL);
                try {
                    if (!isPasswordFromDataBase(email, (String) value)) {
                        throw new IllegalArgumentException(bundle.getString("invalid.password"));
                    }
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public boolean isPasswordFromDataBase(String email, String password) throws SQLException {
        Connection connection = Database.getConnection();  //getConnectionForTest()
        PreparedStatement preparedStatement =  connection.prepareStatement("select email, password from flow_production.login where email= ?");
        preparedStatement.setString(1,email);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        if (password.equals(resultSet.getString("password"))) {
            return true;
        }
        return false;
    }
}
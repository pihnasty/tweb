package validators;


import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by pom on 12.03.2018.
 */
public class PasswordValidatorTest extends Assert {
    @Test
    public void isPasswordFromDataBaseTest() throws SQLException {
        PasswordValidator passwordValidator = new PasswordValidator();
        boolean isPassword = passwordValidator.isPasswordFromDataBase("pom7@bk.ru", "69");
        isPassword = passwordValidator.isPasswordFromDataBase("pom7@bk.ru", "69");
        assertEquals(true, isPassword);
    }
}

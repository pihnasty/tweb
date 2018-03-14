package common;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static common.ExpressionValidator.checkByRegularExpression;

public class ExpressionValidatorTest extends Assert {
    @Test
    public void checkByRegularExpressionTest() {
        List<String> emailList = checkByRegularExpression("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", "pom7@bk.ru");
        emailList.stream().forEach(e -> System.out.println(e));
        assertEquals(1, emailList.size());
    }
}

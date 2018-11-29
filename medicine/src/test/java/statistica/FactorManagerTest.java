package statistica;

import org.junit.Test;

import java.util.Locale;

public class FactorManagerTest {

    private FactorManager factorManager = new FactorManager();

    @Test
    public void createTableTest(){
        Locale.setDefault(new Locale("en", "US"));
        factorManager.createTable();
        Locale.setDefault(new Locale("ru", "RY"));
    }
}

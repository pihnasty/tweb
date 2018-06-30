package integration;

import function.F;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    @Test
    public void integralTest() {

        double integral = I.integral(10.0,tau->(double)tau,10.0/10000);

        assertEquals("Unexpected double value", 50.0, integral, 0.001);

    }


}

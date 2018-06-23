package function;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Ftest {
    @Test
    public void Htest() {
        assertEquals("Unexpected double value", (Double) 1.0, F.H(1.0));
        assertEquals("Unexpected double value", (Double) 1.0, F.H(0.0));
        assertEquals("Unexpected double value", (Double) 0.0, F.H(-1.0));
    }
}

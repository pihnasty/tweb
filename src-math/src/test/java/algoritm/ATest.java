package algoritm;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class ATest {
    @Test
    public void halfDivisionMethodTest() {
        List<Pair<Double,Double>> list = new ArrayList<>();
        double dTau = 0.0001;
        for (double tau=0; tau<10.0; tau+=dTau) {
            list.add(new Pair<>(tau,tau*tau));
        }
       double resultTau = A.halfDivisionMethodForValue( list, 6.25);
       assertEquals("Unexpected double value", 2.25, resultTau, 0.001);
    }

    @Test
    public void halfDivisionMethodTestRevers() {
        List<Pair<Double,Double>> list = new ArrayList<>();
        double dTau = 0.0001;
        for (double tau=10; tau>0.0; tau-=dTau) {
            list.add(new Pair<>(tau,tau*tau));
            System.out.println(tau +  "  " + tau*tau);
        }
        double resultTau = A.halfDivisionMethodForValue( list, 6.25);
        assertEquals("Unexpected double value", 2.5, resultTau, 0.0001);
    }
}

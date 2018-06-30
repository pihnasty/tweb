package function;

import cash.CashList;
import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

import static org.junit.Assert.assertEquals;

public class Ftest {
    @Test
    public void Htest() {
        assertEquals("Unexpected double value",  1.0, F.H(1.0),0.001);
        assertEquals("Unexpected double value",  1.0, F.H(0.0),0.001);
        assertEquals("Unexpected double value", 0.0, F.H(-1.0),0.001);
    }

   @Test
    public void GminusTest() {
      double result = F.Gminus(6.25,tau -> tau*tau,  new CashList());
      assertEquals("Unexpected double value", 2.5, result,0.0001);
   }

   @Test
    public void GTest () {
       CashList cash = new CashList(0.0001);
       assertEquals("Unexpected double value", 12.5,
               F.G(5.0,  tau -> tau, cash ),0.001);


//       double result = F.Gminus(25.0,  tau -> tau*tau,  cash);
//       assertEquals("Unexpected double value", 5.0, result,0.0001);




   }









}

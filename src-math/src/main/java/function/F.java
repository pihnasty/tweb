package function;

import algoritm.A;
import cash.CashList;
import common.DefaultConstants;
import integration.I;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;

import static java.util.OptionalDouble.of;

public class F {
    /**
     * Heaviside step function  https://en.wikipedia.org/wiki/Heaviside_step_function
     * @param argument
     * @return 1.0 or 0.0
     */
    public static double H(double argument) {
        return argument >= 0 ? 1.0 : 0.0;
    }

    public static double rG(double xi, double tau, double tauStart, ToDoubleFunction<Double> g) {
        return xi -  I.integral(tauStart,tau,g,DefaultConstants.PRECISION);
    }


    public static double rG(double xi, double tau, double tauStart, CashList cash) {
        return xi -  G(tau,cash);
    }

    public static double Gminus(double G, CashList  cash) {
        cash = Optional.of(cash).orElse(new CashList());
        cash.sort(Comparator.comparingDouble(Pair::getValue));     //         cash.sort((d1,d2)->{ return (int) (d1.getValue() -  d2.getValue());  });
        return A.halfDivisionMethodForValue(cash, G);
    }

    public static double G(double tau, CashList cash) {
        cash =  Optional.ofNullable(cash).orElse(new CashList());
        cash.sort(Comparator.comparingDouble(Pair::getValue));
        return A.halfDivisionMethodForKey(cash, tau);
    }



}

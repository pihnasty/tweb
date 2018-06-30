package integration;

import java.util.function.ToDoubleFunction;

public class I {

    /**
     * This method calculates the integral of the function {@code function} from the value {@code tauStart}
     * to the value {@code tau} with the step of integration
     * @param tau   finish value
     * @param tauStart    initial value
     * @param function integrand function
     * @param dTau  step of integration
     * @return  result of the integration
     */
    public static  Double  integral( double tauStart,double tau, ToDoubleFunction function, double dTau) {
        double sum = 0.0;
        for (double tauTemp = tauStart; tauTemp<=tau; tauTemp+=dTau ) {
            sum += dTau*  function.applyAsDouble(tauTemp);
        }
        return sum;
    }

    /**
     * This method calculates the integral of the function {@code function} from the value {@code 0}
     * to the value {@code tau} with the step of integration
     * @param tau   finish value
     * @param function integrand function
     * @param dTau  step of integration
     * @return  result of the integration
     */
    public static  Double  integral( double tau, ToDoubleFunction function, double dTau) {
        return  integral( 0, tau, function, dTau);
    }

}

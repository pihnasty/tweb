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


    public static double rG(double xi, double tau, double tauStart, ToDoubleFunction<Double> g, CashList cash) {
        return xi -  G(tau,  g, cash)+G( tauStart,  g, cash);
    }

    public static double Gminus(double G, ToDoubleFunction<Double> function, CashList  cash) {
        cash = Optional.of(cash).orElse(new CashList());
        double dTau = cash.getPrecision();
        double tauStart = cash.getTauStart();
        double tauFinish = cash.getTauFinish();
        if (cash.isEmpty()) {
            for (double tauTemn = tauStart; tauTemn < tauFinish; tauTemn += dTau) {
                cash.add(new Pair<>(tauTemn, function.applyAsDouble(tauTemn)*dTau));
            }
        }
        cash.sort(Comparator.comparingDouble(Pair::getValue));     //         cash.sort((d1,d2)->{ return (int) (d1.getValue() -  d2.getValue());  });

        if (G > cash.get(cash.size() - 1).getValue()) {
            double tauTemn = cash.get(cash.size() - 1).getKey();
            double GTerm = cash.get(cash.size() - 1).getValue();

            while(GTerm<G*1.1) {
                tauTemn += dTau;
                GTerm =  function.applyAsDouble(tauTemn);
                cash.add(new Pair<>(tauTemn, GTerm ));
            }
        }

        if (G < cash.get(0).getValue()) {
            double tauTemn = cash.get(0).getKey();
            double GTerm = cash.get(0).getValue();
            while(GTerm>G*0.9) {
                tauTemn -= dTau;
                GTerm =  function.applyAsDouble(tauTemn);
                cash.add(new Pair<>(tauTemn, GTerm ));
            }
        }
        cash.sort(Comparator.comparingDouble(Pair::getValue));
        return A.halfDivisionMethodForValue(cash, G);
    }

    public static double G(double tau, ToDoubleFunction<Double> function, CashList cash) {
        cash =  Optional.ofNullable(cash).orElse(new CashList());
        double dTau = cash.getPrecision();

        double result;
        if (cash.isEmpty()) {
            result=0.0;
            for (double tauTemp = cash.getTauStart(); tauTemp < tau+100.0*dTau; tauTemp += dTau) {
                result +=function.applyAsDouble(tauTemp) *dTau;
                cash.add(new Pair<>(tauTemp, result ));
            }
        }

        cash.sort(Comparator.comparingDouble(Pair::getValue));

        if (tau > cash.get(cash.size() - 1).getKey()) {
            double tauTemp = cash.get(cash.size() - 1).getKey();
            result = cash.get(cash.size() - 1).getValue();
            while(tauTemp <tau*1.1) {
                tauTemp += dTau;
                result +=function.applyAsDouble(tauTemp) *dTau;
                cash.add(new Pair<>(tauTemp, result));
            }
        }

        if (tau< cash.get(0).getKey()) {
            double tauTemp = cash.get(0).getKey();
            result = cash.get(cash.size() - 1).getValue();
            while(tauTemp>tau*0.9) {
                tauTemp -= dTau;
                cash.add(new Pair<>(tauTemp, result));
            }
        }

        cash.sort(Comparator.comparingDouble(Pair::getValue));
        return A.halfDivisionMethodForKey(cash, tau);
    }



}

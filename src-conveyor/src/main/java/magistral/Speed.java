package magistral;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class Speed {

    private Map<Double, ToDoubleFunction> speeds;

    public synchronized void setSpeeds(Map<Double, ToDoubleFunction> speeds) {
        this.speeds = speeds;
    }

    public List<Double> getKeysByDiscrease() {
        List<Double> keys = speeds.keySet().stream().sorted(Comparator.comparingDouble(key -> - key )).collect(Collectors.toList());
        return keys;
    }

    public Double getSpeed(Double tau, Double ksi) {
        if (ksi < 0 ) {
            throw  new InvalidParameterException("You have ksi<0 "+this.getClass());
        }
        Double key = getKeysByDiscrease().stream().filter(k -> ksi >= k).findFirst().get();
        return speeds.get(key).applyAsDouble(tau);
    }

    public Map<Double, ToDoubleFunction> getSpeedsDefault() {
        speeds = new HashMap<Double, ToDoubleFunction>() {
            {
                put(0.0, tau -> 1.75);
                put(0.35,tau -> 0.25);
                put(0.4, tau -> 1.50);
                put(0.7, tau -> 0.33);
                put(0.8, tau -> 2.00);
            }
        };
        return speeds;
    }

    public Map<Double, ToDoubleFunction> getSpeedsDefaultLinearTau() {
        speeds = new HashMap<Double, ToDoubleFunction>() {
            {
                put(0.0, tau -> 1.75 + (Double) tau);
                put(0.35,tau -> 0.25 + (Double) tau);
                put(0.4, tau -> 1.50 + (Double) tau);
                put(0.7, tau -> 0.33 + (Double) tau);
                put(0.8, tau -> 2.00 + (Double) tau);
            }
        };
        return speeds;
    }

}


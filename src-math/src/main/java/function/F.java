package function;

public class F {
    /**
     * Heaviside step function  https://en.wikipedia.org/wiki/Heaviside_step_function
     * @param argument
     * @return 1.0 or 0.0
     */
    public static Double H(Double argument) {
        return argument >= 0 ? 1.0 : 0.0;
    }
}

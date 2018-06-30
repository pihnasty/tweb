package comparator;

import javafx.util.Pair;

import java.util.Comparator;

public class PairDoubleDoubleComparator implements Comparator<Pair<Double,Double>> {

    @Override
    public int compare(Pair<Double, Double> pair1, Pair<Double, Double> pair2) {
        double delta = pair1.getValue() - pair2.getValue();
        if (delta == 0) return 0;
        return (delta > 0) ? 1 : -1;
    }
}

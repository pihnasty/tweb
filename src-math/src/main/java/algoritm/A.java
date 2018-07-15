package algoritm;

import comparator.PairDoubleDoubleComparator;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class A {
    public static double halfDivisionMethodForValue(List<Pair<Double, Double>> list, double pairValue) {
        Optional.ofNullable(list).orElseThrow(IllegalArgumentException::new);
        list.sort(new PairDoubleDoubleComparator());

        int indexMax = list.size();
        int indexMin = 0;
        int indexTemp = (indexMax + indexMin) / 2;

        while (Math.abs(indexMax - indexMin) > 1) {

            if (pairValue > list.get(indexTemp).getValue()) {
                indexMin = indexTemp;
            } else {
                indexMax = indexTemp;
            }
            indexTemp = (indexMax + indexMin) / 2;
        }
//        if (indexTemp==0 || indexTemp==list.size()) throw new IllegalArgumentException("indexTemp="+indexTemp+" key="+list.get(indexTemp).getKey());
        return list.get(indexTemp).getKey();
    }

    public static double halfDivisionMethodForKey(List<Pair<Double, Double>> list, double pairKey) {
        Optional.ofNullable(list).orElseThrow(IllegalArgumentException::new);
        list.sort(new PairDoubleDoubleComparator());

        int indexMax = list.size();
        int indexMin = 0;
        int indexTemp = (indexMax + indexMin) / 2;

        while (Math.abs(indexMax - indexMin) > 1) {

            if (pairKey > list.get(indexTemp).getKey()) {
                indexMin = indexTemp;
            } else {
                indexMax = indexTemp;
            }
            indexTemp = (indexMax + indexMin) / 2;
        }
        return list.get(indexTemp).getValue();
    }
}

package cash;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CashList extends ArrayList<Pair<Double, Double>> {
    double precision = 0.001;
    double tauStart = 0.0;
    double tauFinish = 0.0;


    public CashList(){};
    public CashList(double precision){
        this.precision = precision;
    };

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getTauStart() {
        return  isEmpty() ? tauStart : get(0).getKey();
    }

    public double getTauFinish() {
        return  isEmpty() ? tauStart : get(size()-1).getKey();
    }



}

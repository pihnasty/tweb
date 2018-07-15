package magistral;

import cash.CashList;
import comparator.SectionComparator;
import function.F;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.ToDoubleFunction;

import static function.F.G;
import static function.F.H;

public class Conveyor {

    private List<Section> sections = new ArrayList<>();
    private List<CashList> cashs = getCashForG(0.00001);


    public Conveyor addSection (Section section) {
        sections.add(section);
       return this;
    }

    public Conveyor setBoundaryСonditions (List<ToDoubleFunction> boundaryСonditions) {
        if (sections.size()==boundaryСonditions.size()) {
            for (int i = 0; i < sections.size(); i++) {
                sections.get(i).setBoundaryСonditions(boundaryСonditions.get(i));
            }
        } else throw new IllegalArgumentException(ResourceBundle.getBundle("src-conveyor").getString("boundary.conditions"));
        return this;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public static Conveyor getDefaultConveyor() {
        Conveyor conveyor = new Conveyor();
        conveyor.addSection(new Section("0", 0.00, tau -> 0.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.0, true)))
                .addSection(new Section("1", 0.20, tau -> 1.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.4, true)))
                .addSection(new Section("2", 0.50, tau -> 0.50, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.2, true)))
                .addSection(new Section("3", 0.70, tau -> 1.50, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.1, true)))
                .addSection(new Section("4", 0.80, tau -> 0.50, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.5, true)))
                .addSection(new Section("5", 1.00, tau -> 2.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.5, true)))
                .addSection(new Section("After Section", 10.00,
                        tau -> 0.00, ksi -> 0.0, new Bunker(tau -> 1.5, true)));

//        conveyor.addSection(new Section("0", 0.00, tau -> 0.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.0, true)))
//                .addSection(new Section("1", 0.35, tau -> 1.75, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.4, true)))
//                .addSection(new Section("2", 0.40, tau -> 0.25, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.2, true)))
//                .addSection(new Section("3", 0.70, tau -> 1.50, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.3, true)))
//                .addSection(new Section("4", 0.80, tau -> 0.30, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.4, true)))
//                .addSection(new Section("5", 1.00, tau -> 2.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.5, true)))
//                .addSection(new Section("After Section", 10.00,
//                        tau -> 0.00, ksi -> 0.0, new Bunker(tau -> 1.5, true)));


        for (int i = 1; i < conveyor.sections.size(); i++) {
            conveyor.sections
                    .get(i)
                    .getParentNodes()
                    .add(conveyor.sections.get(i - 1));
        }

        for (int i = conveyor.sections.size() - 2; i >= 0; i--) {
            conveyor.sections.get(i).getChildNodes().add(conveyor.sections.get(i + 1));
        }
        conveyor.cashs = conveyor.getCashForG(0.001);
        return conveyor;
    }

    public Double getSpeed(Double tau, Double ksi) {
        return speedLaw (ksi).applyAsDouble(tau);
    }

    public Double getBoundaryСonditions(Double ksi) {
        Double result = 0.0;
        for (int m = 1; m < sections.size();m++)
            result +=
                    (H(ksi - sections.get(m - 1).getPosition())
                            - H(ksi - sections.get(m).getPosition()))
                            * sections.get(m).getBoundaryСonditions().applyAsDouble(ksi);
        return result;
    }

    public Double getTeta(Double tau, Double ksi) {
        Double result = 0.0;

        for (int m = 0; m < sections.size() - 2; m++) {
            double r = rG(ksi, tau, m);
            double tau_ksi_m = Gminus(ksi, tau, m);
            result += (
                    (F.H(ksi - ksi(m)) - F.H(r - ksi(m)))
            ) * gamma(m, tau_ksi_m) / g(m + 1, tau_ksi_m);

            result -= (
                    F.H(ksi - ksi(m + 1)) - F.H(r - ksi(m + 1))
            ) * getNoPsiM(m , tau_ksi_m);



            result += (
                    (F.H(r - ksi(m)) - F.H(r - ksi(m+1)))
                            * (F.H(ksi - ksi(m)) - F.H(ksi - ksi(m+1)))
            ) *sections.get(m+1).getBoundaryСonditions().applyAsDouble(r);


        }
        return result;
    }

    private double getPsiM (int m, Double tau) {
        Double result = 0.0;
        result = sections.get(m).getBoundaryСonditions().applyAsDouble(ksi(m)-Gminus(ksi(m) ,tau ,m));
        return result;
    }

    private double getNoPsiM (int m, Double tau) {
        return    gamma(m, tau) / g(m+1 , tau);
    }


    public Double getTeta2(Double tau, Double ksi) {
        Double result = 0.0;

        for (int m = 0; m < sections.size() - 2; m++) {
            double r=rG(ksi ,tau ,m);
            double tau_ksi_m  = Gminus(ksi ,tau ,m);
            result += (
                    (F.H(ksi - ksi(m)) - F.H(ksi - ksi(m+1)))
                            -(F.H(r - ksi(m)) - F.H(r - ksi(m+1)))
            )  *  gamma(m, tau_ksi_m) / g(m + 1, tau_ksi_m);


            result -= (
                    (F.H(ksi - ksi(m)) - F.H(r - ksi(m)))

            )  *sections.get(m+1).getBoundaryСonditions().applyAsDouble(ksi(m) - rG(ksi,tau_ksi_m,m));
                result += getBoundaryСonditions( r );
        }
        return result;
    }



    private double rG(double ksi , double tau, int m ) {
        return F.rG(ksi ,tau ,0, cashs.get(m));
    }

    private double Gminus(double ksi , double tau, int m ) {
    return F.Gminus(ksi(m) + F.G(tau , cashs.get(m)) - ksi, cashs.get(m));
    }



    private double ksi (int m) {
        return  sections.get(m).getPosition();
    }

    private double gamma (int m, double tau) {
        return  F.H(tau)*
                sections.get(m).getBunker().getGamma().applyAsDouble(tau);
    }

    private double g (int m, double tau) {
        return  speedLaw (sections.get(m).getPosition()-0.001).applyAsDouble(tau);
    }





    public ToDoubleFunction speedLaw ( double ksi) {
        return  sections.stream().filter(section -> ksi < section.getPosition()).min(new SectionComparator() ).get().getSpeed();
    }

    public CashList getGlist(double r, double precision) {

        CashList cash = new CashList(precision);

        double ksi = r;
        double tau = 0.0;
        double dTau = cash.getPrecision();

        while (ksi<=1.0) {
            cash.add(new Pair<>(tau,ksi));
            tau += dTau;
           ksi += getSpeed(tau, ksi)*dTau;
        }

        ksi = r;
        tau = 0.0;
        while (ksi>=-2.0) {
            tau -= dTau;
            ksi -= 1.0*dTau;
            cash.add(new Pair<>(tau,ksi));
        }
        cash.sort(Comparator.comparingDouble(Pair::getValue));

        return cash;
    }

    public List<CashList> getCashForG(double precision) {
        List<CashList> cashLists = new ArrayList<>();
        for (int m = 1; m <= sections.size()-2; m++) {
            CashList cash = new CashList(precision);
            double tau = 0.0;
            double dTau = cash.getPrecision();
            double Gm = 0.0;
            while (sections.get(m).getPosition() - sections.get(m - 1).getPosition() > 0.7* Gm) {
                cash.add(new Pair<>(tau, Gm));
                tau += dTau;
                Gm += sections.get(m).getSpeed().applyAsDouble(tau) * dTau;
            }


            tau = 0.0;
            dTau = cash.getPrecision();
            Gm = 0.0;
            while (tau>=-1.0) {
                tau -= dTau;
                Gm -=  sections.get(m).getSpeed().applyAsDouble(tau) * dTau;
                cash.add(new Pair<>(tau,Gm));
            }
            cash.sort(Comparator.comparingDouble(Pair::getKey));


            cashLists.add(cash);
        }
        return cashLists;
    }




}

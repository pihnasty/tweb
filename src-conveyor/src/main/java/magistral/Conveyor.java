package magistral;



import cash.CashList;
import comparator.SectionComparator;
import function.F;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.ToDoubleFunction;

import static function.F.H;

public class Conveyor {

    List<Section> sections = new ArrayList<>();

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

    public static Conveyor getDefaultConveyor(){
        Conveyor conveyor = new Conveyor();
        conveyor.addSection(new Section("0", 0.00 , tau -> 0.00, ksi -> 0.50+0.5*Math.sin ( 2.0*3.14*(double) ksi ),  new Bunker(tau -> 1.0, true)))
                .addSection(new Section("1", 0.35 , tau -> 1.75, ksi -> 0.50+0.5*Math.sin ( 2.0*3.14*(double) ksi ),  new Bunker(tau -> 1.1, true)))
                .addSection(new Section("2", 0.40 , tau -> 0.50, ksi -> 0.50+0.5*Math.sin ( 2.0*3.14*(double) ksi ),  new Bunker(tau -> 1.2, true)))
                .addSection(new Section("3", 0.70 , tau -> 1.50, ksi -> 0.50+0.5*Math.sin ( 2.0*3.14*(double) ksi ),  new Bunker(tau -> 1.3, true)))
                .addSection(new Section("4", 0.80 , tau -> 0.50, ksi -> 0.50+0.5*Math.sin ( 2.0*3.14*(double) ksi ),  new Bunker(tau -> 1.4, true)))
                .addSection(new Section("5", 1.00 , tau -> 2.00, ksi -> 0.50+0.5*Math.sin ( 2.0*3.14*(double) ksi ),  new Bunker(tau -> 1.5, true)))
                .addSection(new Section("After Section", 10.00 ,
                                                                   tau -> 0.00, ksi -> 0.0,  new Bunker(tau -> 1.5, true)));

        for (int i=1; i< conveyor.sections.size(); i++) {
            conveyor.sections
                    .get(i)
                    .getParentNodes()
                    .add(conveyor.sections.get(i-1));
        }

        for (int i=conveyor.sections.size()-2; i>=0; i--) {
            conveyor.sections.get(i).getChildNodes().add(conveyor.sections.get(i+1));
        }

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

    private CashList cashList;






    public Double getTeta(Double tau, Double ksi) {
        Double result = 0.0;

       double r= F.rG(ksi,tau,0, speedLaw(ksi)  ,cashList);

        for ( int m=0; m<sections.size(); m++) {

            result += ( F.H(ksi - sections.get(m).getPosition())-F.H(r - sections.get(m).getPosition())  )
                    *sections.get(m).getBunker().getGamma().applyAsDouble(tau)/speedLaw (ksi).applyAsDouble(tau);
        }


        for ( int m=1; m<sections.size()-1; m++) {
            result += ( F.H(ksi - sections.get(m).getPosition())-F.H(r - sections.get(m).getPosition())  )
                    *sections.get(m-1).getBunker().getGamma().applyAsDouble(tau)/speedLaw (ksi).applyAsDouble(tau);
        }

        result+= getBoundaryСonditions( r );

        return result;
    }

    public ToDoubleFunction speedLaw ( double ksi) {
        return  sections.stream().filter(section -> ksi < section.getPosition()).min(new SectionComparator() ).get().getSpeed();
    }

}

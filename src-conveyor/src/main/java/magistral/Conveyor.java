package magistral;



import java.util.ArrayList;
import java.util.List;

import static function.F.H;

public class Conveyor {

    List<Section> sections = new ArrayList<>();

    public Conveyor add (Section section) {
        sections.add(section);
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
        conveyor.add(new Section("0", 0.00 , tau -> 0.0 ,  new Bunker(tau -> 1.0, true)))
                .add(new Section("1", 0.35 , tau -> 1.75,  new Bunker(tau -> 1.1, true)))
                .add(new Section("2", 0.40 , tau -> 0.5,  new Bunker(tau -> 1.2, true)))
                .add(new Section("3", 0.70 , tau -> 1.50,  new Bunker(tau -> 1.3, true)))
                .add(new Section("4", 0.80 , tau -> 0.5,  new Bunker(tau -> 1.4, true)))
                .add(new Section("5", 1.00 , tau -> 2.00,  new Bunker(tau -> 1.5, true)));

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
        Double result = 0.0;
        for (int m = 1; m < sections.size();m++)
            result +=
                    (H(ksi - sections.get(m - 1).getPosition())
                            - H(ksi - sections.get(m).getPosition()))
                            * sections.get(m).getSpeed().applyAsDouble(tau);


        return result;
    }

}

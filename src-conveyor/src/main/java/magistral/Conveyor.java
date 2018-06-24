package magistral;

import java.util.List;

public class Conveyor {

    List<Section> sections;

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
        conveyor.add(new Section("1", 0.0  , tau -> 1.75,  new Bunker(tau -> 1.0, true), null, null))
                .add(new Section("2", 0.35 , tau -> 0.25,  new Bunker(tau -> 2.0, true), null, null))
                .add(new Section("3", 0.40 , tau -> 1.50,  new Bunker(tau -> 1.0, true), null, null))
                .add(new Section("4", 0.70 , tau -> 1.33,  new Bunker(tau -> 2.0, true), null, null))
                .add(new Section("5", 0.80 , tau -> 2.00,  new Bunker(tau -> 1.0, true), null, null));
        return conveyor;
    }

//    public Double getSpeed (Double tau, Double ksi) {
//        F.H(0);
//    }

}

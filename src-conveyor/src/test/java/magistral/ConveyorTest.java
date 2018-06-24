package magistral;

import org.junit.Test;
import write.PrintWriterP;

import java.util.ArrayList;
import java.util.List;

public class ConveyorTest {
    Conveyor conveyor = Conveyor.getDefaultConveyor();
    @Test
    public void getDefaultConveyorTest(){
        conveyor.sections.forEach(section ->
                System.out.println("name="+section.getName()+" ChildNodes "
                        + (!section.getChildNodes().isEmpty() ?  section.getChildNodes().get(0).getName(): "  ")
                        +" ParentsNodes "
                        +  (!section.getParentNodes().isEmpty() ?  section.getParentNodes().get(0).getName(): "  ")
                )
        );
    }

    @Test
    public void  getSpeedTest() {
        Integer N = 100;
        double dksi = 1.0/N;
        for( int i =0; i<N; i++) {
            System.out.println(i * dksi+"   "+ conveyor.getSpeed(0.0, i * dksi));
        }
    }

    @Test
    public void  getSpeedWriteToFileTest() {

        List<Double> ksis = new ArrayList<>();
        List<Double> gs = new ArrayList<>();

        Integer N = 100;
        double dksi = 1.0/N;
        for( int i =0; i<N; i++) {
            System.out.println(i * dksi+"   "+ conveyor.getSpeed(0.0, i * dksi));
            ksis.add(i * dksi); gs.add(conveyor.getSpeed(0.0, i * dksi));
        }

        PrintWriterP printWriterP = new PrintWriterP("%8.3f", "D:", "shag2.dat");
        printWriterP.writeToFile(ksis,gs);




    }
}



package magistral;

import org.junit.Test;
import write.CsvWriterP;
import write.PrintWriterP;

import java.io.IOException;
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
    public void  getSpeedWriteToFilePrintTest() {

        List<Double> ksis = new ArrayList<>();
        List<Double> gs = new ArrayList<>();
        List<Double> number = new ArrayList<>();

        Integer N = 100;
        gmFmKsi(ksis, gs, number, N);

        PrintWriterP printWriterP = new PrintWriterP("  %8.3f   ", "src\\test\\java\\magistral", "test3.txt");
        printWriterP.writeToFile(number,ksis,gs);
    }

    @Test
    public void  getSpeedWriteToFileCsvTest() throws IOException {

        List<Double> ksis = new ArrayList<>();
        List<Double> gs = new ArrayList<>();
        List<Double> number = new ArrayList<>();
        Integer N = 100;
        gmFmKsi(ksis, gs, number, N);
        CsvWriterP p = new CsvWriterP("  %8.3f",';',"src\\test\\java\\magistral","c2.csv");
        p.setHeader("       ksi","        gm","ROWNAMES");
        p.writeToFile(ksis,gs,number );
    }

    private void gmFmKsi(List<Double> ksis, List<Double> gs, List<Double> number, Integer n) {
        double dksi = 1.0/ n;
        for(int i = 0; i< n; i++) {
            System.out.println(i * dksi+"   "+ conveyor.getSpeed(0.0, i * dksi));
            ksis.add(i * dksi); gs.add(conveyor.getSpeed(0.0, i * dksi));
            number.add(i*2.0);
        }
    }
}



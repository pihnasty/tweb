package magistral;

import org.junit.Test;
import write.CsvWriterP;
import write.PrintWriterP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        gmFromKsi(ksis, gs, number, N);

        PrintWriterP printWriterP = new PrintWriterP("  %8.3f   ", "src\\test\\java\\magistral", "test3.txt");
        Locale.setDefault(new Locale("en", "US"));
        printWriterP.writeToFile(number,ksis,gs);
        Locale.setDefault(new Locale("ru", "RU"));
    }

    @Test
    public void  getSpeedWriteToFileCsvTest() throws IOException {

        List<Double> ksis = new ArrayList<>();
        List<Double> gs = new ArrayList<>();
        List<Double> number = new ArrayList<>();
        Integer N = 1000;
        gmFromKsi(ksis, gs, number, N);
        CsvWriterP p = new CsvWriterP("  %8.3f   ",';',"src\\test\\java\\magistral","gm_ksi.csv");
        Locale.setDefault(new Locale("en", "US"));
        p.setHeader("     ksi     ","     gm      ","     number");
        p.writeToFile(ksis,gs,number );
        p.getLocaleCurrent();
    }

    private void gmFromKsi(List<Double> ksis, List<Double> gs, List<Double> number, Integer n) {
        double dksi = 1.0/ n;
        for(int i = 0; i< n; i++) {
            System.out.println(i * dksi+"   "+ conveyor.getSpeed(0.0, i * dksi));
            ksis.add(i * dksi); gs.add(conveyor.getSpeed(0.0, i * dksi));
            number.add(i*1.0);
        }
    }

    @Test
    public void  get_Ksi_From_Tau_WriteToFileCsvTest() throws IOException {

        List<Double> ksis = new ArrayList<>();
        List<Double> number = new ArrayList<>();
        List<Double> taus = new ArrayList<>();
        Integer N = 1000;

        double ksi = 0.0;
        double tauMin = 0.0; double tauMax = 1.0;  double dTau = (tauMax-tauMin)/N;
        double tau = tauMin;

        for(int i = 0; i< N; i++) {
            ksis.add(ksi);
            taus.add(tau);
            System.out.println(ksi+"   "+tau+ "    "+ conveyor.getSpeed(tau,ksi));
            ksi += conveyor.getSpeed(tau,ksi)*dTau;
            tau+=dTau;

            number.add(i*1.0);
        }

        CsvWriterP p = new CsvWriterP("  %8.3f   ",';',"src\\test\\java\\magistral","ksi_tau.csv");
        Locale.setDefault(new Locale("en", "US"));
        p.setHeader("     tau     ","     ksi     ","     number");
        p.writeToFile(ksis,taus,number );
        p.getLocaleCurrent();
    }
}



package magistral;

import cash.CashList;
import function.F;
import org.junit.Test;
import write.CsvWriterP;
import write.PrintWriterP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.ToDoubleFunction;

import static org.junit.Assert.assertEquals;

public class ConveyorTest {

//------------------------------------------------------------------------------------------
    Integer N = 1000;   // number of partitions for a technological route
    double dksi = 1.0/ N;
//------------------------------------------------------------------------------------------

    Conveyor conveyor = Conveyor.getDefaultConveyor();
    @Test
    public void getDefaultConveyorTest(){
        conveyor.getSections().forEach(section ->
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
    public void  getSpeedWriteToFilePrintTest() throws IOException {
        String path = "src\\test\\java\\magistral\\"+new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
        Files.createDirectories(Paths.get(path));
        List<Double> ksis = new ArrayList<>();
        List<Double> gs = new ArrayList<>();
        List<Double> number = new ArrayList<>();

        Integer N = 100;
        gmFromKsi(0,ksis, gs, number, N);

        PrintWriterP printWriterP = new PrintWriterP("  %8.3f   ", path, "speed_ksi.txt");
        Locale.setDefault(new Locale("en", "US"));
        printWriterP.writeToFile(number,ksis,gs);
        Locale.setDefault(new Locale("ru", "RU"));
    }

    @Test
    public void  getSpeedWriteToFileCsvTest( int variant) throws IOException {
        String path = "src\\test\\java\\magistral\\"+new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
        Files.createDirectories(Paths.get(path));
        double tau;
        double dTau = 0.025;
       if (variant == 0) {
           for (Integer t = 0; t < 35; t++) {
               List<Double> ksis = new ArrayList<>();
               List<Double> gs = new ArrayList<>();
               List<Double> number = new ArrayList<>();
               tau = t * dTau;
               gmFromKsi(tau, ksis, gs, number, N);

               CsvWriterP p = new CsvWriterP("  %8.3f   ", ';', path, "gm_ksi"+ t.toString() + ".csv");
               Locale.setDefault(new Locale("en", "US"));
               p.setHeader("     ksi     ", "     gm      ", "     number");
               p.writeToFile(ksis, gs, number);
               p.getLocaleCurrent();

           }
       }

        if (variant == 1) {

                for (Integer is=0; is<conveyor.getSections().size()-1; is++) {
                    List<Double> taus = new ArrayList<>();
                    List<Double> gs = new ArrayList<>();
                    List<Double> number = new ArrayList<>();

                    gmFromTau(conveyor.getSections().get(is).getPosition(), taus, gs, number, N);

                    CsvWriterP p = new CsvWriterP("  %8.3f   ", ';', path, "gm_ksi" + is.toString() + ".csv");
                    Locale.setDefault(new Locale("en", "US"));
                    p.setHeader("     ksi     ", "     gm      ", "     number");
                    p.writeToFile(taus, gs, number);
                    p.getLocaleCurrent();
                }
        }

    }

    public void getSpeedWriteToFileCsvTest(List<Section> sections, double precision, int variant) throws IOException {
        conveyor = new Conveyor(sections,precision);
        getSpeedWriteToFileCsvTest( variant);
    }

    private void gmFromKsi(double tau, List<Double> ksis, List<Double> gs, List<Double> number, Integer n) {
        double dksi = 1.0/ n;
        for(int i = 0; i< n; i++) {
            System.out.println(i * dksi+"   "+ conveyor.getSpeed(tau, i * dksi));
            ksis.add(i * dksi); gs.add(conveyor.getSpeed(tau, i * dksi));
            number.add(i*1.0);
        }
    }

    private void gmFromTau(double ksi, List<Double> taus, List<Double> gs, List<Double> number, Integer n) {
        double dtau = 1.0/ n;
        for(int i = 0; i< n; i++) {
            System.out.println(i * dtau+"   "+ conveyor.getSpeed(i * dtau, ksi));
            taus.add(i * dtau); gs.add(conveyor.getSpeed(i * dtau, ksi));
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
        double tauMin = 0.0; double tauMax = 2.0;  double dTau = (tauMax-tauMin)/N;
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

    @Test
    public void  get_Ksi_From_Tau_02_WriteToFileCsvTest() throws IOException {

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
            System.out.println(ksi+"   "+tau+ "    "+ conveyor.speedLaw(ksi).applyAsDouble(tau));
            ksi += conveyor.speedLaw(ksi).applyAsDouble(tau)*dTau;
            tau+=dTau;

            number.add(i*1.0);
        }

        CsvWriterP p = new CsvWriterP("  %8.3f   ",';',"src\\test\\java\\magistral","ksi_tau02.csv");
        Locale.setDefault(new Locale("en", "US"));
        p.setHeader("     tau     ","     ksi     ","     number");
        p.writeToFile(ksis,taus,number );
        p.getLocaleCurrent();
    }



    @Test
    public void  getBoundaryСonditionsWriteToFileCsvTest() throws IOException {

        List<Double> ksis = new ArrayList<>();
        List<Double> boundaryСonditions = new ArrayList<>();
        List<Double> number = new ArrayList<>();
        Integer N = 1000;
        double dksi = 1.0/ N;

        for(int i = 0; i< N; i++) {
            System.out.println(i * dksi+"   "+ conveyor.getBoundaryСonditions(i*dksi));
            ksis.add(i * dksi); boundaryСonditions.add(conveyor.getBoundaryСonditions(i*dksi));
            number.add(i*1.0);
        }

        CsvWriterP p = new CsvWriterP("  %8.3f   ",';',"src\\test\\java\\magistral","psi_ksi.csv");
        Locale.setDefault(new Locale("en", "US"));
        p.setHeader("     ksi     ","     psi     ","     number");
        p.writeToFile(ksis,boundaryСonditions,number );
        p.getLocaleCurrent();
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void getBoundaryСonditionsConstV1WriteToFileCsvTest() throws IOException {
        Locale.setDefault(new Locale("ru", "RY"));
        List<Double> ksis = new ArrayList<>();
        List<Double> boundaryСonditions = new ArrayList<>();
        List<Double> number = new ArrayList<>();

        conveyor.setBoundaryСonditions(new ArrayList<ToDoubleFunction>() {
            { add(ksi-> 0.00);add(ksi-> 1.00);add(ksi-> 0.75);add(ksi-> 0.75);add(ksi-> 0.50);add(ksi-> 0.25);}
        });

        for(int i = 0; i< N; i++) {
            System.out.println(i * dksi+"   "+ conveyor.getBoundaryСonditions(i*dksi));
            ksis.add(i * dksi); boundaryСonditions.add(conveyor.getBoundaryСonditions(i*dksi));
            number.add(i*1.0);
        }

        CsvWriterP p = new CsvWriterP("  %8.3f   ",';',"src\\test\\java\\magistral","psi_ksiV1.csv");
        Locale.setDefault(new Locale("en", "US"));
        p.setHeader("     ksi     ","     psi     ","     number");
        p.writeToFile(ksis,boundaryСonditions,number );
        p.getLocaleCurrent();
    }

    @Test
    public void speedLawTest() {
        assertEquals("Unexpected double value", 0.5, conveyor.speedLaw(0.36).applyAsDouble(0.0), 0.001);
        assertEquals("Unexpected double value", 2.0, conveyor.speedLaw(0.99).applyAsDouble(1.0), 0.001);
        assertEquals("Unexpected double value", 0.0, conveyor.speedLaw(1.0).applyAsDouble(1.0), 0.001);
    }

    @Test
    public void getTetaWriteToFileCsvTest() throws IOException {
        throw new NullPointerException("getTetaWriteToFileCsvTest()");

//        String path = "src\\test\\java\\magistral\\"+new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
//        Files.createDirectories(Paths.get(path));
//        double tau;
//        double dTau = 0.025;
//
//        for (Integer t = 0; t < 35; t++) {
//            Locale.setDefault(new Locale("ru", "RY"));
//            List<Double> ksis = new ArrayList<>();
//            List<Double> tetas = new ArrayList<>();
//            List<Double> number = new ArrayList<>();
//            tau = t * dTau;
//            for (int i = 0; i < N; i++) {
//                System.out.println(i * dksi + "   " + conveyor.getTeta(tau, i * dksi));
//                ksis.add(i * dksi);
//                tetas.add(conveyor.getTeta(tau, i * dksi));
//                number.add(i * 1.0);
//            }
//            CsvWriterP p = new CsvWriterP("  %8.3f   ", ';', path, "teta_ksi" + t.toString() + ".csv");
//            Locale.setDefault(new Locale("en", "US"));
//            p.setHeader("     ksi     ", "     teta    ", "     number");
//            p.writeToFile(ksis, tetas, number);
//            p.getLocaleCurrent();
//        }
    }

    @Test
    public void getTetaWriteToFileCsvTest(List<Section> sections, double precision, int variantDecision) throws IOException {
        conveyor = new Conveyor(sections,precision);
        getTetaWriteToFileCsvTest(variantDecision);
    }

    /**
     *
     * @param variant
     * @throws IOException
     */
    @Test
    public void getTetaWriteToFileCsvTest(int variant) throws IOException {
        String path = "src\\test\\java\\magistral\\"+new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
        Files.createDirectories(Paths.get(path));
        double tau;
        double dTau = 0.025;

        for (Integer t = 0; t < 35; t++) {
            Locale.setDefault(new Locale("ru", "RY"));
            List<Double> ksis = new ArrayList<>();
            List<Double> tetas = new ArrayList<>();
            List<Double> number = new ArrayList<>();
            tau = t * dTau;
            for (int i = 0; i < N; i++) {
                if (variant == 0) {
                    System.out.println(i * dksi + "   " + conveyor.getTeta(tau, i * dksi));
                    tetas.add(conveyor.getTeta(tau, i * dksi));
                } else {
                    System.out.println(i * dksi + "   " + conveyor.getTeta(tau, i * dksi, variant));
                    tetas.add(conveyor.getTeta(tau, i * dksi, variant));
                }
                ksis.add(i * dksi);
                number.add(i * 1.0);
            }
            CsvWriterP p = new CsvWriterP("  %8.3f   ", ';', path, "teta_ksi" + t.toString() + ".csv");
            Locale.setDefault(new Locale("en", "US"));
            p.setHeader("     ksi     ", "     teta    ", "     number");
            p.writeToFile(ksis, tetas, number);
            p.getLocaleCurrent();
        }
    }

    @Test
    public void getTetavTest() throws IOException {

        Double result = conveyor.getTeta(0.02,0.0);
        System.out.println(result);
        }






    @Test
    public void GTest() {
        CashList cash = conveyor.getGlist(0.0, 0.00001);

        assertEquals("Unexpected double value", 0.75, F.G(-0.6, cash), 0.001);

//        assertEquals("Unexpected double value", 0.75, F.G(0.6, cash), 0.001);
//        assertEquals("Unexpected double value", 0.6, F.Gminus(0.75, cash), 0.001);
    }

    @Test
    public void GminusTest() {
        CashList cash = conveyor.getGlist(0.0, 0.00001);

        assertEquals("Unexpected double value", 0.75, F.Gminus(-0.6, cash), 0.001);

//        assertEquals("Unexpected double value", 0.75, F.G(0.6, cash), 0.001);
//        assertEquals("Unexpected double value", 0.6, F.Gminus(0.75, cash), 0.001);
    }
}






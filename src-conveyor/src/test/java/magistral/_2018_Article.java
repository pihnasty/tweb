package magistral;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class _2018_Article {
    private ConveyorTest conveyorTest = new ConveyorTest();

    @Test
    /**
     * Fig.6. The belt speed (a) and the family characteristics (b)    of the main conveyor depending on the section.
     */
    public void fugure06() throws IOException {
        conveyorTest.getSpeedWriteToFileCsvTest();
        conveyorTest.get_Ksi_From_Tau_WriteToFileCsvTest();
    }

    @Test
    public void fugure08() throws IOException {
        conveyorTest.getBoundaryСonditionsWriteToFileCsvTest();
        conveyorTest.getBoundaryСonditionsConstV1WriteToFileCsvTest();
    }

    /**
     * This option corresponds to the solution in the form
     * Solution01.PNG
     * The Method fugure09()==fugure09_01()
     * Fig.7. The linear density of the rock along the transport route at times
     * C:\E\A\D02\tweb\src-conveyor\src\test\java\magistral\2018_07_21_16_46
     */
    @Test
    public void fugure09() throws IOException {
        conveyorTest.getTetaWriteToFileCsvTest();
    }
    /**
     * This option corresponds to the solution in the form
     * Solution02.PNG   !!!!
     *      * The Method fugure09()==fugure09_01()
     * Fig.7. The linear density of the rock along the transport route at times
     */
    @Test
    public void fugure09_01() throws IOException {
        conveyorTest.getTetaWriteToFileCsvTest(1);
    }

    @Test
    public void fugure10() throws IOException {
        List<Section> sections = Arrays.asList(
                new Section("0", 0.00, tau -> 0.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.0, true)),
                new Section("1", 0.20, tau -> 1.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.4, true)),
                new Section("2", 0.50, tau -> 0.50, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.2, true)),
                new Section("3", 0.70, tau -> 1.50, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.1, true)),
                new Section("4", 0.80, tau -> 0.50, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.5, true)),
                new Section("5", 1.00, tau -> 2.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.5, true)),
                new Section("After Section", 10.00, tau -> 0.00, ksi -> 0.0, new Bunker(tau -> 1.5, true))
        );

//        double a = 0.5;
//        List<Section> sections = Arrays.asList(
//                new Section("0", 0.00, tau -> 0.00, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.0, true)),
//                new Section("1", 0.20, tau -> 1.00 + a*(double) tau, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.4, true)),
//                new Section("2", 0.50, tau -> 0.50 + a*(double) tau, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.2, true)),
//                new Section("3", 0.70, tau -> 1.50 + a*(double) tau, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.1, true)),
//                new Section("4", 0.80, tau -> 0.50 + a*(double) tau, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 0.5, true)),
//                new Section("5", 1.00, tau -> 2.00 + a*(double) tau, ksi -> 0.50 + 0.5 * Math.sin(2.0 * 3.14 * (double) ksi), new Bunker(tau -> 1.5, true)),
//                new Section("After Section", 10.00 , tau -> 0.00, ksi -> 0.0, new Bunker(tau -> 1.5, true))
//        );


        conveyorTest.getTetaWriteToFileCsvTest(sections, 0.001, 0);
    }
}


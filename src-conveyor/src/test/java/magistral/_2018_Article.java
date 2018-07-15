package magistral;

import org.junit.Test;

import java.io.IOException;

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
     * Fig.7. The linear density of the rock along the transport route at times
     *
     */
    @Test
    public void fugure09() throws IOException {
        conveyorTest.getTetaWriteToFileCsvTest();
    }
}


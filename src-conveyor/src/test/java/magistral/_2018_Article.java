package magistral;

import org.junit.Test;

import java.io.IOException;

public class _2018_Article {
    private ConveyorTest conveyorTest = new ConveyorTest();

    @Test
    public void fugure06() throws IOException {
        conveyorTest.getSpeedWriteToFileCsvTest();
        conveyorTest.get_Ksi_From_Tau_WriteToFileCsvTest();
    }

    @Test
    public void fugure08() throws IOException {
        conveyorTest.getBoundaryСonditionsWriteToFileCsvTest();
        conveyorTest.getBoundaryСonditionsConstV1WriteToFileCsvTest();
    }
}


package magistral;


import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpeedTest {
    @Test
    public void getKeysTest() {

        List<Double> expendetKeys =  Arrays.asList(0.8, 0.7,0.4, 0.35, 0.0);
        Speed speed = new Speed();
        speed.getSpeedsDefault();
        assertEquals(expendetKeys , speed.getKeysByDiscrease() );

    }

    @Test
    public void getSpeedTest() {
        Speed speed = new Speed();
        speed.getSpeedsDefault();
        assertEquals( speed.getSpeed(0.5,-0.1) , (Double) 1.75 );
        assertEquals( speed.getSpeed(0.5,0.0) , (Double) 1.75 );
        assertEquals( speed.getSpeed(0.5,0.75) , (Double) 0.33 );

    }

    @Test
    public void getSpeedsDefaultLinearTauTest() {
        Speed speed = new Speed();
        speed.getSpeedsDefaultLinearTau();
        assertEquals( speed.getSpeed(1.0,0.1) , (Double) 2.75 );
        assertEquals( speed.getSpeed(1.0,0.71) , (Double) 1.33 );
    }
}

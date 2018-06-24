package write;

import com.sun.javafx.binding.StringFormatter;
import org.junit.Test;
import write.PrintWriterP;


public class PrintWriterTest {

    @Test
    public void writeToFileTest() {


        PrintWriterP printWriterP = new PrintWriterP("%8.3f ", "D:", "shag2.csv");

        printWriterP.writeToFile();








    }
}

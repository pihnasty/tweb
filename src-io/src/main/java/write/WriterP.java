package write;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public interface WriterP {
    void writeToFile(List<Double>... arg) throws IOException;
    String getFullPathToFile();

}

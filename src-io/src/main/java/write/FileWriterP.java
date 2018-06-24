package write;

import com.sun.javafx.binding.StringFormatter;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class FileWriterP {
    public StringFormatter stringFormatter;
    public Path path;
    public String fileName;

    public FileWriterP(StringFormatter stringFormatter, Path path, String fileName) {
        this.stringFormatter = stringFormatter;
        this.path = path;
        this.fileName = fileName;
    }

    public void writeToFile (List<Double>... arg ){
        Integer size = arg.length;
        try{
            PrintWriter pw = new PrintWriter(new File("C:/shag2.txt"));
            pw.println("regtrdofm1");
            pw.println("regtrodfm2");
            pw.println("regtdrofm3");
            pw.close();
        }catch(FileNotFoundException e){e.printStackTrace();}
    }



}




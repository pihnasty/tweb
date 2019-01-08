package util.read;

import com.opencsv.CSVReader;
import util.AbstractCsvP;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReaderP extends AbstractCsvP {

    public CsvReaderP(String columnsFormat, char delimiter, String path, String fileName) {
        super(columnsFormat, delimiter, path, fileName);
    }

    @Override
    public  List<List<String>> readFromFile() throws IOException {
        Reader reader = new FileReader(getFile());
        List<List<String>> rows = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(reader, getDelimiter())) {
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                List<String> row = new ArrayList<>();
                row.addAll(Arrays.asList(record));
                rows.add(row);
            }
        }
        return rows;
    }


}

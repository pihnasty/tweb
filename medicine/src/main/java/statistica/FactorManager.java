package statistica;

import write.CsvWriterP;


public class FactorManager {

    void createTable(){
        FactorBuilder factorBuilder = new FactorBuilder();
        factorBuilder.createHeader("count",  " name                         "," category             "," averageValue ","minValue","maxValue"
                ," author                   "," numberPeople ",    "    age "," probability "," toolName "," reference ","description ");
        factorBuilder.createFormat(             " %3d "," %28s "                          ,  " %20s "              ,    " %12.3f "  ," %6.3f "   ," %6.3f "
                ," %24s "                    ,        " %12d ",       " %6s ",   " %11.3f ",  " %8s ",      " %9s ",  "%25s ");
        factorBuilder.constructFactors();
        String path = "C:\\E\\A\\D02\\tweb\\medicine\\src\\main\\java\\data";
        CsvWriterP csvWriterP =  new CsvWriterP( "%8.3f  ", ';', path, "fig01.csv");
        csvWriterP.writeToFile(factorBuilder.getTable());
    }

}

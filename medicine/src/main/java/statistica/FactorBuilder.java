package statistica;

import factors.Factor;
import factors.FactorConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FactorBuilder {
    List<Factor> factors;
    List<List<String>> rows = new ArrayList<>();

    public List<Factor> getFactors() {
        return factors;
    }


    public List<Factor> constructFactors () {
        List<Factor> factorList = new ArrayList<>();

        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.MATERNAL_ASTHMA,
                4.1,
                2.1,
                7.9,
                FactorConstants.Author.MARTINEZ,
                1246,
                FactorConstants.Age.SIX,
                0.95,
                "TCRS",
                "[7]",
                ""
        ));

//        factorList.add(new Factor(
//                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
//                FactorConstants.Category.PARENTAL,
//                0.0,
//                0.0,
//                0.0,
//                FactorConstants.Author.CASTRO_RODRIGUEZ,
//                1246,
//                FactorConstants.Age.THREE,
//                0.95,
//                "TCRS",
//                "[3,8,9]",
//                ""
//        ));


        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.PARENTAL,
                2.6,
                1.8,
                3.7,
                FactorConstants.Author.HAFKAMP_DE_GROEN,
                2877,
                FactorConstants.Age.SIX,
                0.95,
                "PIAMA",
                "[10]",
                ""
        ));

        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.ASTHMATIC_FAMILY_HISTORY,
                2.31,
                1.22,
                4.37,
                FactorConstants.Author.KURUKULAARATCHY,
                1456,
                FactorConstants.Age.FOUR,
                0.95,
                "",
                "[11]",
                ""
        ));

        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.PARENTAL_ATOPY,
                8.32,
                2.74,
                25.20,
                FactorConstants.Author.MATRICARDI,
                441,
                FactorConstants.Age.ELEVEN,
                0.95,
                "MAS",
                "[12]",
                ""
        ));

        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.ASTHMATIC_FAMILY_HISTORY,
                2.34,
                1.06,
                5.13,
                FactorConstants.Author.JUST,
                219,
                FactorConstants.Age.EIGHT,
                0.95,
                "",
                "[13]",
                ""
        ));

        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.MATERNAL_ASTHMA,
                4.17,
                3.12,
                5.56,
                FactorConstants.Author.HENDERSON,
                6225,
                FactorConstants.Age.NONE,
                0.0,
                "ALSPAC",
                "[14]",
                ""
        ));


        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.MATERNAL_ALLERGY,
                2.09,
                1.67,
                2.62,
                FactorConstants.Author.HENDERSON,
                6225,
                FactorConstants.Age.NONE,
                0.0,
                "ALSPAC",
                "[14]",
                ""
        ));

        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.MATERNAL_ALLERGY,
                2.06,
                1.36,
                3.12,
                FactorConstants.Author.CAUDRI,
                0,
                FactorConstants.Age.EIGHT,
                0.95,
                "PIAMA",
                "[15]",
                ""
        ));


        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.PARENTAL_ALLERGY,
                1.74,
                1.15,
                2.36,
                FactorConstants.Author.CAUDRI,
                0,
                FactorConstants.Age.EIGHT,
                0.95,
                "PIAMA",
                "[15]",
                ""
        ));



        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.ASTHMATIC_FAMILY_HISTORY,
                2.4,
                1.3,
                4.5,
                FactorConstants.Author.VIAL_DUPUY,
                200,
                FactorConstants.Age.SIX,
                0.95,
                "",
                "[16]",
                ""
        ));


        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.ASTHMATIC_FAMILY_HISTORY,
                1.3,
                0.0,
                0.0,
                FactorConstants.Author.PESCATORE,
                1126,
                FactorConstants.Age.THREE,
                0.0,
                "",
                "[18]",
                ""
        ));




        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.PARENTAL_ATOPY,
                7.2,
                1.3,
                41.6,
                FactorConstants.Author.MIKALSEN,
                93,
                FactorConstants.Age.ELEVEN,
                0.95,
                "",
                "[19]",
                ""
        ));



        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.PARENTAL_ATOPY,
                1.9,
                1.3,
                2.8,
                FactorConstants.Author.CSONKA,
                1816,
                FactorConstants.Age.THREE,
                0.95,
                "",
                "[20]",
                ""
        ));



        factorList.add(new Factor(
                FactorConstants.Name.FAMILY_ASTHMA_OR_ATOPY,
                FactorConstants.Category.ASTHMATIC_FAMILY_HISTORY,
                1.9,
                1.3,
                2.8,
                FactorConstants.Author.NEUMAN,
                823,
                FactorConstants.Age.EIGHT,
                0.95,
                "",
                "[20]",
                ""
        ));









        factors =factorList;
        return factors;
    }

    private List<String> headers;
    private List<String>  formatHeaders;

    public List<String> createHeader (String ... newHeaders) {
        headers = new ArrayList<>();
        headers.addAll(Arrays.asList(newHeaders));
        return headers;
    }

    public List<String> createFormat (String ... format) {
        formatHeaders = new ArrayList<>();
        formatHeaders .addAll(Arrays.asList(format));
        return formatHeaders ;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void createRows() {
        factors.forEach(factor -> {
            List<String> row = new ArrayList<>();
            int count = Factor.getCount();
            row.add(String.format(formatHeaders.get(0),count));
            row.add(String.format(formatHeaders.get(1),factor.getName().getValue()));
            row.add(String.format(formatHeaders.get(2),factor.getCategory().getValue()));
            row.add(String.format(formatHeaders.get(3),factor.getAverageValue()));
            row.add(String.format(formatHeaders.get(4),factor.getMinValue()));
            row.add(String.format(formatHeaders.get(5),factor.getMaxValue()));
            row.add(String.format(formatHeaders.get(6),factor.getAuthor().getValue()));
            row.add(String.format(formatHeaders.get(7),factor.getNumberPeople()));
            row.add(String.format(formatHeaders.get(8),factor.getAge().getValue()));
            row.add(String.format(formatHeaders.get(9),factor.getProbability()));
            row.add(String.format(formatHeaders.get(10),factor.getToolName()));
            row.add(String.format(formatHeaders.get(11),factor.getReference()));
            row.add(String.format(formatHeaders.get(12),factor.getDescription()));
//            row.add(String.format(formatHeaders.get(13),count));
            rows.add(row);
        });

    }


    public List<List<String>> getRows() {
        return rows;
    }

    public List<List<String>> getTable() {
        List<List<String>> table = new ArrayList<>();
        createRows();
        table.add(getHeaders());
        table.addAll(getRows());
        return table;
    }



}

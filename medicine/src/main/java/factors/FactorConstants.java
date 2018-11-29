package factors;

public class FactorConstants {

    public  enum Name {
        FAMILY_ASTHMA_OR_ATOPY("Family asthma or atopy");

        private final String name;

        Name(String name) {
            this.name = name;
        }

        public String getValue() {
            return name;
        }
    }

    public enum  Category {

        ASTHMATIC_FAMILY_HISTORY ("asthma family hist."),
        MATERNAL_ASTHMA ("maternal asthma"),
        MATERNAL_ALLERGY  ("maternal allergy "),

        PARENTAL ("parental"),
        PARENTAL_ATOPY ("parental atopy"),
        PARENTAL_ALLERGY  ("parental allergy"),

        NONE("");

        private final String category;

        Category(String category) {
            this.category = category;
        }

        public String getValue() {
            return category;
        }
    }

    public enum  Author {

        CASTRO_RODRIGUEZ ("Castro-Rodriguez "),
        CAUDRI ("Caudri et al"),
        CSONKA ("Csonka et"),
        HAFKAMP_DE_GROEN ("Hafkamp-de Groen et al"),
        JUST ("Just  et  al "),
        MARTINEZ ("Martinez et al"),
        HENDERSON ("Henderson "),
        MATRICARDI ("Matricardi et al "),
        MIKALSEN ("Mikalsen et al"),
        NEUMAN("Neuman et al "),
        KURUKULAARATCHY ("Kurukulaaratchy et al"),
        PESCATORE ("Pescatore et al"),
        VAN_DER_MARK("van der Mark et al"),
        VIAL_DUPUY ("Vial Dupuy et al");





        private final String author;

        Author(String author) {
            this.author = author;
        }

        public String getValue() {
            return author;
        }
    }

    public enum  Age {

        EIGHT("0-8"),
        FOUR("4-10"),
        THREE ("3"),
        ELEVEN("11-13"),
        SIX ("6"),
        NONE("");


        private final String age;

        Age(String author) {
            this.age = author;
        }

        public String getValue() {
            return age;
        }
    }
}

package factors;

public class Factor {
    private FactorConstants.Name name;
    private FactorConstants.Category category;
    private double averageValue;
    private double minValue;
    private double maxValue;
    private FactorConstants.Author author;
    private int numberPeople;
    private FactorConstants.Age age;
    private double probability;
    private String toolName;
    private String reference;
    private String description;
    private static int count = 1;


    public Factor(FactorConstants.Name name,
                  FactorConstants.Category category,
                  double averageValue,
                  double minValue,
                  double maxValue,
                  FactorConstants.Author author,
                  int numberPeople,
                  FactorConstants.Age age,
                  double probability,
                  String toolName,
                  String reference,
                  String description) {
        this.name = name;
        this.category = category;
        this.averageValue = averageValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.author = author;
        this.numberPeople = numberPeople;
        this.age = age;
        this.probability = probability;
        this.toolName = toolName;
        this.reference = reference;
        this.description = description;
    }

    public FactorConstants.Name getName() {
        return name;
    }

    public FactorConstants.Category getCategory() {
        return category;
    }

    public double getAverageValue() {
        return averageValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public FactorConstants.Author getAuthor() {
        return author;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public FactorConstants.Age getAge() {
        return age;
    }

    public double getProbability() {
        return probability;
    }

    public String getToolName() {
        return toolName;
    }

    public String getReference() {
        return reference;
    }

    public String getDescription() {
        return  toString();
    }

    public static int getCount() {
        return count++;
    }

    @Override
    public String toString() {
        return
                "\"" +
                        author.getValue() + ", " +category.getValue()+ ", " +reference
                        +"\"";
    }
}


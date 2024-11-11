package utilities;

public class UnitType
{
  private static String[] imperialVolumeUnits = {"Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon"};
  private static String[] metricVolumeUnits = {"Milliliters"};
  private static String[] metricMassUnits = {"Gram"};
  private static String[] imperialMassUnits = {"Dram", "Ounce", "Pound"};
  private static String[] imperialUnits = {"Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  private static String[] imperialUnitsSpace = {"", "Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  private static String[] metricUnits = {"Gram", "Milliliters"};
  private static String[] metricUnitsSpace = {"", "Gram", "Milliliters"};
  private static String[] metricUnitsIndividual = {"", "Individual", "Gram", "Milliliters"};
  private static String[] imperialUnitsIndividual = {"", "Individual", "Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  public static boolean imperialSelected = true; // Imperial is automatically used unless user specifies to use metric.
  public static boolean metricSelected = false;
  
  public static String[] getImperialMassUnits() {
    return imperialMassUnits;
  }
  
  public static String[] getImperialVolumeUnits() {
    return imperialVolumeUnits;
  }
  
  public static String[] getMetricMassUnits() {
    return metricMassUnits;
  }
  
  public static String[] getMetricVolumeUnits() {
    return metricVolumeUnits;
  }
  
  public static String[] getMetricUnits() {
    return metricUnits;
  }
  
  public static String[] getImperialUnits() {
    return imperialUnits;
  }
  
  public static String[] getImperialUnitsIndividual() {
    return imperialUnitsIndividual;
  }
  
  public static String[] getMetricUnitsIndividual() {
    return metricUnitsIndividual;
  }
  
  public static String[] getMetricUnitsSpace() {
    return metricUnitsSpace;
  }
  
  public static String[] getImperialUnitsSpace() {
    return imperialUnitsSpace;
  }
}

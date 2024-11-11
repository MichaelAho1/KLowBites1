package utilities;

public class UnitType
{
  private static final String[] imperialVolumeUnits = {"Pinches", "Teaspoon", "Tabelspoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon"};
  private static final String[] metricVolumeUnits = {"Milliliters"};
  private static final String[] metricMassUnits = {"Gram"};
  private static final String[] imperialMassUnits = {"Dram", "Ounce", "Pound"};
  private static final String[] imperialUnits = {"Pinches", "Teaspoon", "Tabelspoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  private static final String[] metricUnits = {"Gram", "Milliliters"};
  
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
    return metricUnits;
  }
}

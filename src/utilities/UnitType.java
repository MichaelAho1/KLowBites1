package utilities;

/**
 * UnitType class.
 *
 * @author f24team3d
 * @version 11/12/24
 */
public class UnitType
{
  public static boolean imperialSelected = true; // Imperial is automatically used unless user
  // specifies to use metric.
  public static boolean metricSelected = false;

  private static final String[] imperialVolumeUnits = {"Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce",
      "Cup", "Pint", "Quart", "Gallon"};
  private static final String[] metricVolumeUnits = {"Milliliters"};
  private static final String[] metricMassUnits = {"Gram"};
  private static final String[] imperialMassUnits = {"Dram", "Ounce", "Pound"};
  private static final String[] imperialUnits = {"Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup",
      "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  private static final String[] imperialUnitsSpace = {"", "Pinch", "Teaspoon", "Tablespoon",
      "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  private static final String[] metricUnits = {"Gram", "Milliliters"};
  private static final String[] metricUnitsSpace = {"", "Gram", "Milliliters"};
  private static final String[] metricUnitsIndividual = {"", "Individual", "Gram", "Milliliters"};
  private static final String[] imperialUnitsIndividual = {"", "Individual", "Pinch", "Teaspoon",
      "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};

  public static String[] getImperialMassUnits()
  {
    return imperialMassUnits;
  }

  public static String[] getImperialVolumeUnits()
  {
    return imperialVolumeUnits;
  }

  public static String[] getMetricMassUnits()
  {
    return metricMassUnits;
  }

  public static String[] getMetricVolumeUnits()
  {
    return metricVolumeUnits;
  }

  public static String[] getMetricUnits()
  {
    return metricUnits;
  }

  public static String[] getImperialUnits()
  {
    return imperialUnits;
  }

  public static String[] getImperialUnitsIndividual()
  {
    return imperialUnitsIndividual;
  }

  public static String[] getMetricUnitsIndividual()
  {
    return metricUnitsIndividual;
  }

  public static String[] getMetricUnitsSpace()
  {
    return metricUnitsSpace;
  }

  public static String[] getImperialUnitsSpace()
  {
    return imperialUnitsSpace;
  }
}

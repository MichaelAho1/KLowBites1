package utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Units class.
 * 
 * Contains all units, both mass and volume, mentioned in product domain glossary.
 *
 * @author f24team3d
 * @version 10/30/24
 */
public class Units
{
  private static Map<String, String> units;

  /**
   * Default constructor.
   */
  public Units()
  {
    units = new LinkedHashMap<>();

    units.put("Gram", "Mass");
    units.put("Dram", "Mass");
    units.put("Ounce", "Mass");
    units.put("Pound", "Mass");

    units.put("Milliliter", "Volume");
    units.put("Pinch", "Volume");
    units.put("Teaspoon", "Volume");
    units.put("Tablespoon", "Volume");
    units.put("Fluid Ounce", "Volume");
    units.put("Cup", "Volume");
    units.put("Pint", "Volume");
    units.put("Quart", "Volume");
    units.put("Gallon", "Volume");
  }

  /**
   * Return array of all units
   * 
   * @return all units
   */
  public String[] getAllUnits()
  {
    Set<String> keys = units.keySet();
    String[] allUnits = new String[keys.size() + 1];

    allUnits[0] = "";

    int index = 1;
    for (String key : keys)
    {
      allUnits[index++] = key;
    }

    return allUnits;
  }

  /**
   * Return array of all units
   * 
   * @return all units
   */
  public String[] getAllUnitsPlusIndividual()
  {
    Set<String> keys = units.keySet();
    String[] allUnits = new String[keys.size() + 2];

    allUnits[0] = "";
    allUnits[1] = "Individual";

    int index = 2;
    for (String key : keys)
    {
      allUnits[index++] = key;
    }

    return allUnits;
  }

  /**
   * Return array of all units without first being empty
   * 
   * @return all units
   */
  public String[] getAllUnitsNoPadding()
  {
    Set<String> keys = units.keySet();
    String[] allUnits = new String[keys.size() + 1];

    int index = 0;
    for (String key : keys)
    {
      allUnits[index++] = key;
    }

    return allUnits;
  }

  /**
   * Return array of mass units.
   * 
   * @return array of mass units
   */
  public static String[] getMassUnits()
  {
    List<String> massUnits = new ArrayList<>();

    for (Map.Entry<String, String> entry : units.entrySet())
    {
      if ("Mass".equals(entry.getValue()))
      {
        massUnits.add(entry.getKey());
      }
    }

    return massUnits.toArray(new String[0]);
  }

  /**
   * Return map of units.
   */
  public Map<String, String> getUnits()
  {
    return units;
  }

  /**
   * Return array of volume units.
   * 
   * @return array of volume units
   */
  public static String[] getVolumeUnits()
  {
    List<String> volumeUnits = new ArrayList<>();

    for (Map.Entry<String, String> entry : units.entrySet())
    {
      if ("Volume".equals(entry.getValue()))
      {
        volumeUnits.add(entry.getKey());
      }
    }
    return volumeUnits.toArray(new String[0]);
  }

  /**
   * Return whether a unit is for mass or volume.
   * 
   * @param unit
   *          Unit of measurement
   * @return mass or volume
   */
  public String unitMeasure(String unit)
  {
    return units.get(unit);
  }

}

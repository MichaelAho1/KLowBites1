package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import utilities.Units;

/**
 * Test cases for Units class.
 * 
 * @author f24team3d
 * @version 10/30/24
 */

class UnitsTest
{

  @Test
  void test()
  {
    Units units = new Units();

    assertEquals(13, units.getUnits().size());

    String[] allUnits = units.getAllUnits();
    String[] massUnits = units.getMassUnits();
    String[] volumeUnits = units.getVolumeUnits();

    assertEquals(14, allUnits.length);
    assertEquals("Dram", allUnits[2]);

    assertEquals(4, massUnits.length);
    assertEquals("Pound", massUnits[3]);

    assertEquals(9, volumeUnits.length);
    assertEquals("Pint", volumeUnits[6]);

    assertEquals("Volume", units.unitMeasure("Milliliter"));
    assertEquals("Mass", units.unitMeasure("Pound"));
  }

}

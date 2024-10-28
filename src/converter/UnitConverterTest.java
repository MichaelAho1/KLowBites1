package converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitConverterTest
{

  @Test
  void test()
  {
    final MassToVolume converter = new MassToVolume();
    Double num = 9.0;
    String milliliter = "Milliliter";
    String pinch = "Pinch";
    String teaspoon = "Teaspoon";
    String tablespoon = "Tablespoon";
    String fluidOunce = "Fluid Ounce";
    String cup = "Cup";
    String pint = "Pint";
    String quart = "Quart";
    String gallon = "Gallon";
    String gram = "Gram";
    String dram = "Dram";
    String ounce = "Ounce";
    String pound = "Pound";
    assertEquals(3.0, VolumeConverter.teaspoonConverter(tablespoon, 9.0));
    assertEquals(0.01171875, VolumeConverter.teaspoonConverter(gallon, num));
    assertEquals(0.03515625, VolumeConverter.tablespoonConverter(gallon, num));
    assertEquals(0.28125, VolumeConverter.fluidOunceConverter(quart, num));
    assertEquals(432, VolumeConverter.cupConverter(teaspoon, num));
    
  }

}

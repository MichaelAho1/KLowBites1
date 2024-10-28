package converter;

public class MassToVolume
{

  public Double interConverting(final String fromUnit, String toUnit, Double fromAmount)
  {
    Double grams = 0.0;
    double milliliters = 0.0;
    if ("Gram".equals(fromUnit))
    {
      milliliters = fromAmount / 1.04;
      return VolumeConverter.milliliterToFinal(toUnit, milliliters);
    }
    if ("Dram".equals(fromUnit))
    {
      grams = MassConverter.gramConverter("Dram", fromAmount);
      milliliters = grams / 1.04;
      return VolumeConverter.milliliterToFinal(toUnit, milliliters);
    }
    if ("Ounce".equals(fromUnit))
    {
      grams = MassConverter.gramConverter("Ounce", fromAmount);
      milliliters = grams / 1.04;
      return VolumeConverter.milliliterToFinal(toUnit, milliliters);
    }
    if ("Pound".equals(fromUnit))
    {
      grams = MassConverter.gramConverter("Pound", fromAmount);
      milliliters = grams / 1.04;
      return VolumeConverter.milliliterToFinal(toUnit, milliliters);
    }
    if ("Milliliter".equals(fromUnit))
    {
      grams = fromAmount * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Pinch".equals(fromUnit))
    {
      milliliters = VolumeConverter.milliliterConverter("pinches", fromAmount);
      grams = milliliters * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Teaspoon".equals(fromUnit))
    {
      milliliters = VolumeConverter.milliliterConverter("teaspoon", fromAmount);
      grams = milliliters * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Tablespoon".equals(fromUnit))
    {
      milliliters = VolumeConverter.milliliterConverter("Tablespoon", fromAmount);
      grams = milliliters * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Fluid Ounce".equals(fromUnit))
    {
      milliliters = VolumeConverter.milliliterConverter("Fluid Ounce", fromAmount);
      grams = milliliters * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Cup".equals(fromUnit))
    {
      milliliters = VolumeConverter.milliliterConverter("Cup", fromAmount);
      grams = milliliters * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Pint".equals(fromUnit))
    {
      milliliters = VolumeConverter.milliliterConverter("Pint", fromAmount);
      grams = milliliters * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Quart".equals(fromUnit))
    {
      milliliters = VolumeConverter.milliliterConverter("Quart", fromAmount);
      grams = milliliters * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Gallon".equals(fromUnit))
    {
      milliliters = VolumeConverter.milliliterConverter("Gallon", fromAmount);
      grams = milliliters * 1.04;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    return 0.0; // Should not get here
  }
  
}

package converter;

/**
 * Converts a certain mass unit to another volume unit and vice versa.
 *
 * @author f24team3d
 * @version 10/23/24
 */
public class MassToVolume
{

  /**
  /**
   * Converts from mass to volume or vice versa.
   *
   * @param toUnit The type of the to amount.
   * @param fromUnit The type of the from amount.
   * @param density The density of the ingredient.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double interConverting(final String fromUnit, final String toUnit, 
      final Double fromAmount, final Double density)
  {
    Double grams = 0.0;
    double milliliters = 0.0;
    if ("Gram".equals(fromUnit))
    {
      milliliters = fromAmount / density;
    }
    if ("Dram".equals(fromUnit))
    {
      grams = MassConverter.dramConverter("Gram", fromAmount);
      milliliters = grams / density;
    }
    if ("Ounce".equals(fromUnit))
    {
      grams = MassConverter.ounceConverter("Gram", fromAmount);
      milliliters = grams / density;
    }
    if ("Pound".equals(fromUnit))
    {
      grams = MassConverter.poundConverter("Gram", fromAmount);
      milliliters = grams / density;
    }
    if ("Milliliter".equals(fromUnit))
    {
      grams = fromAmount * density;
    }
    if ("Pinch".equals(fromUnit))
    {
      milliliters = VolumeConverter.pinchConverter("Milliliter", fromAmount);
      grams = milliliters * density;
    }
    if ("Teaspoon".equals(fromUnit))
    {
      milliliters = VolumeConverter.teaspoonConverter("Milliliter", fromAmount);
      grams = milliliters * density;
    }
    if ("Tablespoon".equals(fromUnit))
    {
      milliliters = VolumeConverter.tablespoonConverter("Milliliter", fromAmount);
      grams = milliliters * density;
    }
    if ("Fluid Ounce".equals(fromUnit))
    {
      milliliters = VolumeConverter.fluidOunceConverter("Milliliter", fromAmount);
      grams = milliliters * density;
    }
    if ("Cup".equals(fromUnit))
    {
      milliliters = VolumeConverter.cupConverter("Milliliter", fromAmount);
      grams = milliliters * density;
    }
    if ("Pint".equals(fromUnit))
    {
      milliliters = VolumeConverter.pintConverter("Milliliter", fromAmount);
      grams = milliliters * density;
    }
    if ("Quart".equals(fromUnit))
    {
      milliliters = VolumeConverter.quartConverter("Milliliter", fromAmount);
      grams = milliliters * density;
    }
    if ("Gallon".equals(fromUnit))
    {
      milliliters = VolumeConverter.gallonConverter("Milliliter", fromAmount);
      grams = milliliters * density;
    }
    return MassConverter.gramToFinal(toUnit, grams);
  }
  
}

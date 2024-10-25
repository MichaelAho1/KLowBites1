package converter;

public class UnitConverter
{

  private Double convertHelp(Double pinches, String toUnit)
  {
    if ("Milliliter".equals(toUnit))
    {
      return (pinches / 48) * 14.7867648;
    }
    if ("Teaspoon".equals(toUnit))
    {
      return pinches / 16;
    }
    if ("Tablespoon".equals(toUnit))
    {
      return pinches / 48;
    }
    if ("Fluid Ounce".equals(toUnit))
    {
      return pinches / 96;
    }
    if ("Cup".equals(toUnit))
    {
      return pinches / 768;
    }
    if ("Pint".equals(toUnit))
    {
      return pinches / 1536;
    }
    if ("Quart".equals(toUnit))
    {
      return pinches / 3072;
    }
    if ("Gallon".equals(toUnit))
    {
      return pinches / 12288;
    }
    return pinches;
  }
  
  public Double teaspoonConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 16, toUnit);
  }

  public Double tablespoonConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 48, toUnit);
  }

  public Double fluidOunceConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 96, toUnit);
  }

  public Double cupConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 768, toUnit);
  }

  public Double pintConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 1536, toUnit);
  }

  public Double quartConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 3072, toUnit);
  }

  public Double gallonConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 12288, toUnit);
  }

  public Double milliliterConverter(String toUnit, Double fromAmount)
  {
    return convertHelp((fromAmount * 48) / 14.7867648, toUnit);
  }

  public Double pinchConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount, toUnit);
  }

  public Double gramConverter(String toUnit, Double fromAmount)
  {
    if ("Dram".equals(toUnit))
    {
      return (fromAmount / 28.34952) * 16;
    }
    if ("Ounce".equals(toUnit))
    {
      return fromAmount / 28.34952;
    }
    else
    { // Pound
      return (fromAmount / 28.34952) / 16;
    }
  }

  public Double dramConverter(String toUnit, Double fromAmount)
  {
    if ("Gram".equals(toUnit))
    {
      return (fromAmount / 16) * 28.34952;
    }
    if ("Ounce".equals(toUnit))
    {
      return fromAmount / 16;
    }
    else
    { // Pound
      return (fromAmount / 16) / 16;
    }
  }

  public Double ounceConverter(String toUnit, Double fromAmount)
  {
    if ("Gram".equals(toUnit))
    {
      return (fromAmount * 28.34952);
    }
    if ("Dram".equals(toUnit))
    {
      return fromAmount * 16;
    }
    else
    { // Pound
      return fromAmount / 16;
    }
  }

  public Double poundConverter(String toUnit, Double fromAmount)
  {
    if ("Gram".equals(toUnit))
    {
      return (fromAmount * 16) * 28.34952;
    }
    if ("Dram".equals(toUnit))
    {
      return (fromAmount * 16) * 16;
    }
    else
    { // Ounce
      return fromAmount * 16;
    }
  }

  private Double gramToFinal(String toUnit, Double amount)
  {
    if ("Gram".equals(toUnit))
    {
      return amount;
    }
    if ("Dram".equals(toUnit))
    {
      return dramConverter(toUnit, amount);
    }
    if ("Ounce".equals(toUnit))
    {
      return ounceConverter(toUnit, amount);
    }
    if ("Pound".equals(toUnit))
    {
      return poundConverter(toUnit, amount);
    }
    return 0.0; // Should not get here
  }

  private Double milliliterToFinal(String toUnit, Double amount)
  {
    if (toUnit == "Pinch")
    {
      return pinchConverter(toUnit, amount);
    }
    if ("Milliliter".equals(toUnit))
    {
      return amount;
    }
    if ("Teaspoon".equals(toUnit))
    {
      return teaspoonConverter(toUnit, amount);
    }
    if ("Tablespoon".equals(toUnit))
    {
      return tablespoonConverter(toUnit, amount);
    }
    if ("Fluid Ounce".equals(toUnit))
    {
      return fluidOunceConverter(toUnit, amount);
    }
    if ("Cup".equals(toUnit))
    {
      return cupConverter(toUnit, amount);
    }
    if ("Pint".equals(toUnit))
    {
      return pintConverter(toUnit, amount);
    }
    if ("Quart".equals(toUnit))
    {
      return quartConverter(toUnit, amount);
    }
    if ("Gallon".equals(toUnit))
    {
      return gallonConverter(toUnit, amount);
    }
    return 0.0; // Should not get here
  }

  public Double interConverting(final String fromUnit, String toUnit, Double fromAmount)
  {
    Double grams = 0.0;
    double milliliters = 0.0;
    if ("Gram".equals(fromUnit))
    {
      milliliters = fromAmount / 1.04;
      return milliliterToFinal(toUnit, milliliters);
    }
    if ("Dram".equals(fromUnit))
    {
      grams = gramConverter("Dram", fromAmount);
      milliliters = grams / 1.04;
      return milliliterToFinal(toUnit, milliliters);
    }
    if ("Ounce".equals(fromUnit))
    {
      grams = gramConverter("Ounce", fromAmount);
      milliliters = grams / 1.04;
      return milliliterToFinal(toUnit, milliliters);
    }
    if ("Pound".equals(fromUnit))
    {
      grams = gramConverter("Pound", fromAmount);
      milliliters = grams / 1.04;
      return milliliterToFinal(toUnit, milliliters);
    }
    if ("Milliliter".equals(fromUnit))
    {
      grams = fromAmount * 1.04;
      return gramToFinal(toUnit, grams);
    }
    if ("Pinch".equals(fromUnit))
    {
      milliliters = milliliterConverter("pinches", fromAmount);
      grams = milliliters * 1.04;
      return gramToFinal(toUnit, grams);
    }
    if ("Teaspoon".equals(fromUnit))
    {
      milliliters = milliliterConverter("teaspoon", fromAmount);
      grams = milliliters * 1.04;
      return gramToFinal(toUnit, grams);
    }
    if ("Tablespoon".equals(fromUnit))
    {
      milliliters = milliliterConverter("Tablespoon", fromAmount);
      grams = milliliters * 1.04;
      return gramToFinal(toUnit, grams);
    }
    if ("Fluid Ounce".equals(fromUnit))
    {
      milliliters = milliliterConverter("Fluid Ounce", fromAmount);
      grams = milliliters * 1.04;
      return gramToFinal(toUnit, grams);
    }
    if ("Cup".equals(fromUnit))
    {
      milliliters = milliliterConverter("Cup", fromAmount);
      grams = milliliters * 1.04;
      return gramToFinal(toUnit, grams);
    }
    if ("Pint".equals(fromUnit))
    {
      milliliters = milliliterConverter("Pint", fromAmount);
      grams = milliliters * 1.04;
      return gramToFinal(toUnit, grams);
    }
    if ("Quart".equals(fromUnit))
    {
      milliliters = milliliterConverter("Quart", fromAmount);
      grams = milliliters * 1.04;
      return gramToFinal(toUnit, grams);
    }
    if ("Gallon".equals(fromUnit))
    {
      milliliters = milliliterConverter("Gallon", fromAmount);
      grams = milliliters * 1.04;
      return gramToFinal(toUnit, grams);
    }
    return 0.0; // Should not get here
  }
  
}

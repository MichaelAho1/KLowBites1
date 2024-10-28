package converter;

public class VolumeConverter
{
  private static Double convertHelp(Double pinches, String toUnit)
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
  
  public static Double teaspoonConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 16, toUnit);
  }

  public static Double tablespoonConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 48, toUnit);
  }

  public static Double fluidOunceConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 96, toUnit);
  }

  public static Double cupConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 768, toUnit);
  }

  public static Double pintConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 1536, toUnit);
  }

  public static Double quartConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 3072, toUnit);
  }

  public static Double gallonConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount * 12288, toUnit);
  }

  public static Double milliliterConverter(String toUnit, Double fromAmount)
  {
    return convertHelp((fromAmount * 48) / 14.7867648, toUnit);
  }

  public static Double pinchConverter(String toUnit, Double fromAmount)
  {
    return convertHelp(fromAmount, toUnit);
  }
  
  public static Double milliliterToFinal(String toUnit, Double amount)
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
}

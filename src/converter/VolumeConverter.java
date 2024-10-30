package converter;

public class VolumeConverter
{
  public static Double callerHelp(String fromUnit, String toUnit, Double fromAmount) {
    if ("Milliliter".equals(toUnit))
    {
      return milliliterConverter(fromUnit, fromAmount);
    }
    if ("Teaspoon".equals(toUnit))
    {
      return teaspoonConverter(fromUnit, fromAmount);
    }
    if ("Tablespoon".equals(toUnit))
    {
      return tablespoonConverter(fromUnit, fromAmount);
    }
    if ("Fluid Ounce".equals(toUnit))
    {
      return fluidOunceConverter(fromUnit, fromAmount);
    }
    if ("Cup".equals(toUnit))
    {
      return cupConverter(fromUnit, fromAmount);
    }
    if ("Pint".equals(toUnit))
    {
      return pintConverter(fromUnit, fromAmount);
    }
    if ("Quart".equals(toUnit))
    {
      return quartConverter(fromUnit, fromAmount);
    }
    if ("Gallon".equals(toUnit))
    {
      return gallonConverter(fromUnit, fromAmount);
    }
    return pinchConverter(fromUnit, fromAmount);
  }
  
  
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
    if ("Milliliter".equals(toUnit))
    {
      return amount;
    }
    return milliliterConverter(toUnit, amount);
  }
}

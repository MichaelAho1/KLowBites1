package converter;

public class MassConverter
{
  public static Double gramConverter(String toUnit, Double fromAmount)
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

  public static Double dramConverter(String toUnit, Double fromAmount)
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

  public static Double ounceConverter(String toUnit, Double fromAmount)
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

  public static Double poundConverter(String toUnit, Double fromAmount)
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

  public static Double gramToFinal(String toUnit, Double amount)
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

}

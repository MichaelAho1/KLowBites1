package converter;

public class MassConverter
{
  public static Double callerHelp(String fromUnit, String toUnit, Double fromAmount) {
    if ("Gram".equals(fromUnit))
    {
      return gramConverter(toUnit, fromAmount);
    }
    if ("Dram".equals(fromUnit))
    {
      return dramConverter(toUnit, fromAmount);
    }
    if ("Ounce".equals(fromUnit))
    {
      return ounceConverter(toUnit, fromAmount);
    }
    if ("Pound".equals(fromUnit))
    {
      return poundConverter(toUnit, fromAmount);
    }
    return 0.0; //Should not get here
  }
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
    return gramConverter(toUnit, amount);
  }

}

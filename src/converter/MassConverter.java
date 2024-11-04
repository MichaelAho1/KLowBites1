package converter;

/**
 * Converts a certain mass unit to another mass unit.
 *
 * @author f24team3d
 * @version 10/23/24
 */
public class MassConverter
{
  
  /**
   * Calls a certain converter based on what unit it is given.
   *
   * @param toUnit The type of the to amount.
   * @param fromUnit The type of the from amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double callerHelp(final String fromUnit, final String toUnit, 
      final Double fromAmount) 
  {
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
    else
    {
      return poundConverter(toUnit, fromAmount);
    }
  }
  
  /**
   * Calculates gram to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double gramConverter(final String toUnit, final Double fromAmount)
  {
    if ("Dram".equals(toUnit))
    {
      return (fromAmount / 28.34952) * 16;
    }
    if ("Ounce".equals(toUnit))
    {
      return fromAmount / 28.34952;
    }
    if ("Pound".equals(toUnit))
    { 
      return (fromAmount / 28.34952) / 16;
    }
    else
    {
      return fromAmount;
    }
  }
  
  /**
   * Calculates dram to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double dramConverter(final String toUnit, final Double fromAmount)
  {
    if ("Gram".equals(toUnit))
    {
      return (fromAmount / 16) * 28.34952;
    }
    if ("Ounce".equals(toUnit))
    {
      return fromAmount / 16;
    }
    if ("Pound".equals(toUnit))
    {
      return (fromAmount / 16) / 16;
    }
    else
    { 
      return fromAmount;
    }
  }

  /**
   * Calculates ounce to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double ounceConverter(final String toUnit, final Double fromAmount)
  {
    if ("Gram".equals(toUnit))
    {
      return (fromAmount * 28.34952);
    }
    if ("Dram".equals(toUnit))
    {
      return fromAmount * 16;
    }
    if("Pound".equals(toUnit))
    {
      return fromAmount / 16;
    }
    else
    { // Pound
      return fromAmount;
    }
  }
  
  /**
   * Calculates pound to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double poundConverter(final String toUnit, final Double fromAmount)
  {
    if ("Gram".equals(toUnit))
    {
      return (fromAmount * 16) * 28.34952;
    }
    if ("Dram".equals(toUnit))
    {
      return (fromAmount * 16) * 16;
    }
    if("Ounce".equals(toUnit))
    {
      return fromAmount * 16;
    }
    else
    { // Ounce
      return fromAmount;
    }
  }
  
  /**
   * Calculates gram to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param amount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double gramToFinal(final String toUnit, final Double amount)
  {
    if ("Gram".equals(toUnit))
    {
      return amount;
    }
    return gramConverter(toUnit, amount);
  }

}

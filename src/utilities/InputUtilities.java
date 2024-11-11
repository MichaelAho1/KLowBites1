package utilities;

public class InputUtilities
{
  public static boolean isInt(String str)
  {
    return str.matches("[0-9]+");
  }

  public static boolean isDouble(String str)
  {
    return str.matches("[0-9]+(\\.[0-9]+)?");
  }

  public static boolean isPositiveInt(String str)
  {
    if (isInt(str))
    {
      return Integer.parseInt(str) > 0;
    }
    else
    {
      return false;
    }
  }

  public static boolean isPositiveDouble(String str)
  {
    if (isDouble(str))
    {
      return Double.parseDouble(str) > 0;
    }
    else
    {
      return false;
    }
  }

  public static boolean isAlphaNumeric(String str)
  {
    return str.matches("[a-zA-Z0-9 ]+");
  }

  public static boolean isAlphabetical(String str)
  {
    return str.matches("[a-zA-Z ]+");
  }
}

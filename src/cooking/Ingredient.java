package cooking;

public class Ingredient
{
  private double amount = 0.0;
  private String unit = "";
  private String details = "";
  private String name = "";
  
  public Ingredient(double amount, String unit, String details, String name)
  {
    this.amount = amount;
    this.unit = unit;
    this.details = details;
    this.name = name;
  }
  public double getAmount()
  {
    return amount;
  }
  public String getUnit()
  {
    return unit;
  } 
  public String getDetails()
  {
    return details;
  }
  public String getName()
  {
    return name;
  }
}

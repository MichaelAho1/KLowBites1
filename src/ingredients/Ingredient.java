package ingredients;

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
}

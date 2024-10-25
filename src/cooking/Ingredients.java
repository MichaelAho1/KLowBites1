package cooking;

import javax.swing.JLabel;

public class Ingredients
{
  private double amount = 0.0;
  private String units = "";
  private String details = "";
  private String name = "";

  public Ingredients(double amount, String unit, String details, String name)
  {
    this.amount = amount;
    this.units = unit;
    this.details = details;
    this.name = name;
  }

  public double getAmount()
  {
    return amount;
  }

  public String getUnit()
  {
    return units;
  }

  public String getDetails()
  {
    return details;
  }

  public String getName()
  {
    return name;
  }

  @Override
  public String toString()
  {
    return amount + " " + units + " " + details + " " + name;
  }

  public JLabel add()
  {
    return new JLabel();
  }
}

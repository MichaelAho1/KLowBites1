package cooking;

import javax.swing.JLabel;

/**
 * Ingredients Class, a RecipeElement.
 *
 *  @author f24team3d
 *  @version 10/29/24
 */
public class Ingredients implements RecipeElement, StepSource
{
  private double amount = 0.0;
  private String units = "";
  private String details = "";
  private String name = "";
  private RecipeElementType type = RecipeElementType.INGREDIENT;

  /**
   * Default constructor.
   */
public Ingredients()
  {
    this.amount = 0.0;
    this.units = "";
    this.details = "";
    this.name = "";
  }

  /**
   * Constructor with parameters.
   *
   * @param amount
   * @param unit
   * @param details
   * @param name
   */
  public Ingredients(double amount, String unit, String details, String name)
  {
    this.amount = amount;
    this.units = unit;
    this.details = details;
    this.name = name;
  }

  /**
   * Get the amount of the ingredient.
   *
   * @return the amount of the ingredient
   */
  public double getAmount()
  {
    return amount;
  }

  public RecipeElementType getType()
  {
    return type;
  }

  /**
   * Set the amount of the ingredient.
   *
   * @param amount
   */
  public void setAmount(double amount)
  {
    this.amount = amount;
  }

  /**
   * Get the unit of the ingredient.
   *
   * @return the unit of the ingredient
   */
  public String getUnit()
  {
    return units;
  }

  /**
   * Set the unit of the ingredient.
   *
   * @param unit
   */
  public void setUnit(String unit)
  {
    this.units = unit;
  }

  /**
   * Get the details of the ingredient.
   *
   * @return the details of the ingredient
   */
  public String getDetails()
  {
    return details;
  }

  /**
   * Set the details of the ingredient.
   *
   * @param details
   */
  public void setDetails(String details)
  {
    this.details = details;
  }

  /**
   * Get the name of the ingredient.
   *
   * @return the name of the ingredient
   */
  public String getName()
  {
    return name;
  }

  /**
   * Set the name of the ingredient.
   *
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  @Override
  public String toString()
  {
    return amount + " " + units + " of " + details + " " + name;
  }

  public JLabel add()
  {
    return new JLabel();
  }
}

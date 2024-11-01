package cooking;

/**
 * Ingredients Class, a RecipeElement.
 *
 *  @author f24team3d
 *  @version 10/29/24
 */
public class Steps implements RecipeElement
{
  private String action;
  private Ingredients source;
  private Utensils destination;
  private String details;

  /**
   * Default constructor.
   */
  public Steps()
  {
    this.action = "";
    this.source = null;
    this.destination = null;
    this.details = "";
  }

  /**
   * Constructor with parameters.
   *
   * @param action
   * @param source
   * @param destination
   * @param details
   */
  public Steps(String action, Ingredients source, Utensils destination, String details)
  {
    this.action = action;
    this.source = source;
    this.destination = destination;
    this.details = details;
  }

  /**
   * Get the action of the step.
   *
   * @return the action of the step
   */
  public String getAction()
  {
    return action;
  }

  /**
   * Set the action of the step.
   *
   * @param action
   */
  public void setAction(String action)
  {
    this.action = action;
  }

  /**
   * Get the source of the step.
   *
   * @return the source of the step
   */
  public Ingredients getSource()
  {
    return source;
  }

  /**
   * Set the source of the step.
   *
   * @param source
   */
  public void setSource(Ingredients source)
  {
    this.source = source;
  }

  /**
   * Get the destination of the step.
   *
   * @return the destination of the step
   */
  public Utensils getDestination()
  {
    return destination;
  }

  /**
   * Set the destination of the step.
   *
   * @param destination
   */
  public void setDestination(Utensils destination)
  {
    this.destination = destination;
  }

  /**
   * Get the details of the step.
   *
   * @return the details of the step
   */
  public String getDetails()
  {
    return details;
  }

  /**
   * Set the details of the step.
   *
   * @param details
   */
  public void setDetails(String details)
  {
    this.details = details;
  }

  @Override
  public String toString()
  {
    return action + " " + source + " in " + destination + " (" + details + ")";
  }
}
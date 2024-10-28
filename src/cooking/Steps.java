package cooking;

/**
 * KILowBites controller class. Handles the actions of the main menu items.
 *
 * @author f24team3d
 * @version 10/24/24
 */
public class Steps
{
  private String action;
  private Ingredients source;
  private Utensils destination;
  private String details;

  public Steps(String action, Ingredients source, Utensils destination, String details)
  {
    this.action = action;
    this.source = source;
    this.destination = destination;
    this.details = details;
  }



  public String getAction()
  {
    return action;
  }

  public Ingredients getSource()
  {
    return source;
  }

  public Utensils getDestination()
  {
    return destination;
  }

  public String getDetails()
  {
    return details;
  }

  @Override
  public String toString() {
      return action + " " + source + " in " + destination + " (" + details + ")";
  }
}
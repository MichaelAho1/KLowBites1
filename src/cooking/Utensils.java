package cooking;

public class Utensils
{
  private String name;
  private String details;
  
  public Utensils(String name, String details) {
    this.name = name;
    this.details = details;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDetails() {
    return details;
  }
  
  @Override
  public String toString() {
    return name + "(" + details + ")";
  }
}

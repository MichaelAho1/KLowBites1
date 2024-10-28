package calculation;

import java.util.HashMap;
import java.util.Map;

/**
 * Foods class.
 * 
 * Contains all foods mentioned in product domain glossary and their calories.
 *
 * @author f24team3d
 * @version 10/25/24
 */
public class Foods
{
  // string -> name of food
  // double[2]
  // double[0] -> cal/100g
  // double[1] -> g/ml
  private Map<String, double[]> foods;

  /**
   * Default constructor.
   */
  public Foods()
  {
    foods = new HashMap<>();

    foods.put("Alcohol", new double[] {275, 0.79});
    foods.put("Almond", new double[] {601, 0.46});
    foods.put("American cheese", new double[] {440, 0.34});
    foods.put("Apple", new double[] {44, 0.56});
    foods.put("Apple juice", new double[] {48, 1.04});

    foods.put("Banana", new double[] {65, 0.56});
    foods.put("Bean", new double[] {130, 0.77});
    foods.put("Beef", new double[] {280, 1.05});
    foods.put("Blackberry", new double[] {25, 0.53});
    foods.put("Black pepper", new double[] {255, 1.01});
    foods.put("Bread", new double[] {240, 0.42});
    foods.put("Broccoli", new double[] {32, 0.37});
    foods.put("Brown sugar", new double[] {380, 1.5});
    foods.put("Butter", new double[] {750, 0.91});

    foods.put("Cabbage", new double[] {28, 0.36});
    foods.put("Carrot", new double[] {41, 0.64});
    foods.put("Cashew", new double[] {553, 0.5});
    foods.put("Cauliflower", new double[] {25, 0.27});
    foods.put("Celery", new double[] {14, 0.61});
    foods.put("Cheddar cheese", new double[] {440, 0.34});
    foods.put("Cherry", new double[] {50, 1.02});
    foods.put("Chicken", new double[] {200, 1.04});
    foods.put("Chocolate", new double[] {500, 1.33});
    foods.put("Cinnamon", new double[] {261, 0.45});
    foods.put("Cod", new double[] {100, 0.58});
    foods.put("Corn", new double[] {130, 0.72});
    foods.put("Cornflake", new double[] {370, 0.12});
    foods.put("Cottage cheese", new double[] {98, 0.96});
    foods.put("Crab", new double[] {110, 0.61});
    foods.put("Creme de cacao", new double[] {275, 0.79});
    foods.put("Cucumber", new double[] {10, 0.67});

    foods.put("Egg", new double[] {150, 0.6});

    foods.put("Flour", new double[] {364, 0.45});

    foods.put("Garlic", new double[] {111, 0.32});
    foods.put("Grapefruit", new double[] {32, 0.33});
    foods.put("Grape", new double[] {62, 0.37});
    foods.put("Grapefruit", new double[] {60, 1.04});
    foods.put("Grape juice", new double[] {60, 0.53});
    foods.put("Green bean", new double[] {31, 0.53});

  }
}

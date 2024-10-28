package utilities;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
    // TreeMap is used to maintain alphabetical order
    foods = new TreeMap<>();

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

    foods.put("Haddock", new double[] {110, 0.58});
    foods.put("Ham", new double[] {240, 1.4});
    foods.put("Honey", new double[] {280, 1.5});

    foods.put("Ice cream", new double[] {180, 0.55});

    foods.put("Kidney bean", new double[] {333, 0.79});

    foods.put("Lamb", new double[] {200, 1.3});
    foods.put("Lemon", new double[] {29, 0.77});
    foods.put("Lentil", new double[] {116, 0.85});
    foods.put("Lettuce", new double[] {15, 0.06});

    foods.put("Macaroni", new double[] {371, 1.31});
    foods.put("Milk", new double[] {70, 1.04});
    foods.put("Mushroom", new double[] {15, 1.17});

    foods.put("Oil", new double[] {900, 0.88});
    foods.put("Olive", new double[] {80, 0.65});
    foods.put("Onion", new double[] {22, 0.74});
    foods.put("Orange", new double[] {30, 0.77});

    foods.put("Paprika", new double[] {282, 0.46});
    foods.put("Pasta", new double[] {371, 1.31});
    foods.put("Peach", new double[] {30, 0.61});
    foods.put("Peanut", new double[] {567, 0.53});
    foods.put("Pear", new double[] {16, 0.61});
    foods.put("Peas", new double[] {148, 0.73});
    foods.put("Pepper", new double[] {20, 0.51});
    foods.put("Pineapple", new double[] {40, 0.54});
    foods.put("Plum", new double[] {39, 0.58});
    foods.put("Pork", new double[] {290, 0.7});

    foods.put("Rum", new double[] {275, 0.79});

    foods.put("Salmon", new double[] {180, 0.58});
    foods.put("Salt", new double[] {0, 1.38});
    foods.put("Saltine crackers", new double[] {421, 0.43});
    foods.put("Spaghetti", new double[] {371, 1.31});
    foods.put("Spinich", new double[] {8, 0.08});
    foods.put("Strawberries", new double[] {30, 0.58});
    foods.put("Sugar", new double[] {400, 0.95});
    foods.put("Sweet potato", new double[] {86, 0.65});
    foods.put("Syrup", new double[] {260, 1.38});

    foods.put("Thyme", new double[] {101, 0.46});
    foods.put("Tomato", new double[] {20, 0.67});

    foods.put("Wine", new double[] {83, 0.99});
  }

  /**
   * Return foods map.
   * 
   * @return foods map
   */
  public Map<String, double[]> getFoods()
  {
    return foods;
  }

  /**
   * Retrieve array containing all food names.
   * 
   * @return Array of food names
   */
  public String[] getFoodNames()
  {
    Set<String> keys = foods.keySet();
    String[] foodNames = new String[keys.size() + 1]; // Create an array with an additional space
                                                      // for the empty entry
    foodNames[0] = ""; // Set the first element to an empty string

    int index = 1; // Start adding food names from the second position
    for (String key : keys)
    {
      foodNames[index++] = key;
    }

    return foodNames;
  }

  /**
   * Return size of map.
   * 
   * @return size of map
   */
  public int getSize()
  {
    return foods.size();
  }
}

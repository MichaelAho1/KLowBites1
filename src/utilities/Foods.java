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
  public static Map<String, double[]> foods;

  /**
   * Default constructor.
   */
  public Foods()
  {
    // TreeMap is used to maintain alphabetical order
    foods = new TreeMap<>();

    foods.put("alcohol", new double[] {275, 0.79});
    foods.put("almond", new double[] {601, 0.46});
    foods.put("american cheese", new double[] {440, 0.34});
    foods.put("apple", new double[] {44, 0.56});
    foods.put("apple juice", new double[] {48, 1.04});

    foods.put("banana", new double[] {65, 0.56});
    foods.put("bean", new double[] {130, 0.77});
    foods.put("beef", new double[] {280, 1.05});
    foods.put("blackberry", new double[] {25, 0.53});
    foods.put("black pepper", new double[] {255, 1.01});
    foods.put("bread", new double[] {240, 0.42});
    foods.put("broccoli", new double[] {32, 0.37});
    foods.put("brown sugar", new double[] {380, 1.5});
    foods.put("butter", new double[] {750, 0.91});

    foods.put("cabbage", new double[] {28, 0.36});
    foods.put("carrot", new double[] {41, 0.64});
    foods.put("cashew", new double[] {553, 0.5});
    foods.put("cauliflower", new double[] {25, 0.27});
    foods.put("celery", new double[] {14, 0.61});
    foods.put("cheddar cheese", new double[] {440, 0.34});
    foods.put("cherry", new double[] {50, 1.02});
    foods.put("chicken", new double[] {200, 1.04});
    foods.put("chocolate", new double[] {500, 1.33});
    foods.put("cinnamon", new double[] {261, 0.45});
    foods.put("cod", new double[] {100, 0.58});
    foods.put("corn", new double[] {130, 0.72});
    foods.put("cornflake", new double[] {370, 0.12});
    foods.put("cottage cheese", new double[] {98, 0.96});
    foods.put("crab", new double[] {110, 0.61});
    foods.put("creme de cacao", new double[] {275, 0.79});
    foods.put("cucumber", new double[] {10, 0.67});

    foods.put("egg", new double[] {150, 0.6});

    foods.put("flour", new double[] {364, 0.45});

    foods.put("garlic", new double[] {111, 0.32});
    foods.put("grapefruit", new double[] {32, 0.33});
    foods.put("grape", new double[] {62, 0.37});
    foods.put("grapefruit", new double[] {60, 1.04});
    foods.put("grape juice", new double[] {60, 0.53});
    foods.put("green bean", new double[] {31, 0.53});

    foods.put("haddock", new double[] {110, 0.58});
    foods.put("ham", new double[] {240, 1.4});
    foods.put("honey", new double[] {280, 1.5});

    foods.put("ice cream", new double[] {180, 0.55});

    foods.put("kidney bean", new double[] {333, 0.79});

    foods.put("lamb", new double[] {200, 1.3});
    foods.put("lemon", new double[] {29, 0.77});
    foods.put("lentil", new double[] {116, 0.85});
    foods.put("lettuce", new double[] {15, 0.06});

    foods.put("macaroni", new double[] {371, 1.31});
    foods.put("milk", new double[] {70, 1.04});
    foods.put("mushroom", new double[] {15, 1.17});

    foods.put("oil", new double[] {900, 0.88});
    foods.put("olive", new double[] {80, 0.65});
    foods.put("onion", new double[] {22, 0.74});
    foods.put("orange", new double[] {30, 0.77});

    foods.put("paprika", new double[] {282, 0.46});
    foods.put("pasta", new double[] {371, 1.31});
    foods.put("peach", new double[] {30, 0.61});
    foods.put("peanut", new double[] {567, 0.53});
    foods.put("pear", new double[] {16, 0.61});
    foods.put("peas", new double[] {148, 0.73});
    foods.put("pepper", new double[] {20, 0.51});
    foods.put("pineapple", new double[] {40, 0.54});
    foods.put("plum", new double[] {39, 0.58});
    foods.put("pork", new double[] {290, 0.7});

    foods.put("rum", new double[] {275, 0.79});

    foods.put("salmon", new double[] {180, 0.58});
    foods.put("salt", new double[] {0, 1.38});
    foods.put("saltine crackers", new double[] {421, 0.43});
    foods.put("spaghetti", new double[] {371, 1.31});
    foods.put("spinich", new double[] {8, 0.08});
    foods.put("strawberries", new double[] {30, 0.58});
    foods.put("sugar", new double[] {400, 0.95});
    foods.put("sweet potato", new double[] {86, 0.65});
    foods.put("syrup", new double[] {260, 1.38});

    foods.put("thyme", new double[] {101, 0.46});
    foods.put("tomato", new double[] {20, 0.67});

    foods.put("wine", new double[] {83, 0.99});
  }

  public double getDensity(String ingredient)
  {
    Map<String, Double> densities = new TreeMap<>();

    for (String key : foods.keySet())
    {
      densities.put(key, foods.get(key)[1]);
    }

    return densities.get(ingredient);
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
    String[] foodNames = new String[keys.size() + 1];

    // setting first index to blank for GUI purposes
    foodNames[0] = "";

    int index = 1;
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

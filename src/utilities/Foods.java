package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
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
    foods = loadFoods();
  }

  public boolean addFoods(String name, double[] values)
  {
    if (foods.containsKey(name))
    {
      return false;
    }
    else
    {
      foods.put(name, values);
      FileUtilities.saveFoods();
      return true;
    }
  }

  /**
   * 
   * @return
   */
  private Map<String, double[]> defaultFoods()
  {
    // TreeMap is used to maintain alphabetical order
    Map<String, double[]> defaultFoods = new TreeMap<>();

    defaultFoods.put("alcohol", new double[] {275, 0.79});
    defaultFoods.put("almond", new double[] {601, 0.46});
    defaultFoods.put("american cheese", new double[] {440, 0.34});
    defaultFoods.put("apple", new double[] {44, 0.56});
    defaultFoods.put("apple juice", new double[] {48, 1.04});

    defaultFoods.put("banana", new double[] {65, 0.56});
    defaultFoods.put("bean", new double[] {130, 0.77});
    defaultFoods.put("beef", new double[] {280, 1.05});
    defaultFoods.put("blackberry", new double[] {25, 0.53});
    defaultFoods.put("black pepper", new double[] {255, 1.01});
    defaultFoods.put("bread", new double[] {240, 0.42});
    defaultFoods.put("broccoli", new double[] {32, 0.37});
    defaultFoods.put("brown sugar", new double[] {380, 1.5});
    defaultFoods.put("butter", new double[] {750, 0.91});

    defaultFoods.put("cabbage", new double[] {28, 0.36});
    defaultFoods.put("carrot", new double[] {41, 0.64});
    defaultFoods.put("cashew", new double[] {553, 0.5});
    defaultFoods.put("cauliflower", new double[] {25, 0.27});
    defaultFoods.put("celery", new double[] {14, 0.61});
    defaultFoods.put("cheddar cheese", new double[] {440, 0.34});
    defaultFoods.put("cherry", new double[] {50, 1.02});
    defaultFoods.put("chicken", new double[] {200, 1.04});
    defaultFoods.put("chocolate", new double[] {500, 1.33});
    defaultFoods.put("cinnamon", new double[] {261, 0.45});
    defaultFoods.put("cod", new double[] {100, 0.58});
    defaultFoods.put("corn", new double[] {130, 0.72});
    defaultFoods.put("cornflake", new double[] {370, 0.12});
    defaultFoods.put("cottage cheese", new double[] {98, 0.96});
    defaultFoods.put("crab", new double[] {110, 0.61});
    defaultFoods.put("creme de cacao", new double[] {275, 0.79});
    defaultFoods.put("cucumber", new double[] {10, 0.67});

    defaultFoods.put("egg", new double[] {150, 0.6});

    defaultFoods.put("flour", new double[] {364, 0.45});

    defaultFoods.put("garlic", new double[] {111, 0.32});
    defaultFoods.put("grapefruit", new double[] {32, 0.33});
    defaultFoods.put("grape", new double[] {62, 0.37});
    defaultFoods.put("grape juice", new double[] {60, 0.53});
    defaultFoods.put("green bean", new double[] {31, 0.53});

    defaultFoods.put("haddock", new double[] {110, 0.58});
    defaultFoods.put("ham", new double[] {240, 1.4});
    defaultFoods.put("honey", new double[] {280, 1.5});

    defaultFoods.put("ice cream", new double[] {180, 0.55});

    defaultFoods.put("kidney bean", new double[] {333, 0.79});

    defaultFoods.put("lamb", new double[] {200, 1.3});
    defaultFoods.put("lemon", new double[] {29, 0.77});
    defaultFoods.put("lentil", new double[] {116, 0.85});
    defaultFoods.put("lettuce", new double[] {15, 0.06});

    defaultFoods.put("macaroni", new double[] {371, 1.31});
    defaultFoods.put("milk", new double[] {70, 1.04});
    defaultFoods.put("mushroom", new double[] {15, 1.17});

    defaultFoods.put("oil", new double[] {900, 0.88});
    defaultFoods.put("olive", new double[] {80, 0.65});
    defaultFoods.put("onion", new double[] {22, 0.74});
    defaultFoods.put("orange", new double[] {30, 0.77});

    defaultFoods.put("paprika", new double[] {282, 0.46});
    defaultFoods.put("pasta", new double[] {371, 1.31});
    defaultFoods.put("peach", new double[] {30, 0.61});
    defaultFoods.put("peanut", new double[] {567, 0.53});
    defaultFoods.put("pear", new double[] {16, 0.61});
    defaultFoods.put("peas", new double[] {148, 0.73});
    defaultFoods.put("pepper", new double[] {20, 0.51});
    defaultFoods.put("pineapple", new double[] {40, 0.54});
    defaultFoods.put("plum", new double[] {39, 0.58});
    defaultFoods.put("pork", new double[] {290, 0.7});

    defaultFoods.put("rum", new double[] {275, 0.79});

    defaultFoods.put("salmon", new double[] {180, 0.58});
    defaultFoods.put("salt", new double[] {0, 1.38});
    defaultFoods.put("saltine crackers", new double[] {421, 0.43});
    defaultFoods.put("spaghetti", new double[] {371, 1.31});
    defaultFoods.put("spinich", new double[] {8, 0.08});
    defaultFoods.put("strawberries", new double[] {30, 0.58});
    defaultFoods.put("sugar", new double[] {400, 0.95});
    defaultFoods.put("sweet potato", new double[] {86, 0.65});
    defaultFoods.put("syrup", new double[] {260, 1.38});

    defaultFoods.put("thyme", new double[] {101, 0.46});
    defaultFoods.put("tomato", new double[] {20, 0.67});

    defaultFoods.put("wine", new double[] {83, 0.99});

    return defaultFoods;
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

  /**
   * 
   * @return
   */
  private Map<String, double[]> loadFoods()
  {
    // Step 1: Define the file in the current working directory
    File file = new File(Paths.get("").toAbsolutePath().toString(), "Foods.mel");

    // Step 2: Check if the file exists, and try to load it if it does
    if (file.exists())
    {
      try (FileInputStream fileIn = new FileInputStream(file);
          ObjectInputStream in = new ObjectInputStream(fileIn))
      {
        return (Map<String, double[]>) in.readObject();
      }
      catch (IOException | ClassNotFoundException e)
      {
        System.err.println("Error loading Foods.mel, using default foods: " + e.getMessage());
        e.printStackTrace();
      }
    }

    // Step 3: Return the default set if the file doesn't exist or loading fails
    return defaultFoods();
  }
}

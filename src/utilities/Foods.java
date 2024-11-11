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

  private static final String ALCOHOL = "alcohol";
  private static final String ALMOND = "almond";
  private static final String AMERICAN_CHEESE = "american cheese";
  private static final String APPLE = "apple";
  private static final String APPLE_JUICE = "apple juice";
  
  private static final String BANANA = "banana";
  private static final String BEAN = "bean";
  private static final String BEEF = "beef";
  private static final String BLACKBERRY = "blackberry";
  private static final String BLACK_PEPPER = "black pepper";
  private static final String BREAD = "bread";
  private static final String BROCCOLI = "broccoli";
  private static final String BROWN_SUGAR = "brown sugar";
  private static final String BUTTER = "butter";
  
  private static final String CABBAGE = "cabbage";
  private static final String CARROT = "carrot";
  private static final String CASHEW = "cashew";
  private static final String CAULIFLOWER = "cauliflower";
  private static final String CELERY = "celery";
  private static final String CHEDDAR_CHEESE = "cheddar cheese";
  private static final String CHERRY = "cherry";
  private static final String CHICKEN = "chicken";
  private static final String CHOCOLATE = "chocolate";
  private static final String CINNAMON = "cinnamon";
  private static final String COD = "cod";
  private static final String CORN = "corn";
  private static final String CORNFLAKE = "cornflake";
  private static final String COTTAGE_CHEESE = "cottage cheese";
  private static final String CRAB = "crab";
  private static final String CREME_DE_CACAO = "creme de cacao";
  private static final String CUCUMBER = "cucumber";
  
  private static final String EGG = "egg";
  
  private static final String FLOUR = "flour";
  
  private static final String GARLIC = "garlic";
  private static final String GRAPEFRUIT = "grapefruit";
  private static final String GRAPE = "grape";
  private static final String GRAPE_JUICE = "grape juice";
  private static final String GREEN_BEAN = "green bean";
  
  private static final String HADDOCK = "haddock";
  private static final String HAM = "ham";
  private static final String HONEY = "honey";
  
  private static final String ICE_CREAM = "ice cream";
  
  private static final String KIDNEY_BEAN = "kidney bean";

  private static final String LAMB = "lamb";
  private static final String LEMON = "lemon";
  private static final String LENTIL = "lentil";
  private static final String LETTUCE = "lettuce";
  
  private static final String MACARONI = "macaroni";
  private static final String MILK = "milk";
  private static final String MUSHROOOM = "mushroom";
  
  private static final String OIL = "oil";
  private static final String OLIVE = "olive";
  private static final String ONION = "onion";
  private static final String ORANGE = "orange";
  
  private static final String PAPRIKA = "paprika";
  private static final String PASTA = "pasta";
  private static final String PEACH = "peach";
  private static final String PEANUT = "peanut";
  private static final String PEAR = "pear";
  private static final String PEAS = "peas";
  private static final String PEPPER = "pepper";
  private static final String PINEAPPLE = "pineapple";
  private static final String PLUM = "plum";
  private static final String PORK = "pork";
  
  private static final String RUM = "rum";
  
  private static final String SALMON = "salmon";
  private static final String SALT = "salt";
  private static final String SALTINE_CRACKERS = "saltine crackers";
  private static final String SPAGHETTI = "spaghetti";
  private static final String SPINICH = "spinich";
  private static final String STRAWBERRIES = "strawberries";
  private static final String SUGAR = "sugar";
  private static final String SWEET_POTATO = "sweet potato";
  private static final String SYRUP = "syrup";
  
  private static final String THYME = "thyme"; 
  private static final String TOMATO = "tomato";

  private static final String WINE = "wine";
  
  /**
   * Default constructor.
   */
  public Foods()
  {
    // TreeMap is used to maintain alphabetical order
    foods = new TreeMap<>();

    foods.put(ALCOHOL, new double[] {275, 0.79});
    foods.put(ALMOND, new double[] {601, 0.46});
    foods.put(AMERICAN_CHEESE, new double[] {440, 0.34});
    foods.put(APPLE, new double[] {44, 0.56});
    foods.put(APPLE_JUICE, new double[] {48, 1.04});

    foods.put(BANANA, new double[] {65, 0.56});
    foods.put(BEAN, new double[] {130, 0.77});
    foods.put(BEEF, new double[] {280, 1.05});
    foods.put(BLACKBERRY, new double[] {25, 0.53});
    foods.put(BLACK_PEPPER, new double[] {255, 1.01});
    foods.put(BREAD, new double[] {240, 0.42});
    foods.put(BROCCOLI, new double[] {32, 0.37});
    foods.put(BROWN_SUGAR, new double[] {380, 1.5});
    foods.put(BUTTER, new double[] {750, 0.91});

    foods.put(CABBAGE, new double[] {28, 0.36});
    foods.put(CARROT, new double[] {41, 0.64});
    foods.put(CASHEW, new double[] {553, 0.5});
    foods.put(CAULIFLOWER, new double[] {25, 0.27});
    foods.put(CELERY, new double[] {14, 0.61});
    foods.put(CHEDDAR_CHEESE, new double[] {440, 0.34});
    foods.put(CHERRY, new double[] {50, 1.02});
    foods.put(CHICKEN, new double[] {200, 1.04});
    foods.put(CHOCOLATE, new double[] {500, 1.33});
    foods.put(CINNAMON, new double[] {261, 0.45});
    foods.put(COD, new double[] {100, 0.58});
    foods.put(CORN, new double[] {130, 0.72});
    foods.put(CORNFLAKE, new double[] {370, 0.12});
    foods.put(COTTAGE_CHEESE, new double[] {98, 0.96});
    foods.put(CRAB, new double[] {110, 0.61});
    foods.put(CREME_DE_CACAO, new double[] {275, 0.79});
    foods.put(CUCUMBER, new double[] {10, 0.67});

    foods.put(EGG, new double[] {150, 0.6});

    foods.put(FLOUR, new double[] {364, 0.45});

    foods.put(GARLIC, new double[] {111, 0.32});
    foods.put(GRAPEFRUIT, new double[] {32, 0.33});
    foods.put(GRAPE, new double[] {62, 0.37});
    foods.put(GRAPE_JUICE, new double[] {60, 1.04});
    foods.put(GREEN_BEAN, new double[] {31, 0.53});

    foods.put("garlic", new double[] {111, 0.32});
    foods.put("grapefruit", new double[] {32, 0.33});
    foods.put("grape", new double[] {62, 0.37});
    foods.put("grape juice", new double[] {60, 0.53});
    foods.put("green bean", new double[] {31, 0.53});

    foods.put(HADDOCK, new double[] {110, 0.58});
    foods.put(HAM, new double[] {240, 1.4});
    foods.put(HONEY, new double[] {280, 1.5});

    foods.put(ICE_CREAM, new double[] {180, 0.55});

    foods.put(KIDNEY_BEAN, new double[] {333, 0.79});

    foods.put(LAMB, new double[] {200, 1.3});
    foods.put(LEMON, new double[] {29, 0.77});
    foods.put(LENTIL, new double[] {116, 0.85});
    foods.put(LETTUCE, new double[] {15, 0.06});

    foods.put(MACARONI, new double[] {371, 1.31});
    foods.put(MILK, new double[] {70, 1.04});
    foods.put(MUSHROOOM, new double[] {15, 1.17});

    foods.put(OIL, new double[] {900, 0.88});
    foods.put(OLIVE, new double[] {80, 0.65});
    foods.put(ONION, new double[] {22, 0.74});
    foods.put(ORANGE, new double[] {30, 0.77});

    foods.put(PAPRIKA, new double[] {282, 0.46});
    foods.put(PASTA, new double[] {371, 1.31});
    foods.put(PEACH, new double[] {30, 0.61});
    foods.put(PEANUT, new double[] {567, 0.53});
    foods.put(PEAR, new double[] {16, 0.61});
    foods.put(PEAS, new double[] {148, 0.73});
    foods.put(PEPPER, new double[] {20, 0.51});
    foods.put(PINEAPPLE, new double[] {40, 0.54});
    foods.put(PLUM, new double[] {39, 0.58});
    foods.put(PORK, new double[] {290, 0.7});

    foods.put(RUM, new double[] {275, 0.79});

    foods.put(SALMON, new double[] {180, 0.58});
    foods.put(SALT, new double[] {0, 1.38});
    foods.put(SALTINE_CRACKERS, new double[] {421, 0.43});
    foods.put(SPAGHETTI, new double[] {371, 1.31});
    foods.put(SPINICH, new double[] {8, 0.08});
    foods.put(STRAWBERRIES, new double[] {30, 0.58});
    foods.put(SUGAR, new double[] {400, 0.95});
    foods.put(SWEET_POTATO, new double[] {86, 0.65});
    foods.put(SYRUP, new double[] {260, 1.38});

    foods.put(THYME, new double[] {101, 0.46});
    foods.put(TOMATO, new double[] {20, 0.67});

    foods.put(WINE, new double[] {83, 0.99});
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

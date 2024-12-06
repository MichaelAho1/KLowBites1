package utilities;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.KILowBites;
import controller.MealEditorController;
import controller.RecipeEditorController;
import cooking.Meal;
import cooking.Recipe;

/**
 * FileUtilities class. Handles opening and saving files.
 *
 * @author f24team3d
 * @version 11/12/24
 */
public class FileUtilities
{
  private static JFileChooser fileChooser = new JFileChooser();
  private static String noDirectory = "No directory selected";
  private static String selectedDirectory = "Selected directory: ";
  private static String mel = ".mel";
  private static String rcp = ".rcp";
  private static String mealFiles = "Meal Files";
  private static String recipeFiles = "Recipe Files";

  /**
   * Generic opening method for both recipe and meal files.
   * 
   * @return Recipe/Meal returned
   */
  public static List<Object> open()
  {
    List<Object> data = new ArrayList<>();

    // Create a JFileChooser for selecting directories
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing recipe or meal files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      System.out.println(noDirectory);
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    // System.out.println(selectedDirectory + directory.getAbsolutePath());

    // List files in the directory that have .rcp or .mel extensions
    File[] files = directory.listFiles((dir, name) -> name.endsWith(rcp) || name.endsWith(mel));
    if (files == null || files.length == 0)
    {
      // System.out.println("No recipe or meal files found in the selected directory.");
      return null;
    }

    // Show a list of available files and let the user pick one
    String[] fileNames = new String[files.length];
    for (int i = 0; i < files.length; i++)
    {
      fileNames[i] = files[i].getName();
    }

    String selectedFile = (String) JOptionPane.showInputDialog(null, "Select a file:",
        "Recipe/Meal Files", JOptionPane.PLAIN_MESSAGE, null, fileNames, fileNames[0]);

    if (selectedFile == null)
    {
      return null;
    }

    // Add the selected filename as the first element in the data list
    data.add(selectedFile);

    // Deserialize the selected file
    File file = new File(directory, selectedFile);
    try (FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn))
    {

      // Read objects from the file
      while (true)
      {
        try
        {
          Object obj = in.readObject();
          if (obj instanceof Recipe || obj instanceof Meal)
          {
            data.add(obj); // Add Recipe or Meal objects to the data list
          }
          else
          {
            // System.out.println("Unknown object type: " + obj.getClass().getName());
          }
        }
        catch (EOFException eof)
        {
          break; // End of file reached
        }
      }

      // System.out.println("File loaded successfully from " + file.getAbsolutePath());
    }
    catch (IOException | ClassNotFoundException e)
    {
      // System.err.println("Error loading file: " + e.getMessage());
      e.printStackTrace();
      return null;
    }

    return data;
  }

  /**
   * Open an existing recipe file and return deserialized version.
   * 
   * @return Deserialized recipe
   */
  public static Recipe openRecipe()
  {
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing recipe files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      // System.out.println(noDirectory);
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    // System.out.println(selectedDirectory + directory.getAbsolutePath());
    RecipeEditorController.recipeSavePath = directory.getAbsolutePath();

    // Let user select a recipe file within the chosen directory
    File[] files = directory.listFiles((dir, name) -> name.endsWith(rcp));
    if (files == null || files.length == 0)
    {
      // System.out.println("No recipe files found in the selected directory.");
      return null;
    }

    // Show a list of available recipe files and ask the user to select one
    String[] fileNames = new String[files.length];
    for (int i = 0; i < files.length; i++)
    {
      fileNames[i] = files[i].getName();
    }

    // Use JOptionPane to let the user pick a file from the list
    String selectedFile = (String) JOptionPane.showInputDialog(null, "Select a recipe file:",
        recipeFiles, JOptionPane.PLAIN_MESSAGE, null, fileNames, fileNames[0]);

    // If no file was selected, return null
    if (selectedFile == null)
    {
      // System.out.println("No file selected.");
      return null;
    }

    // Deserialize the selected recipe file
    File file = new File(directory, selectedFile);
    try (FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn))
    {

      Recipe loadedRecipe = (Recipe) in.readObject();
      // System.out.println("Recipe loaded successfully from " + file.getAbsolutePath());

      // for (Ingredients i : loadedRecipe.getIngredients())
      // {
      // System.out.println(i.getName());
      // }

      return loadedRecipe;

    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Opens all recipes in a directory (for searching)
   *
   * @return recipe arraylist
   */
  public static ArrayList<Recipe> openRecipeDirectory()
  {
    ArrayList<Recipe> recipes = new ArrayList<>();

    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing recipe files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      System.out.println(noDirectory);
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    System.out.println(selectedDirectory + directory.getAbsolutePath());
    RecipeEditorController.recipeSavePath = directory.getAbsolutePath();

    // Let user select a recipe file within the chosen directory
    File[] files = directory.listFiles((dir, name) -> name.endsWith(rcp));
    if (files == null || files.length == 0)
    {
      System.out.println("No recipe files found in the selected directory.");
      return null;
    }

    // Show a list of available recipe files and ask the user to select one
    String[] fileNames = new String[files.length];
    for (int i = 0; i < files.length; i++)
    {
      fileNames[i] = files[i].getName();
    }

    // loop through all recipes in that directory
    for (File file : files)
    {
      String selectedFile = file.getName();

      // Deserialize the selected recipe file
      File tempFile = new File(directory, selectedFile);
      try (FileInputStream fileIn = new FileInputStream(tempFile);
          ObjectInputStream in = new ObjectInputStream(fileIn))
      {
        Recipe loadedRecipe = (Recipe) in.readObject();
        recipes.add(loadedRecipe);

        loadedRecipe = null;
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
        return null;
      }
    }

    return recipes;
  }

  /**
   * Open meal and deserialize.
   * 
   * @return Deserialized meal
   */
  public static Meal openMeal()
  {
    // Create a JFileChooser for selecting directories
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing meal files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      // System.out.println("No directory selected.");
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    // System.out.println(selectedDirectory + directory.getAbsolutePath());
    MealEditorController.mealSavePath = directory.getAbsolutePath();

    // Let user select a meal file within the chosen directory
    File[] files = directory.listFiles((dir, name) -> name.endsWith(mel));
    if (files == null || files.length == 0)
    {
      // System.out.println("No meal files found in the selected directory.");
      return null;
    }

    // Show a list of available meal files and ask the user to select one
    String[] fileNames = new String[files.length];
    for (int i = 0; i < files.length; i++)
    {
      fileNames[i] = files[i].getName();
    }

    // Use JOptionPane to let the user pick a file from the list
    String selectedFile = (String) JOptionPane.showInputDialog(null, "Select a meal file:",
        mealFiles, JOptionPane.PLAIN_MESSAGE, null, fileNames, fileNames[0]);

    // If no file was selected, return null
    if (selectedFile == null)
    {
      // System.out.println("No file selected.");
      return null;
    }

    // Deserialize the selected meal file
    File file = new File(directory, selectedFile);
    try (FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn))
    {

      Meal loadedMeal = (Meal) in.readObject();
      // System.out.println("Meal loaded successfully from " + file.getAbsolutePath());
      return loadedMeal;
    }
    catch (IOException | ClassNotFoundException e)
    {
      // System.err.println("Error loading meal: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Open all meals in a directory (for searching)
   *
   * @return Meal arraylist
   */
  public static ArrayList<Recipe> openMealDirectory()
  {
    ArrayList<Meal> meals = new ArrayList<>();
    ArrayList<Recipe> recipes = new ArrayList<>();

    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing meal files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      System.out.println(noDirectory);
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    System.out.println(selectedDirectory + directory.getAbsolutePath());

    // Let user select a recipe file within the chosen directory
    File[] files = directory.listFiles((dir, name) -> name.endsWith(mel));
    if (files == null || files.length == 0)
    {
      System.out.println("No recipe files found in the selected directory.");
      return null;
    }

    // get all meal files
    // loop through all meals in that directory
    for (File file : files)
    {
      String selectedFile = file.getName();

      // Deserialize the selected recipe file
      File tempFile = new File(directory, selectedFile);
      try (FileInputStream fileIn = new FileInputStream(tempFile);
          ObjectInputStream in = new ObjectInputStream(fileIn))
      {
        Meal loadedMeal = (Meal) in.readObject();
        meals.add(loadedMeal);

        loadedMeal = null;
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
        return null;
      }
    }

    // loop through all meals and get recipes
    for (Meal meal : meals)
    {
      for (Recipe recipe : meal.getRecipes())
      {
        System.out.println(recipe.getName());
        recipes.add(recipe);
      }
    }

    return recipes;
  }

  /**
   * Open all meals in a directory (for searching)
   *
   * @return Meal arraylist
   */
  public static HashMap<Meal, List<Recipe>> openMealDirectory2()
  {
    ArrayList<Meal> meals = new ArrayList<>();
    ArrayList<Recipe> recipes = new ArrayList<>();

    HashMap<Meal, List<Recipe>> mealMap = new HashMap<>();
    HashMap<Meal, List<Recipe>> mealMapFiltered = new HashMap<>();

    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing meal files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      System.out.println(noDirectory);
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    System.out.println(selectedDirectory + directory.getAbsolutePath());

    // Let user select a recipe file within the chosen directory
    File[] files = directory.listFiles((dir, name) -> name.endsWith(mel));
    if (files == null || files.length == 0)
    {
      System.out.println("No recipe files found in the selected directory.");
      return null;
    }

    // get all meal files
    // loop through all meals in that directory
    for (File file : files)
    {
      String selectedFile = file.getName();

      // Deserialize the selected recipe file
      File tempFile = new File(directory, selectedFile);
      try (FileInputStream fileIn = new FileInputStream(tempFile);
          ObjectInputStream in = new ObjectInputStream(fileIn))
      {
        Meal loadedMeal = (Meal) in.readObject();
        // meals.add(loadedMeal);
        mealMap.put(loadedMeal, loadedMeal.getRecipes());

        loadedMeal = null;
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
        return null;
      }
    }

    // loop through all meals and get recipes for that meal
    for (Meal meal : mealMap.keySet())
    {
      for (Recipe recipe : mealMap.get(meal))
      {
        System.out.println(recipe.getName());
        recipes.add(recipe);
      }
      mealMapFiltered.put(meal, recipes);
      recipes.clear();
    }

    return mealMapFiltered;
  }

  /**
   * Save a recipe to the designated file path.
   * 
   * @param filePath
   *          File path
   * @param recipe
   *          Recipe being saved
   * @param fileName
   *          Name of file
   */
  public static void saveRecipe(final String filePath, final Recipe recipe, final String fileName)
  {
    // Step 1: Extract the directory path from the file path
    File file = new File(filePath, fileName + rcp);

    // Step 2: Ensure the directory exists
    File parentDirectory = file.getParentFile();

    if (parentDirectory != null && !parentDirectory.exists())
    {
      parentDirectory.mkdirs();
    }

    // Step 3: Serialize and save the recipe to the file
    try (FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut))
    {
      out.writeObject(recipe);
      // System.out.println("Recipe saved successfully to " + filePath);
    }
    catch (IOException e)
    {
      // System.err.println("Error saving recipe: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Open file explorer and let user decide save location.
   * 
   * @param recipe
   *          Recipe being saved
   * @param fileName
   *          Name of file
   * 
   * @return file path
   */
  public static String saveAsRecipe(final Recipe recipe, final String fileName)
  {
    // set up a file filter for .txt or any specific recipe format (if desired)
    FileNameExtensionFilter recipeFilter = new FileNameExtensionFilter(recipeFiles, "rcp");

    fileChooser = new JFileChooser();
    fileChooser.setFileFilter(recipeFilter);

    // open the file explorer and let the user select where to save
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = fileChooser.getSelectedFile();

      String newFileName = selectedFile.getName();

      String filePath = selectedFile.getAbsolutePath().substring(0,
          selectedFile.getAbsolutePath().length() - newFileName.length());

      // call the saveFile method to actually save the recipe
      saveRecipe(filePath, recipe, newFileName);

      return filePath;
    }
    else
    {
      // System.out.println("Save As operation was canceled.");
      return "";
    }
  }

  /**
   * Saves meal in file path.
   * 
   * @param filePath
   *          File path for saving meal
   * @param meal
   *          Meal being saved
   * @param fileName
   *          Name of file
   */
  public static void saveMeal(final String filePath, final Meal meal, final String fileName)
  {
    // Step 1: Extract the directory path from the file path
    File file = new File(filePath, fileName + mel);

    // Step 2: Ensure the directory exists
    File parentDirectory = file.getParentFile();
    if (parentDirectory != null && !parentDirectory.exists())
    {
      parentDirectory.mkdirs();
    }

    // Step 3: Serialize and save the recipe to the file
    try (FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut))
    {
      out.writeObject(meal);
      // System.out.println("Meal saved successfully to " + filePath);
    }
    catch (IOException e)
    {
      // System.err.println("Error saving meal: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Allow user to choose directory to save meal file.
   * 
   * @param meal
   *          Meal being saved
   * @param fileName
   *          Name of file
   * @return file path
   */
  public static String saveAsMeal(final Meal meal, final String fileName)
  {
    // set up a file filter for .txt or any specific recipe format (if desired)
    String mealExtension = "mel";

    fileChooser = new JFileChooser();
    FileNameExtensionFilter mealFilter = new FileNameExtensionFilter(mealFiles, mealExtension);
    fileChooser.setFileFilter(mealFilter);

    // open the file explorer and let the user select where to save
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = fileChooser.getSelectedFile();

      String newFileName = selectedFile.getName();

      String filePath = selectedFile.getAbsolutePath().substring(0,
          selectedFile.getAbsolutePath().length() - newFileName.length());

      // call the saveFile method to actually save the recipe
      saveMeal(filePath, meal, newFileName);

      return filePath;
    }
    else
    {
      // System.out.println("Save As operation was canceled.");
      return "";
    }
  }

  /**
   * Save foods file.
   */
  public static void saveFoods()
  {
    // Retrieve directory path from file path
    File file = new File(Paths.get("").toAbsolutePath().toString(), "Foods.ntr");

    // ONLY FOR JUNIT
    // File file = new File(Paths.get("").toAbsolutePath().toString(), "FoodsCopy.ntr");

    // Serialize and save foods to file
    try (FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut))
    {
      out.writeObject(KILowBites.FOODS.getFoods());
      // System.out.println("Foods.ntr saved successfully to " + file.getAbsolutePath());
    }
    catch (IOException e)
    {
      // System.err.println("Error saving Foods.ntr: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Load file containing aisle numbers and parse data into map.
   * 
   * @return map of ingredient to aisle number
   */
  public static Map<String, Integer> loadAisles()
  {
    // load in Aisles.dat from directory
    File file = new File(Paths.get("").toAbsolutePath().toString(), "Aisles.dat");

    Map<String, Integer> aisles = new TreeMap<>();

    // if file exists, parse data and return aisles map
    if (file.exists())
    {
      try (BufferedReader reader = new BufferedReader(new FileReader(file)))
      {
        String line;
        while ((line = reader.readLine()) != null)
        {
          line = line.trim();
          if (!line.isEmpty())
          {
            // Find the last space in the line to separate the name and the aisle number
            int lastSpaceIndex = line.lastIndexOf(' ');
            if (lastSpaceIndex > 0)
            {
              String ingredient = line.substring(0, lastSpaceIndex).trim();
              String aisleString = line.substring(lastSpaceIndex + 1).trim();
              try
              {
                int aisleNumber = Integer.parseInt(aisleString);
                aisles.put(ingredient, aisleNumber);
              }
              catch (NumberFormatException e)
              {
                // System.err.println("Invalid aisle number format: " + aisleString);
              }
            }
            else
            {
              return null;
              // System.err.println("Invalid line format: " + line);
            }
          }
        }
      }
      catch (IOException e)
      {
        // System.err.println("Error reading the file: " + e.getMessage());
      }
    }
    else
    {
      // System.err.println("File does not exist: " + file.getAbsolutePath());
      return null;
    }

    return aisles;
  }

  /**
   * Load file containing prices and parse data into map.
   * 
   * @return map of ingredient to price
   */
  public static Map<String, Double> loadPrices()
  {
    // load in Prices.dat from directory
    File file = new File(Paths.get("").toAbsolutePath().toString(), "Prices.dat");

    Map<String, Double> prices = new TreeMap<>();

    // if file exists, parse data and return prices map
    if (file.exists())
    {
      try (BufferedReader reader = new BufferedReader(new FileReader(file)))
      {
        String line;
        while ((line = reader.readLine()) != null)
        {
          line = line.trim();
          if (!line.isEmpty())
          {
            // Find the last space in the line to separate the name and price
            int lastSpaceIndex = line.lastIndexOf(' ');
            if (lastSpaceIndex > 0)
            {
              String ingredient = line.substring(0, lastSpaceIndex).trim();
              String aisleString = line.substring(lastSpaceIndex + 1).trim();
              try
              {
                double price = Double.parseDouble(aisleString);
                prices.put(ingredient, price);
              }
              catch (NumberFormatException e)
              {
                // System.err.println("Invalid price format: " + aisleString);
              }
            }
            else
            {
              return null;
              // System.err.println("Invalid line format: " + line);
            }
          }
        }
      }
      catch (IOException e)
      {
        // System.err.println("Error reading the file: " + e.getMessage());
      }
    }
    else
    {
      return null;
      // System.err.println("File does not exist: " + file.getAbsolutePath());
    }

    return prices;
  }
}

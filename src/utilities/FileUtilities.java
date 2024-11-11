package utilities;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.KILowBites;
import controller.MealEditorController;
import controller.RecipeEditorController;
import cooking.Ingredients;
import cooking.Meal;
import cooking.Recipe;
import cooking.RecipeElementType;
import cooking.Steps;
import cooking.Utensils;

public class FileUtilities
{
  private static JFileChooser fileChooser = new JFileChooser();

  /**
   * Generic opening method for both recipe and meal files.
   * 
   * @return Recipe/Meal returned
   */
  public static List<Object> open()
  {
    List<Object> data = new ArrayList<>();

    // Create a JFileChooser for selecting directories
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing recipe or meal files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      System.out.println("No directory selected.");
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    System.out.println("Selected directory: " + directory.getAbsolutePath());

    // List files in the directory that have .rcp or .mel extensions
    File[] files = directory
        .listFiles((dir, name) -> name.endsWith(".rcp") || name.endsWith(".mel"));
    if (files == null || files.length == 0)
    {
      System.out.println("No recipe or meal files found in the selected directory.");
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
      System.out.println("No file selected.");
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
            System.out.println("Unknown object type: " + obj.getClass().getName());
          }
        }
        catch (EOFException eof)
        {
          break; // End of file reached
        }
      }

      System.out.println("File loaded successfully from " + file.getAbsolutePath());
    }
    catch (IOException | ClassNotFoundException e)
    {
      System.err.println("Error loading file: " + e.getMessage());
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
    // Create a JFileChooser for selecting directories
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing recipe files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      System.out.println("No directory selected.");
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    System.out.println("Selected directory: " + directory.getAbsolutePath());
    RecipeEditorController.recipeSavePath = directory.getAbsolutePath();

    // Let user select a recipe file within the chosen directory
    File[] files = directory.listFiles((dir, name) -> name.endsWith(".rcp"));
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

    // Use JOptionPane to let the user pick a file from the list
    String selectedFile = (String) JOptionPane.showInputDialog(null, "Select a recipe file:",
        "Recipe Files", JOptionPane.PLAIN_MESSAGE, null, fileNames, fileNames[0]);

    // If no file was selected, return null
    if (selectedFile == null)
    {
      System.out.println("No file selected.");
      return null;
    }

    // Deserialize the selected recipe file
    File file = new File(directory, selectedFile);
    try (FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn))
    {

      Recipe loadedRecipe = (Recipe) in.readObject();
      System.out.println("Recipe loaded successfully from " + file.getAbsolutePath());

      // for (Ingredients i : loadedRecipe.getIngredients())
      // {
      // System.out.println(i.getName());
      // }

      return loadedRecipe;

    }
    catch (IOException | ClassNotFoundException e)
    {
      System.err.println("Error loading recipe: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Save a recipe to the designated file path.
   * 
   * @param filePath
   *          File path
   * @param recipe
   *          Recipe being saved
   */
  public static void saveRecipe(String filePath, Recipe recipe)
  {
    // Step 1: Extract the directory path from the file path
    File file = new File(filePath, recipe.getName() + ".rcp");

    // Step 2: Ensure the directory exists
    File parentDirectory = file.getParentFile();
    if (parentDirectory != null && !parentDirectory.exists())
    {
      if (parentDirectory.mkdirs())
      {
        System.out.println("Directory created at " + parentDirectory.getAbsolutePath());
      }
      else
      {
        System.err.println("Failed to create directory at " + parentDirectory.getAbsolutePath());
        return;
      }
    }

    // Step 3: Serialize and save the recipe to the file
    try (FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut))
    {
      out.writeObject(recipe);
      System.out.println("Recipe saved successfully to " + filePath);
    }
    catch (IOException e)
    {
      System.err.println("Error saving recipe: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Open file explorer and let user decide save location.
   * 
   * @param recipe
   *          Recipe being saved
   * @return file path
   */
  public static String saveAsRecipe(Recipe recipe)
  {
    // set up a file filter for .txt or any specific recipe format (if desired)
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Recipe Files", "rcp");
    fileChooser.setFileFilter(filter);

    // open the file explorer and let the user select where to save
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();

      // if the file doesn't have the desired extension, append it
      if (!filePath.endsWith(".rcp"))
      {
        filePath += ".rcp";
      }

      // call the saveFile method to actually save the recipe
      saveRecipe(filePath, recipe);

      return filePath;
    }
    else
    {
      System.out.println("Save As operation was canceled.");
      return "";
    }
  }

  /**
   * 
   * 
   * @param filePath
   * @param data
   */
  public static void saveMeal(String filePath, Meal meal)
  {
    // Step 1: Extract the directory path from the file path
    File file = new File(filePath, meal.getName() + ".mel");

    // Step 2: Ensure the directory exists
    File parentDirectory = file.getParentFile();
    if (parentDirectory != null && !parentDirectory.exists())
    {
      if (parentDirectory.mkdirs())
      {
        System.out.println("Directory created at " + parentDirectory.getAbsolutePath());
      }
      else
      {
        System.err.println("Failed to create directory at " + parentDirectory.getAbsolutePath());
        return;
      }
    }

    // Step 3: Serialize and save the recipe to the file
    try (FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut))
    {
      out.writeObject(meal);
      System.out.println("Meal saved successfully to " + filePath);
    }
    catch (IOException e)
    {
      System.err.println("Error saving meal: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Save As: Open a file explorer and let the user select where to save the recipe.
   */
  public static String saveAsMeal(Meal meal)
  {
    // set up a file filter for .txt or any specific recipe format (if desired)
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Meal Files", "mel");
    fileChooser.setFileFilter(filter);

    // open the file explorer and let the user select where to save
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();

      // if the file doesn't have the desired extension, append it
      if (!filePath.endsWith(".rcp"))
      {
        filePath += ".rcp";
      }

      // call the saveFile method to actually save the recipe
      saveMeal(filePath, meal);

      return filePath;
    }
    else
    {
      System.out.println("Save As operation was canceled.");
      return "";
    }
  }

  public static Meal openMeal()
  {
    // Create a JFileChooser for selecting directories
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Select the directory containing meal files");

    // Show the directory chooser dialog
    int result = fileChooser.showOpenDialog(null);
    if (result != JFileChooser.APPROVE_OPTION)
    {
      System.out.println("No directory selected.");
      return null;
    }

    // Get the selected directory
    File directory = fileChooser.getSelectedFile();
    System.out.println("Selected directory: " + directory.getAbsolutePath());
    MealEditorController.mealSavePath = directory.getAbsolutePath();

    // Let user select a recipe file within the chosen directory
    File[] files = directory.listFiles((dir, name) -> name.endsWith(".mel"));
    if (files == null || files.length == 0)
    {
      System.out.println("No meal files found in the selected directory.");
      return null;
    }

    // Show a list of available recipe files and ask the user to select one
    String[] fileNames = new String[files.length];
    for (int i = 0; i < files.length; i++)
    {
      fileNames[i] = files[i].getName();
    }

    // Use JOptionPane to let the user pick a file from the list
    String selectedFile = (String) JOptionPane.showInputDialog(null, "Select a meal file:",
        "Meal Files", JOptionPane.PLAIN_MESSAGE, null, fileNames, fileNames[0]);

    // If no file was selected, return null
    if (selectedFile == null)
    {
      System.out.println("No file selected.");
      return null;
    }

    // Deserialize the selected recipe file
    File file = new File(directory, selectedFile);
    try (FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn))
    {

      Meal loadedMeal = (Meal) in.readObject();
      System.out.println("Recipe loaded successfully from " + file.getAbsolutePath());
      for (Recipe i : loadedMeal.getRecipes())
      {
        System.out.println(i.getName());
      }
      return loadedMeal;
    }
    catch (IOException | ClassNotFoundException e)
    {
      System.err.println("Error loading recipe: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  public static String dumpRecipe(Recipe recipe)
  {
    String output = "**Recipe**\n--Main Fields--\n";

    output += "Main - Name: " + recipe.getName() + "\n";
    output += "Main - Serves: " + recipe.getServes() + "\n";

    output += "\n--Utensils--\n";

    for (Utensils utensil : recipe.getUtensils())
    {
      output += "Utensil - Name: " + utensil.getName() + "\n";
      output += "Utensil - Details: " + utensil.getDetails() + "\n";
    }

    output += "\n--Ingredients--\n";

    for (Ingredients ingredient : recipe.getIngredients())
    {
      output += "Ingredient - Name: " + ingredient.getName() + "\n";
      output += "Ingredient - Details: " + ingredient.getDetails() + "\n";
      output += "Ingredient - Amount: " + ingredient.getAmount() + "\n";
      output += "Ingredient - Unit: " + ingredient.getUnit() + "\n";
    }

    output += "\n--Steps--\n";

    for (Steps step : recipe.getSteps())
    {
      output += "Step - Action: " + step.getAction() + "\n";
      if (step.getSource().getType() == RecipeElementType.INGREDIENT)
      {
        output += "Step - SourceI: " + step.getSource().getName() + "\n";
      }
      else
      {
        output += "Step - SourceU: " + step.getSource().getName() + "\n";
      }
      output += "Step - Destination: " + step.getDestination().getName() + "\n";
      output += "Step - Details: " + step.getDetails() + "\n";
    }

    return output;
  }

  public static void saveFoods()
  {
    // Step 1: Extract the directory path from the file path
    File file = new File(Paths.get("").toAbsolutePath().toString(), "Foods.mel");

    // Step 2: Serialize and save the foods to the file
    try (FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut))
    {
      out.writeObject(KILowBites.FOODS.getFoods());
      System.out.println("Foods.mel saved successfully to " + file.getAbsolutePath());
    }
    catch (IOException e)
    {
      System.err.println("Error saving Foods.mel: " + e.getMessage());
      e.printStackTrace();
    }
  }
}

package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import cooking.Ingredients;
import cooking.Recipe;
import cooking.RecipeElementType;
import cooking.Steps;
import cooking.Utensils;

public class FileUtilities
{
  private static JFileChooser fileChooser = new JFileChooser();

  // Open an existing recipe file and load into currentRecipe
  public static String openRecipe()
  {
    String data = "";

    // set up a file filter for .txt or any specific recipe format (if desired)
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Recipe Files", "rcp");
    fileChooser.setFileFilter(filter);

    // open the file explorer and let the user select where to save
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();

      try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
      {
        while (reader.ready())
        {
          data += reader.readLine() + "\n";
        }

        System.out.println("Recipe opened from file: " + filePath);
      }
      catch (IOException e)
      {
        System.err.println("Error reading the file: " + e.getMessage());
        return null;
      }
      return data;
    }
    return null;
  }

  /**
   * Save the current recipe to a specified file.
   */
  public static void saveRecipe(String filePath, String data)
  {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
    {
      writer.write(data);
      System.out.println("Recipe saved to file: " + filePath);
    }
    catch (IOException e)
    {
      System.err.println("Error saving the file: " + e.getMessage());
    }
  }

  public static Recipe parseData(String data)
  {
    Recipe recipe = new Recipe();
    String[] lines = data.split("\n");

    // sets main fields
    for (String line : lines)
    {
      if (line.startsWith("**Recipe**"))
      {
        continue;
      }
      else if (line.startsWith("--Main Fields--"))
      {
        continue;
      }
      else if (line.startsWith("Main - Name: "))
      {
        String name = line.substring(13);
        recipe.setName(name);
      }
      else if (line.startsWith("Main - Serves: "))
      {
        recipe.setServes(Integer.parseInt(line.substring(15)));
      }
      else if (line.startsWith("--Utensils--"))
      {
        break;
      }
    }

    // sets utensils
    ArrayList<Utensils> utensils = new ArrayList<>();
    Utensils utensil = new Utensils();
    for (String line : lines)
    {
      if (line.startsWith("--Utensils--"))
      {
        utensil = new Utensils();
        continue;
      }
      else if (line.startsWith("Utensil - Name: "))
      {
        utensil.setName(line.substring(16));
      }
      else if (line.startsWith("Utensil - Details: "))
      {
        utensil.setDetails(line.substring(19));
        utensils.add(utensil);
        utensil = new Utensils();
      }
      else if (line.startsWith("--Ingredients--"))
      {
        break;
      }
    }

    for (Utensils u : utensils)
    {
      recipe.addUtensils(u);
    }

    // sets ingredients
    ArrayList<Ingredients> ingredients = new ArrayList<>();
    Ingredients ingredient = new Ingredients();
    for (String line : lines)
    {
      if (line.startsWith("--Ingredients--"))
      {
        ingredient = new Ingredients();
        continue;
      }
      else if (line.startsWith("Ingredient - Name: "))
      {
        ingredient.setName(line.substring(19));
      }
      else if (line.startsWith("Ingredient - Details: "))
      {
        ingredient.setDetails(line.substring(22));
      }
      else if (line.startsWith("Ingredient - Amount: "))
      {
        ingredient.setAmount(Double.parseDouble(line.substring(21)));
      }
      else if (line.startsWith("Ingredient - Unit: "))
      {
        ingredient.setUnit(line.substring(19));
        ingredients.add(ingredient);
        ingredient = new Ingredients();
      }
      else if (line.startsWith("--Steps--"))
      {
        break;
      }
    }

    for (Ingredients i : ingredients)
    {
      recipe.addIngredient(i);
    }

    // sets steps

    ArrayList<Steps> steps = new ArrayList<>();
    Steps step = new Steps();
    for (String line : lines)
    {
      if (line.startsWith("--Steps--"))
      {
        step = new Steps();
        continue;
      }
      else if (line.startsWith("Step - Action: "))
      {
        step.setAction(line.substring(15));
      }
      else if (line.startsWith("Step - SourceI: "))
      {
        Ingredients source = new Ingredients();
        source.setName(line.substring(16));
        step.setSource(source);
      }
      else if (line.startsWith("Step - SourceU: "))
      {
        Utensils source = new Utensils();
        source.setName(line.substring(16));
        step.setSource(source);
      }
      else if (line.startsWith("Step - Destination: "))
      {
        Utensils destination = new Utensils();
        destination.setName(line.substring(20));
        step.setDestination(destination);
      }
      else if (line.startsWith("Step - Details: "))
      {
        step.setDetails(line.substring(16));
        steps.add(step);
        step = new Steps();
      }
    }

    for (Steps s : steps)
    {
      recipe.addStep(s);
    }

    System.out.println();

    return recipe;
  }

  /**
   * Save As: Open a file explorer and let the user select where to save the recipe.
   */
  public static String saveAsRecipe(String data)
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
      saveRecipe(filePath, data);

      return filePath;
    }
    else
    {
      System.out.println("Save As operation was canceled.");
      return "";
    }
  }

  public static void saveMeal(String filePath, ArrayList<Recipe> data)
  {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true)))
    {
      for (Recipe recipe : data)
      {
        writer.write(dumpRecipe(recipe));
        writer.write("\n\n --Recipe End-- \n\n");
      }

      System.out.println("Meal saved to file: " + filePath);
    }
    catch (IOException e)
    {
      System.err.println("Error saving the file: " + e.getMessage());
    }
  }

  /**
   * Save As: Open a file explorer and let the user select where to save the recipe.
   */
  public static String saveAsMeal(ArrayList<Recipe> data)
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
      if (!filePath.endsWith(".mel"))
      {
        filePath += ".mel";
      }

      // call the saveFile method to actually save the recipe
      saveMeal(filePath, data);

      return filePath;
    }
    else
    {
      System.out.println("Save As operation was canceled.");
      return "";
    }
  }

  public static String openMeal()
  {
    fileChooser = new JFileChooser();

    // Open an existing recipe file and load into currentRecipe
    String data = "";

    // set up a file filter for .txt or any specific recipe format (if desired)
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Meal Files", "mel");
    fileChooser.setFileFilter(filter);

    // open the file explorer and let the user select where to save
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();

      try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
      {
        while (reader.ready())
        {
          data += reader.readLine() + "\n";
        }

        System.out.println("Meal opened from file: " + filePath);
      }
      catch (IOException e)
      {
        System.err.println("Error reading the file: " + e.getMessage());
        return null;
      }
      return data;
    }
    return null;
  }

  public ArrayList<Recipe> parseMealData(String mealData)
  {
    ArrayList<Recipe> meal = new ArrayList<>();
    String[] recipes = mealData.split("\n\n --Recipe End-- \n\n");

    for (String recipe : recipes)
    {
      meal.add(parseData(recipe));
    }

    return meal;
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
}

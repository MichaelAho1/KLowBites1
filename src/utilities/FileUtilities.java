package utilities;

import cooking.Ingredients;
import cooking.Recipe;
import cooking.Steps;
import cooking.Utensils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

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
        recipe.setName(line.substring(13));
      }
      else if (line.startsWith("Main - Serves: "))
      {
        recipe.setServes(Integer.parseInt(line.substring(15)));
      }
      else if (line.startsWith("--Utensils--"))
      {
        continue;
      }
      else if (line.startsWith("Utensil - Name: "))
      {
        Utensils utensil = new Utensils();
        utensil.setName(line.substring(16));
        recipe.addUtensils(utensil);
      }
      else if (line.startsWith("--Ingredients--"))
      {
        continue;
      }
      else if (line.startsWith("Ingredient - Name: "))
      {
        Ingredients ingredient = new Ingredients();
        ingredient.setName(line.substring(19));
        recipe.addIngredient(ingredient);
      }
      else if (line.startsWith("--Steps--"))
      {
        continue;
      }
      else if (line.startsWith("Step - Action: "))
      {
        Steps step = new Steps();
        step.setAction(line.substring(15));
        recipe.addStep(step);
      }
    }

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

  public static String dumpRecipe(Recipe recipe)
  {
    String output = "**Recipe**\n--Main Fields--\n";

    output += "Main - Name: " + recipe.getName() + "\n";
    output += "Main - Serves: " + recipe.getServes() + "\n";

    output += "--Utensils--\n";

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
      // output += "Step - Source: " + step.getSource().getName() + ", ";
      // output += "Step - Destination: " + step.getDestination().getName() + ", ";
      // output += "Step - Details: " + step.getDetails() + ", ; ";
    }

    return output;
  }
}

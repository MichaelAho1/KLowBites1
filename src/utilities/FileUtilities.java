package utilities;

import cooking.Recipe;

import java.io.*;

public class FileUtilities
{

  private static File currentFile;


  // Open an existing recipe file and load into currentRecipe
  public static Recipe openFile(String filePath)
  {
    Recipe recipe = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
    {
      String name = reader.readLine();
      int serves = Integer.parseInt(reader.readLine()); 
      recipe = new Recipe(name, serves);
  
      currentFile = new File(filePath); // Store the file reference
      System.out.println("Recipe opened from file: " + filePath);
    }
    catch (IOException e)
    { 
      System.err.println("Error reading the file: " + e.getMessage());
    }

    return recipe; 
  }

  // Save the current recipe to the current file
  public static void saveFile(String name, int serves, String ingredients, String steps)
  {
    if (currentFile == null)
    {
      System.out.println("No file selected. Use Save As.");
      return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile)))
    {
      writer.write(name + "\n"); 
      writer.write(serves + "\n"); 
      writer.write(ingredients + "\n");
      writer.write(steps + "\n");

      // Write ingredients, utensils, and steps (in the format you need)
      System.out.println("Recipe saved to file: " + currentFile.getName());
    }
    catch (IOException e)
    {
      System.err.println("Error saving the file: " + e.getMessage());
    }
  }

  // Save As: Save the recipe to a new file
  public void saveAsFile(String filePath, String name, int serves, String ingredients, String steps)
  {
    currentFile = new File(filePath);
    saveFile(name, serves, ingredients, steps);
  }


}

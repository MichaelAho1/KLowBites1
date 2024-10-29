package utilities;

import cooking.Recipe;

import java.io.*;

public class FileUtilities
{

  private Recipe currentRecipe;
  private File currentFile;

  public FileUtilities()
  {
    currentRecipe = null;
    currentFile = null;
  }

  // New file: Initialize a new recipe
  public void newFile()
  {
    currentRecipe = new Recipe("Untitled", 0); // Default recipe
    currentFile = null; // No associated file yet
    System.out.println("New Recipe created.");
  }

  // Open an existing recipe file and load into currentRecipe
  public Recipe openFile(String filePath)
  {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
    {
      String name = reader.readLine();
      int serves = Integer.parseInt(reader.readLine()); 
      currentRecipe = new Recipe(name, serves); 
      

      currentFile = new File(filePath); // Store the file reference
      System.out.println("Recipe opened from file: " + filePath);
    }
    catch (IOException e)
    {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    return currentRecipe; 
  }

  // Save the current recipe to the current file
  public void saveFile()
  {
    if (currentFile == null)
    {
      System.out.println("No file selected. Use Save As.");
      return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile)))
    {
      writer.write(currentRecipe.getName() + "\n"); 
      writer.write(currentRecipe.getServes() + "\n"); 

      // Write ingredients, utensils, and steps (in the format you need)
      System.out.println("Recipe saved to file: " + currentFile.getName());
    }
    catch (IOException e)
    {
      System.err.println("Error saving the file: " + e.getMessage());
    }
  }

  // Save As: Save the recipe to a new file
  public void saveAsFile(String filePath)
  {
    currentFile = new File(filePath);
    saveFile();
  }

  // Close the current recipe (clears the current recipe and file)
  public void closeFile()
  {
    currentRecipe = null;
    currentFile = null;
    System.out.println("Recipe closed.");
  }

  // Set the current recipe (used by the RecipeEditor)
  public void setCurrentRecipe(Recipe recipe)
  {
    this.currentRecipe = recipe;
  }

  // Get the current recipe 
  public Recipe getCurrentRecipe()
  {
    return currentRecipe;
  }
}

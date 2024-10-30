package utilities;

import cooking.Recipe;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class FileUtilities
{
  private static JFileChooser fileChooser = new JFileChooser();

  // Open an existing recipe file and load into currentRecipe
  public static Recipe openFile(String filePath)
  {
    Recipe recipe = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
    {
      String name = reader.readLine();
      int serves = Integer.parseInt(reader.readLine());
      recipe = new Recipe(name, serves);

      System.out.println("Recipe opened from file: " + filePath);
    }
    catch (IOException e)
    {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    return recipe;
  }

  /**
   * Save the current recipe to a specified file.
   */
  public static void saveFile(String filePath, String name, int serves, String ingredients,
      String steps)
  {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
    {
      writer.write(name + "\n");
      writer.write(serves + "\n");
      writer.write(ingredients + "\n"); 
      writer.write(steps + "\n"); 
      System.out.println("Recipe saved to file: " + filePath);
    }
    catch (IOException e)
    {
      System.err.println("Error saving the file: " + e.getMessage());
    }
  }

  /**
   * Save As: Open a file explorer and let the user select where to save the recipe.
   */
  public static void saveAsFile(String name, int serves, String ingredients, String steps)
  {
    // set up a file filter for .txt or any specific recipe format (if desired)
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
    fileChooser.setFileFilter(filter);

    // open the file explorer and let the user select where to save
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();

      // if the file doesn't have the desired extension, append it
      if (!filePath.endsWith(".txt"))
      {
        filePath += ".txt";
      }

      // call the saveFile method to actually save the recipe
      saveFile(filePath, name, serves, ingredients, steps);
    }
    else
    {
      System.out.println("Save As operation was canceled.");
    }
  }
}

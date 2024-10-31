package controller;

import java.awt.event.*;

import cooking.*;
import gui.RecipeEditor;

/**
 * RecipeEditor controller class. Handles the actions of the RecipeEditor GUI elements
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class RecipeEditorController implements ActionListener
{
  private static String NEW = "New";
  private static String OPEN = "Open";
  private static String SAVE = "Save";
  private static String SAVE_AS = "Save As";
  private static String CLOSE = "Close";

  private static String UTENSILADD = "Utensil Add";
  private static String UTENSILDELETE = "Utensil Delete";

  private static String INGREDIENTADD = "Ingredient Add";
  private static String INGREDIENTDELETE = "Ingredient Delete";

  private static String STEPADD = "Step Add";
  private static String STEPDELETE = "Step Delete";

  private RecipeEditor editor;
  private Recipe recipe;

  /**
   * Constructor for controller.
   */
  public RecipeEditorController()
  {
    createRecipeEditor();
  }

  /**
   * Creates a new RecipeEditor window
   */
  private void createRecipeEditor()
  {
    recipe = new Recipe();
    editor = new RecipeEditor(recipe, this);
  }

  private void addUtensil(Utensils utensil)
  {
    recipe.addUtensils(utensil);
  }

  /**
   * Method for when action is performed
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command;

    command = e.getActionCommand();

    // commands for Toolbar
    if (command.equals(NEW))
    {
      System.out.println("Recipe Editor ToolBar: New button selected");
    }
    else if (command.equals(OPEN))
    {
      System.out.println("Recipe Editor ToolBar: Open button selected");
    }
    else if (command.equals(SAVE))
    {
      System.out.println("Recipe Editor ToolBar: Save button selected");
    }
    else if (command.equals(SAVE_AS))
    {
      System.out.println("Recipe Editor ToolBar: Save As button selected");
    }
    else if (command.equals(CLOSE))
    {
      System.out.println("Recipe Editor ToolBar: Close button selected");
    }
    else if (command.equals(UTENSILADD))
    {
      System.out.println("Recipe Editor Panel: Utensil Add button selected");
    }
    else if (command.equals(UTENSILDELETE))
    {
      System.out.println("Recipe Editor Panel: Utensil Delete button selected");
    }
    else if (command.equals(INGREDIENTADD))
    {
      System.out.println("Recipe Editor Panel: Ingredient Add button selected");
    }
    else if (command.equals(INGREDIENTDELETE))
    {
      System.out.println("Recipe Editor Panel: Ingredient Delete button selected");
    }
    else if (command.equals(STEPADD))
    {
      System.out.println("Recipe Editor Panel: Step Add button selected");
    }
    else if (command.equals(STEPDELETE))
    {
      System.out.println("Recipe Editor Panel: Step Delete button selected");
    }
  }

  private void oldCode()
  {
      // /**
  //  * Saves the current recipe to the file, using FileUtilities saveFile method.
  //  */
  // private void saveRecipe()
  // {
  //   if (currentRecipe != null)
  //   {
  //     String name = currentRecipe.getName();
  //     int serves = currentRecipe.getServes();
  //     String ingredients = ""; // Collect ingredients from the UI 
  //     String steps = ""; // Collect steps from the UI 

  //     // Assuming you have a file path from previous saves
  //     FileUtilities.saveFile("recipe.txt", name, serves, ingredients, steps);
  //   }
  //   else
  //   {
  //     System.out.println("No recipe to save.");
  //   }
  // }

  // /**
  //  * Opens a recipe using the FileUtilities openFile method.
  //  */
  // private void openRecipe()
  // {
  //   JFileChooser fileChooser = new JFileChooser();
  //   int result = fileChooser.showOpenDialog(null);
  //   if (result == JFileChooser.APPROVE_OPTION)
  //   {
  //     String filePath = fileChooser.getSelectedFile().getAbsolutePath();
  //     currentRecipe = FileUtilities.openFile(filePath);
  //   }
  // }

  // /**
  //  * Opens a file dialog for the user to select where to save the recipe.
  //  */
  // private void saveAsRecipe()
  // {
  //   if (currentRecipe != null)
  //   {
  //     String name = currentRecipe.getName();
  //     int serves = currentRecipe.getServes();
  //     String ingredients = ""; // Collect ingredients from the UI
  //     String steps = ""; // Collect steps from the UI 

  //     FileUtilities.saveAsFile(name, serves, ingredients, steps);
  //   }
  //   else
  //   {
  //     System.out.println("No recipe to save.");
  //   }
  // }

  // /**
  //  * Closes the current recipe (sets it to null).
  //  */
  // private void closeRecipe()
  // {
  //   currentRecipe = null;
  //   System.out.println("Recipe closed.");
  // }
  }
}

package controller;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import cooking.*;
import gui.RecipeEditor;
import utilities.*;

/**
 * RecipeEditor controller class. Handles the actions of the RecipeEditor GUI elements
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class RecipeEditorController implements ActionListener, DocumentStateObserver
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
  private DocumentState state;

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
    state = DocumentState.NULL;
    editor = new RecipeEditor(recipe, this);
    editor.updateToolBar(state);

    editor.getContent().getMainIFP().addObserver(this);
    editor.getContent().getUtensilIFP().addObserver(this);
    editor.getContent().getIngredientIFP().addObserver(this);
    editor.getContent().getStepIFP().addObserver(this);
  }

  /**
   * Gets the name of the recipe from the editor
   * Includes input checking.
   */
  private String getName()
  {
    String name = editor.getContent().getNameField();
    if (name == null || name.equals(""))
    {
      name = "Untitled Recipe";
    }

    return name;
  }

  /**
   * Gets the number of serves from the editor
   * Includes input checking.
   */
  private int getServes()
  {
    String serves = editor.getContent().getServesField();
    if (serves == null || serves.equals(""))
    {
      serves = "0";
    }

    if (serves.matches("[0-9]+")) // valid numerical input
    {
      return Integer.parseInt(serves);
    }
    else
    {
      return 0; // default
    }
  }

  /**
   * Called when the document state changes
   *
   * @param state
   */
  public void handleNotification(DocumentState state)
  {
    this.state = state;
    editor.updateToolBar(state);
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
      recipe = new Recipe();
      editor.resetRecipeEditor();
      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(OPEN))
    {
      editor.resetRecipeEditor();
      // load
      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(SAVE))
    {
      // save
      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(SAVE_AS))
    {
      // save as
      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(CLOSE))
    {
      recipe = null;
      editor.resetRecipeEditor();
      state = DocumentState.NULL;
      editor.updateToolBar(state);
    }

    // commands for Editors
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

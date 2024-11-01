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
      recipe.setName(editor.getContent().getNameField());
      recipe.setServes(Integer.parseInt(editor.getContent().getServesField()));

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
      Utensils utensil = new Utensils();

      String name = editor.getContent().getUtensilIFP().getText("Name: ");
      String details = editor.getContent().getUtensilIFP().getText("Details: ");

      if (InputUtilities.isAlphaNumeric(name) &&
        InputUtilities.isAlphaNumeric(details))
      {
        utensil.setName(name);
        utensil.setDetails(details);

        editor.getContent().getUtensilPanel().addRecipeElement(utensil);
      }
      else
      {
        System.out.println("Invalid input");
      }
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(UTENSILDELETE))
    {
      editor.getContent().getUtensilPanel().deleteRecipeElement();
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(INGREDIENTADD))
    {
      Ingredients ingredient = new Ingredients();

      String name = editor.getContent().getIngredientIFP().getText("Name: ");
      String details = editor.getContent().getIngredientIFP().getText("Details: ");
      String amount = editor.getContent().getIngredientIFP().getText("Amount: ");
      String unit = editor.getContent().getIngredientIFP().getComboBox("Units: ");

      if (InputUtilities.isAlphaNumeric(name) &&
        InputUtilities.isAlphaNumeric(details) &&
        InputUtilities.isPositiveDouble(amount))
      {
        ingredient.setName(name);
        ingredient.setDetails(details);
        ingredient.setAmount(Double.parseDouble(amount));

        ingredient.setUnit(unit);

        editor.getContent().getIngredientPanel().addRecipeElement(ingredient);
      }
      else
      {
        System.out.println("Invalid input");
      }
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(INGREDIENTDELETE))
    {
      editor.getContent().getIngredientPanel().deleteRecipeElement();
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(STEPADD))
    {
      Steps step = new Steps();

      String action = editor.getContent().getStepIFP().getComboBox("Action: ");
      String on = editor.getContent().getStepIFP().getComboBox("On: ");
      String utensil = editor.getContent().getStepIFP().getComboBox("Utensil: ");
      String details = editor.getContent().getStepIFP().getText("Details: ");

      if (InputUtilities.isAlphaNumeric(action) &&
        InputUtilities.isAlphaNumeric(details) &&
        !on.equals("") && !utensil.equals(""))
      {
        step.setAction(action);

        // if the Utensil and ingredient have the same name, the Utensil will be selected
        step.setSource(editor.getContent().getUtensilPanel().getSelectedUtensil(on));
        if (step.getSource() == null)
        {
          step.setSource(editor.getContent().getIngredientPanel().getSelectedIngredient(on));
        }

        step.setDestination(editor.getContent().getUtensilPanel().getSelectedUtensil(utensil));
        step.setDetails(details);

        editor.getContent().getStepPanel().addRecipeElement(step);
      }
      else
      {
        System.out.println("Invalid input");
      }

    }
    else if (command.equals(STEPDELETE))
    {
      editor.getContent().getStepPanel().deleteRecipeElement();
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

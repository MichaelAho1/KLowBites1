package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import cooking.Ingredients;
import cooking.Recipe;
import cooking.Steps;
import cooking.Utensils;
import gui.RecipeEditor;
import utilities.DocumentState;
import utilities.DocumentStateObserver;
import utilities.FileUtilities;
import utilities.InputUtilities;

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

  private boolean savedAs = false;
  public static String recipeSavePath = "";

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
    editor = new RecipeEditor(recipe, this, true);
    editor.updateToolBar(state);

    editor.getContent().getMainIFP().addObserver(this);
    editor.getContent().getUtensilIFP().addObserver(this);
    editor.getContent().getIngredientIFP().addObserver(this);
    editor.getContent().getStepIFP().addObserver(this);
  }

  /**
   * Gets the name of the recipe from the editor Includes input checking.
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
   * Gets the number of serves from the editor Includes input checking.
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

  private void updateAllElements(Recipe recipe)
  {
    // creates everything with preset values
    editor.dispose();

    state = DocumentState.UNCHANGED;
    editor = new RecipeEditor(recipe, this, false);
    editor.updateToolBar(state);

    editor.getContent().getMainIFP().addObserver(this);
    editor.getContent().getUtensilIFP().addObserver(this);
    editor.getContent().getIngredientIFP().addObserver(this);
    editor.getContent().getStepIFP().addObserver(this);
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
      // ALLOW USERS TO CHOOSE DIRECTORY TO SAVE RECIPE

      JFileChooser directoryChooser = new JFileChooser();
      directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int result = directoryChooser.showOpenDialog(null);

      if (result == JFileChooser.APPROVE_OPTION)
      {
        File selectedDirectory = directoryChooser.getSelectedFile();
        recipeSavePath = selectedDirectory.getAbsolutePath(); // Set the selected directory as
                                                              // savePath

        recipe = new Recipe(); // Create a new recipe
        editor.resetRecipeEditor(); // Reset editor
        state = DocumentState.UNCHANGED; // Set document state
        savedAs = false; // Indicate not yet saved
        editor.updateToolBar(state); // Update toolbar state

        System.out.println("New directory selected: " + recipeSavePath);
      }
      else
      {
        System.out.println("Directory selection was cancelled.");
      }
    }
    else if (command.equals(OPEN))
    {
      try
      {

        recipe = FileUtilities.openRecipe();
        state = DocumentState.UNCHANGED;

        // tests the constructor so that it only resets if the user selects a file
        RecipeEditor testEditor = new RecipeEditor(recipe, this, false);

        editor.resetRecipeEditor();

        editor.updateToolBar(state);
        editor.dispose();

        // propagate changes to window
        editor = new RecipeEditor(recipe, this, false);
      }
      catch (Exception anotherException)
      {
        System.out.println("RecipeEditor: user cancelled recipe file selection, caught exception");
      }
    }
    else if (command.equals(SAVE))
    {
      String name = editor.getContent().getNameField();
      String serves = editor.getContent().getServesField();

      // Validating inputs
      if (name != null)
      {
        recipe.setName(name);
      }
      else
      {
        System.out.println("Invalid input");
      }

      if (InputUtilities.isPositiveInt(serves))
      {
        recipe.setServes(Integer.parseInt(serves));
      }
      else
      {
        System.out.println("Invalid input");
      }

      if (!recipeSavePath.equals(""))
      {
        FileUtilities.saveRecipe(recipeSavePath, recipe); // save
      }

      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(SAVE_AS))
    {
      // save as
      String name = editor.getContent().getNameField();
      String serves = editor.getContent().getServesField();

      if (name != null)
      {
        recipe.setName(name);
      }
      else
      {
        System.out.println("Invalid input");
      }

      if (InputUtilities.isPositiveInt(serves))
      {
        recipe.setServes(Integer.parseInt(serves));
      }
      else
      {
        System.out.println("Invalid input");
      }

      recipeSavePath = FileUtilities.saveAsRecipe(recipe);
      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(CLOSE))
    {
      recipe = null;
      recipeSavePath = "";
      savedAs = false;
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

      if (InputUtilities.isAlphaNumeric(name))
      {
        utensil.setName(name);
        if (InputUtilities.isAlphaNumeric(details) && !details.equals(""))
        {
          utensil.setDetails(details);
        }

        editor.getContent().getUtensilPanel().addRecipeElement(utensil);

        try
        {
          recipe.addUtensils(utensil);
          editor.getContent().getUtensilIFP().resetFields();
        }
        catch (NullPointerException ex)
        {
          System.out.println("idk");
        }
        editor.getContent().getUtensilIFP().resetFields();
      }
      else
      {
        System.out.println("Invalid input");
      }
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(UTENSILDELETE))
    {
      try
      {
        for (Utensils utensil : recipe.getUtensils())
        {
          try
          {
            if (utensil.getName().equals(editor.getContent().getUtensilPanel()
                .getSelectedUtensil(utensil.getName()).getName()))
            {
              recipe.removeUtensils(utensil);
              break;
            }
          }
          catch (NullPointerException ex)
          {
            System.out.println("No element selected");
          }
        }
      }
      catch (NullPointerException ex)
      {
        System.out.println("trying to delete when empty (caused by deleting when file closed)");
      }

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

      if (InputUtilities.isAlphaNumeric(name) && InputUtilities.isPositiveDouble(amount))
      {
        ingredient.setName(name);
        ingredient.setAmount(Double.parseDouble(amount));
        if (InputUtilities.isAlphaNumeric(details))
        {
          ingredient.setDetails(details);
        }

        ingredient.setUnit(unit);

        editor.getContent().getIngredientPanel().addRecipeElement(ingredient);

        recipe.addIngredient(ingredient);
        editor.getContent().getIngredientIFP().resetFields();

      }
      else
      {
        System.out.println("Invalid input");
      }
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(INGREDIENTDELETE))
    {
      try
      {
        for (Ingredients ingredient : recipe.getIngredients())
        {
          try
          {
            if (ingredient.getName().equals(editor.getContent().getIngredientPanel()
                .getSelectedIngredient(ingredient.getName()).getName()))
            {
              recipe.removeIngredients(ingredient);
              break;
            }
          }
          catch (NullPointerException ex)
          {
            System.out.println("No element selected");
          }
        }
      }
      catch (NullPointerException ex)
      {
        System.out.println("trying to delete when empty (caused by deleting when file closed)");
      }

      editor.getContent().getIngredientPanel().deleteRecipeElement();
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(STEPADD))
    {
      Steps step = new Steps();

      String action = editor.getContent().getStepIFP().getText("Action: ");
      String on = editor.getContent().getStepIFP().getComboBox("On: ");
      String utensil = editor.getContent().getStepIFP().getComboBox("Utensil: ");
      String details = editor.getContent().getStepIFP().getText("Details: ");

      if (InputUtilities.isAlphaNumeric(action) && !on.equals("") && !utensil.equals(""))
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

        recipe.addStep(step);
        editor.getContent().getStepIFP().resetStepInput();
      }
      else
      {
        System.out.println("Invalid input");
      }

    }
    else if (command.equals(STEPDELETE))
    {
      try
      {
        for (Steps step : recipe.getSteps())
        {
          try
          {
            if (step.getDetails().equals(editor.getContent().getStepPanel()
                .getSelectedStep(step.getAction(), step.getDetails()).getDetails()))
            {
              recipe.removeSteps(step);
              break;
            }
          }
          catch (NullPointerException ex)
          {
            System.out.println("No element selected");
          }
        }
      }
      catch (NullPointerException ex)
      {
        System.out.println("trying to delete when empty (caused by deleting when file closed)");
      }

      editor.getContent().getStepPanel().deleteRecipeElement();
    }
  }

  private void oldCode()
  {
    // /**
    // * Saves the current recipe to the file, using FileUtilities saveFile method.
    // */
    // private void saveRecipe()
    // {
    // if (currentRecipe != null)
    // {
    // String name = currentRecipe.getName();
    // int serves = currentRecipe.getServes();
    // String ingredients = ""; // Collect ingredients from the UI
    // String steps = ""; // Collect steps from the UI

    // // Assuming you have a file path from previous saves
    // FileUtilities.saveFile("recipe.txt", name, serves, ingredients, steps);
    // }
    // else
    // {
    // System.out.println("No recipe to save.");
    // }
    // }

    // /**
    // * Opens a recipe using the FileUtilities openFile method.
    // */
    // private void openRecipe()
    // {
    // JFileChooser fileChooser = new JFileChooser();
    // int result = fileChooser.showOpenDialog(null);
    // if (result == JFileChooser.APPROVE_OPTION)
    // {
    // String filePath = fileChooser.getSelectedFile().getAbsolutePath();
    // currentRecipe = FileUtilities.openFile(filePath);
    // }
    // }

    // /**
    // * Opens a file dialog for the user to select where to save the recipe.
    // */
    // private void saveAsRecipe()
    // {
    // if (currentRecipe != null)
    // {
    // String name = currentRecipe.getName();
    // int serves = currentRecipe.getServes();
    // String ingredients = ""; // Collect ingredients from the UI
    // String steps = ""; // Collect steps from the UI

    // FileUtilities.saveAsFile(name, serves, ingredients, steps);
    // }
    // else
    // {
    // System.out.println("No recipe to save.");
    // }
    // }

    // /**
    // * Closes the current recipe (sets it to null).
    // */
    // private void closeRecipe()
    // {
    // currentRecipe = null;
    // System.out.println("Recipe closed.");
    // }
  }
}

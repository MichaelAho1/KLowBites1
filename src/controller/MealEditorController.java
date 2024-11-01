package controller;

import java.awt.event.*;

import java.util.ArrayList;

import cooking.*;
import utilities.*;

import gui.MealEditor;

/**
 * MealEditor controller class. Handles the actions of the MealEditor GUI elements
 *
 * @author f24team3d
 * @version 10/29/24
 */
public class MealEditorController implements ActionListener, DocumentStateObserver
{
  private static String NEW = "New";
  private static String OPEN = "Open";
  private static String SAVE = "Save";
  private static String SAVE_AS = "Save As";
  private static String CLOSE = "Close";

  private static String RECIPEADD = "Recipe Add";
  private static String RECIPEDELETE = "Recipe Delete";

  private MealEditor editor;
  private ArrayList<Recipe> meal;
  private DocumentState state;

  private String mealName = "";

  private boolean savedAs = false;
  private String savePath = "";

  /**
   * Constructor for controller.
   */
  public MealEditorController()
  {
    createMealEditor();
  }

  private void createMealEditor()
  {
    state = DocumentState.NULL;
    meal = new ArrayList<Recipe>();
    editor = new MealEditor(meal, this);

    editor.updateToolBar(state);

    editor.getContent().getMainIFP().addObserver(this);
    editor.getContent().getRecipeIFP().addObserver(this);
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
      meal = new ArrayList<Recipe>();
      editor.resetMealEditor();
      state = DocumentState.UNCHANGED;
      savedAs = false;
      savePath = "";
      editor.updateToolBar(state);
    }
    else if (command.equals(OPEN))
    {
      editor.resetMealEditor();

      Recipe r = new Recipe();
      if (r != null)
      {
        r = FileUtilities.parseData(FileUtilities.openMeal());
      }

      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);

      // create new populated editor
      // editor.dispose();
    }
    else if (command.equals(SAVE))
    {
      String name = editor.getContent().getNameField();

      if (name != null)
      {
        mealName = name;
      }

      if (savedAs && !savePath.equals(""))
      {
        FileUtilities.saveMeal(savePath, meal);
      }
      else
      {
        savePath = FileUtilities.saveAsMeal(meal);
        state = DocumentState.UNCHANGED;
        editor.updateToolBar(state);
      }

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
      meal = null;
      editor.resetMealEditor();
      state = DocumentState.NULL;
      editor.updateToolBar(state);
    }
    else if (command.equals(RECIPEADD))
    {
      System.out.println("Meal Editor Panel: Recipe Add button selected");
      
      Recipe r = new Recipe();
      r = FileUtilities.parseData(FileUtilities.openRecipe());

      if (r != null)
      {
        editor.getContent().getEditorPanel().addMealElement(r);
        meal.add(r);
      }
    }
    else if (command.equals(RECIPEDELETE))
    {
      meal.remove(editor.getContent().getEditorPanel().getMealList().getSelectedValue());
      editor.getContent().getEditorPanel().deleteMealElement();
    }
  }
}

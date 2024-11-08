package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import cooking.Meal;
import cooking.Recipe;
import gui.MealEditor;
import utilities.DocumentState;
import utilities.DocumentStateObserver;
import utilities.FileUtilities;

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
  private Meal meal;
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
    meal = new Meal();
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
      JFileChooser directoryChooser = new JFileChooser();
      directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Set to select
                                                                            // directories only
      int result = directoryChooser.showOpenDialog(null);

      if (result == JFileChooser.APPROVE_OPTION)
      {
        File selectedDirectory = directoryChooser.getSelectedFile();
        savePath = selectedDirectory.getAbsolutePath(); // Set the selected directory as savePath

        meal = new Meal(); // Create a new meal
        editor.resetMealEditor(); // Reset editor
        state = DocumentState.UNCHANGED; // Set document state
        savedAs = false; // Indicate not yet saved
        editor.updateToolBar(state); // Update toolbar state

        System.out.println("New directory selected: " + savePath);
      }
      else
      {
        System.out.println("Directory selection was cancelled.");
      }
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
        meal.addRecipe(r);
      }
    }
    else if (command.equals(RECIPEDELETE))
    {
      meal.removeRecipe(editor.getContent().getEditorPanel().getMealList().getSelectedValue());
      editor.getContent().getEditorPanel().deleteMealElement();
    }
  }
}

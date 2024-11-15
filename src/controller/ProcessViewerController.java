package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import app.KILowBites;
import cooking.Meal;
import cooking.Recipe;
import gui.CalorieOutputWindow;
import gui.DelegatingPrintable;
import gui.ProcessViewer;
import utilities.DocumentState;
import utilities.DocumentStateObserver;
import utilities.FileUtilities;
import utilities.InputUtilities;

/**
 * RecipeViewer controller class. Handles the actions of the RecipeViewer GUI elements
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class ProcessViewerController implements ActionListener, DocumentStateObserver
{
// ALEX START METHOD, RECIPE EDITOR CONTENT
//  private static final String PRINT = "Print";

  private boolean savedAs = false;
  public static String processSavePath = "";

  static final Locale         LOCALE  = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  
  private ProcessViewer viewer;
  private Recipe recipe;
  private Meal meal;
  private DocumentState state;
  /**
   * Constructor for controller.
   */
  public ProcessViewerController()
  {
	  List<Object> data = FileUtilities.open();

	if (data == null)
      {
//		viewer = new ProcessViewer(null, this, false);
      }
      else
      {
        String openedFile = (String) data.get(0);
        String extension = openedFile.substring(openedFile.length() - 4, openedFile.length());

        // System.out.println(extension);

        if (data.get(1) instanceof Recipe)
        {
          recipe = (Recipe) data.get(1);
          viewer = new ProcessViewer(recipe, this, false);
        }
        else
        {
          meal = (Meal) data.get(1);
          viewer = new ProcessViewer(meal, this, false);
        }
      }
    }


  /**
   * Creates a new RecipeViewer window
   */
//  private void createRecipeViewer()
  {
    // viewer.resetRecipeViewer();
//	  Recipe test1 = (Recipe) FileUtilities.open().getLast();
//	  String test2 = FileUtilities.open().getFirst().toString();
//	  List<Object> test0 = FileUtilities.open();
//	  System.out.println(test2);
//	  System.out.println(test1.getName());
//	  System.out.println(FileUtilities.open().getLast().toString());
//    recipe = test1; 
//    if (FileUtilities.open().get() == null) 
//    {
//    	FileUtilities.openRecipe();
//    	return;
//    }
    //ALEX ADD FILE TYPE DETECTION, Likely by using file name/extension
//	viewer = new ProcessViewer(recipe, this, false);
//	state = DocumentState.UNCHANGED;
    
//	meal = FileUtilities.openMeal();
//    viewer = new ProcessViewer(meal, this, false);
//    state = DocumentState.UNCHANGED;

    // propagate changes to window
  }

  /**
   * Gets the name of the recipe from the viewer Includes input checking.
   */
  private String getName()
  {
    String name = viewer.getContent().getNameField();
    if (name == null || name.equals(""))
    {
      name = "Untitled Recipe";
    }

    return name;
  }

  /**
   * Gets the number of serves from the viewer Includes input checking.
   */
  private int getServes()
  {
    String serves = viewer.getContent().getServesField();
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
    viewer.dispose();

    state = DocumentState.UNCHANGED;
    viewer = new ProcessViewer(recipe, this, false);
    viewer.updateToolBar(state);

    viewer.getContent().getMainIFP().addObserver(this);
    viewer.getContent().getUtensilIFP().addObserver(this);
    viewer.getContent().getStepIFP().addObserver(this);
  }

  /**
   * Called when the document state changes
   *
   * @param state
   */
  public void handleNotification(DocumentState state)
  {
    this.state = state;
    viewer.updateToolBar(state);
  }

  /**
   * Method for when action is performed
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command;

    command = e.getActionCommand();

    if (command.equals(STRINGS.getString("PRINT")))
      {
    	DelegatingPrintable dp = new DelegatingPrintable(viewer.getContent());
//    	PrinterJob pj = PrinterJob.getPrinterJob();
    	
//    	dp.print(viewer.getGraphics(), pj.defaultPage(), 1);
    	
//    	PrinterController pc = new PrinterController();
    	PrinterController.print(dp, viewer);
      }
    // commands for Toolbar
//    if (command.equals(NEW))
//    {
//      // ALLOW USERS TO CHOOSE DIRECTORY TO SAVE RECIPE
//
//      JFileChooser directoryChooser = new JFileChooser();
//      directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//      int result = directoryChooser.showOpenDialog(null);
//
//      if (result == JFileChooser.APPROVE_OPTION)
//      {
//        File selectedDirectory = directoryChooser.getSelectedFile();
//        processSavePath = selectedDirectory.getAbsolutePath(); // Set the selected directory as
//                                                               // savePath
//
//        recipe = new Recipe(); // Create a new meal
//        viewer.resetRecipeViewer(); // Reset viewer
//        state = DocumentState.UNCHANGED; // Set document state
//        savedAs = false; // Indicate not yet saved
//        viewer.updateToolBar(state); // Update toolbar state
//
//        System.out.println("New directory selected: " + processSavePath);
//      }
//      else
//      {
//        System.out.println("Directory selection was cancelled.");
//      }
//    }
//    else if (command.equals(OPEN))
//    {
//      viewer.resetRecipeViewer();
//
//      recipe = FileUtilities.openRecipe();
//
//      state = DocumentState.UNCHANGED;
//      viewer.updateToolBar(state);
//
//      viewer.dispose();
//
//      // propagate changes to window
//      viewer = new ProcessViewer(recipe, this, false);
//    }
//    else if (command.equals(SAVE))
//    {
//      String name = viewer.getContent().getNameField();
//      String serves = viewer.getContent().getServesField();
//
//      // Validating inputs
//      if (name != null)
//      {
//        recipe.setName(name);
//      }
//      else
//      {
//        System.out.println("Invalid input");
//      }
//
//      if (InputUtilities.isPositiveInt(serves))
//      {
//        recipe.setServes(Integer.parseInt(serves));
//      }
//      else
//      {
//        System.out.println("Invalid input");
//      }
//
//      if (!processSavePath.equals(""))
//      {
//        FileUtilities.saveRecipe(processSavePath, recipe); // save
//      }
//
//      state = DocumentState.UNCHANGED;
//      viewer.updateToolBar(state);
//    }
//    else if (command.equals(SAVE_AS))
//    {
//      // save as
//      String name = viewer.getContent().getNameField();
//      String serves = viewer.getContent().getServesField();
//
//      if (name != null)
//      {
//        recipe.setName(name);
//      }
//      else
//      {
//        System.out.println("Invalid input");
//      }
//
//      if (InputUtilities.isPositiveInt(serves))
//      {
//        recipe.setServes(Integer.parseInt(serves));
//      }
//      else
//      {
//        System.out.println("Invalid input");
//      }
//
//      processSavePath = FileUtilities.saveAsRecipe(recipe);
//      state = DocumentState.UNCHANGED;
//      viewer.updateToolBar(state);
//    }
//    else if (command.equals(CLOSE))
//    {
//      recipe = null;
//      processSavePath = "";
//      savedAs = false;
//      viewer.resetRecipeViewer();
//      state = DocumentState.NULL;
//      viewer.updateToolBar(state);
//    }
  }
  // // commands for Editors
  // else if (command.equals(UTENSILADD))
  // {
  // Utensils utensil = new Utensils();
  //
  // String name = viewer.getContent().getUtensilIFP().getText("Name: ");
  // String details = viewer.getContent().getUtensilIFP().getText("Details: ");
  //
  // if (InputUtilities.isAlphaNumeric(name))
  // {
  // utensil.setName(name);
  // if (InputUtilities.isAlphaNumeric(details) && !details.equals(""))
  // {
  // utensil.setDetails(details);
  // }
  //
  // viewer.getContent().getUtensilPanel().addRecipeElement(utensil);
  //
  // try
  // {
  // recipe.addUtensils(utensil);
  // viewer.getContent().getUtensilIFP().resetFields();
  // }
  // catch (NullPointerException ex)
  // {
  // System.out.println("idk");
  // }
  // viewer.getContent().getUtensilIFP().resetFields();
  // }
  // else
  // {
  // System.out.println("Invalid input");
  // }
  // viewer.getContent().updateStepSourcePanel();
  // }
  // else if (command.equals(UTENSILDELETE))
  // {
  // try
  // {
  // for (Utensils utensil : recipe.getUtensils())
  // {
  // try
  // {
  // if (utensil.getName().equals(viewer.getContent().getUtensilPanel()
  // .getSelectedUtensil(utensil.getName()).getName()))
  // {
  // recipe.removeUtensils(utensil);
  // break;
  // }
  // }
  // catch (NullPointerException ex)
  // {
  // System.out.println("No element selected");
  // }
  // }
  // }
  // catch (NullPointerException ex)
  // {
  // System.out.println("trying to delete when empty (caused by deleting when file closed)");
  // }
  //
  // viewer.getContent().getUtensilPanel().deleteRecipeElement();
  // viewer.getContent().updateStepSourcePanel();
  // }
  // else if (command.equals(INGREDIENTADD))
  // {
  // Ingredients ingredient = new Ingredients();
  //
  // String name = viewer.getContent().getIngredientIFP().getText("Name: ");
  // String details = viewer.getContent().getIngredientIFP().getText("Details: ");
  // String amount = viewer.getContent().getIngredientIFP().getText("Amount: ");
  // String unit = viewer.getContent().getIngredientIFP().getComboBox("Units: ");
  //
  // if (InputUtilities.isAlphaNumeric(name) && InputUtilities.isPositiveDouble(amount))
  // {
  // ingredient.setName(name);
  // ingredient.setAmount(Double.parseDouble(amount));
  // if (InputUtilities.isAlphaNumeric(details))
  // {
  // ingredient.setDetails(details);
  // }
  //
  // ingredient.setUnit(unit);
  //
  // viewer.getContent().getIngredientPanel().addRecipeElement(ingredient);
  //
  // recipe.addIngredient(ingredient);
  // viewer.getContent().getIngredientIFP().resetFields();
  //
  // }
  // else
  // {
  // System.out.println("Invalid input");
  // }
  // viewer.getContent().updateStepSourcePanel();
  // }
  // else if (command.equals(INGREDIENTDELETE))
  // {
  // try
  // {
  // for (Ingredients ingredient : recipe.getIngredients())
  // {
  // try
  // {
  // if (ingredient.getName().equals(viewer.getContent().getIngredientPanel()
  // .getSelectedIngredient(ingredient.getName()).getName()))
  // {
  // recipe.removeIngredients(ingredient);
  // break;
  // }
  // }
  // catch (NullPointerException ex)
  // {
  // System.out.println("No element selected");
  // }
  // }
  // }
  // catch (NullPointerException ex)
  // {
  // System.out.println("trying to delete when empty (caused by deleting when file closed)");
  // }
  //
  // viewer.getContent().getIngredientPanel().deleteRecipeElement();
  // viewer.getContent().updateStepSourcePanel();
  // }
  // else if (command.equals(STEPADD))
  // {
  // Steps step = new Steps();
  //
  // String action = viewer.getContent().getStepIFP().getText("Action: ");
  // String on = viewer.getContent().getStepIFP().getComboBox("On: ");
  // String utensil = viewer.getContent().getStepIFP().getComboBox("Utensil: ");
  // String details = viewer.getContent().getStepIFP().getText("Details: ");
  //
  // if (InputUtilities.isAlphaNumeric(action) && !on.equals("") && !utensil.equals(""))
  // {
  // step.setAction(action);
  //
  // // if the Utensil and ingredient have the same name, the Utensil will be selected
  // step.setSource(viewer.getContent().getUtensilPanel().getSelectedUtensil(on));
  // if (step.getSource() == null)
  // {
  // step.setSource(viewer.getContent().getIngredientPanel().getSelectedIngredient(on));
  // }
  //
  // step.setDestination(viewer.getContent().getUtensilPanel().getSelectedUtensil(utensil));
  // step.setDetails(details);
  //
  // viewer.getContent().getStepPanel().addRecipeElement(step);
  //
  // recipe.addStep(step);
  // viewer.getContent().getStepIFP().resetStepInput();
  // }
  // else
  // {
  // System.out.println("Invalid input");
  // }
  //
  // }
  // else if (command.equals(STEPDELETE))
  // {
  // try
  // {
  // for (Steps step : recipe.getSteps())
  // {
  // try
  // {
  // if (step.getDetails().equals(viewer.getContent().getStepPanel()
  // .getSelectedStep(step.getAction(), step.getDetails()).getDetails()))
  // {
  // recipe.removeSteps(step);
  // break;
  // }
  // }
  // catch (NullPointerException ex)
  // {
  // System.out.println("No element selected");
  // }
  // }
  // }
  // catch (NullPointerException ex)
  // {
  // System.out.println("trying to delete when empty (caused by deleting when file closed)");
  // }
  //
  // viewer.getContent().getStepPanel().deleteRecipeElement();
  // }
  // }

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

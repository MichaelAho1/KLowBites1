package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.*;
import gui.*;
import cooking.*;

/**
 * KILowBites controller class. Handles the actions of the main menu items.
 *
 * @author f24team3d
 * @version 10/24/24
 */
public class KILowBitesController implements ActionListener
{
  // File
  private static String EXIT = "Exit";

  // Edit
  private static String RECIPE = "Recipe";
  private static String MEAL = "Meal";

  // Search
  private static String RECIPES = "Recipes";
  private static String MEALS = "Meals";

  // View
  private static String SHOPPING_LIST = "Shopping List";
  private static String PROCESS = "Process";

  // Tools
  private static String CALORIE_CALCULATOR = "Calorie Calculator";
  private static String UNITS_CONVERTER = "Units Converter";

  // Configure
  private static String PREFERENCES = "Preferences";
  private static String SHORTCUTS = "Shortcuts";

  // Help
  private static String ABOUT = "About";
  private static String USER_GUIDE = "User Guide";

  private RecipeEditorController recipeController;

  private MealEditorController mealController;

  /**
   * Constructor for controller.
   */
  public KILowBitesController()
  {
    // empty constructor
  }

  /**
   * Method for when action is performed
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command;

    command = e.getActionCommand();

    // commands for File
    if (command.equals(EXIT))
    {
      System.exit(0);
    }

    // commands for Edit
    if (command.equals(RECIPE))
    {
      recipeController = new RecipeEditorController();
    }
    if (command.equals(MEAL))
    {
      mealController = new MealEditorController();
    }

    // commands for Search
    if (command.equals(RECIPES))
    {
      System.out.println("recipe search");
    }
    if (command.equals(MEALS))
    {
      System.out.println("meal search");
    }

    // commands for View
    if (command.equals(SHOPPING_LIST))
    {
      System.out.println("view shopping list");
    }
    if (command.equals(PROCESS))
    {
      System.out.println("view process");
    }

    // commands for Tools
    if (command.equals(CALORIE_CALCULATOR))
    {
      System.out.println("Opening calorie calculator...");
      CalorieCalculatorWindow calorieCalc = new CalorieCalculatorWindow();
      calorieCalc.setVisible(true);
      KILowBites.openCalc.setEnabled(false);
    }
    if (command.equals(UNITS_CONVERTER))
    {
      System.out.println("Opening unit converter...");
      UnitConverterWindow unitConverter = new UnitConverterWindow();
      unitConverter.setVisible(true);
      KILowBites.openConvert.setEnabled(false);
    }

    // commands for Configure
    if (command.equals(PREFERENCES))
    {
      System.out.println("open preferences");
    }
    if (command.equals(SHORTCUTS))
    {
      System.out.println("open shortcuts");
    }

    // commands for Help
    if (command.equals(ABOUT))
    {
      System.out.println("open about");
    }
    if (command.equals(USER_GUIDE))
    {
      System.out.println("open user guide");
    }
  }
}

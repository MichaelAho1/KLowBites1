package gui;

import java.awt.event.*;
import javax.swing.*;

/**
 * KILowBites controller class. Handles the actions of the main menu items.
 *
 * @author f24team3d
 * @version 10/24/24
 */
public class KILowBitesController implements ActionListener
{
  private static String FILE = "File";
  private static String EXIT = "Exit";

  private static String EDIT = "Edit";
  private static String RECIPE = "Recipe";
  private static String MEAL = "Meal";

  private static String SEARCH = "Search";
  private static String RECIPES = "Recipes";
  private static String MEALS = "Meals";

  private static String VIEW = "View";
  private static String SHOPPING_LIST = "Shopping List";
  private static String PROCESS = "Process";

  private static String TOOLS = "Tools";
  private static String CALORIE_CALCULATOR = "Calorie Calculator";
  private static String UNITS_CONVERTER = "Units Converter";

  private static String CONFIGURE = "Configure";
  private static String PREFERENCES = "Preferences";
  private static String SHORTCUTS = "Shortcuts";

  private static String HELP = "Help";
  private static String ABOUT = "About";
  private static String USER_GUIDE = "User Guide";

  /**
   * Constructor for controller.
   */
  public KILowBitesController()
  {
    // initialize stuff
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
      System.out.println("open the recipe editor");
    }
    if (command.equals(MEAL))
    {
      System.out.println("open the meal editor");
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
    }
    if (command.equals(UNITS_CONVERTER))
    {
      System.out.println("open units converter");
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

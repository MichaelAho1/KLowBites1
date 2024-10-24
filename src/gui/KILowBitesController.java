package gui;

import java.awt.event.*;
import javax.swing.*;

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

    if (command.equals(EXIT))
    {
      System.exit(0);
    }
    if (command.equals(RECIPE))
    {
      System.out.println("open the recipe editor");
    }
    if (command.equals(MEAL))
    {
      System.out.println("open the meal editor");
    }
  }
}

package gui;

import java.awt.event.*;

/**
 * MealEditor controller class. Handles the actions of the MealEditor GUI elements
 *
 * @author f24team3d
 * @version 10/29/24
 */
public class MealEditorController implements ActionListener
{
  private static String NEW = "New";
  private static String OPEN = "Open";
  private static String SAVE = "Save";
  private static String SAVE_AS = "Save As";
  private static String CLOSE = "Close";

  private static String RECIPEADD = "Recipe Add";
  private static String RECIPEDELETE = "Recipe Delete";

  /**
   * Constructor for controller.
   */
  public MealEditorController()
  {

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
      System.out.println("Meal Editor ToolBar: New button selected");
    }
    else if (command.equals(OPEN))
    {
      System.out.println("Meal Editor ToolBar: Open button selected");
    }
    else if (command.equals(SAVE))
    {
      System.out.println("Meal Editor ToolBar: Save button selected");
    }
    else if (command.equals(SAVE_AS))
    {
      System.out.println("Meal Editor ToolBar: Save As button selected");
    }
    else if (command.equals(CLOSE))
    {
      System.out.println("Meal Editor ToolBar: Close button selected");
    }
    else if (command.equals(RECIPEADD))
    {
      System.out.println("Meal Editor Panel: Recipe Add button selected");
    }
    else if (command.equals(RECIPEDELETE))
    {
      System.out.println("Meal Editor Panel: Recipe Delete button selected");
    }
  }
}

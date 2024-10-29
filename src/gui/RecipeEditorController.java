package gui;

import java.awt.event.*;

/**
 * RecipeEditor controller class. Handles the actions of the RecipeEditor GUI elements
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class RecipeEditorController implements ActionListener
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

  /**
   * Constructor for controller.
   */
  public RecipeEditorController()
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
      System.out.println("Recipe Editor ToolBar: New button selected");
    }
    else if (command.equals(OPEN))
    {
      System.out.println("Recipe Editor ToolBar: Open button selected");
    }
    else if (command.equals(SAVE))
    {
      System.out.println("Recipe Editor ToolBar: Save button selected");
    }
    else if (command.equals(SAVE_AS))
    {
      System.out.println("Recipe Editor ToolBar: Save As button selected");
    }
    else if (command.equals(CLOSE))
    {
      System.out.println("Recipe Editor ToolBar: Close button selected");
    }
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
}

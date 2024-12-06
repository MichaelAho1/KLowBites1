package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import app.KILowBites;
import cooking.Meal;
import cooking.Recipe;
import gui.DelegatingPrintable;
import gui.ProcessViewer;
import utilities.FileUtilities;

/**
 * RecipeViewer controller class. Handles the actions of the RecipeViewer GUI elements
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class ProcessViewerController implements ActionListener
{
  // ALEX START METHOD, RECIPE EDITOR CONTENT
  static final Locale LOCALE = Locale.getDefault();
  private static String PRINT = "Print";

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  private ProcessViewer viewer;
  private Recipe recipe;
  private Meal meal;

  /**
   * Constructor for controller.
   */
  public ProcessViewerController()
  {
    createRecipeViewer();
  }

  /**
   * Creates a new RecipeViewer window.
   */
  private void createRecipeViewer()
  {
    try
    {
      List<Object> data = FileUtilities.open();

      if (data == null)
      {
        return;
      }
      else
      {
        String openedFile = (String) data.get(0);
        String extension = openedFile.substring(openedFile.length() - 4, openedFile.length());

        if (data.get(1) instanceof Recipe)
        {
          Recipe recipe = (Recipe) data.get(1);
          viewer = new ProcessViewer(recipe, this);
        }
        else
        {
          Meal meal = (Meal) data.get(1);
          viewer = new ProcessViewer(meal, this);
        }
        viewer.setVisible(true);
      }
    }
    catch (Exception e)
    {
    }

  }

  /**
   * Method for when action is performed.
   * @param e
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command = e.getActionCommand();

    // commands for Toolbar
    if (command.equals(PRINT))
    {
//    	JPanel jp = new JPanel();
//    	jp.add(viewer.getContent().getUtensilPanel().getRecipeList());
//    	jp.add(viewer.getContent().getStepPanel().getRecipeList());
//      DelegatingPrintable dp = new DelegatingPrintable(jp);
//      PrinterController.print(dp, viewer);
      DelegatingPrintable dp = new DelegatingPrintable(viewer.getContent()
          .getUtensilPanel().getRecipeList(),
    		  viewer.getContent().getStepPanel().getRecipeList());
      PrinterController.print(dp, viewer);
      // PrinterJob pj = PrinterJob.getPrinterJob();

      // dp.print(viewer.getGraphics(), pj.defaultPage(), 1);

      // PrinterController pc = new PrinterController();
//      PrinterController.print(dp, viewer);
    }
  }
}

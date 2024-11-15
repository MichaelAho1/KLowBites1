package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
=======
import java.awt.print.PrinterJob;
import java.io.File;
>>>>>>> branch 'main' of https://github.com/bernstdh/f24team3d
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import app.KILowBites;
import cooking.Meal;
import cooking.Recipe;
<<<<<<< HEAD
=======
import gui.CalorieOutputWindow;
>>>>>>> branch 'main' of https://github.com/bernstdh/f24team3d
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
  private static String PRINT = "Print";

  static final Locale LOCALE = Locale.getDefault();
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
   * Creates a new RecipeViewer window
   */
  private void createRecipeViewer()
  {
    try
    {
      List<Object> data = FileUtilities.open();

      if (data == null)
      {
        System.out.println("Null file");
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
      System.out.println("Cancelled.");
    }

  }

  /**
   * Method for when action is performed
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command = e.getActionCommand();

<<<<<<< HEAD
=======
    command = e.getActionCommand();

    if (command.equals(STRINGS.getString("PRINT")))
      {
    	DelegatingPrintable dp = new DelegatingPrintable(viewer.getContent());
//    	PrinterJob pj = PrinterJob.getPrinterJob();
    	
//    	dp.print(viewer.getGraphics(), pj.defaultPage(), 1);
    	
//    	PrinterController pc = new PrinterController();
    	PrinterController.print(dp, viewer);
      }
>>>>>>> branch 'main' of https://github.com/bernstdh/f24team3d
    // commands for Toolbar
    if (command.equals(PRINT))
    {
      DelegatingPrintable dp = new DelegatingPrintable(viewer.getContent());
      // PrinterJob pj = PrinterJob.getPrinterJob();

      // dp.print(viewer.getGraphics(), pj.defaultPage(), 1);

      // PrinterController pc = new PrinterController();
      PrinterController.print(dp, viewer);
    }
  }
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import app.KILowBites;
import controller.ProcessViewerController;
import cooking.Meal;
import cooking.Recipe;
import utilities.ImageUtilities;
import utilities.InputUtilities;

/**
 * RecipeViewer class. Handles the toolbar and main container of the Recipe Viewer.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class ProcessViewer extends JFrame
{
  private static final long serialVersionUID = 1L;
  private JButton printButton;

  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  Container processOuterPane;
  JPanel processContentPane;
  ProcessViewerContent processContent;
  ProcessViewerController processViewerController;

  /**
   * Constructor for RecipeViewer.
   */
  public ProcessViewer(Recipe recipe, ProcessViewerController processViewerController)
  {
    super("KILowBites Process Viewer: " + InputUtilities.separateByCapital(recipe.getName()));
    this.processViewerController = processViewerController;

    System.out.println("a");

    setup();

    // creates the main content panel
    processContent = new ProcessViewerContent(recipe, processViewerController);
    processContentPane = processContent;

    processOuterPane.add(processContentPane, BorderLayout.CENTER); // adds the content pane to
                                                                   // outerPane
    this.add(processOuterPane); // adds outerPane to the frame
  }

  public ProcessViewer(Meal meal, ProcessViewerController processViewerController)
  {
    super("KILowBites Process Viewer: " + InputUtilities.separateByCapital(meal.getName()));
    this.processViewerController = processViewerController;

    setup();

    // creates the main content panel
    processContent = new ProcessViewerContent(meal, processViewerController);
    processContentPane = processContent;

    processOuterPane.add(processContentPane, BorderLayout.CENTER);

    this.add(processOuterPane); // adds outerPane to the frame
  }

  /**
   * Gets the Content Pane
   *
   * @return the content pane
   */
  public ProcessViewerContent getContent()
  {
    return processContent;
  }

  /**
   * Set up visual components of window.
   */
  private void setup()
  {
    // creates the outer border layout (contains menubar and content)
    processOuterPane = new Container();

    processOuterPane.setLayout(new BorderLayout());

    // creates the tool bar
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);

    // create toolbar buttons
    printButton = new JButton(ImageUtilities.getFormattedImage("print.png", Color.GRAY, 25, 25));
    printButton.setActionCommand("Print");
    printButton.setToolTipText("Print");
    printButton.setEnabled(true);
    printButton.addActionListener(processViewerController);

    System.out.println("X");

    toolbar.add(printButton);
    processOuterPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north

    this.setSize(new Dimension(800, 550));
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    System.out.println("X");

  }

}
<<<<<<< HEAD
=======

/**
   * Gets the Content Pane
   *
   * @return the content pane
   */
  public ProcessViewerContent getContent()
  {
    return content;
  }
  
  /**
   * Prints the Content Pane
   *
   * @return the content pane
   */
  public ProcessViewerContent printContent()
  {
    return content;
  }


  /**
   * Resets the Recipe Viewer
   */
  public void resetRecipeViewer()
  {
    content.reset();
  }

  /**
   * Updates the toolbar according to the current document state
   */
  public void updateToolBar(DocumentState state)
  {
    buttons[5].setEnabled(true); // Print
    // if (state == DocumentState.NULL)
    // {
    // // enabled
    // buttons[5].setEnabled(true); // Print
    // // disabled
    // buttons[0].setEnabled(false); // new
    // buttons[1].setEnabled(false); // open
    // buttons[2].setEnabled(false); // save
    // buttons[3].setEnabled(false); // save as
    // buttons[4].setEnabled(false); // close
    // }
    // else if (state == DocumentState.CHANGED)
    // {
    // // enabled
    // buttons[2].setEnabled(true); // save
    // buttons[3].setEnabled(true); // save as
    //
    // // disabled
    // buttons[0].setEnabled(false); // new
    // buttons[1].setEnabled(false); // open
    // buttons[4].setEnabled(false); // close
    // }
    // else if (state == DocumentState.UNCHANGED)
    // {
    // // enabled
    // buttons[0].setEnabled(true); // new
    // buttons[1].setEnabled(true); // open
    // buttons[3].setEnabled(true); // save as
    // buttons[4].setEnabled(true); // close
    //
    // // disabled
    // buttons[2].setEnabled(false); // save
    // }
  }
}
>>>>>>> branch 'main' of https://github.com/bernstdh/f24team3d

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.ProcessViewerController;
import cooking.Meal;
import cooking.Recipe;
import utilities.DocumentState;
import utilities.ImageUtilities;

/**
 * RecipeViewer class. Handles the toolbar and main container of the Recipe Viewer.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class ProcessViewer extends JFrame
{
  private static final String KILOWBITES_RECIPE_VIEWER = "KILowBites Recipe Viewer";
  
  Container outerPane;
  JPanel contentPane;
  ProcessViewerContent content;

  JButton[] buttons;

  /**
   * Constructor for RecipeViewer.
   */
  public ProcessViewer(Recipe recipe, ProcessViewerController recipeViewerController, boolean isNew)
  {
    super(KILOWBITES_RECIPE_VIEWER + "  " + recipe.getName());
    // creates the outer border layout (contains menubar and content)
    outerPane = new Container();

    outerPane.setLayout(new BorderLayout());

    // creates the tool bar
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);

    // create toolbar buttons
    buttons = new JButton[1];
    String[] buttonNames = {"Print"};
    String[] buttonPaths = {"img/print.png"};

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(
          ImageUtilities.getColoredIconAndScale(buttonPaths[i], Color.GRAY, 25, 25));
      buttons[i].setActionCommand(buttonNames[i]);
      buttons[i].setToolTipText(buttonNames[i]);
      buttons[i].setEnabled(true);
      buttons[i].addActionListener(recipeViewerController);
    }

    // adds the buttons to the toolbar
    for (JButton button : buttons)
    {
      toolbar.add(button);
    }

    // creates the main content panel
    content = new ProcessViewerContent(recipe, recipeViewerController, isNew);
    contentPane = content;

    this.setSize(new Dimension(800, 800));
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);

    outerPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north
    outerPane.add(contentPane, BorderLayout.CENTER); // adds the content pane to outerPane center

    this.add(outerPane); // adds outerPane to the frame
  }

  public ProcessViewer(Meal meal, ProcessViewerController mealViewerController, boolean isNew) {
	  super("KILowBites Process Viewer" + "  " + meal.getName());

	    // creates the outer border layout (contains menubar and content)
	    outerPane = new Container();

	    outerPane.setLayout(new BorderLayout());

	    // creates the tool bar
	    JToolBar toolbar = new JToolBar();
	    toolbar.setFloatable(false);
	    toolbar.setRollover(true);

	    // create toolbar buttons
	    buttons = new JButton[1];
	    String[] buttonNames = {"Print"};
	    String[] buttonPaths = {"img/print.png"};

	    for (int i = 0; i < buttons.length; i++)
	    {
	      buttons[i] = new JButton(
	          ImageUtilities.getColoredIconAndScale(buttonPaths[i], Color.GRAY, 25, 25));
	      buttons[i].setActionCommand(buttonNames[i]);
	      buttons[i].setToolTipText(buttonNames[i]);
	      buttons[i].setEnabled(true);
	      buttons[i].addActionListener(mealViewerController);
	    }

	    // adds the buttons to the toolbar
	    for (JButton button : buttons)
	    {
	      toolbar.add(button);
	    }

	    // creates the main content panel
	    content = new ProcessViewerContent(meal, mealViewerController, isNew);
	    contentPane = content;

	    this.setSize(new Dimension(800, 800));
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);

	    outerPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north
	    outerPane.add(contentPane, BorderLayout.CENTER); // adds the content pane to outerPane center

	    this.add(outerPane); // adds outerPane to the frame
	  
}

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
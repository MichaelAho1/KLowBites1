package gui;

import java.awt.*;
import javax.swing.*;

import utilities.ImageUtilities;

/**
 * RecipeEditor class. Handles the toolbar and main container of the Recipe Editor.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class RecipeEditor extends JFrame
{
  /**
   * Constructor for RecipeEditor.
   */
  public RecipeEditor()
  {
    super("KILowBites Recipe Editor");

    // creates the controller
    RecipeEditorController controller = new RecipeEditorController();

    // creates the outer border layout (contains menubar and content)
    Container outerPane = new Container();

    outerPane.setLayout(new BorderLayout());

    // creates the tool bar
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);

    // create toolbar buttons
    JButton[] buttons = new JButton[5];
    String[] buttonNames = {"New", "Open", "Save", "Save As", "Close"};
    String[] buttonPaths = {"img/new.png", "img/open.png", "img/save.png", "img/save_as.png", "img/close.png"};

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(ImageUtilities.getColoredIconAndScale(buttonPaths[i], Color.GRAY, 25, 25));
      buttons[i].setActionCommand(buttonNames[i]);
      buttons[i].setToolTipText(buttonNames[i]);
      buttons[i].setEnabled(true);
      buttons[i].addActionListener(controller);
    }

    // adds the buttons to the toolbar
    for (JButton button : buttons)
    {
      toolbar.add(button);
    }

    // creates the main content panel
    Container contentPane = new RecipeEditorContent(controller);

    // final window setup
    this.setMinimumSize(new Dimension(1200, 750));
    this.setSize(800, 500);
    this.setVisible(true);

    outerPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north
    outerPane.add(contentPane, BorderLayout.CENTER); // adds the content pane to outerPane center

    this.add(outerPane); // adds outerPane to the frame
  }
}

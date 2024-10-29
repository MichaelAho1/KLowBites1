package gui;

import java.awt.*;
import javax.swing.*;

import cooking.*;
import utilities.ImageUtilities;

/**
 * MealEditor class. Handles the toolbar and main container of the Meal Editor.
 *
 * @author f24team3d
 * @version 10/29/24
 */
public class MealEditor extends JFrame
{
  /**
   * Constructor for RecipeEditor.
   */
  public MealEditor()
  {
    super("KILowBites Meal Editor");

    // The meal being edited
    // TODO

    // creates the controller
    // TODO: CREATE CONTROLLER
    MealEditorController controller = new MealEditorController();

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
    String[] buttonPaths = {"img/new.png", "img/open.png", "img/save.png", "img/save_as.png",
        "img/close.png"};

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(
          ImageUtilities.getColoredIconAndScale(buttonPaths[i], Color.GRAY, 25, 25));
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
    Container contentPane = new MealEditorContent(controller);

    // final window setup
    // this.setMinimumSize(new Dimension(1350, 1200));
    // this.setMaximumSize(new Dimension(1350, 1200));
    this.setSize(new Dimension(900, 500));
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);

    outerPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north
    outerPane.add(contentPane, BorderLayout.CENTER); // adds the content pane to outerPane center

    this.add(outerPane); // adds outerPane to the frame
  }
}

package gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import controller.MealEditorController;
import cooking.*;
import utilities.ImageUtilities;

/**
 * MealEditor class. Handles the toolbar and main container of the Meal Editor.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class MealEditor extends JFrame
{
  Container outerPane;

  /**
   * Constructor for RecipeEditor.
   */
  public MealEditor(ArrayList<Recipe> meal, MealEditorController controller)
  {
    super("KILowBites Meal Editor");

    // creates the outer border layout (contains menubar and content)
    outerPane = new Container();

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

    this.setSize(new Dimension(900, 500));
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);

    outerPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north
    outerPane.add(contentPane, BorderLayout.CENTER); // adds the content pane to outerPane center

    this.add(outerPane); // adds outerPane to the frame
  }
}

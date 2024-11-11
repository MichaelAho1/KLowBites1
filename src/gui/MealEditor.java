package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.MealEditorController;
import cooking.Meal;
import utilities.DocumentState;
import utilities.ImageUtilities;

/**
 * MealEditor class. Handles the toolbar and main container of the Meal Editor.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class MealEditor extends JFrame
{
  private static final long serialVersionUID = -2406924416722235203L;

  Container outerPane;
  JPanel contentPane;
  MealEditorContent content;

  JButton[] buttons;

  /**
   * Constructor for RecipeEditor.
   */
  public MealEditor(Meal meal, MealEditorController controller, boolean isNew)
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
    buttons = new JButton[5];
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
    content = new MealEditorContent(meal, controller, isNew);
    contentPane = content;

    this.setSize(new Dimension(900, 500));
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);

    outerPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north
    outerPane.add(contentPane, BorderLayout.CENTER); // adds the content pane to outerPane center

    this.add(outerPane); // adds outerPane to the frame
  }

  public MealEditorContent getContent()
  {
    return content;
  }

  public void resetMealEditor()
  {
    content.reset();
  }

  public void disableToolBar()
  {
    for (JButton button : buttons)
    {
      button.setEnabled(false);
    }
  }

  /**
   * Updates the toolbar according to the current document state
   */
  public void updateToolBar(DocumentState state)
  {
    if (state == DocumentState.NULL)
    {
      // enabled
      buttons[0].setEnabled(true); // new
      buttons[1].setEnabled(true); // open

      // disabled
      buttons[2].setEnabled(false); // save
      buttons[3].setEnabled(false); // save as
      buttons[4].setEnabled(false); // close
    }
    else if (state == DocumentState.CHANGED)
    {
      // enabled
      buttons[2].setEnabled(true); // save
      buttons[3].setEnabled(true); // save as

      // disabled
      buttons[0].setEnabled(false); // new
      buttons[1].setEnabled(false); // open
      buttons[4].setEnabled(false); // close
    }
    else if (state == DocumentState.UNCHANGED)
    {
      // enabled
      buttons[0].setEnabled(true); // new
      buttons[1].setEnabled(true); // open
      buttons[3].setEnabled(true); // save as
      buttons[4].setEnabled(true); // close

      // disabled
      buttons[2].setEnabled(false); // save
    }

    // TODO: THIS CODE DEACTIVATES THE TOOLBAR SINCE FILE SUPPORT ISN'T READY
    // this.disableToolBar();
  }
}

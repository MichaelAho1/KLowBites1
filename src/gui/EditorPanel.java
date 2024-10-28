package gui;

import java.awt.*;
import javax.swing.*;

/**
 * EditorPanel class. Flexibly handles the Editor panels for Editor frames.
 * Examples: Utensils, Ingredients, Steps
 * (where the file input goes)
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class EditorPanel extends JPanel
{
  /**
   * Constructor for EditorPanel.
   *
   * @param inputFieldPanel the input field panel
   * @param controller the controller for the RecipeEditor
   */
  public EditorPanel(String name, InputFieldPanel inputFieldPanel, RecipeEditorController controller)
  {
    super();

    Container contentPane = new Container();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // creates the file editor
    Container fileEditor = new Container();
    fileEditor.setLayout(new BorderLayout());

    // creates the label for the editor panel
    JLabel editorLabel = new JLabel(name);

    // creates the text area for the file editor
    JTextArea fileTextArea = new JTextArea();
    fileTextArea.setLineWrap(true);
    fileTextArea.setWrapStyleWord(true);
    // JScrollPane fileScrollPane = new JScrollPane(fileTextArea);

    // creates the delete button
    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(controller);

    // fix for delete button (needs to be able to tell what to delete)
    if (name.equals("Utensils"))
    {
      deleteButton.setActionCommand("Utensil Delete");
    }
    else if (name.equals("Ingredients"))
    {
      deleteButton.setActionCommand("Ingredient Delete");
    }
    else if (name.equals("Steps"))
    {
      deleteButton.setActionCommand("Step Delete");
    }

    // adds the file editor and delete button to the file editor container
    fileEditor.add(fileTextArea, BorderLayout.CENTER);
    fileEditor.add(deleteButton, BorderLayout.EAST);

    // adds the editor label
    contentPane.add(editorLabel);

    // adds the input field panel
    contentPane.add(inputFieldPanel);

    // adds the file editor container
    contentPane.add(fileEditor);

    this.add(contentPane);
  }

  //TODO: NEED TO WRITE GETTERS FOR THE TEXT FIELDS
}

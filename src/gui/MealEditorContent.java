package gui;

import java.awt.*;
import javax.swing.*;

import controller.MealEditorController;

/**
 * MealEditorContent class. Handles MealEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class MealEditorContent extends JPanel
{
  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller the controller for the RecipeEditor
   */
  public MealEditorContent(MealEditorController controller)
  {
    super();

    Container contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // **** INPUT FIELDS ****

    // creates the elements for the input fields
    JLabel nameLabel = new JLabel("Name: ");
    JTextField nameField = new JTextField(50);

    // adds all the elements to the input fields
    JComponent[] fields = {nameLabel, nameField};
    InputFieldPanel inputFieldPanel = new InputFieldPanel(fields);

    // **** EDITOR PANELS ****

    // Recipes
    JButton recipeEditorAddButton = new JButton("Add Recipe");
    recipeEditorAddButton.setActionCommand("Recipe Add");
    recipeEditorAddButton.addActionListener(controller);

    InputFieldPanel recipeEditorInputFieldPanel = new InputFieldPanel(new JComponent[] {recipeEditorAddButton});

    // creates the panel for recipes
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new BorderLayout());

    EditorPanel recipeEditorPanel = new EditorPanel("Recipes", recipeEditorInputFieldPanel, controller);

    editorPanel.add(recipeEditorPanel, BorderLayout.CENTER);

    // adds the input fields into the content pane
    contentPane.add(inputFieldPanel, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(editorPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }
}

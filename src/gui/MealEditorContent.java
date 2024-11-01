package gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import cooking.*;

import controller.MealEditorController;

/**
 * MealEditorContent class. Handles MealEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class MealEditorContent extends JPanel
{
  Container contentPane;

  ArrayList<Recipe> currentMeal; // meal being used

  InputFieldPanel mainIFP;
  InputFieldPanel recipeIFP;

  EditorPanel recipeEditorPanel;

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
    mainIFP = new InputFieldPanel();
    mainIFP.addJTextField("Name: ", 50);

    // **** EDITOR PANELS ****

    // Recipes
    recipeIFP = new InputFieldPanel();
    recipeIFP.addJButton("Add Recipe", "Recipe Add", controller);

    // creates the panel for recipes
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new BorderLayout());

    recipeEditorPanel = new EditorPanel("Recipes", recipeIFP, controller);

    editorPanel.add(recipeEditorPanel, BorderLayout.CENTER);

    // adds the input fields into the content pane
    contentPane.add(mainIFP, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(editorPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

  public String getNameField()
  {
    return mainIFP.getText("Name: ");
  }

  public void reset()
  {
    mainIFP.resetFields();
    recipeIFP.resetFields();
  }

  public InputFieldPanel getMainIFP()
  {
    return mainIFP;
  }

  public InputFieldPanel getRecipeIFP()
  {
    return recipeIFP;
  }

  public EditorPanel getEditorPanel()
  {
    return recipeEditorPanel;
  }
}

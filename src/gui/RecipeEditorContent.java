package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cooking.*;

import controller.RecipeEditorController;
import utilities.DocumentState;
import utilities.Units;

/**
 * RecipeEditorContent class. Handles RecipeEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class RecipeEditorContent extends JPanel
{
  Container contentPane;

  Recipe currentRecipe; // recipe being used

  JPanel editorPanel;

  InputFieldPanel mainIFP;
  InputFieldPanel utensilIFP;
  InputFieldPanel ingredientIFP;
  InputFieldPanel stepIFP;

  EditorPanel utensilEditorPanel;
  EditorPanel ingredientEditorPanel;
  EditorPanel stepEditorPanel;

  Units units;

  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller the controller for the RecipeEditor
   */
  public RecipeEditorContent(Recipe recipe, RecipeEditorController controller)
  {
    super();

    currentRecipe = recipe;

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    units = new Units();

    // **** INPUT FIELDS ****

    // adds all the elements to the input fields
    mainIFP = new InputFieldPanel();
    mainIFP.addJTextField("Name: ", 50);
    mainIFP.addJTextField("Serves: ", 10);

    // **** EDITOR PANELS ****

    // Utensils
    utensilIFP = new InputFieldPanel();
    utensilIFP.addJTextField("Name: ", 25);
    utensilIFP.addJTextField("Details: ", 25);
    utensilIFP.addJButton("Add", "Utensil Add", controller);

    // Ingredients
    ingredientIFP = new InputFieldPanel();
    ingredientIFP.addJTextField("Name: ", 15);
    ingredientIFP.addJTextField("Details: ", 7);
    ingredientIFP.addJTextField("Amount: ", 7);
    ingredientIFP.addJComboBox("Units: ", units.getAllUnits());

    ingredientIFP.addJButton("Add", "Ingredient Add", controller);

    // Steps
    stepIFP = new InputFieldPanel();
    stepIFP.addJComboBox("Action: ", new String[] {"", "test"});
    stepIFP.addJComboBox("On: ", new String[] {"", "test"});
    stepIFP.addJComboBox("Utensil: ", new String[] {"", "test"});
    stepIFP.addJTextField("Details: ", 15);
    stepIFP.addJButton("Add", "Step Add", controller);

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.Y_AXIS));

    utensilEditorPanel = new EditorPanel(RecipeElementType.UTENSIL, utensilIFP, controller);
    ingredientEditorPanel = new EditorPanel(RecipeElementType.INGREDIENT, ingredientIFP, controller);
    stepEditorPanel = new EditorPanel(RecipeElementType.STEP, stepIFP, controller);

    editorPanel.add(utensilEditorPanel);
    editorPanel.add(ingredientEditorPanel);
    editorPanel.add(stepEditorPanel);

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

  public String getServesField()
  {
    return mainIFP.getText("Serves: ");
  }

  public EditorPanel getUtensilPanel()
  {
    return utensilEditorPanel;
  }

  public EditorPanel getIngredientPanel()
  {
    return ingredientEditorPanel;
  }

  public EditorPanel getStepPanel()
  {
    return stepEditorPanel;
  }

  public void reset()
  {
    mainIFP.resetFields();
    utensilIFP.resetFields();
    ingredientIFP.resetFields();
    stepIFP.resetFields();
  }

  public InputFieldPanel getMainIFP()
  {
    return mainIFP;
  }

  public InputFieldPanel getUtensilIFP()
  {
    return utensilIFP;
  }

  public InputFieldPanel getIngredientIFP()
  {
    return ingredientIFP;
  }

  public InputFieldPanel getStepIFP()
  {
    return stepIFP;
  }
}

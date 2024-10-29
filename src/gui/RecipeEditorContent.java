package gui;

import java.awt.*;
import javax.swing.*;

/**
 * RecipeEditorContent class. Handles RecipeEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class RecipeEditorContent extends JPanel
{
  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller the controller for the RecipeEditor
   */
  public RecipeEditorContent(RecipeEditorController controller)
  {
    super();

    Container contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // **** INPUT FIELDS ****

    // creates the elements for the input fields
    JLabel nameLabel = new JLabel("Name: ");
    JTextField nameField = new JTextField(50);
    JLabel servesLabel = new JLabel("Serves: ");
    JTextField servesField = new JTextField(10);

    // adds all the elements to the input fields
    JComponent[] fields = {nameLabel, nameField, servesLabel, servesField};
    InputFieldPanel inputFieldPanel = new InputFieldPanel(fields);

    // **** EDITOR PANELS ****

    // Utensils
    JLabel utensilEditorNameLabel = new JLabel("Name: ");
    JTextField utensilEditorNameField = new JTextField(25);

    JLabel utensilEditorDetailsLabel = new JLabel("Details: ");
    JTextField utensilEditorDetailsField = new JTextField(25);

    JButton utensilEditorAddButton = new JButton("Add");
    utensilEditorAddButton.setActionCommand("Utensil Add");
    utensilEditorAddButton.addActionListener(controller);

    InputFieldPanel utensilEditorInputFieldPanel = new InputFieldPanel(new JComponent[] {utensilEditorNameLabel, utensilEditorNameField, utensilEditorDetailsLabel, utensilEditorDetailsField, utensilEditorAddButton});

    // Ingredients
    JLabel ingredientEditorNameLabel = new JLabel("Name: ");
    JTextField ingredientEditorNameField = new JTextField(15);

    JLabel ingredientEditorDetailsLabel = new JLabel("Details: ");
    JTextField ingredientEditorDetailsField = new JTextField(7);

    JLabel ingredientEditorAmountLabel = new JLabel("Amount: ");
    JTextField ingredientEditorAmountField = new JTextField(7);

    JLabel ingredientEditorUnitsLabel = new JLabel("Units: ");
    JComboBox<String> ingredientEditorUnitsComboBox = new JComboBox<String>();
    ingredientEditorUnitsComboBox.addItem("");
    ingredientEditorUnitsComboBox.addItem("test");
    ingredientEditorUnitsComboBox.addItem("test2");

    JButton ingredientEditorAddButton = new JButton("Add");
    ingredientEditorAddButton.setActionCommand("Ingredient Add");
    ingredientEditorAddButton.addActionListener(controller);

    InputFieldPanel ingredientEditorInputFieldPanel = new InputFieldPanel(new JComponent[] {ingredientEditorNameLabel, ingredientEditorNameField, ingredientEditorDetailsLabel, ingredientEditorDetailsField, ingredientEditorAmountLabel, ingredientEditorAmountField, ingredientEditorUnitsLabel, ingredientEditorUnitsComboBox, ingredientEditorAddButton});

    // Steps
    JLabel stepEditorActionLabel = new JLabel("Action: ");
    JComboBox<String> stepEditorActionComboBox = new JComboBox<String>();
    stepEditorActionComboBox.addItem("");
    stepEditorActionComboBox.addItem("test");

    JLabel stepEditorOnLabel = new JLabel("On: ");
    JComboBox<String> stepEditorOnComboBox = new JComboBox<String>();
    stepEditorOnComboBox.addItem("");
    stepEditorOnComboBox.addItem("test");

    JLabel stepEditorUtensilLabel = new JLabel("Utensil: ");
    JComboBox<String> stepEditorUtensilComboBox = new JComboBox<String>();
    stepEditorUtensilComboBox.addItem("");
    stepEditorUtensilComboBox.addItem("test");

    JLabel stepEditorDetailsLabel = new JLabel("Details: ");
    JTextField stepEditorDetailsField = new JTextField(15);

    JButton stepEditorAddButton = new JButton("Add");
    stepEditorAddButton.setActionCommand("Step Add");
    stepEditorAddButton.addActionListener(controller);

    InputFieldPanel stepEditorInputFieldPanel = new InputFieldPanel(new JComponent[] {stepEditorActionLabel, stepEditorActionComboBox, stepEditorOnLabel, stepEditorOnComboBox, stepEditorUtensilLabel, stepEditorUtensilComboBox, stepEditorDetailsLabel, stepEditorDetailsField, stepEditorAddButton});

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.Y_AXIS));

    EditorPanel utensilEditorPanel = new EditorPanel("Utensils", utensilEditorInputFieldPanel, controller, "Utensils");
    EditorPanel ingredientEditorPanel = new EditorPanel("Ingredients", ingredientEditorInputFieldPanel, controller, "Ingredients");
    EditorPanel stepEditorPanel = new EditorPanel("Steps", stepEditorInputFieldPanel, controller, "Steps");

    editorPanel.add(utensilEditorPanel);
    editorPanel.add(ingredientEditorPanel);
    editorPanel.add(stepEditorPanel);

    // adds the input fields into the content pane
    contentPane.add(inputFieldPanel, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(editorPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }
}

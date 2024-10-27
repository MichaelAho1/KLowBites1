
package gui;

import cooking.Ingredients;
import cooking.Recipe;
import cooking.Steps;
import cooking.Utensils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class RecipeEditor extends JFrame
{
  private JTextField nameField;
  private JTextField servesField;
  private DefaultListModel<Ingredients> ingredientsModel;
  private DefaultListModel<Utensils> utensilsModel;
  private DefaultListModel<Steps> stepsModel;
  private JComboBox<String> unitsComboBox;
  private JComboBox<String> actionComboBox;
  private JComboBox<Ingredients> onComboBox;
  private JComboBox<Utensils> utensilComboBox;

  private JButton newButton, openButton, saveButton, saveAsButton, closeButton;
  private Recipe currentRecipe;

  public RecipeEditor()
  {
    setTitle("Recipe Editor");
    setSize(600, 700);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel();
    mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    addRecipeFields(mainPanel);
    addUtensilList(mainPanel);
    addIngredientList(mainPanel);
    addStepList(mainPanel);

    add(mainPanel, BorderLayout.CENTER);
    add(createButtonPanel(), BorderLayout.SOUTH);

    setStateButtons("null");
    setLocationRelativeTo(null);
  }

  private void addRecipeFields(JPanel panel)
  {
    JPanel recipePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    recipePanel.setBorder(BorderFactory.createTitledBorder("Recipe"));

    recipePanel.add(new JLabel("Name:"));
    nameField = new JTextField(30);
    recipePanel.add(nameField);

    recipePanel.add(new JLabel("Serves:"));
    servesField = new JTextField(5);
    recipePanel.add(servesField);

    panel.add(recipePanel);
  }

  private void addUtensilList(JPanel panel)
  {
    utensilsModel = new DefaultListModel<>();
    JList<Utensils> utensilsList = new JList<>(utensilsModel);
    utensilsList.setBorder(BorderFactory.createTitledBorder("Utensils"));

    JPanel utensilPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    utensilPanel.add(new JLabel("Name:"));
    JTextField utensilNameField = new JTextField(10);
    utensilPanel.add(utensilNameField);

    utensilPanel.add(new JLabel("Details:"));
    JTextField utensilDetailsField = new JTextField(10);
    utensilPanel.add(utensilDetailsField);

    JButton addUtensilButton = new JButton("Add");
    addUtensilButton.addActionListener(e -> {
      Utensils utensil = new Utensils(utensilNameField.getText(), utensilDetailsField.getText());
      utensilsModel.addElement(utensil);
      utensilComboBox.addItem(utensil); // Add to dropdown
      setStateButtons("changed");
    });
    utensilPanel.add(addUtensilButton);

    JButton deleteUtensilButton = new JButton("Delete");
    deleteUtensilButton.addActionListener(e -> {
      int selectedIndex = utensilsList.getSelectedIndex();
      if (selectedIndex != -1)
      {
        utensilsModel.remove(selectedIndex);
        utensilComboBox.removeItemAt(selectedIndex); // Remove from dropdown
        setStateButtons("changed");
      }
    });
    utensilPanel.add(deleteUtensilButton);

    panel.add(utensilPanel);
    panel.add(new JScrollPane(utensilsList));
  }

  private void addIngredientList(JPanel panel)
  {
    ingredientsModel = new DefaultListModel<>();
    JList<Ingredients> ingredientsList = new JList<>(ingredientsModel);
    ingredientsList.setBorder(BorderFactory.createTitledBorder("Ingredients"));

    JPanel ingredientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    ingredientPanel.add(new JLabel("Name:"));
    JTextField ingredientNameField = new JTextField(10);
    ingredientPanel.add(ingredientNameField);

    ingredientPanel.add(new JLabel("Details:"));
    JTextField ingredientDetailsField = new JTextField(10);
    ingredientPanel.add(ingredientDetailsField);

    ingredientPanel.add(new JLabel("Amount:"));
    JTextField ingredientAmountField = new JTextField(5);
    ingredientPanel.add(ingredientAmountField);

    ingredientPanel.add(new JLabel("Units:"));
//    unitsComboBox = new JComboBox<>(UnitConverter.getAllUnits().toArray(new String[0])); // Check for that
//    ingredientPanel.add(unitsComboBox);

    JButton addIngredientButton = new JButton("Add");
    addIngredientButton.addActionListener(e -> {
      double amount = Double.parseDouble(ingredientAmountField.getText());
      String unit = (String) unitsComboBox.getSelectedItem();
      Ingredients ingredient = new Ingredients(amount, ingredientNameField.getText(), unit,
          ingredientDetailsField.getText());
      ingredientsModel.addElement(ingredient);
      onComboBox.addItem(ingredient); // add to dropdown
      setStateButtons("changed");
    });
    ingredientPanel.add(addIngredientButton);

    JButton deleteIngredientButton = new JButton("Delete");
    deleteIngredientButton.addActionListener(e -> {
      int selectedIndex = ingredientsList.getSelectedIndex();
      if (selectedIndex != -1)
      {
        ingredientsModel.remove(selectedIndex);
        onComboBox.removeItemAt(selectedIndex); // remove from dropdown
        setStateButtons("changed");
      }
    });
    ingredientPanel.add(deleteIngredientButton);

    panel.add(ingredientPanel);
    panel.add(new JScrollPane(ingredientsList));
  }

  private void addStepList(JPanel panel)
  {
    stepsModel = new DefaultListModel<>();
    JList<Steps> stepsList = new JList<>(stepsModel);
    stepsList.setBorder(BorderFactory.createTitledBorder("Steps"));

    JPanel stepPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    stepPanel.add(new JLabel("Action:"));
    actionComboBox = new JComboBox<>(new String[] {"Chop", "Mix", "Bake", "Fry"}); // sample actions, modify to what the recipe says
    stepPanel.add(actionComboBox);

    List<Ingredients> ingredientsList = Collections.list(ingredientsModel.elements());
    Ingredients[] ingredientsArray = ingredientsList.toArray(new Ingredients[0]);
    onComboBox = new JComboBox<>(ingredientsArray); // populate with ingredients
    stepPanel.add(new JLabel("On:"));
    stepPanel.add(onComboBox);

    List<Utensils> utensilsList = Collections.list(utensilsModel.elements());
    Utensils[] utensilsArray = utensilsList.toArray(new Utensils[0]);
    utensilComboBox = new JComboBox<>(utensilsArray); // populate with utensils
    stepPanel.add(new JLabel("Utensil:"));
    stepPanel.add(utensilComboBox);

    JTextField stepDetailsField = new JTextField(10);
    stepPanel.add(stepDetailsField);

    JButton addStepButton = new JButton("Add");
    addStepButton.addActionListener(e -> {
      Steps step = new Steps((String) actionComboBox.getSelectedItem(),
          (Ingredients) onComboBox.getSelectedItem(), (Utensils) utensilComboBox.getSelectedItem(),
          stepDetailsField.getText());
      stepsModel.addElement(step);
      setStateButtons("changed");
    });
    stepPanel.add(addStepButton);

    JButton deleteStepButton = new JButton("Delete");
    deleteStepButton.addActionListener(e -> {
      int selectedIndex = stepsList.getSelectedIndex();
      if (selectedIndex != -1)
      {
        stepsModel.remove(selectedIndex);
        setStateButtons("changed");
      }
    });
    stepPanel.add(deleteStepButton);

    panel.add(stepPanel);
    panel.add(new JScrollPane(stepsList));
  }

  private JPanel createButtonPanel() // need fix
  {
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

      // Load Icons
      newButton = createIconButton("New", "new.png"); 
      openButton = createIconButton("Open", "open.png");
      saveButton = createIconButton("Save", "save.png");
      saveAsButton = createIconButton("Save As", "save_as.png");
      closeButton = createIconButton("Close", "close.png");

      buttonPanel.add(newButton);
      buttonPanel.add(openButton);
      buttonPanel.add(saveButton);
      buttonPanel.add(saveAsButton);
      buttonPanel.add(closeButton);

      return buttonPanel;
  }
  
  private JButton createIconButton(String text, String iconPath) // need fix
  {
      JButton button = new JButton(text);
      try
      {
          // Load icon
          ImageIcon icon = new ImageIcon(ImageIO.read(new File(iconPath)));
          button.setIcon(icon);
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }
      return button;
  }

  private void setStateButtons(String state)
  {
    newButton.setEnabled(state.equals("null") || state.equals("changed"));
    openButton.setEnabled(true);
    saveButton.setEnabled(state.equals("changed"));
    saveAsButton.setEnabled(state.equals("changed") || state.equals("saved"));
    closeButton.setEnabled(state.equals("saved") || state.equals("changed"));
  }

  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(() -> new RecipeEditor().setVisible(true));
  }
}

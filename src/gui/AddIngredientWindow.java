package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import app.KILowBites;
import controller.AddIngredientController;
import utilities.ImageUtilities;

public class AddIngredientWindow extends JFrame
{
  private static final long serialVersionUID = 1L;

  private AddIngredientController controller;

  private JPanel namePanel;
  private JPanel caloriePanel;
  private JPanel densityPanel;
  private JPanel successPanel;

  public static JButton ingredientAddButton;
  public static JButton ingredientResetButton;

  public static JTextField ingredientNameField;
  public static JTextField ingredientCalorieField;
  public static JTextField ingredientDensityField;
  public static JLabel successfulAdd;

  // private static final String ADD_INGREDIENT = "Add Ingredient";
  // private static final String RESET = "Reset";
  // private static final String INGREDIENT_NAME = "Ingredient name:";

  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  public AddIngredientWindow()
  {
    super("Add Ingredient to System");

    controller = new AddIngredientController();

    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.CENTER));

    setupInputs();

    inputs.add(namePanel);
    inputs.add(caloriePanel);
    inputs.add(densityPanel);
    inputs.add(successPanel);

    add(inputs);

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    ingredientAddButton = new JButton(STRINGS.getString("ADD_INGREDIENT"));
    ingredientAddButton.setEnabled(false);
    ingredientAddButton.setToolTipText(STRINGS.getString("ADD_INGREDIENT"));
    ingredientAddButton.setActionCommand(STRINGS.getString("ADD_INGREDIENT"));
    ingredientAddButton.addActionListener(controller);

    ingredientResetButton = new JButton(
        ImageUtilities.getFormattedImage("reset.png", Color.GRAY, 25, 25));
    ingredientResetButton.setEnabled(false);
    ingredientResetButton.setToolTipText(STRINGS.getString("RESET"));
    ingredientResetButton.setActionCommand(STRINGS.getString("RESET"));
    ingredientResetButton.addActionListener(controller);

    toolbar.add(ingredientAddButton);
    toolbar.add(ingredientResetButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    setSize(800, 200);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Set up inputs in window.
   */
  private void setupInputs()
  {
    // NAME PANEL
    namePanel = new JPanel();
    namePanel.setLayout(new BorderLayout());

    ingredientNameField = new JTextField(15);
    ingredientNameField.getDocument().addDocumentListener(controller);

    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(STRINGS.getString("INGREDIENT_NAME")), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(ingredientNameField, gbc);

    namePanel.add(boxPanel, BorderLayout.CENTER);

    // CALORIE PANEL
    caloriePanel = new JPanel();
    caloriePanel.setLayout(new BorderLayout());

    ingredientCalorieField = new JTextField(5);
    ingredientCalorieField.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("cal/100g:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(ingredientCalorieField, gbc);

    caloriePanel.add(boxPanel, BorderLayout.CENTER);

    // DENSITY PANEL
    densityPanel = new JPanel();
    densityPanel.setLayout(new BorderLayout());

    ingredientDensityField = new JTextField(5);
    ingredientDensityField.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("g/mL:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(ingredientDensityField, gbc);

    densityPanel.add(boxPanel, BorderLayout.CENTER);

    // SUCCESS PANEL
    successPanel = new JPanel();
    successPanel.setLayout(new BorderLayout());

    successfulAdd = new JLabel("");

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(successfulAdd, gbc);

    successPanel.add(boxPanel, BorderLayout.CENTER);
  }
}

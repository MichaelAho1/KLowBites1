package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import app.KILowBites;
import controller.CalorieCalculatorController;
import utilities.ImageUtilities;

/**
 * Main window for KiLowBites calorie calculator.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class CalorieCalculatorWindow extends JFrame
{
  private static final long serialVersionUID = 1L;

  private String[] ingredients;
  private String[] units;
  private CalorieCalculatorController controller;

  private JPanel ingredientsPanel;
  private JPanel amountPanel;
  private JPanel unitsPanel;
  private JPanel outputPanel;

  public static JButton calorieCalcButton;
  public static JButton calorieResetButton;
  public static JButton calorieOpenButton;

  public static JComboBox calorieIngredientsMenu;
  public static JComboBox calorieUnitsMenu;
  public static JTextField calorieAmountField;
  public static JLabel calorieOutputField;

  /**
   * Default constructor.
   */
  public CalorieCalculatorWindow()
  {
    super("KILowBites Calorie Calculator");

    controller = new CalorieCalculatorController();

    // adding input/output components
    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT));

    ingredients = KILowBites.FOODS.getFoodNames();
    
    // if User selected Metric {
    //   units = UnitType.getMetricUnits();
    // }
    /* else {
     *   units = UnitType.getImperialUnits();
     * }
     */
    units = KILowBites.UNITS.getAllUnits();

    setupInputs();

    inputs.add(ingredientsPanel);
    inputs.add(amountPanel);
    inputs.add(unitsPanel);
    inputs.add(outputPanel);

    add(inputs);

    // Create toolbar with buttons (and associated icons)
    // buttons should be set disabled at first
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    calorieCalcButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/calculate.png", Color.GRAY, 25, 25));
    calorieCalcButton.setEnabled(false);
    calorieCalcButton.setToolTipText("Calculate");
    calorieCalcButton.setActionCommand("Calculate");
    calorieCalcButton.addActionListener(controller);

    calorieResetButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/reset.png", Color.GRAY, 25, 25));
    calorieResetButton.setEnabled(false);
    calorieResetButton.setToolTipText("Reset");
    calorieResetButton.setActionCommand("Reset");
    calorieResetButton.addActionListener(controller);

    calorieOpenButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/open.png", Color.GRAY, 25, 25));
    calorieOpenButton.setToolTipText("Open Recipe/Meal");
    calorieOpenButton.setActionCommand("Open");
    calorieOpenButton.addActionListener(controller);

    toolbar.add(calorieOpenButton);
    toolbar.add(calorieCalcButton);
    toolbar.add(calorieResetButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    setSize(700, 200);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  @Override
  protected void processWindowEvent(java.awt.event.WindowEvent e)
  {
    super.processWindowEvent(e);
    if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
    {
      System.out.println("Closing calorie calculator...");
      KILowBites.openCalc.setEnabled(true);
    }
  }

  /**
   * Set up inputs in window.
   */
  private void setupInputs()
  {
    // INGREDIENT PANEL
    ingredientsPanel = new JPanel();
    ingredientsPanel.setLayout(new BorderLayout());

    calorieIngredientsMenu = new JComboBox(ingredients);
    calorieIngredientsMenu.setActionCommand("Choose Ingredient");
    calorieIngredientsMenu.addActionListener(controller);

    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // west -> label, center -> ingredients
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Ingredient:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(calorieIngredientsMenu, gbc);

    ingredientsPanel.add(boxPanel, BorderLayout.CENTER);

    // AMOUNT PANEL
    amountPanel = new JPanel();
    amountPanel.setLayout(new BorderLayout());

    calorieAmountField = new JTextField(8);
    calorieAmountField.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Amount:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(calorieAmountField, gbc);

    amountPanel.add(boxPanel, BorderLayout.CENTER);

    // UNITS PANEL
    unitsPanel = new JPanel();
    unitsPanel.setLayout(new BorderLayout());

    calorieUnitsMenu = new JComboBox(units);
    calorieUnitsMenu.setActionCommand("Choose Unit");
    calorieUnitsMenu.addActionListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Units:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(calorieUnitsMenu, gbc);

    unitsPanel.add(boxPanel, BorderLayout.CENTER);

    // OUTPUT PANEL
    outputPanel = new JPanel();
    outputPanel.setLayout(new BorderLayout());

    calorieOutputField = new JLabel("___________");

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Calories:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(calorieOutputField, gbc);

    outputPanel.add(boxPanel, BorderLayout.CENTER);
  }
}

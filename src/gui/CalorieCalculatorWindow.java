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
import utilities.ImageUtilities;

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

  static JButton calculateButton;
  static JButton resetButton;

  static JComboBox ingredientsMenu;
  static JComboBox unitsMenu;
  static JTextField inputAmount;
  static JLabel outputField;

  public CalorieCalculatorWindow()
  {
    super("Calorie Calculator");

    controller = new CalorieCalculatorController();

    // adding input/output components
    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT));

    ingredients = new String[] {"", "bananas", "eggs", "sugar"};
    units = new String[] {"", "g", "mL"};

    setupInputs();

    inputs.add(ingredientsPanel);
    inputs.add(amountPanel);
    inputs.add(unitsPanel);
    inputs.add(outputPanel);

    add(inputs);

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    // Create buttons with icons
    calculateButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/calculate.png", Color.GRAY, 25, 25));
    calculateButton.setEnabled(false);
    calculateButton.setActionCommand("Calculate");
    calculateButton.addActionListener(controller);

    resetButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/reset.png", Color.GRAY, 25, 25));
    resetButton.setEnabled(false);
    resetButton.setActionCommand("Reset");
    resetButton.addActionListener(controller);

    toolbar.add(calculateButton);
    toolbar.add(resetButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    setSize(650, 200);
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

    ingredientsMenu = new JComboBox(ingredients);
    ingredientsMenu.setActionCommand("Choose Ingredient");
    ingredientsMenu.addActionListener(controller);

    // creating box for ingredients
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
    boxPanel.add(ingredientsMenu, gbc);

    ingredientsPanel.add(boxPanel, BorderLayout.CENTER);

    // AMOUNT PANEL
    amountPanel = new JPanel();
    amountPanel.setLayout(new BorderLayout());

    inputAmount = new JTextField(8);
    inputAmount.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Amount:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(inputAmount, gbc);

    amountPanel.add(boxPanel, BorderLayout.CENTER);

    // UNITS PANEL
    unitsPanel = new JPanel();
    unitsPanel.setLayout(new BorderLayout());

    unitsMenu = new JComboBox(units);
    unitsMenu.setActionCommand("Choose Unit");
    unitsMenu.addActionListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Units:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(unitsMenu, gbc);

    unitsPanel.add(boxPanel, BorderLayout.CENTER);

    // OUTPUT PANEL
    outputPanel = new JPanel();
    outputPanel.setLayout(new BorderLayout());

    outputField = new JLabel("___________");

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Calories:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(outputField, gbc);

    outputPanel.add(boxPanel, BorderLayout.CENTER);
  }
}

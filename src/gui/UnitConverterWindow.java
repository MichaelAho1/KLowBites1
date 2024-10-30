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

/**
 * Main window for KiLowBites unit converter.
 * 
 * @author f24team3d
 * @version 10/29/24
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class UnitConverterWindow extends JFrame
{
  private static final long serialVersionUID = 1L;
  private String[] volumeUnits; // MAYBE UNNEEDED
  private String[] massUnits; // MAYBE UNNEEDED
  private String[] units;
  private String[] ingredients;
  private UnitConverterController controller;

  private JPanel ingredientsPanel;
  private JPanel fromUnitsPanel;
  private JPanel toUnitsPanel;
  private JPanel fromAmountPanel;
  private JPanel outputPanel;

  static JButton unitCalcButton;
  static JButton unitResetButton;

  static JComboBox fromUnitsMenu;
  static JComboBox toUnitsMenu;
  static JComboBox unitIngredientsMenu;
  static JTextField fromAmountField;
  static JLabel unitOutputField;

  /**
   * Default constructor.
   */
  public UnitConverterWindow()
  {
    super("KiLowBites Unit Converter");

    controller = new UnitConverterController();

    // MAYBE REMOVE
    // potentially create Units class
    volumeUnits = new String[] {"Milliliter", "Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce",
        "Cup", "Pint", "Quart", "Gallon"};
    massUnits = new String[] {"", "Gram", "Dram", "Ounce", "Pound"};

    units = new String[] {"", "Gram", "Dram", "Ounce", "Pound", "Milliliter", "Pinch", "Teaspoon",
        "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon"};

    ingredients = KILowBites.FOODS.getFoodNames();

    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT));

    setupInputs();

    inputs.add(fromUnitsPanel);
    inputs.add(toUnitsPanel);
    inputs.add(ingredientsPanel);
    inputs.add(fromAmountPanel);
    inputs.add(outputPanel);

    add(inputs);

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    unitCalcButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/calculate.png", Color.GRAY, 25, 25));
    unitCalcButton.setEnabled(false);
    unitCalcButton.setActionCommand("Calculate");
    unitCalcButton.addActionListener(controller);

    unitResetButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/reset.png", Color.GRAY, 25, 25));
    unitResetButton.setEnabled(false);
    unitResetButton.setActionCommand("Reset");
    unitResetButton.addActionListener(controller);

    toolbar.add(unitCalcButton);
    toolbar.add(unitResetButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    setSize(720, 200);
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
      System.out.println("Closing unit converter...");
      KILowBites.openConvert.setEnabled(true);
    }
  }

  /**
   * Set up inputs in window.
   */
  private void setupInputs()
  {
    // FROM UNITS PANEL
    fromUnitsPanel = new JPanel();
    fromUnitsPanel.setLayout(new BorderLayout());

    fromUnitsMenu = new JComboBox(units);
    fromUnitsMenu.setActionCommand("From Unit");
    fromUnitsMenu.addActionListener(controller);

    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // west -> label, center -> ingredients
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("From Units:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(fromUnitsMenu, gbc);

    fromUnitsPanel.add(boxPanel, BorderLayout.CENTER);

    // TO UNITS PANEL
    toUnitsPanel = new JPanel();
    toUnitsPanel.setLayout(new BorderLayout());

    toUnitsMenu = new JComboBox(units);
    toUnitsMenu.setActionCommand("To Unit");
    toUnitsMenu.addActionListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("To Units:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(toUnitsMenu, gbc);

    toUnitsPanel.add(boxPanel, BorderLayout.CENTER);

    // INGREDIENTS PANEL
    ingredientsPanel = new JPanel();
    ingredientsPanel.setLayout(new BorderLayout());

    unitIngredientsMenu = new JComboBox(ingredients);
    unitIngredientsMenu.setActionCommand("Choose Ingredient");
    unitIngredientsMenu.addActionListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Ingredient:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(unitIngredientsMenu, gbc);

    ingredientsPanel.add(boxPanel, BorderLayout.CENTER);

    // FROM AMOUNT PANEL
    fromAmountPanel = new JPanel();
    fromAmountPanel.setLayout(new BorderLayout());

    fromAmountField = new JTextField(8);
    fromAmountField.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("Amount:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(fromAmountField, gbc);

    fromAmountPanel.add(boxPanel, BorderLayout.CENTER);

    // OUTPUT PANEL
    outputPanel = new JPanel();
    outputPanel.setLayout(new BorderLayout());

    unitOutputField = new JLabel("___________");

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("To Amount:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(unitOutputField, gbc);

    outputPanel.add(boxPanel, BorderLayout.CENTER);
  }
}

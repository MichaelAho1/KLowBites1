package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.KILowBites;

/**
 * Main window for KiLowBites unit converter.
 * 
 * @author f24team3d
 * @version 10/28/24
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

  @SuppressWarnings({"unchecked", "rawtypes"})
  // Creating the GUI
  public static void constructWindow()
  {
    JFrame frame = new JFrame("KiLowBitesUnit Converter");
    frame.setSize(600, 220);
    frame.setLayout(new BorderLayout());

    JPanel center = new JPanel();
    center.setLayout(new GridLayout(2, 3));

    // JComboBox fromUnitBox = new JComboBox<>();
    // JComboBox toUnitBox = new JComboBox<>();
    // for (int i = 0; i < massUnits.length; i++)
    // {
    // fromUnitBox.addItem(massUnits[i]);
    // toUnitBox.addItem(massUnits[i]);
    // }
    // for (int i = 0; i < volumeUnits.length; i++)
    // {
    // fromUnitBox.addItem(volumeUnits[i]);
    // toUnitBox.addItem(volumeUnits[i]);
    // }

    JLabel fromUnitsLabel = new JLabel("From Units:");
    JPanel fromUnitPanel = new JPanel();
    fromUnitPanel.setLayout(new GridLayout(1, 2));
    fromUnitPanel.add(fromUnitsLabel);
    // fromUnitPanel.add(fromUnitBox);
    fromUnitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    center.add(fromUnitPanel);

    JLabel toUnitsLabel = new JLabel("To Units:");
    JPanel toUnitPanel = new JPanel();
    toUnitPanel.setLayout(new GridLayout(1, 2));
    toUnitPanel.add(toUnitsLabel);
    // toUnitPanel.add(toUnitBox);
    toUnitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    center.add(toUnitPanel);

    // JComboBox ingredientBox = new JComboBox<>();
    // for (int i = 0; i < ingredientsList.length; i++)
    // {
    // ingredientBox.addItem(ingredientsList[i]);
    // }
    JLabel ingredientLabel = new JLabel("Ingredient:");
    JPanel ingredientPanel = new JPanel();
    ingredientPanel.setLayout(new GridLayout(1, 2));
    ingredientPanel.add(ingredientLabel);
    // ingredientPanel.add(ingredientBox);
    ingredientPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    center.add(ingredientPanel);

    JLabel fromAmountLabel = new JLabel("From Amount:");
    JPanel fromAmountPanel = new JPanel();
    fromAmountPanel.setLayout(new GridLayout(1, 2));
    fromAmountPanel.add(fromAmountLabel);
    JTextField fromAmountField = new JTextField();
    fromAmountPanel.add(fromAmountField);
    fromAmountPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    center.add(fromAmountPanel);

    JLabel toAmountLabel = new JLabel("To Amount:");
    JPanel toAmountPanel = new JPanel();
    toAmountPanel.setLayout(new GridLayout(1, 2));
    toAmountPanel.add(toAmountLabel);
    JLabel toAmountField = new JLabel("___________"); // Needs to have less height
    toAmountPanel.add(toAmountField);
    toAmountPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    center.add(toAmountPanel);

    JButton tempCalculate = new JButton("Calc");
    // tempCalculate.addActionListener(new ActionListener()
    // {
    // @Override
    // public void actionPerformed(ActionEvent e)
    // {
    // String fromUnits = (String) fromUnitBox.getSelectedItem();
    // String toUnits = (String) toUnitBox.getSelectedItem();
    // String ingredients = (String) ingredientBox.getSelectedItem();
    // Double fromAmount = Double.parseDouble(fromAmountField.getText());
    // if (Arrays.asList(volumeUnits).contains(fromUnits)) // Volume
    // {
    // if (Arrays.asList(volumeUnits).contains(toUnits)) // Volume to Volume
    // {
    // toAmountField
    // .setText(VolumeConverter.callerHelp(fromUnits, toUnits, fromAmount).toString());
    // }
    // else // Volume to Mass
    // {
    // // Need to add density (Currently has a placeholder of 1.04
    // toAmountField.setText(
    // MassToVolume.interConverting(fromUnits, toUnits, fromAmount, 1.04).toString());
    // }
    // }
    // else // Mass
    // {
    // if (Arrays.asList(massUnits).contains(toUnits)) // Mass to Mass
    // {
    // toAmountField
    // .setText(MassConverter.callerHelp(fromUnits, toUnits, fromAmount).toString());
    // }
    // else // Mass To Volume
    // {
    // // Need to add density (Currently has a placeholder of 1.04
    // toAmountField.setText(
    // MassToVolume.interConverting(fromUnits, toUnits, fromAmount, 1.04).toString());
    // }
    // }
    // }
    //
    // });

    JButton tempReset = new JButton("Reset");
    JPanel north = new JPanel();
    north.setLayout(new FlowLayout(FlowLayout.LEFT));
    north.add(tempCalculate);
    north.add(tempReset);

    frame.add(center, BorderLayout.CENTER);
    frame.add(north, BorderLayout.NORTH);
    frame.setVisible(true);
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
    boxPanel.add(new JLabel("Calories:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(unitOutputField, gbc);

    outputPanel.add(boxPanel, BorderLayout.CENTER);
  }
}

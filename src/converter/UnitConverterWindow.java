package converter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UnitConverterWindow /*implements ActionListener*/
{
  private static String[] volumeUnits = {"Milliliter", "Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon"};
  private static String[] massUnits = {"", "Gram", "Dram", "Ounce", "Pound"};
  // temp ingredient list
  private static String[] ingredientList = {"", "Meat", "Apple", "Pepper"}; //Need the actual ingrediets

  @SuppressWarnings({ "unchecked", "rawtypes" })
  // Creating the GUI
  public static void constructWindow()
  {
    JFrame frame = new JFrame("KiLowBitesUnit Converter");
    frame.setSize(600, 220);
    frame.setLayout(new BorderLayout());

    JPanel center = new JPanel();
    center.setLayout(new GridLayout(2, 3));

    JComboBox fromUnitBox = new JComboBox<>();
    JComboBox toUnitBox = new JComboBox<>();
    for (int i = 0; i < massUnits.length; i++)
    {
      fromUnitBox.addItem(massUnits[i]);
      toUnitBox.addItem(massUnits[i]);
    }
    for (int i = 0; i < volumeUnits.length; i++)
    {
      fromUnitBox.addItem(volumeUnits[i]);
      toUnitBox.addItem(volumeUnits[i]);
    }

    JLabel fromUnitsLabel = new JLabel("From Units:");
    JPanel fromUnitPanel = new JPanel();
    fromUnitPanel.setLayout(new GridLayout(1, 2));
    fromUnitPanel.add(fromUnitsLabel);
    fromUnitPanel.add(fromUnitBox);
    fromUnitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    center.add(fromUnitPanel);

    JLabel toUnitsLabel = new JLabel("To Units:");
    JPanel toUnitPanel = new JPanel();
    toUnitPanel.setLayout(new GridLayout(1, 2));
    toUnitPanel.add(toUnitsLabel);
    toUnitPanel.add(toUnitBox);
    toUnitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    center.add(toUnitPanel);

    JComboBox ingredientBox = new JComboBox<>();
    for (int i = 0; i < ingredientList.length; i++)
    {
      ingredientBox.addItem(ingredientList[i]);
    }
    JLabel ingredientLabel = new JLabel("Ingredient:");
    JPanel ingredientPanel = new JPanel();
    ingredientPanel.setLayout(new GridLayout(1, 2));
    ingredientPanel.add(ingredientLabel);
    ingredientPanel.add(ingredientBox);
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
    tempCalculate.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String fromUnits = (String) fromUnitBox.getSelectedItem();
        String toUnits = (String) toUnitBox.getSelectedItem();
        String ingredients = (String) ingredientBox.getSelectedItem();
        Double fromAmount = Double.parseDouble(fromAmountField.getText());
        if (Arrays.asList(volumeUnits).contains(fromUnits)) // Volume
        { 
          if (Arrays.asList(volumeUnits).contains(toUnits)) // Volume to Volume
          { 
            toAmountField.setText(VolumeConverter.callerHelp(fromUnits, toUnits, fromAmount).toString());
          } 
          else //Volume to Mass
          {
            toAmountField.setText(MassToVolume.interConverting(fromUnits, toUnits, fromAmount, 1.04).toString()); // Need to add density (Currently has a placeholder of 1.04
          }
        }
        else // Mass
        { 
          if (Arrays.asList(massUnits).contains(toUnits)) // Mass to Mass
          {
            toAmountField.setText(MassConverter.callerHelp(fromUnits, toUnits, fromAmount).toString());
          } 
          else // Mass To Volume
          {
            toAmountField.setText(MassToVolume.interConverting(fromUnits, toUnits, fromAmount, 1.04).toString()); // Need to add density (Currently has a placeholder of 1.04
          }
        }
      }
      
    });

    JButton tempReset = new JButton("Reset");
    JPanel north = new JPanel();
    north.setLayout(new FlowLayout(FlowLayout.LEFT));
    north.add(tempCalculate);
    north.add(tempReset);

    frame.add(center, BorderLayout.CENTER);
    frame.add(north, BorderLayout.NORTH);
    frame.setVisible(true);
  }

  public void actionPerformed(final ActionEvent e) {
      
  }

  public void calculate() {
    
  }
  
  
  public static void main(String[] args)
  {
    constructWindow();
  }
}


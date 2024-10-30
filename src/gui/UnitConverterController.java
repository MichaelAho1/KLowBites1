package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import converter.MassConverter;
import converter.MassToVolume;
import converter.VolumeConverter;

public class UnitConverterController implements ActionListener, DocumentListener
{
  private static final String CALCULATE = "Calculate";
  private static final String RESET = "Reset";
  private static final String FROM_UNITS = "From Unit";
  private static final String TO_UNITS = "To Unit";
  private static final String CHOOSE_INGREDIENT = "Choose Ingredient";

  private boolean sameMeasureType;

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(CALCULATE))
    {
      calculate();
    }
    if (ac.equals(RESET))
    {
      reset();
      updateResetButton();
      updateCalculateButton();
    }
    if (ac.equals(FROM_UNITS))
    {
      updateResetButton();
      updateCalculateButton();
      disableIngredients();
    }
    if (ac.equals(TO_UNITS))
    {
      updateResetButton();
      updateCalculateButton();
      disableIngredients();
    }
    if (ac.equals(CHOOSE_INGREDIENT))
    {
      updateResetButton();
      updateCalculateButton();
    }
  }

  @Override
  public void insertUpdate(DocumentEvent e)
  {
    updateResetButton();
    updateCalculateButton();
  }

  @Override
  public void removeUpdate(DocumentEvent e)
  {
    updateResetButton();
    updateCalculateButton();
  }

  @Override
  public void changedUpdate(DocumentEvent e)
  {
    updateResetButton();
    updateCalculateButton();
  }

  /*
   * Updates output field to correct conversion.
   */
  private void calculate()
  {
    double amount;
    try
    {
      // Retrieve and parse the input amount
      amount = Double.parseDouble(UnitConverterWindow.fromAmountField.getText().trim());
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      UnitConverterWindow.unitOutputField.setText("Invalid input");
      return;
    }

    // TODO: implement calculate
    String[] volumeUnits = KILowBites.UNITS.getVolumeUnits();
    String[] massUnits = KILowBites.UNITS.getMassUnits();

    String fromUnits = UnitConverterWindow.getFromUnitsMenu();
    String toUnits = UnitConverterWindow.getToUnitsMenu();
    Double fromAmount = UnitConverterWindow.getFromAmountField();
    String ingredient = UnitConverterWindow.getIngredientsUnitsMenu();
    Double density = KILowBites.FOODS.getDensity(ingredient);

    if (Arrays.asList(volumeUnits).contains(fromUnits)) // Volume
    {
      if (Arrays.asList(volumeUnits).contains(toUnits)) // Volume to Volume
      {
        amount = VolumeConverter.callerHelp(fromUnits, toUnits, fromAmount);
      }
      else // Volume to Mass
      {
        // Need to add density (Currently has a placeholder of 1.04)
        amount = MassToVolume.interConverting(fromUnits, toUnits, fromAmount, density);
      }
    }
    else // Mass
    {
      if (Arrays.asList(massUnits).contains(toUnits)) // Mass to Mass
      {
        amount = MassConverter.callerHelp(fromUnits, toUnits, fromAmount);
      }
      else // Mass To Volume
      {
        // Need to add density (Currently has a placeholder of 1.04
        amount = MassToVolume.interConverting(fromUnits, toUnits, fromAmount, density);
      }
      UnitConverterWindow.unitOutputField.setText(String.format("%.5f", amount));
      System.out.println("Calculating...");
    }
  }

  /**
   * Disable ingredient field if conversion does not involve mass AND volume.
   */
  private void disableIngredients()
  {
    String fromUnits = UnitConverterWindow.getFromUnitsMenu();
    String toUnits = UnitConverterWindow.getToUnitsMenu();

    if (fromUnits != null && toUnits != null)
    {
      String fromMeasureType = KILowBites.UNITS.unitMeasure(fromUnits);
      String toMeasureType = KILowBites.UNITS.unitMeasure(toUnits);

      sameMeasureType = fromMeasureType != null && fromMeasureType.equals(toMeasureType);
      UnitConverterWindow.unitIngredientsMenu.setEnabled(!sameMeasureType);
    }
    else
    {
      UnitConverterWindow.unitIngredientsMenu.setEnabled(true);
    }
  }

  /**
   * Reset all input menus and output field.
   */
  private void reset()
  {
    UnitConverterWindow.unitIngredientsMenu.setSelectedIndex(0);
    UnitConverterWindow.fromUnitsMenu.setSelectedIndex(0);
    UnitConverterWindow.toUnitsMenu.setSelectedIndex(0);
    UnitConverterWindow.fromAmountField.setText("");
    UnitConverterWindow.unitOutputField.setText("___________");
  }

  /**
   * Enable reset button if any there are any inputs.
   */
  private void updateResetButton()
  {
    boolean empty = !UnitConverterWindow.unitIngredientsMenu.getSelectedItem().toString().isEmpty()
        || !UnitConverterWindow.fromUnitsMenu.getSelectedItem().toString().isEmpty()
        || !UnitConverterWindow.fromAmountField.getText().trim().isEmpty()
        || !UnitConverterWindow.toUnitsMenu.getSelectedItem().toString().isEmpty();
    UnitConverterWindow.unitResetButton.setEnabled(empty);
  }

  /**
   * Enable calculate button if all inputs are entered.
   */
  private void updateCalculateButton()
  {
    boolean empty;
    if (sameMeasureType)
    {
      empty = !UnitConverterWindow.fromUnitsMenu.getSelectedItem().toString().isEmpty()
          && !UnitConverterWindow.fromAmountField.getText().trim().isEmpty()
          && !UnitConverterWindow.toUnitsMenu.getSelectedItem().toString().isEmpty();
    }
    else
    {
      empty = !UnitConverterWindow.unitIngredientsMenu.getSelectedItem().toString().isEmpty()
          && !UnitConverterWindow.fromUnitsMenu.getSelectedItem().toString().isEmpty()
          && !UnitConverterWindow.fromAmountField.getText().trim().isEmpty()
          && !UnitConverterWindow.toUnitsMenu.getSelectedItem().toString().isEmpty();
    }
    UnitConverterWindow.unitCalcButton.setEnabled(empty);
  }
}

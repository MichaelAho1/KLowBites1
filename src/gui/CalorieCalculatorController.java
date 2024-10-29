package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;

/**
 * Controller for actions in CalorieCalculatorWindow.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

public class CalorieCalculatorController implements ActionListener, DocumentListener
{
  static final String CALCULATE = "Calculate";
  static final String RESET = "Reset";
  static final String ENTER_AMOUNT = "Enter Amount";
  static final String CHOOSE_INGREDIENT = "Choose Ingredient";
  static final String CHOOSE_UNIT = "Choose Unit";

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub
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
    if (ac.equals(CHOOSE_INGREDIENT))
    {
      updateResetButton();
      updateCalculateButton();
    }
    if (ac.equals(CHOOSE_UNIT))
    {
      updateResetButton();
      updateCalculateButton();
    }
    if (ac.equals(ENTER_AMOUNT))
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

  /**
   * Updates output field to calculated number of calories.
   */
  private void calculate()
  {
    double amount;
    try
    {
      // Retrieve and parse the input amount
      amount = Double.parseDouble(CalorieCalculatorWindow.calorieAmountField.getText().trim());
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      CalorieCalculatorWindow.calorieOutputField.setText("Invalid input");
      return;
    }

    // retrieve ingredient name from input and get values associated with ingredient
    String ingredient = CalorieCalculatorWindow.calorieIngredientsMenu.getSelectedItem().toString();
    double[] values = KILowBites.FOODS.getFoods().get(ingredient);

    double gramsPerUnit;
    if (CalorieCalculatorWindow.calorieUnitsMenu.getSelectedItem().toString().equals("g"))
    {
      // Use the value in grams directly if "g" is selected
      gramsPerUnit = 1.0; // 1 gram = 1 gram
    }
    else
    {
      // Use g/ml conversion
      gramsPerUnit = values[1];
    }

    // Calculate total calories
    double calories = ((amount * gramsPerUnit) / 100) * values[0];

    // Display result to 2 decimal places
    CalorieCalculatorWindow.calorieOutputField.setText(String.format("%.2f", calories));
  }

  /**
   * Reset all input menus and output field.
   */
  private void reset()
  {
    CalorieCalculatorWindow.calorieIngredientsMenu.setSelectedIndex(0);
    CalorieCalculatorWindow.calorieUnitsMenu.setSelectedIndex(0);
    CalorieCalculatorWindow.calorieAmountField.setText("");
    CalorieCalculatorWindow.calorieOutputField.setText("___________");
  }

  /**
   * Enable reset button if any there are any inputs.
   */
  private void updateResetButton()
  {
    boolean empty = !CalorieCalculatorWindow.calorieIngredientsMenu.getSelectedItem().toString()
        .isEmpty()
        || !CalorieCalculatorWindow.calorieUnitsMenu.getSelectedItem().toString().isEmpty()
        || !CalorieCalculatorWindow.calorieAmountField.getText().trim().isEmpty();
    CalorieCalculatorWindow.calorieResetButton.setEnabled(empty);
  }

  /**
   * Enable calculate button if all inputs are entered.
   */
  private void updateCalculateButton()
  {
    boolean empty = !CalorieCalculatorWindow.calorieIngredientsMenu.getSelectedItem().toString()
        .isEmpty()
        && !CalorieCalculatorWindow.calorieUnitsMenu.getSelectedItem().toString().isEmpty()
        && !CalorieCalculatorWindow.calorieAmountField.getText().trim().isEmpty();
    CalorieCalculatorWindow.calorieCalcButton.setEnabled(empty);
  }
}

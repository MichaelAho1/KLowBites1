package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 */

public class CalorieCalculatorController implements ActionListener, DocumentListener
{
  static final String CALCULATE = "Calculate";
  static final String RESET = "Reset";
  static final String ENTER_AMOUNT = "Enter Amount";
  static final String CHOOSE_INGREDIENT = "Choose Ingredient";
  static final String CHOOSE_UNIT = "Choose Unit";

  public CalorieCalculatorController()
  {

  }

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

  private void reset()
  {
    CalorieCalculatorWindow.ingredientsMenu.setSelectedIndex(0);
    CalorieCalculatorWindow.unitsMenu.setSelectedIndex(0);
    CalorieCalculatorWindow.inputAmount.setText("");
    CalorieCalculatorWindow.outputField.setText("___________");
  }

  private void updateResetButton()
  {
    boolean empty = !CalorieCalculatorWindow.ingredientsMenu.getSelectedItem().toString().isEmpty()
        || !CalorieCalculatorWindow.unitsMenu.getSelectedItem().toString().isEmpty()
        || !CalorieCalculatorWindow.inputAmount.getText().trim().isEmpty();
    CalorieCalculatorWindow.resetButton.setEnabled(empty);
  }

  private void updateCalculateButton()
  {
    boolean empty = !CalorieCalculatorWindow.ingredientsMenu.getSelectedItem().toString().isEmpty()
        && !CalorieCalculatorWindow.unitsMenu.getSelectedItem().toString().isEmpty()
        && !CalorieCalculatorWindow.inputAmount.getText().trim().isEmpty();
    CalorieCalculatorWindow.calculateButton.setEnabled(empty);
  }

  @Override
  public void insertUpdate(DocumentEvent e)
  {
    updateResetButton();
  }

  @Override
  public void removeUpdate(DocumentEvent e)
  {
    updateResetButton();
  }

  @Override
  public void changedUpdate(DocumentEvent e)
  {
    updateResetButton();
  }

  /**
   * Updates output field to calculated number of calories.
   */
  private void calculate()
  {
    double grams;

    if (CalorieCalculatorWindow.unitsMenu.getSelectedItem().toString().equals("g"))
    {
      grams = CalorieCalculatorWindow.foodsMap
          .get(CalorieCalculatorWindow.ingredientsMenu.getSelectedItem().toString())[0];
    }
    else
    {
      grams = CalorieCalculatorWindow.foodsMap
          .get(CalorieCalculatorWindow.ingredientsMenu.getSelectedItem().toString())[1];
    }

    CalorieCalculatorWindow.outputField.setText("" + grams);
  }
}

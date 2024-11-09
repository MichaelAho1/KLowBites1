package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import converter.MassConverter;
import converter.VolumeConverter;
import cooking.Meal;
import cooking.Recipe;
import gui.CalorieCalculatorWindow;
import gui.CalorieOutputWindow;
import utilities.FileUtilities;

/**
 * Controller for actions in CalorieCalculatorWindow.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

public class CalorieCalculatorController implements ActionListener, DocumentListener
{
  private static final String CALCULATE = "Calculate";
  private static final String RESET = "Reset";
  private static final String CHOOSE_INGREDIENT = "Choose Ingredient";
  private static final String CHOOSE_UNIT = "Choose Unit";
  private static final String OPEN = "Open";

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
    if (ac.equals(OPEN))
    {
      // open recipe/meal, parse, and store in object
      // using stored recipe/meal, call calculateRecipe/calculateMeal
      // make output window and pass in recipe/meal & calories

      System.out.println("Opening recipe/meal...");

      CalorieOutputWindow output;
      double calories;
      String data[] = FileUtilities.open();
      String extension = data[0].substring(data[0].length() - 4, data[0].length());

      if (!extension.equals(".rcp") && !extension.equals(".mel"))
      {
        output = new CalorieOutputWindow("Invalid file", 0.0);
      }
      else
      {
        if (extension.equals(".rcp"))
        {
          // Recipe recipe = FileUtilities.open();
          calories = calculateRecipe(null);
        }
        else
        {
          Meal meal = new Meal();
          calories = calculateMeal();
        }

        output = new CalorieOutputWindow(data[0], calories);
      }

      output.setVisible(true);
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
    String unit = CalorieCalculatorWindow.calorieUnitsMenu.getSelectedItem().toString();
    String unitMeasure = KILowBites.UNITS.unitMeasure(unit);
    double[] values = KILowBites.FOODS.getFoods().get(ingredient);

    // convert to grams/milliliters if not already, depending on mass/volume unit selected
    if (!(unit.equals("Gram")) || !(unit.equals("Milliliter")))
    {
      if (unitMeasure.equals("Mass"))
      {
        amount = MassConverter.callerHelp("Gram", unit, amount);
      }
      else
      {
        amount = VolumeConverter.callerHelp(unit, "Milliliter", amount);
      }
    }

    double gramsPerUnit;
    if (unitMeasure.equals("Mass"))
    {
      // Use the value in grams directly if mass unit
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

  private double calculateMeal()
  {
    double calories = 0.0;

    return calories;
  }

  private double calculateRecipe(Recipe recipe)
  {
    double calories = 0.0;

    return calories;
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

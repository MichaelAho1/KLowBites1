package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import gui.AddIngredientWindow;
import utilities.InputUtilities;

/**
 * Controller for AddIngredientWindow.
 * 
 * @author f24team3d
 * @version 12/2/24
 */
public class AddIngredientController implements ActionListener, DocumentListener
{
  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  // private static final String ADD_INGREDIENT = "Add Ingredient";
  // private static final String RESET = "Reset";

  private AddIngredientWindow window;

  /**
   * Constructor.
   * 
   * @param window
   *          Add Ingredient Window
   */
  public AddIngredientController(AddIngredientWindow window)
  {
    this.window = window;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(STRINGS.getString("ADD_INGREDIENT")))
    {
      addIngredient();
    }
    if (ac.equals(STRINGS.getString("RESET")))
    {
      reset();
      updateResetButton();
      updateAddButton();
    }

  }

  @Override
  public void insertUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();
  }

  @Override
  public void removeUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();
  }

  @Override
  public void changedUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();
  }

  /**
   * Adds ingredient to food list.
   */
  private void addIngredient()
  {
    String ingreName;

    if (InputUtilities.isAlphabetical(AddIngredientWindow.ingredientNameField.getText().trim()))
    {
      ingreName = AddIngredientWindow.ingredientNameField.getText().trim().toLowerCase();
    }
    else
    {
      AddIngredientWindow.successfulAdd.setText(STRINGS.getString("INVALID_INPUT"));
      return;
    }

    double cals100G, gML;
    try
    {
      // Retrieve and parse the input amount
      cals100G = Double.parseDouble(AddIngredientWindow.ingredientCalorieField.getText().trim());
      gML = Double.parseDouble(AddIngredientWindow.ingredientDensityField.getText().trim());
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      AddIngredientWindow.successfulAdd.setText(STRINGS.getString("INVALID_INPUT"));
      return;
    }

    if (cals100G < 0 || gML < 0)
    {
      AddIngredientWindow.successfulAdd.setText(STRINGS.getString("INVALID_INPUT"));
      return;
    }

    double[] values = {cals100G, gML};
    boolean success = KILowBites.FOODS.addFoods(ingreName, values);

    if (success)
    {
      AddIngredientWindow.successfulAdd.setText(
          String.format(LOCALE, STRINGS.getString("SUCCESSFULLY_ADDED") + " %s!", ingreName));

      AddIngredientWindow.added = true;

      new javax.swing.Timer(2000, e -> {
        window.dispose(); // Adjust this to match your window closing logic
      }).start();
    }
    else
    {
      AddIngredientWindow.successfulAdd
          .setText(String.format(LOCALE, STRINGS.getString("ALREADY_IN_SYSTEM"), ingreName));
    }
    System.out.println(Arrays.toString(KILowBites.FOODS.getFoodNames()));
  }

  /**
   * Reset all input menus and output field.
   */
  private void reset()
  {
    AddIngredientWindow.ingredientCalorieField.setText("");
    AddIngredientWindow.ingredientDensityField.setText("");
    AddIngredientWindow.successfulAdd.setText("");
  }

  private void updateAddButton()
  {
    boolean empty = !AddIngredientWindow.ingredientCalorieField.getText().trim().isEmpty()
        && !AddIngredientWindow.ingredientDensityField.getText().trim().isEmpty();
    AddIngredientWindow.ingredientAddButton.setEnabled(empty);
  }

  /**
   * Enable reset button if any there are any inputs.
   */
  private void updateResetButton()
  {
    boolean empty = !AddIngredientWindow.ingredientCalorieField.getText().trim().isEmpty()
        || !AddIngredientWindow.ingredientDensityField.getText().trim().isEmpty();
    AddIngredientWindow.ingredientResetButton.setEnabled(empty);
  }
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import gui.ShoppingListWindow;

public class ShoppingListController implements ActionListener, DocumentListener
{
  private static String PRINT = "Print";
  private Object[][] originalIngredients;

  public ShoppingListController(Object[][] originalIngredients)
  {
    this.originalIngredients = originalIngredients;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals(PRINT))
    {
      System.out.println("Printing...");
    }

  }

  @Override
  public void insertUpdate(DocumentEvent e)
  {
    updateTable();
  }

  @Override
  public void removeUpdate(DocumentEvent e)
  {
    updateTable();
  }

  @Override
  public void changedUpdate(DocumentEvent e)
  {
    updateTable();
  }

  /**
   * 
   */
  private void updateTable()
  {
    int people;

    try
    {
      // Retrieve and parse the input amount
      people = Integer.parseInt(ShoppingListWindow.peopleField.getText().trim());
      updateIngredientQuantities(people);
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      resetTable();
    }
  }

  /**
   * Update the ingredient quantities based on the number of people
   */
  private void updateIngredientQuantities(int people)
  {
    for (int row = 0; row < ShoppingListWindow.ingredients.length; row++)
    {
      double originalQuantity = (Double) ShoppingListWindow.ingredients[row][1];
      ShoppingListWindow.ingredients[row][1] = originalQuantity * people; // Update quantity
    }
    refreshTable();
  }

  private void resetTable()
  {
    ShoppingListWindow.ingredients = originalIngredients; // Reset to original data
    refreshTable();
  }

  /**
   * Refresh the table by resetting the model
   */
  private void refreshTable()
  {
    ShoppingListWindow.shoppingList.setModel(
        new DefaultTableModel(ShoppingListWindow.ingredients, ShoppingListWindow.COLUMNNAMES));
  }

}

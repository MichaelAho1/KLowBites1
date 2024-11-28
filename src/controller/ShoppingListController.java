package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.ShoppingListWindow;

public class ShoppingListController implements ActionListener, DocumentListener
{
//  private static String PRINT = "Print";
//  private static String CHANGE_UNIT = "Change Unit";
  private ShoppingListWindow parent;

  public ShoppingListController(ShoppingListWindow parent)
  {
    this.parent = parent;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("PRINT"))
    {
      System.out.println("Printing...");
    }

    if (command.equals("CHANGE_UNIT"))
    {
      parent.updateQuantity();
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
      people = Integer.parseInt(parent.getPeopleField().getText().trim());
      parent.refreshTable(people);
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      parent.defaultTable();
    }
  }

}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import app.KILowBites;
import controller.ShoppingListController;
import converter.MassConverter;
import converter.VolumeConverter;
import cooking.Ingredients;
import cooking.Meal;
import cooking.Recipe;
import utilities.ImageUtilities;
import utilities.InputUtilities;

public class ShoppingListWindow extends JFrame
{
  private static final long serialVersionUID = 1L;

  private ShoppingListController controller;
  private JButton printButton;

  private static Meal meal;
  public static final String[] COLUMNNAMES = {"Ingredient", "Quantity", "Unit", "Recipe", "Serves",
      "Aisle", "Price", "Index"};
  // private String[] columnNames = {"Ingredient", "Quantity", "Unit"};
  private List<Object[]> data;
  private Object[][] ingredients;
  private Object[][] originalIngredients;

  private JPanel peoplePanel;
  private JPanel shoppingListPanel;
  private JPanel inputs;
  private JComboBox<String> unitComboBox;

  private JTextField peopleField;
  private JTable shoppingList;

  public ShoppingListWindow(Meal meal)
  {
    super("KILowBites Shopping List Viewer: " + InputUtilities.separateByCapital(meal.getName()));

    ShoppingListWindow.meal = meal;

    data = getIngredients();
    ingredients = new Object[data.size()][7]; // [5] for testing
    originalIngredients = new Object[data.size()][7];

    for (int i = 0; i < data.size(); i++)
    {
      ingredients[i] = data.get(i).clone();
      originalIngredients[i] = data.get(i).clone();
    }

    controller = new ShoppingListController(this);

    inputs = new JPanel();
    inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));

    setupInputs();

    inputs.add(peoplePanel);
    // inputs.add(Box.createVerticalStrut(10));
    inputs.add(shoppingListPanel);

    add(inputs);

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    printButton = new JButton(ImageUtilities.getFormattedImage("print.png", Color.GRAY, 25, 25));
    printButton.setToolTipText("Print");
    printButton.setActionCommand("Print");
    printButton.addActionListener(controller);

    toolbar.add(printButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    setSize(800, 600);
    setResizable(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    this.setBackground(KILowBites.COLOR);
  }

  /**
   * Set up inputs in window.
   */
  @SuppressWarnings("serial")
  private void setupInputs()
  {
    // PEOPLE PANEL
    peoplePanel = new JPanel();
    peoplePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    peoplePanel.setBackground(KILowBites.COLOR);

    peopleField = new JTextField(5);
    peopleField.getDocument().addDocumentListener(controller);

    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    boxPanel.setBackground(KILowBites.COLOR);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(0, 5, 0, 0);
    boxPanel.add(new JLabel("Number of People: "), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(peopleField, gbc);

    peoplePanel.add(boxPanel, BorderLayout.CENTER);

    // SHOPPING LIST PANEL
    shoppingListPanel = new JPanel();
    shoppingListPanel.setSize(650, 215);
    shoppingListPanel.setLayout(new BorderLayout());
    shoppingListPanel.setBorder(BorderFactory.createTitledBorder("Shopping List"));

    shoppingListPanel.setBackground(KILowBites.COLOR);

    shoppingList = new JTable(new DefaultTableModel(originalIngredients, COLUMNNAMES))
    {
      @Override
      public boolean isCellEditable(int row, int column)
      {
        // Make the unit column editable only if the unit is not "Individual"
        if (column == 2)
        {
          String unit = (String) getValueAt(row, column);
          return !unit.equals("Individual");
        }
        return super.isCellEditable(row, column); // Default behavior for other columns
      }
    };

    shoppingList.getColumnModel().getColumn(1).setCellRenderer(new DecimalTableCellRenderer());
    shoppingList.getColumnModel().getColumn(6).setCellRenderer(new DecimalTableCellRenderer());

    unitComboBox = new JComboBox<>(KILowBites.UNITS.getAllUnitsNoPadding());
    unitComboBox.setActionCommand("Change Unit");
    unitComboBox.addActionListener(controller);
    shoppingList.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(unitComboBox));

    TableRowSorter<TableModel> sorter = new TableRowSorter<>(shoppingList.getModel());
    shoppingList.setRowSorter(sorter);

    JScrollPane scrollPane = new JScrollPane(shoppingList); // Add scrolling if needed
    shoppingListPanel.add(scrollPane, BorderLayout.CENTER);

  }

  @SuppressWarnings("serial")
  private void setupUpdatedInputs(Object[][] update)
  {
    inputs.remove(shoppingListPanel);
    inputs.revalidate();
    inputs.repaint();

    shoppingListPanel = new JPanel();
    shoppingListPanel.setSize(650, 215);
    shoppingListPanel.setLayout(new BorderLayout());
    shoppingListPanel.setBorder(BorderFactory.createTitledBorder("Shopping List"));

    shoppingListPanel.setBackground(KILowBites.COLOR);

    shoppingList = new JTable(new DefaultTableModel(update, COLUMNNAMES))
    {
      @Override
      public boolean isCellEditable(int row, int column)
      {
        // Make the unit column editable only if the unit is not "Individual"
        if (column == 2)
        {
          String unit = (String) getValueAt(row, column);
          return !unit.equals("Individual");
        }
        return super.isCellEditable(row, column); // Default behavior for other columns
      }
    };

    shoppingList.getColumnModel().getColumn(1).setCellRenderer(new DecimalTableCellRenderer());
    // shoppingList.getColumnModel().getColumn(6).setCellRenderer(new DecimalTableCellRenderer());

    unitComboBox = new JComboBox<>(KILowBites.UNITS.getAllUnitsNoPadding());
    unitComboBox.setActionCommand("Change Unit");
    unitComboBox.addActionListener(controller);
    shoppingList.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(unitComboBox));

    TableRowSorter<TableModel> sorter = new TableRowSorter<>(shoppingList.getModel());
    shoppingList.setRowSorter(sorter);

    JScrollPane scrollPane = new JScrollPane(shoppingList); // Add scrolling if needed
    shoppingListPanel.add(scrollPane, BorderLayout.CENTER);

    inputs.add(shoppingListPanel);
    inputs.revalidate();
    inputs.repaint();
  }

  private List<Object[]> getIngredients()
  {
    List<Object[]> ingredients = new ArrayList<>();
    int index = 0;

    for (Recipe r : meal.getRecipes())
    {
      for (Ingredients i : r.getIngredients())
      {
        Object[] info = new Object[] {i.getName(), i.getAmount(), i.getUnit(), r.getName(),
            r.getServes(), "???", "N/A", index};
        ingredients.add(info);
        index++;
      }
    }

    // sort ingredients alphabetically in shopping list
    // easier for user to see duplicate ingredients
    // ingredients.sort((o1, o2) -> ((String o1[0]).compareTo((String) o2[0]));

    return ingredients;
  }

  public void defaultTable()
  {
    setupUpdatedInputs(originalIngredients);
  }

  public void refreshTable(int people)
  {
    int i = 0;
    for (Object[] ingredient : ingredients)
    {
      int basePeople = (int) originalIngredients[i][4];

      double baseQuantity = (double) originalIngredients[i][1];

      double newQuantity = (baseQuantity / basePeople) * people;
      ingredient[1] = newQuantity;
      i++;
    }

    setupUpdatedInputs(ingredients);
  }

  public JTextField getPeopleField()
  {
    return peopleField;
  }

  public void updateQuantity()
  {
    int row = shoppingList.getSelectedRow();

    double newQuantity;
    double quantity = (double) shoppingList.getValueAt(row, 1);

    if (row != -1)
    {
      int index = (int) shoppingList.getValueAt(row, 7);

      String oldUnit = (String) ingredients[index][2];
      String oldUnitMeasure = KILowBites.UNITS.unitMeasure(oldUnit);

      String newUnit = (String) unitComboBox.getSelectedItem();
      String newUnitMeasure = KILowBites.UNITS.unitMeasure(newUnit);

      if (newUnit.equals(oldUnit))
      {
        System.out.println("same unit");
      }
      else
      {
        if (newUnitMeasure.equals("Mass") && oldUnitMeasure.equals("Mass"))
        {
          System.out.println("converting from " + oldUnit + " to " + newUnit);
          newQuantity = MassConverter.callerHelp(oldUnit, newUnit, quantity);

          shoppingList.setValueAt(newQuantity, row, 1);
        }
        else if (newUnitMeasure.equals("Volume") && oldUnitMeasure.equals("Volume"))
        {
          System.out.println("converting from " + oldUnit + " to " + newUnit);
          newQuantity = VolumeConverter.callerHelp(oldUnit, newUnit, quantity);

          shoppingList.setValueAt(newQuantity, row, 1);
        }
        else
        {
          System.out.println("DO NOT CHANGE MEASURES");
          return;
        }
        ingredients[index][2] = newUnit;
      }
    }
  }

  private int findIndex(String recipe, String ingredient)
  {
    int index = 0;
    for (Recipe r : meal.getRecipes())
    {
      if (r.getName().equals(recipe))
      {
        for (Ingredients i : r.getIngredients())
        {
          if (i.getName().equals(ingredient))
          {
            return index;
          }
        }
      }
      index++;
    }

    return index;
  }

  // Custom renderer to format numeric values
  class DecimalTableCellRenderer extends DefaultTableCellRenderer
  {
    private static final DecimalFormat format = new DecimalFormat("#.##");

    @Override
    public void setValue(Object value)
    {
      if (value instanceof Number)
      {
        value = format.format(value); // Format the number to 2 decimals
      }
      super.setValue(value);
    }
  }
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import app.KILowBites;
import controller.ShoppingListController;
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
      "Aisle", "Price"};
  // private String[] columnNames = {"Ingredient", "Quantity", "Unit"};
  private List<Object[]> data;
  public static Object[][] ingredients;

  private JPanel peoplePanel;
  private JPanel shoppingListPanel;

  public static JTextField peopleField;
  public static JTable shoppingList;

  public ShoppingListWindow(Meal meal)
  {
    super("KILowBites Shopping List Viewer: " + InputUtilities.separateByCapital(meal.getName()));

    this.meal = meal;

    Object[][] originalIngredients = getIngredients().toArray(new Object[0][]);

    controller = new ShoppingListController(originalIngredients);

    data = getIngredients();
    ingredients = new Object[data.size()][7]; // [5] for testing

    for (int i = 0; i < data.size(); i++)
    {
      ingredients[i] = data.get(i);
    }

    JPanel inputs = new JPanel();
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

    shoppingList = new JTable(new DefaultTableModel(ingredients, COLUMNNAMES))
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

    JComboBox<String> unitComboBox = new JComboBox<>(KILowBites.UNITS.getAllUnitsNoPadding());
    shoppingList.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(unitComboBox));

    TableRowSorter<TableModel> sorter = new TableRowSorter<>(shoppingList.getModel());
    shoppingList.setRowSorter(sorter);

    JScrollPane scrollPane = new JScrollPane(shoppingList); // Add scrolling if needed
    shoppingListPanel.add(scrollPane, BorderLayout.CENTER);

  }

  private List<Object[]> getIngredients()
  {
    List<Object[]> ingredients = new ArrayList<>();

    for (Recipe r : meal.getRecipes())
    {
      for (Ingredients i : r.getIngredients())
      {
        Object[] info = new Object[] {i.getName(), i.getAmount(), i.getUnit(), r.getName(),
            r.getServes(), "???", "N/A"};
        ingredients.add(info);
      }
    }

    // sort ingredients alphabetically in shopping list
    // easier for user to see duplicate ingredients
    ingredients.sort((o1, o2) -> ((String) o1[0]).compareTo((String) o2[0]));

    return ingredients;
  }

  // // Method to update the table based on the number of people
  // public static void updateTableQuantities(int people)
  // {
  // List<Object[]> updatedIngredients = new ArrayList<>();
  //
  // // Calculate quantities based on the number of people
  // for (Recipe r : meal.getRecipes())
  // {
  // for (Ingredients i : r.getIngredients())
  // {
  // double updatedAmount = i.getAmount() * people / r.getServes();
  // Object[] info = new Object[] {i.getName(), updatedAmount, i.getUnit(), r.getName(),
  // r.getServes(), "???", "N/A"};
  // updatedIngredients.add(info);
  // }
  // }
  //
  // updatedIngredients.sort((o1, o2) -> ((String) o1[0]).compareTo((String) o2[0]));
  //
  // shoppingList
  // .setModel(new DefaultTableModel(updatedIngredients.toArray(new Object[0][0]), COLUMNNAMES));
  // }
}

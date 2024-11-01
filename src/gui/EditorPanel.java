package gui;

import java.awt.*;
import javax.swing.*;

import controller.MealEditorController;
import controller.RecipeEditorController;
import cooking.*;
import utilities.Units;

/**
 * EditorPanel class. Flexibly handles the Editor panels for Editor frames.
 * Examples: Utensils, Ingredients, Steps, Recipes
 * (where the file changes go)
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class EditorPanel extends JPanel
{
  // strings needed to determine the type of the input, needed since no interface
  final String UTENSILS = "Utensils";
  final String INGREDIENTS = "Ingredients";
  final String STEPS = "Steps";

  Container contentPane;

  // shared variables
  JPanel fileEditorPanel;
  JButton deleteButton;
  JScrollPane scrollPane;

  // RecipeEditor variables
  DefaultListModel<RecipeElement> recipeFileArea;
  JList<RecipeElement> recipeList;

  // MealEditor variables
  DefaultListModel<Recipe> mealFileArea;
  JList<Recipe> mealList;

  /**
   * Constructor for EditorPanel (for RecipeEditor).
   *
   * @param inputFieldPanel the input field panel
   * @param controller the controller for the RecipeEditor
   */
  public EditorPanel(RecipeElementType type, InputFieldPanel inputFieldPanel, RecipeEditorController controller)
  {
    super();

    contentPane = new Container();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // set preferred size for EditorPanels
    this.setPreferredSize(new Dimension(650, 215));

    // creates the editor label border
    this.setBorder(BorderFactory.createTitledBorder(type.getLabel(true)));

    // creates the file editor
    fileEditorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // creates the lists shown in the file editor
    recipeFileArea = new DefaultListModel<>();
    recipeList = new JList<>(recipeFileArea);

    // creates the file area for the editor panel
    scrollPane = new JScrollPane(recipeList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(500, 125));

    // creates the delete button
    deleteButton = new JButton("Delete");
    deleteButton.addActionListener(controller);
    deleteButton.setActionCommand(type.getDeleteCommand());

    // adds the details field
    fileEditorPanel.add(scrollPane);

    // adds the delete button
    fileEditorPanel.add(deleteButton);

    // adds the input field panel
    contentPane.add(inputFieldPanel);

    // adds the file editor container
    contentPane.add(fileEditorPanel);

    this.add(contentPane);
  }

  /**
   * Constructor for EditorPanel (for MealEditor).
   *
   * @param name the name of the EditorPanel
   * @param inputFieldPanel the input field panel
   * @param controller the controller for the MealEditor
   * @param type the type of the input
   */
  public EditorPanel(String name, InputFieldPanel inputFieldPanel, MealEditorController controller)
  {
    super();

    this.setLayout(new BorderLayout());

    Container contentPane = new Container();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // set preferred size for EditorPanels
    this.setPreferredSize(new Dimension(800, 215));

    // creates the editor label border
    // this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
    this.setBorder(BorderFactory.createTitledBorder(""));

    // creates the file editor
    JPanel fileEditorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // creates the lists shown in the file editor
    mealFileArea = new DefaultListModel<Recipe>();
    mealList = new JList<Recipe>(mealFileArea);

    // creates the file area for the editor panel
    JScrollPane scrollPane = new JScrollPane(mealList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(500, 150));

    // creates the delete button
    deleteButton = new JButton("Delete");
    deleteButton.setActionCommand("Recipe Delete");
    deleteButton.addActionListener(controller);

    fileEditorPanel.add(scrollPane);
    fileEditorPanel.add(deleteButton);


    this.add(inputFieldPanel, BorderLayout.NORTH);
    this.add(fileEditorPanel, BorderLayout.CENTER);
    this.add(contentPane, BorderLayout.SOUTH);
  }

  public void reset()
  {
    recipeFileArea.clear();
  }

  public void addRecipeElement(RecipeElement element)
  {
    recipeFileArea.addElement(element);
  }

  public JList<RecipeElement> getRecipeList()
  {
    return recipeList;
  }

  public JList<Recipe> getMealList()
  {
    return mealList;
  }

  public Ingredients getSelectedIngredient(String name)
  {
    Ingredients[] ingredient = new Ingredients[recipeList.getModel().getSize()];

    for (int i = 0; i < ingredient.length; i++)
    {
      ingredient[i] = (Ingredients) recipeList.getModel().getElementAt(i);
    }

    for (int i = 0; i < ingredient.length; i++)
    {
      if (ingredient[i].getName().equals(name))
      {
        return ingredient[i];
      }
    }
    return null;
  }

  public Utensils getSelectedUtensil(String name)
  {
    Utensils[] utensil = new Utensils[recipeList.getModel().getSize()];

    for (int i = 0; i < utensil.length; i++)
    {
      utensil[i] = (Utensils) recipeList.getModel().getElementAt(i);
    }

    for (int i = 0; i < utensil.length; i++)
    {
      if (utensil[i].getName().equals(name))
      {
        return utensil[i];
      }
    }
    return null;
  }

  public void deleteRecipeElement()
  {
    try
    {
      int index = recipeList.getSelectedIndex();

      if (index >= 0 && index < recipeList.getModel().getSize());
      {
        recipeFileArea.remove(index);
      }
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      System.out.println("No element selected");
    }
  }

  public void deleteMealElement()
  {
    try
    {
      int index = mealList.getSelectedIndex();

      if (index >= 0 && index < mealList.getModel().getSize());
      {
        mealFileArea.remove(index);
      }
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      System.out.println("No element selected");
    }
  }
}

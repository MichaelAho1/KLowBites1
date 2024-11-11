package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controller.MealEditorController;
import controller.RecipeEditorController;
import cooking.Ingredients;
import cooking.Meal;
import cooking.Recipe;
import cooking.RecipeElement;
import cooking.RecipeElementType;
import cooking.Steps;
import cooking.Utensils;

/**
 * EditorPanel class. Flexibly handles the Editor panels for Editor frames. Examples: Utensils,
 * Ingredients, Steps, Recipes (where the file changes go)
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
   * @param inputFieldPanel
   *          the input field panel
   * @param controller
   *          the controller for the RecipeEditor
   */
  public EditorPanel(RecipeElementType type, Recipe recipe, InputFieldPanel inputFieldPanel,
      RecipeEditorController controller, boolean isNew)
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
    if (!isNew)
    {
      recipeFileArea = new DefaultListModel<>();
      recipeList = new JList<>(recipeFileArea);

      if (type == RecipeElementType.UTENSIL)
      {
        recipeFileArea.clear();
        for (Utensils utensil : recipe.getUtensils())
        {
          recipeFileArea.addElement(utensil);
        }
      }
      else if (type == RecipeElementType.INGREDIENT)
      {
        recipeFileArea.clear();
        for (Ingredients ingredient : recipe.getIngredients())
        {
          recipeFileArea.addElement(ingredient);
        }
      }
      else if (type == RecipeElementType.STEP)
      {
        recipeFileArea.clear();
        for (Steps step : recipe.getSteps())
        {
          recipeFileArea.addElement(step);
        }
      }
    }
    else
    {
      recipeFileArea = new DefaultListModel<>();
      recipeList = new JList<>(recipeFileArea);
    }

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
   * @param name
   *          the name of the EditorPanel
   * @param inputFieldPanel
   *          the input field panel
   * @param controller
   *          the controller for the MealEditor
   * @param type
   *          the type of the input
   */
  public EditorPanel(String name, Meal meal, InputFieldPanel inputFieldPanel,
      MealEditorController controller, boolean isNew)
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
    if (!isNew)
    {
      mealFileArea = new DefaultListModel<>();
      mealList = new JList<>(mealFileArea);

      for (Recipe r : meal.getRecipes())
      {
        addMealElement(r);
      }
    }
    else
    {
      mealFileArea = new DefaultListModel<>();
      mealList = new JList<>(mealFileArea);
    }

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
    try
    {
      recipeFileArea.clear();
      mealFileArea.clear();
    }
    catch (NullPointerException e)
    {
      System.out.println("nothing to clear");
    }
  }

  public void addRecipeElement(RecipeElement element)
  {
    recipeFileArea.addElement(element);
  }

  public void addMealElement(Recipe e)
  {
    mealFileArea.addElement(e);
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

  public Steps getSelectedStep(String action, String details)
  {
    Steps[] step = new Steps[recipeList.getModel().getSize()];

    for (int i = 0; i < step.length; i++)
    {
      step[i] = (Steps) recipeList.getModel().getElementAt(i);
    }

    for (int i = 0; i < step.length; i++)
    {
      if (step[i].getDetails().equals(details) && step[i].getAction().equals(action))
      {
        return step[i];
      }
    }
    return null;
  }

  public void deleteRecipeElement()
  {
    try
    {
      int index = recipeList.getSelectedIndex();

      if (index >= 0 && index < recipeList.getModel().getSize())
        ;
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

      if (index >= 0 && index < mealList.getModel().getSize())
        ;
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

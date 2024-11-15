package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import app.KILowBites;
import controller.MealEditorController;
import cooking.Meal;

/**
 * MealEditorContent class. Handles MealEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class MealEditorContent extends JPanel
{
  private static final long serialVersionUID = 1204273009868162205L;

  Container contentPane;

  Meal currentMeal; // meal being used

  InputFieldPanel mainMealIFP;
  InputFieldPanel recipeMealIFP;

  EditorPanel mealEditorPanel;

//  private static final String NAME = "Name: ";
//  private static final String ADD_RECIPE = "Add Recipe";
//  private static final String RECIPE_ADD = "Recipe Add";
//  private static final String RECIPES = "Recipes";
  
  static final Locale         LOCALE  = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  
  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller
   *          the controller for the RecipeEditor
   */
  public MealEditorContent(Meal meal, MealEditorController controller, boolean isNew)
  {
    super();

    currentMeal = meal;

    this.setBackground(KILowBites.COLOR);

    Container contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // **** INPUT FIELDS ****
    mainMealIFP = new InputFieldPanel();
    if (!isNew)
    {
      mainMealIFP.addJTextField(STRINGS.getString("NAME"), 50, meal.getName());
    }
    else
    {
      mainMealIFP.addJTextField(STRINGS.getString("NAME"), 50);
    }

    // **** EDITOR PANELS ****

    // Recipes
    recipeMealIFP = new InputFieldPanel();
    recipeMealIFP.addJButton(STRINGS.getString("ADD_RECIPE"), STRINGS.getString("RECIPE_ADD"), controller);

    // creates the panel for recipes
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new BorderLayout());

    mealEditorPanel = new EditorPanel(STRINGS.getString("RECIPES"), meal, recipeMealIFP, controller, isNew);

    editorPanel.add(mealEditorPanel, BorderLayout.CENTER);

    // adds the input fields into the content pane
    contentPane.add(mainMealIFP, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(editorPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

  public String getNameField()
  {
    return mainMealIFP.getText(STRINGS.getString("NAME"));
  }

  public void reset()
  {
    mainMealIFP.resetFields();
    recipeMealIFP.resetFields();
    mealEditorPanel.resetMeal();
  }

  public InputFieldPanel getMainIFP()
  {
    return mainMealIFP;
  }

  public InputFieldPanel getRecipeIFP()
  {
    return recipeMealIFP;
  }

  public EditorPanel getEditorPanel()
  {
    return mealEditorPanel;
  }
}

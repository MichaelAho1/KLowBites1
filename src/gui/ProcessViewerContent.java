package gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controller.RecipeEditorController;
import controller.ProcessViewerController;
import cooking.Meal;
import cooking.Recipe;
import cooking.RecipeElementType;
import utilities.Units;

/**
 * RecipeEditorContent class. Handles RecipeEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class ProcessViewerContent extends JPanel
{
  private static final String NAME = "Name: ";
  private static final String SERVES = "Serves: ";
  private static final String ADD = "Add";
  private static final String AMOUNT = "Amount: ";
  private static final String INGREDIENT_ADD = "Ingredient Add";
  private static final String STEP_ADD = "Step Add";
  private static final String UNITS = "Units: ";
  private static final String ACTION = "Action: ";
  private static final String DETAILS = "Details: ";
  private static final String ON = "On: ";
  private static final String UTENSIL = "Utensil: ";
  
  Container contentPane;

  Recipe currentRecipe; // recipe being used
  Meal currentMeal;
  JPanel ProcessViewerPanel;

  InputFieldPanel mainIFP;
  InputFieldPanel utensilIFP;
  InputFieldPanel ingredientIFP;
  InputFieldPanel stepIFP;

  ProcessViewerPanel utensilProcessViewerPanel;
  ProcessViewerPanel ingredientProcessViewerPanel;
  ProcessViewerPanel stepProcessViewerPanel;

  String[] stepOn;
  String[] stepUtensils;
  String[] stepIngredients;
  String[] stepSources;

  Units units;

  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller
   *          the controller for the RecipeEditor
   */
  public ProcessViewerContent(Recipe recipe, ProcessViewerController controller, boolean isNew)
  {
    super();

    currentRecipe = recipe;

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    units = new Units();

    // **** INPUT FIELDS ****

    // adds all the elements to the input fields
//    mainIFP = new InputFieldPanel();
//    if (!isNew)
//    {
//      mainIFP.addJTextField("Name: ", 50, recipe.getName());
//      mainIFP.addJTextField("Serves: ", 10, String.valueOf(recipe.getServes()));
//    }
//    else
//    {
//      mainIFP.addJTextField("Name: ", 50);
//      mainIFP.addJTextField("Serves: ", 10);
//    }

    // **** EDITOR PANELS ****

    // Utensils
    utensilIFP = new InputFieldPanel();
//    utensilIFP.addJTextField("Name: ", 25);
//    utensilIFP.addJTextField("Details: ", 25);
//    utensilIFP.addJButton("Add", "Utensil Add", controller);

    // Ingredients
    ingredientIFP = new InputFieldPanel();
    ingredientIFP.addJTextField(NAME, 15);
    ingredientIFP.addJTextField(DETAILS , 7);
    ingredientIFP.addJTextField(AMOUNT, 7);
    
    ingredientIFP.addJComboBox(UNITS, units.getAllUnitsPlusIndividual());

    ingredientIFP.addJButton(ADD, INGREDIENT_ADD, controller);

    // Steps
    stepIFP = new InputFieldPanel();
    stepIFP.addJTextField(ACTION, 7);

    stepOn = new String[] {""};
    stepUtensils = new String[] {""};
    stepIngredients = new String[] {""};

    if (!isNew) 
    {
      stepOn = new String[recipe.getUtensils().size() + recipe.getIngredients().size()];
      for (int i = 0; i < recipe.getUtensils().size(); i++)
      {
        stepOn[i] = recipe.getUtensils().get(i).getName();
      }

      int ingredientIndex = 0; // Track position in ingredients list
      for (int i = recipe.getUtensils().size(); i < stepOn.length; i++)
      {
        stepOn[i] = recipe.getIngredients().get(ingredientIndex).getName();
        ingredientIndex++;
      }

      stepUtensils = new String[recipe.getUtensils().size()];
      for (int i = 0; i < stepUtensils.length; i++)
      {
        stepUtensils[i] = recipe.getUtensils().get(i).getName();
      }

      stepIngredients = new String[recipe.getIngredients().size()];
      for (int i = 0; i < stepIngredients.length; i++)
      {
        stepIngredients[i] = recipe.getIngredients().get(i).getName();
      }
    }

    stepIFP.addJComboBox(ON, stepOn);
    stepIFP.addJComboBox(UTENSIL, stepUtensils);

    stepIFP.addJTextField(DETAILS , 15);
    stepIFP.addJButton(ADD, STEP_ADD, controller);

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel ProcessViewerPanel = new JPanel();
    ProcessViewerPanel.setLayout(new BoxLayout(ProcessViewerPanel, BoxLayout.Y_AXIS));

    utensilProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.UTENSIL, recipe, utensilIFP, controller,
        isNew);
    ingredientProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.INGREDIENT, recipe, ingredientIFP,
        controller, isNew);
    stepProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.STEP, recipe, stepIFP, controller, isNew);

    ProcessViewerPanel.add(utensilProcessViewerPanel);
    ProcessViewerPanel.add(ingredientProcessViewerPanel);
    ProcessViewerPanel.add(stepProcessViewerPanel);

    // adds the input fields into the content pane
//    contentPane.add(mainIFP, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(ProcessViewerPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

  public ProcessViewerContent(Meal meal, ProcessViewerController mealViewerController, boolean isNew) {
	  super();

	    currentMeal = meal;
	    
//	    Recipe recipe = null;
		
		for (Recipe recipe: meal.getRecipes()) 
		{
			currentRecipe = recipe;


	    contentPane = new Container();
	    contentPane.setLayout(new BorderLayout());

	    units = new Units();

	    // **** INPUT FIELDS ****

	    // adds all the elements to the input fields
//	    mainIFP = new InputFieldPanel();
//	    if (!isNew)
//	    {
//	      mainIFP.addJTextField("Name: ", 50, recipe.getName());
//	      mainIFP.addJTextField("Serves: ", 10, String.valueOf(recipe.getServes()));
//	    }
//	    else
//	    {
//	      mainIFP.addJTextField("Name: ", 50);
//	      mainIFP.addJTextField("Serves: ", 10);
//	    }

	    // **** EDITOR PANELS ****

	    // Utensils
	    utensilIFP = new InputFieldPanel();
//	    utensilIFP.addJTextField("Name: ", 25);
//	    utensilIFP.addJTextField("Details: ", 25);
//	    utensilIFP.addJButton("Add", "Utensil Add", controller);

	    // Ingredients
	    ingredientIFP = new InputFieldPanel();
	    ingredientIFP.addJTextField("Name: ", 15);
	    ingredientIFP.addJTextField("Details: ", 7);
	    ingredientIFP.addJTextField("Amount: ", 7);
	    
	    ingredientIFP.addJComboBox("Units: ", units.getAllUnitsPlusIndividual());

	    ingredientIFP.addJButton("Add", "Ingredient Add", mealViewerController);

	    // Steps
	    stepIFP = new InputFieldPanel();
	    stepIFP.addJTextField("Action: ", 7);

	    stepOn = new String[] {""};
	    stepUtensils = new String[] {""};
	    stepIngredients = new String[] {""};

	    if (!isNew) 
	    {
	      stepOn = new String[recipe.getUtensils().size() + recipe.getIngredients().size()];
	      for (int i = 0; i < recipe.getUtensils().size(); i++)
	      {
	        stepOn[i] = recipe.getUtensils().get(i).getName();
	      }

	      int ingredientIndex = 0; // Track position in ingredients list
	      for (int i = recipe.getUtensils().size(); i < stepOn.length; i++)
	      {
	        stepOn[i] = recipe.getIngredients().get(ingredientIndex).getName();
	        ingredientIndex++;
	      }

	      stepUtensils = new String[recipe.getUtensils().size()];
	      for (int i = 0; i < stepUtensils.length; i++)
	      {
	        stepUtensils[i] = recipe.getUtensils().get(i).getName();
	      }

	      stepIngredients = new String[recipe.getIngredients().size()];
	      for (int i = 0; i < stepIngredients.length; i++)
	      {
	        stepIngredients[i] = recipe.getIngredients().get(i).getName();
	      }
	    }

	    stepIFP.addJComboBox("On: ", stepOn);
	    stepIFP.addJComboBox("Utensil: ", stepUtensils);

	    stepIFP.addJTextField("Details: ", 15);
	    stepIFP.addJButton("Add", "Step Add", mealViewerController);

	    // creates the panel for the Utensils, Ingredients, and Steps
	    JPanel ProcessViewerPanel = new JPanel();
	    ProcessViewerPanel.setLayout(new BoxLayout(ProcessViewerPanel, BoxLayout.Y_AXIS));

	    utensilProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.UTENSIL, recipe, utensilIFP, mealViewerController,
	        isNew);
	    ingredientProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.INGREDIENT, recipe, ingredientIFP,
	        mealViewerController, isNew);
	    stepProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.STEP, recipe, stepIFP, mealViewerController, isNew);

	    ProcessViewerPanel.add(utensilProcessViewerPanel);
	    ProcessViewerPanel.add(ingredientProcessViewerPanel);
	    ProcessViewerPanel.add(stepProcessViewerPanel);

	    // adds the input fields into the content pane
//	    contentPane.add(mainIFP, BorderLayout.NORTH);

	    // adds the editorPane into the content pane
	    contentPane.add(ProcessViewerPanel, BorderLayout.CENTER);

	    this.add(contentPane);
	  }
  }

public String getNameField()
  {
    return mainIFP.getText(NAME);
  }

  public String getServesField()
  {
    return mainIFP.getText(SERVES);
  }

  public ProcessViewerPanel getUtensilPanel()
  {
    return utensilProcessViewerPanel;
  }

  public ProcessViewerPanel getIngredientPanel()
  {
    return ingredientProcessViewerPanel;
  }

  public void updateStepSourcePanel()
  {
    stepUtensils = new String[utensilProcessViewerPanel.getRecipeList().getModel().getSize()];

    for (int i = 0; i < stepUtensils.length; i++)
    {
      stepUtensils[i] = utensilProcessViewerPanel.getRecipeList().getModel().getElementAt(i).getName();
    }

    stepIngredients = new String[ingredientProcessViewerPanel.getRecipeList().getModel().getSize()];

    for (int i = 0; i < stepIngredients.length; i++)
    {
      stepIngredients[i] = ingredientProcessViewerPanel.getRecipeList().getModel().getElementAt(i)
          .getName();
    }

    stepIFP.updateComboBox(UTENSIL, stepUtensils);

    stepSources = new String[stepUtensils.length + stepIngredients.length];
    System.arraycopy(stepUtensils, 0, stepSources, 0, stepUtensils.length);
    System.arraycopy(stepIngredients, 0, stepSources, stepUtensils.length, stepIngredients.length);

    stepIFP.updateComboBox(ON, stepSources);
  }

  public ProcessViewerPanel getStepPanel()
  {
    return stepProcessViewerPanel;
  }

  public void reset()
  {
    mainIFP.resetFields();
    utensilIFP.resetFields();
    ingredientIFP.resetFields();
    stepIFP.resetFields();

    utensilProcessViewerPanel.reset();
    ingredientProcessViewerPanel.reset();
    stepProcessViewerPanel.reset();
  }

  public InputFieldPanel getMainIFP()
  {
    return mainIFP;
  }

  public InputFieldPanel getUtensilIFP()
  {
    return utensilIFP;
  }

  public InputFieldPanel getIngredientIFP()
  {
    return ingredientIFP;
  }

  public InputFieldPanel getStepIFP()
  {
    return stepIFP;
  }
  public JPanel getProcessViewerPanel() {
	return ProcessViewerPanel;
}
}

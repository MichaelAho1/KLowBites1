package gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controller.RecipeEditorController;
import controller.ProcessViewerController;
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
  Container contentPane;

  Recipe currentRecipe; // recipe being used

  JPanel ViewerPanel;

  InputFieldPanel mainIFP;
  InputFieldPanel utensilIFP;
  InputFieldPanel ingredientIFP;
  InputFieldPanel stepIFP;

  ViewerPanel utensilViewerPanel;
  ViewerPanel ingredientViewerPanel;
  ViewerPanel stepViewerPanel;

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
    mainIFP = new InputFieldPanel();
    if (!isNew)
    {
      mainIFP.addJTextField("Name: ", 50, recipe.getName());
      mainIFP.addJTextField("Serves: ", 10, String.valueOf(recipe.getServes()));
    }
    else
    {
      mainIFP.addJTextField("Name: ", 50);
      mainIFP.addJTextField("Serves: ", 10);
    }

    // **** EDITOR PANELS ****

    // Utensils
    utensilIFP = new InputFieldPanel();
    utensilIFP.addJTextField("Name: ", 25);
    utensilIFP.addJTextField("Details: ", 25);
    utensilIFP.addJButton("Add", "Utensil Add", controller);

    // Ingredients
    ingredientIFP = new InputFieldPanel();
    ingredientIFP.addJTextField("Name: ", 15);
    ingredientIFP.addJTextField("Details: ", 7);
    ingredientIFP.addJTextField("Amount: ", 7);
    ingredientIFP.addJComboBox("Units: ", units.getAllUnitsPlusIndividual());

    ingredientIFP.addJButton("Add", "Ingredient Add", controller);

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
    stepIFP.addJButton("Add", "Step Add", controller);

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel ViewerPanel = new JPanel();
    ViewerPanel.setLayout(new BoxLayout(ViewerPanel, BoxLayout.Y_AXIS));

    utensilViewerPanel = new ViewerPanel(RecipeElementType.UTENSIL, recipe, utensilIFP, controller,
        isNew);
    ingredientViewerPanel = new ViewerPanel(RecipeElementType.INGREDIENT, recipe, ingredientIFP,
        controller, isNew);
    stepViewerPanel = new ViewerPanel(RecipeElementType.STEP, recipe, stepIFP, controller, isNew);

    ViewerPanel.add(utensilViewerPanel);
    ViewerPanel.add(ingredientViewerPanel);
    ViewerPanel.add(stepViewerPanel);

    // adds the input fields into the content pane
    contentPane.add(mainIFP, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(ViewerPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

  public String getNameField()
  {
    return mainIFP.getText("Name: ");
  }

  public String getServesField()
  {
    return mainIFP.getText("Serves: ");
  }

  public ViewerPanel getUtensilPanel()
  {
    return utensilViewerPanel;
  }

  public ViewerPanel getIngredientPanel()
  {
    return ingredientViewerPanel;
  }

  public void updateStepSourcePanel()
  {
    stepUtensils = new String[utensilViewerPanel.getRecipeList().getModel().getSize()];

    for (int i = 0; i < stepUtensils.length; i++)
    {
      stepUtensils[i] = utensilViewerPanel.getRecipeList().getModel().getElementAt(i).getName();
    }

    stepIngredients = new String[ingredientViewerPanel.getRecipeList().getModel().getSize()];

    for (int i = 0; i < stepIngredients.length; i++)
    {
      stepIngredients[i] = ingredientViewerPanel.getRecipeList().getModel().getElementAt(i)
          .getName();
    }

    stepIFP.updateComboBox("Utensil: ", stepUtensils);

    stepSources = new String[stepUtensils.length + stepIngredients.length];
    System.arraycopy(stepUtensils, 0, stepSources, 0, stepUtensils.length);
    System.arraycopy(stepIngredients, 0, stepSources, stepUtensils.length, stepIngredients.length);

    stepIFP.updateComboBox("On: ", stepSources);
  }

  public ViewerPanel getStepPanel()
  {
    return stepViewerPanel;
  }

  public void reset()
  {
    mainIFP.resetFields();
    utensilIFP.resetFields();
    ingredientIFP.resetFields();
    stepIFP.resetFields();

    utensilViewerPanel.reset();
    ingredientViewerPanel.reset();
    stepViewerPanel.reset();
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
}

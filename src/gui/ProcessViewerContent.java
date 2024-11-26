package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import app.KILowBites;
import controller.ProcessViewerController;
import cooking.Meal;
import cooking.Recipe;
import cooking.RecipeElementType;

/**
 * RecipeEditorContent class. Handles RecipeEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class ProcessViewerContent extends JPanel
{
  private static final long serialVersionUID = 1L;

  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  Container contentPane;

  Recipe currentRecipe; // recipe being used
  Meal currentMeal;

  JPanel processViewerPanel;

  InputFieldPanel mainIFP;
  InputFieldPanel utensilIFP;
  InputFieldPanel stepIFP;

  ProcessViewerPanel utensilProcessViewerPanel;
  ProcessViewerPanel stepProcessViewerPanel;

  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller
   *          the controller for the RecipeEditor
   */
  public ProcessViewerContent(Recipe recipe, ProcessViewerController controller)
  {
    super();

    currentRecipe = recipe;

    this.setBackground(KILowBites.COLOR);

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel ProcessViewerPanel = new JPanel();
    ProcessViewerPanel.setLayout(new BoxLayout(ProcessViewerPanel, BoxLayout.Y_AXIS));

    utensilProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.UTENSIL, recipe,
        utensilIFP, controller);
    stepProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.STEP, recipe, stepIFP,
        controller);

    ProcessViewerPanel.add(utensilProcessViewerPanel);
    ProcessViewerPanel.add(stepProcessViewerPanel);

    contentPane.add(ProcessViewerPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller
   *          the controller for the RecipeEditor
   */
  public ProcessViewerContent(Meal meal, ProcessViewerController controller)
  {
    super();

    currentMeal = meal;

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel ProcessViewerPanel = new JPanel();
    ProcessViewerPanel.setLayout(new BoxLayout(ProcessViewerPanel, BoxLayout.Y_AXIS));

    utensilProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.UTENSIL, meal, utensilIFP,
        controller);
    stepProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.STEP, meal, stepIFP,
        controller);

    ProcessViewerPanel.add(utensilProcessViewerPanel);
    ProcessViewerPanel.add(stepProcessViewerPanel);

    // adds the input fields into the content pane
    // contentPane.add(mainIFP, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(ProcessViewerPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

  public ProcessViewerPanel getUtensilPanel()
  {
    return utensilProcessViewerPanel;
  }

  public ProcessViewerPanel getStepPanel()
  {
    return stepProcessViewerPanel;
  }

  public InputFieldPanel getMainIFP()
  {
    return mainIFP;
  }

  public InputFieldPanel getUtensilIFP()
  {
    return utensilIFP;
  }

  public InputFieldPanel getStepIFP()
  {
    return stepIFP;
  }

  public JPanel getProcessViewerPanel()
  {
    return processViewerPanel;
  }
}

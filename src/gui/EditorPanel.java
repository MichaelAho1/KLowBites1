package gui;

import java.awt.*;
import javax.swing.*;

import controller.MealEditorController;
import controller.RecipeEditorController;
import cooking.*;

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

  JPanel fileEditorPanel;

  JTextArea detailsText;
  JScrollPane scrollPane;

  JButton deleteButton;


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

    DefaultListModel<RecipeElement> fileArea = new DefaultListModel<>();
    JList<RecipeElement> list = new JList<>(fileArea);

    // creates the file area for the editor panel
    scrollPane = new JScrollPane(list);
    scrollPane.setSize(new Dimension(500, 800));
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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
   * @param controller the controller for the RecipeEditor
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

    // creates the file area for the editor panel
    JTextArea detailsText = new JTextArea(8, 50);
    JScrollPane scrollPane = new JScrollPane(detailsText);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    DefaultListModel<Recipe> recipeFileArea = new DefaultListModel<Recipe>();
    JList<Recipe> recipeList = new JList<Recipe>(recipeFileArea);

    // creates the delete button
    JButton deleteButton = new JButton("Delete");
    deleteButton.setActionCommand("Recipe Delete");
    deleteButton.addActionListener(controller);

    fileEditorPanel.add(scrollPane);
    fileEditorPanel.add(deleteButton);


    this.add(inputFieldPanel, BorderLayout.NORTH);
    this.add(fileEditorPanel, BorderLayout.CENTER);
    this.add(contentPane, BorderLayout.SOUTH);
  }
}

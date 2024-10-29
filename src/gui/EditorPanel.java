package gui;

import java.awt.*;
import javax.swing.*;

import cooking.*;

/**
 * EditorPanel class. Flexibly handles the Editor panels for Editor frames.
 * Examples: Utensils, Ingredients, Steps
 * (where the file input goes)
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class EditorPanel extends JPanel
{
  /**
   * Constructor for EditorPanel.
   *
   * @param inputFieldPanel the input field panel
   * @param controller the controller for the RecipeEditor
   */
  public EditorPanel(String name, InputFieldPanel inputFieldPanel, RecipeEditorController controller, String type)
  {
    super();

    // strings needed to determine the type of the input, needed since no interface
    final String UTENSILS = "Utensils";
    final String INGREDIENTS = "Ingredients";
    final String STEPS = "Steps";

    Container contentPane = new Container();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // set preferred size for EditorPanels
    this.setPreferredSize(new Dimension(650, 215));

    // creates the editor label border
    this.setBorder(BorderFactory.createTitledBorder(name));

    // creates the file editor
    JPanel fileEditorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // creates the file area for the editor panel
    JTextArea detailsText = new JTextArea(8, 50);
    JScrollPane scrollPane = new JScrollPane(detailsText);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    DefaultListModel<Utensils> utensilFileArea = new DefaultListModel<Utensils>();
    JList<Utensils> utensilList = new JList<Utensils>(utensilFileArea);

    DefaultListModel<Ingredients> ingredientsFileArea = new DefaultListModel<Ingredients>();
    JList<Ingredients> ingredientsList = new JList<Ingredients>(ingredientsFileArea);

    DefaultListModel<Steps> stepsFileArea = new DefaultListModel<Steps>();
    JList<Steps> stepsList = new JList<Steps>(stepsFileArea);
    // these 3 do the same thing, needed because we don't have an interface

    // creates the delete button
    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(controller);

    // fix for delete button (needs to be able to tell what to delete)
    if (type.equals(UTENSILS))
    {
      deleteButton.setActionCommand("Utensil Delete");
    }
    else if (type.equals(INGREDIENTS))
    {
      deleteButton.setActionCommand("Ingredient Delete");
    }
    else if (type.equals(STEPS))
    {
      deleteButton.setActionCommand("Step Delete");
    }

    // adds the file editor and delete button to the file editor container
    // TODO: once pushed to main, can add a cooking interface to get rid of these if statements
    if (type.equals(UTENSILS))
    {
      // fileEditor.add(utensilList, BorderLayout.CENTER);
    }
    else if (type.equals(INGREDIENTS))
    {
      // fileEditor.add(ingredientsList, BorderLayout.CENTER);
    }
    else if (type.equals(STEPS))
    {
      // fileEditor.add(stepsList, BorderLayout.CENTER);
    }

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

  //TODO: NEED TO WRITE GETTERS FOR THE TEXT FIELDS
}

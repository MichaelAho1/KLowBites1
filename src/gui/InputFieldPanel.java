package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * InputFieldPanel class. Flexibly handles the input fields for Editor frames.
 * Examples: Name, Serves, Number of People
 * (whats below the ribbon, but above the EditorPanel)
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class InputFieldPanel extends JPanel
{
  private Container contentPane;

  JPanel inputFields; // the panel for the gui

  // the editable fields in the IFP. The key is the label of the field.
  HashMap<String, JTextField> fields;
  HashMap<String, JComboBox<String>> comboBoxes;

  /**
   * Constructor for InputFields.
   */
  public InputFieldPanel()
  {
    super();

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // creates the text area for inputs fields (examples: Name, Serves, Number of People)
    inputFields = new JPanel();
    inputFields.setLayout(new BoxLayout(inputFields, BoxLayout.X_AXIS));

    // initializes the ArrayLists of editable fields
    fields = new HashMap<String, JTextField>();
    comboBoxes = new HashMap<String, JComboBox<String>>();

    // adds the input fields into the content pane
    contentPane.add(inputFields, BorderLayout.CENTER);

    this.add(contentPane);
  }

  /**
   * Adds a JLabel to the input field panel.
   *
   * @param content the label to add
   */
  public void addJLabel(String label)
  {
    inputFields.add(new JLabel(label));
  }

/**
 * Adds a JLabel and Field to the input field panel.
 *
 * @param content the field to add
 * @param size the size of the field
 */
  public void addJTextField(String label, int size)
  {
    inputFields.add(new JLabel(label));

    JTextField field = new JTextField(size);
    inputFields.add(field);
    fields.put(label, field);
  }

  /**
   * Adds a JButton to the input field panel.
   */
  public void addJButton(String label, String actionCommand, ActionListener controller)
  {
    JButton button = new JButton(label);
    button.setActionCommand(actionCommand);
    button.addActionListener(controller);

    inputFields.add(button);
  }

  /**
   * Adds a JComboBox to the input field panel.
   */
  public void addJComboBox(String label, String[] items)
  {
    inputFields.add(new JLabel(label));
    JComboBox<String> comboBox = new JComboBox<String>();

    for (String item : items)
    {
      comboBox.addItem(item);
    }

    comboBoxes.put(label, comboBox);
    inputFields.add(comboBox);
  }
}

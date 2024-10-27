package gui;

import java.awt.*;
import javax.swing.*;

/**
 * InputFieldPanel class. Flexibly handles the input fields for Editor frames.
 * Examples: Name, Serves, Number of People
 * (whats below the ribbon, but above the EditorPanel)
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class InputFieldPanel extends JPanel
{
  /**
   * Constructor for InputFields.
   *
   * @param fields the input fields
   */
  public InputFieldPanel(JComponent[] fields)
  {
    super();

    Container contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // creates the text area for inputs fields (examples: Name, Serves, Number of People)
    JPanel inputFields = new JPanel();
    inputFields.setLayout(new BoxLayout(inputFields, BoxLayout.X_AXIS));

    // creates the elements for the input fields
    for (int i = 0; i < fields.length; i++)
    {
      inputFields.add(fields[i]);
    }

    // adds the input fields into the content pane
    contentPane.add(inputFields, BorderLayout.CENTER);

    this.add(contentPane);
  }

  //TODO: NEED TO WRITE GETTERS FOR THE TEXT FIELDS
}

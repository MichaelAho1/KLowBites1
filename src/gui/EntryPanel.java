package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntryPanel extends JPanel
{
  private static final long serialVersionUID = 1L;
  private JLabel calories = new JLabel("___________");

  public EntryPanel(final String label, final String[] menu)
  {
    setLayout(new BorderLayout());

    // west -> label, center -> ingredients
    JComboBox dropDownMenu = new JComboBox(menu);

    // creating box for ingredients
    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(label), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(dropDownMenu, gbc);

    // Apply a border around the box (LineBorder)
    // boxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    add(boxPanel, BorderLayout.CENTER);
  }

  public EntryPanel(final String label, final boolean editable)
  {
    setLayout(new BorderLayout());

    // west -> label, center -> ingredients

    // creating box for ingredients
    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(label), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;

    if (editable)
    {
      boxPanel.add(new JTextField(8), gbc);
    }
    else
    {
      boxPanel.add(calories);
    }

    // Apply a border around the box (LineBorder)
    // boxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    add(boxPanel, BorderLayout.CENTER);
  }
}

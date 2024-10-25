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

  public EntryPanel(final String label, final String[] ingredients)
  {
    setLayout(new BorderLayout());

    // west -> label, center -> ingredients
    JComboBox ingredientsMenu = new JComboBox(ingredients);

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
    boxPanel.add(ingredientsMenu, gbc);

    // Apply a border around the box (LineBorder)
    // boxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    add(boxPanel, BorderLayout.CENTER);
  }

  public EntryPanel(final String label)
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
    boxPanel.add(new JTextField(8), gbc);

    // Apply a border around the box (LineBorder)
    // boxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    add(boxPanel, BorderLayout.CENTER);
  }
}

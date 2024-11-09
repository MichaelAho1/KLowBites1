package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 */

public class CalorieOutputWindow extends JFrame
{
  /**
   * Explicit value constructor.
   * 
   * @param name
   *          Name of recipe/meal
   * @param calories
   *          Number of calories in recipe/meal
   */
  public CalorieOutputWindow(String name, double calories)
  {
    super("KILowBites Recipe/Meal Calories");

    JLabel rmName = new JLabel(name);
    JLabel rmCalories = new JLabel(String.format("%.2f calories", calories));

    // Set layout to GridBagLayout
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0; // Column 0
    gbc.gridy = 0; // Row 0
    gbc.anchor = GridBagConstraints.CENTER; // Center alignment
    gbc.insets = new Insets(10, 0, 10, 0); // Add padding around the labels

    // Add name label
    add(rmName, gbc);

    // Move to the next row for the calories label
    gbc.gridy = 1;
    add(rmCalories, gbc);

    setLocationRelativeTo(null);
    setSize(500, 200);
    setResizable(false);
  }
}

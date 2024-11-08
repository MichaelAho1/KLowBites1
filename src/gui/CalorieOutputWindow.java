package gui;

import java.awt.BorderLayout;

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
    JLabel rmCalories = new JLabel(String.format("%.2f", calories));

    setLayout(new BorderLayout());

    add(rmName, BorderLayout.NORTH);
    add(rmCalories, BorderLayout.CENTER);

    setLocationRelativeTo(null);
    setSize(500, 200);
    setResizable(false);
  }
}

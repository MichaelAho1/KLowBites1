package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import utilities.ImageUtilities;

public class CalorieCalculatorWindow extends JFrame
{
  private static final long serialVersionUID = 1L;
  private String[] ingredients;
  private String[] units;

  private JPanel ingredientPanel;
  private JPanel amountPanel;
  private JPanel unitPanel;
  private JPanel outputPanel;

  public CalorieCalculatorWindow()
  {
    super("Calorie Calculator");
    // Add content and components here

    // adding input/output components
    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT));

    ingredients = new String[] {"", "bananas", "eggs", "sugar"};
    units = new String[] {"", "teaspoon", "cup", "individual"};

    this.ingredientPanel = new EntryPanel("Ingredient:", ingredients);
    inputs.add(ingredientPanel);

    this.amountPanel = new EntryPanel("Amount:", true);
    inputs.add(amountPanel);

    this.unitPanel = new EntryPanel("Units:", units);
    inputs.add(unitPanel);

    this.outputPanel = new EntryPanel("Calories:", false);
    inputs.add(outputPanel);

    add(inputs);

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    // Create buttons with icons
    JButton calculateButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/calculate.png", Color.GRAY, 25, 25));
    calculateButton.setToolTipText("Calculate");
    calculateButton.setEnabled(false);
    calculateButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("Calculating...");
      }
    });

    JButton resetButton = new JButton(
        ImageUtilities.getColoredIconAndScale("img/reset.png", Color.GRAY, 25, 25));
    resetButton.setToolTipText("Reset");
    resetButton.setEnabled(false);
    resetButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("Resetting...");
      }
    });

    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
    buttons.add(calculateButton);
    buttons.add(resetButton);

    getContentPane().add(buttons, BorderLayout.NORTH);

    setSize(650, 200);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  @Override
  protected void processWindowEvent(java.awt.event.WindowEvent e)
  {
    super.processWindowEvent(e);
    if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
    {
      System.out.println("Closing calorie calculator...");
    }
  }
}

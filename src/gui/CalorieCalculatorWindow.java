package gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

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
        getColoredIconAndScale("img/calculate.png", Color.GRAY, 25, 25));
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

    JButton resetButton = new JButton(getColoredIconAndScale("img/reset.png", Color.GRAY, 25, 25));
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

  private static ImageIcon getColoredIconAndScale(String path, Color color, int width, int height)
  {
    try
    {
      // Load the original image
      BufferedImage original = ImageIO.read(new File(path));

      // Create a colored version of the original image
      BufferedImage colored = new BufferedImage(original.getWidth(), original.getHeight(),
          BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = colored.createGraphics();
      g.drawImage(original, 0, 0, null);
      g.setComposite(AlphaComposite.SrcIn);
      g.setColor(color);
      g.fillRect(0, 0, original.getWidth(), original.getHeight());
      g.dispose();

      // Scale the colored image
      Image scaledImage = colored.getScaledInstance(width, height, Image.SCALE_SMOOTH);

      // Return the scaled image wrapped in an ImageIcon
      return new ImageIcon(scaledImage);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
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

package gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

  // private JPanel ingredientPanel;
  // private JPanel amountPanel;
  // private JPanel unitPanel;
  // private JPanel outputPanel;

  public CalorieCalculatorWindow()
  {
    super("Calorie Calculator");
    // MainWindow.openCalculator.setEnabled(false);
    // Add content and components here
    
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    // Create buttons with icons
    JButton calculateButton = new JButton(getColoredIconAndScale("calculate.png", Color.GRAY, 25, 25));
    calculateButton.setToolTipText("Calculate");
    calculateButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("Calculating...");
      }
    });
           
    JButton resetButton = new JButton(getColoredIconAndScale("reset.png", Color.GRAY, 25, 25));
    resetButton.setToolTipText("Reset");
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

    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT));

    ingredients = new String[] {"bananas", "eggs", "sugar"};
    units = new String[] {"teaspoon", "cup", "individual"};

    JPanel panel = new EntryPanel("Ingredient:", ingredients);
    inputs.add(panel);

    panel = new EntryPanel("Amount:");
    inputs.add(panel);

    panel = new EntryPanel("Units:", units);
    inputs.add(panel);

    panel = new EntryPanel("Calories:");
    inputs.add(panel);

    add(inputs);

    setSize(650, 200);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  private static ImageIcon getColoredIconAndScale(String path, Color color, int width, int height)
  {
    try {
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
    } catch (Exception e) {
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

package utilities;

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

public class ImageUtilities
{
  public static ImageIcon getColoredIconAndScale(String path, Color color, int width, int height)
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

  public static ImageIcon getImage(String path, int width, int height)
  {
    try
    {
      // Load the original image
      BufferedImage original = ImageIO.read(new File(path));

      // Scale the colored image
      Image scaledImage = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);

      // Return the scaled image wrapped in an ImageIcon
      return new ImageIcon(scaledImage);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }
}

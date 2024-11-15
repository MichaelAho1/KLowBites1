package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import utilities.ImageUtilities;

class ImageUtilitesTest 
{



  @Test
  public void testGetFormattedImage() 
  {
    ImageIcon formattedIcon = ImageUtilities.getFormattedImage("assets/open.png", Color.BLUE, 100, 100);
    assertEquals(100, formattedIcon.getIconWidth());
    assertEquals(100, formattedIcon.getIconHeight());
    ImageIcon scaledIcon = ImageUtilities.getScaledImage("assets/open.png", 100, 100);
    assertEquals(100, scaledIcon.getIconWidth());
    assertEquals(100, scaledIcon.getIconHeight());
  }
}

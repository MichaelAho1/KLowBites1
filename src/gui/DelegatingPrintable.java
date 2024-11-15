package gui;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;
 
public class DelegatingPrintable implements Printable
{
    private boolean          doubleBuffered;
    private JComponent       delegate;
 
 
    public DelegatingPrintable(JComponent delegate)
    {
       this.delegate = delegate;
 
       doubleBuffered = delegate.isDoubleBuffered();
    }
 
 
    public int print(Graphics g, PageFormat format, int page)
    {
       double        cH, cW, h, scale, w, x, y;
       Graphics2D    g2;
       int           status;
 
       g2 = (Graphics2D)g;
 
       // Turn off double buffering
       delegate.setDoubleBuffered(false);
 
       status = Printable.NO_SUCH_PAGE;
       if (page == 0) 
       {
          // Translate the origin
          x = format.getImageableX();
          y = format.getImageableY();
          g2.translate(x, y);
 
          // Scale the coordinate system (without changing the
          // aspect ratio)
          h = format.getImageableHeight();
          w = format.getImageableWidth(); 
          cW = (double)(delegate.getWidth());
          cH = (double)(delegate.getHeight());
          scale = Math.min(w/cW, h/cH);
          g2.scale(scale, scale);
 
          // Have the JComponent paint itself
          delegate.paint(g2);
 
          // Inform the caller that a page has been drawn
          status = Printable.PAGE_EXISTS;
       }
 
       // Restore double buffering (if it was on)
       delegate.setDoubleBuffered(doubleBuffered);
 
       return status;
    }
}
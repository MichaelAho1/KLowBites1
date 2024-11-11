package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.UnitConverterController;
import controller.UnitTypeController;

public class UnitTypeWindow extends JFrame
{
  public static JButton metricButton;
  public static JButton imperialButton;
  private UnitTypeController controller;
  
  public UnitTypeWindow() {
    controller = new UnitTypeController();
    
    metricButton = new JButton("Metric");
    metricButton.setToolTipText("Change Unit types to Metric");
    metricButton.setActionCommand("Metric");
    metricButton.addActionListener(controller);
    
    imperialButton = new JButton("Imperial");
    imperialButton.setToolTipText("Change Unit types to Imperial");
    imperialButton.setActionCommand("Imperial");
    imperialButton.addActionListener(controller);
    
    JPanel inputs = new JPanel();
    setSize(200, 50);
  }
  
}

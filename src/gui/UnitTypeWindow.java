package gui;

import javax.swing.JButton;
import javax.swing.JFrame;

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
    imperialButton = new JButton("Imperial");
    metricButton.setToolTipText("Calculate");
    metricButton.setActionCommand("Calculate");
    metricButton.addActionListener(controller);
  }
}

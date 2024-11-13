package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.UnitConverterController;
import controller.UnitTypeController;
import utilities.UnitType;

public class UnitTypeWindow extends JFrame
{
  public static JButton metricButton;
  public static JButton imperialButton;
  public static JLabel titleLabel;
  private UnitTypeController controller;
  private static String metric = "Metric";
  private static String imperial = "Imperial";
  
  public UnitTypeWindow() {
    setTitle("Unit Type Selector");
    controller = new UnitTypeController();
    
    metricButton = new JButton(metric);
    metricButton.setToolTipText("Change Unit types to Metric");
    metricButton.setActionCommand(metric);
    metricButton.addActionListener(controller);
    
    imperialButton = new JButton(imperial);
    imperialButton.setToolTipText("Change Unit types to Imperial");
    imperialButton.setActionCommand(imperial);
    imperialButton.addActionListener(controller);
    imperialButton.setEnabled(false);
    
    JPanel inputs = new JPanel(new GridLayout(1, 2, 0, 0)); 
    inputs.add(metricButton);
    inputs.add(imperialButton);
    if (UnitType.imperialSelected == true) {
      titleLabel = new JLabel("Current Unit Type: Imperial", JLabel.CENTER);
    } else {
      titleLabel = new JLabel("Current Unit Type: Metric", JLabel.CENTER);
    }
    
    setLayout(new BorderLayout());
    add(titleLabel, BorderLayout.NORTH);
    add(inputs, BorderLayout.CENTER);
    
    setSize(300, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); 
  }
  public static void main(String[] args) {
    UnitTypeWindow unitTypeWindow = new UnitTypeWindow();
    unitTypeWindow.setVisible(true);
  }
}

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
  
  private static final String UNIT_TYPE_SELECTOR = "Unit Type Selector";
  private static final String METRIC = "Metric";
  private static final String CHANGE_UNIT_TO_METRIC = "Change Unit types to Metric";
  private static final String IMPERIAL = "Imperial";
  private static final String CHANGE_UNIT_TYPE_TO_IMPERIAL = "Change Unit types to Imperial";
  private static final String CURRENT_UNIT_TYPE_IMPERIAL = "Current Unit Type: Imperial";
  private static final String CURRENT_UNIT_TYPE_METRIC = "Current Unit Type: Metric";
  
  public UnitTypeWindow() {
    setTitle(UNIT_TYPE_SELECTOR);
    controller = new UnitTypeController();
    
    metricButton = new JButton(METRIC);
    metricButton.setToolTipText(CHANGE_UNIT_TO_METRIC);
    metricButton.setActionCommand(METRIC);
    metricButton.addActionListener(controller);
    
    imperialButton = new JButton(IMPERIAL);
    imperialButton.setToolTipText(CHANGE_UNIT_TYPE_TO_IMPERIAL);
    imperialButton.setActionCommand(IMPERIAL);
    imperialButton.addActionListener(controller);
    imperialButton.setEnabled(false);
    
    JPanel inputs = new JPanel(new GridLayout(1, 2, 0, 0)); 
    inputs.add(metricButton);
    inputs.add(imperialButton);
    if (UnitType.imperialSelected == true) {
      titleLabel = new JLabel(CURRENT_UNIT_TYPE_IMPERIAL, JLabel.CENTER);
    } else {
      titleLabel = new JLabel(CURRENT_UNIT_TYPE_METRIC, JLabel.CENTER);
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

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.UnitTypeWindow;
import utilities.UnitType;

public class UnitTypeController implements ActionListener
{
  private static final String IMPERIAL = "Imperial";
  private static final String METRIC = "Metric";
  
  public void actionPerformed(ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(IMPERIAL))
    {
      UnitTypeWindow.imperialButton.setEnabled(false);
      UnitTypeWindow.metricButton.setEnabled(true);
      UnitTypeWindow.titleLabel.setText("Current Unit Type: Imperial");
      UnitType.imperialSelected = true;
      //imperial
    } else {
      UnitTypeWindow.imperialButton.setEnabled(true);
      UnitTypeWindow.metricButton.setEnabled(false);
      UnitTypeWindow.titleLabel.setText("Current Unit Type: Metric");
      UnitType.metricSelected = true;
    }
  }
}

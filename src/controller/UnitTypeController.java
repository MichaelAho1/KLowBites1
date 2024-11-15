package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import gui.UnitTypeWindow;
import utilities.UnitType;

public class UnitTypeController implements ActionListener
{
//  private static final String IMPERIAL = "Imperial";
  static final Locale         LOCALE  = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  
  public void actionPerformed(ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(STRINGS.getString("IMPERIAL")))
    {
      UnitTypeWindow.imperialButton.setEnabled(false);
      UnitTypeWindow.metricButton.setEnabled(true);
      UnitTypeWindow.titleLabel.setText("Current Unit Type: Imperial");
      UnitType.setImperialSelected();
      System.out.println("Changing to Imperial Units");
    } else 
    {
      UnitTypeWindow.imperialButton.setEnabled(true);
      UnitTypeWindow.metricButton.setEnabled(false);
      UnitTypeWindow.titleLabel.setText("Current Unit Type: Metric");
      UnitType.setMetricSelected();
      System.out.println("Changing to Metric Units");
    }
  }
}

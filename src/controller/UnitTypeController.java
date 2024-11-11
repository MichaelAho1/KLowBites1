package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UnitTypeController implements ActionListener
{
  private static final String IMPERIAL = "Imperial";
  private static final String METRIC = "Metric";
  
  public void actionPerformed(ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(IMPERIAL))
    {
      //imperial
    } else {
      //Metric
    }
  }
}

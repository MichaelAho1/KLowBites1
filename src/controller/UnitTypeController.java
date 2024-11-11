package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UnitTypeController implements ActionListener, DocumentListener
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

  @Override
  public void insertUpdate(DocumentEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeUpdate(DocumentEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void changedUpdate(DocumentEvent e)
  {
    // TODO Auto-generated method stub
    
  }
  
}

package utilities;

import utilities.DocumentState;

public interface DocumentStateSubject
{
  public void addObserver(DocumentStateObserver o);
  public void removeObserver(DocumentStateObserver o);
  public void notifyObservers();
}

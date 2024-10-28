// File: Utilities/FileUtilities.java
package utilities;

import java.io.*;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtilities
{

  // Document States
  private enum DocumentState
  {
    NULL, CHANGED, UNCHANGED
  }

  private DocumentState documentState;
  private File currentFile;

  // Constructor
  public FileUtilities()
  {
    this.documentState = DocumentState.NULL;
    this.currentFile = null;
  }

  public void newFile()
  {
    // Reset the document
    this.currentFile = null;
    this.documentState = DocumentState.UNCHANGED;
    System.out.println("New document created.");
  }

  // Open an existing document (Open button)
  public void openFile(JFrame parentFrame)
  {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(parentFrame);

    if (returnValue == JFileChooser.APPROVE_OPTION)
    {
      this.currentFile = fileChooser.getSelectedFile();
      try
      {

        String content = new String(
            Files.readAllBytes(Paths.get(this.currentFile.getAbsolutePath())));
        System.out.println("Opened file: " + this.currentFile.getName());
        // Set state to unchanged since the file is just opened
        this.documentState = DocumentState.UNCHANGED;
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
//Save the current document (Save button)
 public void saveFile()
 {
   if (this.currentFile != null && documentState == DocumentState.CHANGED)
   {
     try
     {
       // Simulate saving the file content
       Files.write(this.currentFile.toPath(), "File content to be saved".getBytes());
       this.documentState = DocumentState.UNCHANGED;
       System.out.println("File saved: " + this.currentFile.getName());
     }
     catch (IOException e)
     {
       e.printStackTrace();
     }
   }
   else
   {
     System.out.println("No changes to save or no file opened.");
   }
 }

 // Save As function (SaveAs button)
 public void saveAsFile(JFrame parentFrame)
 {
   JFileChooser fileChooser = new JFileChooser();
   int returnValue = fileChooser.showSaveDialog(parentFrame);

   if (returnValue == JFileChooser.APPROVE_OPTION)
   {
     this.currentFile = fileChooser.getSelectedFile();
     try
     {
       // Simulate saving the file under a new name
       Files.write(this.currentFile.toPath(), "File content to be saved".getBytes());
       this.documentState = DocumentState.UNCHANGED;
       System.out.println("File saved as: " + this.currentFile.getName());
     }
     catch (IOException e)
     {
       e.printStackTrace();
     }
   }
 }

 // Close the current document (Close button)
 public void closeFile()
 {
   if (documentState == DocumentState.UNCHANGED)
   {
     this.currentFile = null;
     this.documentState = DocumentState.NULL;
     System.out.println("Document closed.");
   }
   else
   {
     System.out.println("Cannot close, unsaved changes present.");
   }
 }

  // Mark the document as changed when edited
  public void markDocumentChanged()
  {
    this.documentState = DocumentState.CHANGED;
  }

  // Check the state of the document
  public boolean isChanged()
  {
    return this.documentState == DocumentState.CHANGED;
  }

  public boolean isNullState()
  {
    return this.documentState == DocumentState.NULL;
  }

  public boolean isUnchanged()
  {
    return this.documentState == DocumentState.UNCHANGED;
  }
}

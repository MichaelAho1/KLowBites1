package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LanguageSelectionDialog extends JDialog
{

  public LanguageSelectionDialog()
  {
    // Set modal to block execution until dialog is closed
    setModal(true);

    setTitle("Select Language");
    setSize(300, 100);
    setLocationRelativeTo(null); // Center the dialog on the screen

    // Create a label for instructions
    JLabel label = new JLabel("Choose a language:", SwingConstants.CENTER);

    // Create language options
    String[] languages = {"English", "French", "Spanish"};
    JComboBox<String> languageComboBox = new JComboBox<>(languages);
    languageComboBox.setPreferredSize(new Dimension(100, 25)); // Set smaller size

    // Create a confirm button
    JButton confirmButton = new JButton("Confirm");
    confirmButton.setPreferredSize(new Dimension(80, 25)); // Set smaller size

    // Action listener for the confirm button
    confirmButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        // Set Locale based on user selection
        String selectedLanguage = (String) languageComboBox.getSelectedItem();
        switch (selectedLanguage)
        {
          case "French" -> Locale.setDefault(Locale.FRANCE);
          case "Spanish" -> Locale.setDefault(new Locale("es", "ES"));
          default -> Locale.setDefault(Locale.getDefault());
        }

        // Close the dialog
        dispose();
      }
    });

    // Panel to hold dropdown and button
    JPanel panel = new JPanel();
    panel.add(languageComboBox);
    panel.add(confirmButton);

    // Add components to the dialog
    setLayout(new BorderLayout());
    add(label, BorderLayout.NORTH);
    add(panel, BorderLayout.CENTER);
  }
}

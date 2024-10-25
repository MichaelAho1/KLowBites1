package app;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.reflect.*;
import javax.swing.*;
import javax.imageio.ImageIO;

import gui.KILowBitesController;
import utilities.ImageUtilities;

/**
 * KILowBites main class. Runs the program and the main window.
 *
 * @author f24team3d
 * @version 10/23/24
 */
public class KILowBites implements Runnable
{
  private String[] args;

    /**
   * The entry point of the application.
   *
   * @param args  The command-line arguments (ignored)
   * @throws InterruptedException If the system is interrupted
   * @throws InvocationTargetException If there is a problem starting the system
   */
  public static void main(String[] args)
      throws InterruptedException, InvocationTargetException
  {
    // Perform all of the setup activities in the event dispatch thread
    SwingUtilities.invokeAndWait(new KILowBites(args));
  }

  /**
   * Explicit Value Constructor.
   *
   * @param args The command-line arguments
   */
  public KILowBites(final String[] args)
  {
    // Store the command-line arguments if needed
    this.args = args;
  }

  /**
   * Run method.
   */
  public void run()
  {
    // initial setup
    JFrame frame = new JFrame("KILowBites Main Window");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel contentPane = (JPanel)frame.getContentPane();
    contentPane.setLayout(new BorderLayout());

    // Construct the controller
    KILowBitesController controller = new KILowBitesController();

    // creates the ribbon
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    // adds ribbon items
    JMenu menu;
    JMenuItem menuItem;

    menu = new JMenu("File");
    menuBar.add(menu);
    menuItem = new JMenuItem("Exit");
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu("Edit");
    menuBar.add(menu);
    menuItem = new JMenuItem("Recipe");
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem("Meal");
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu("Search");
    menuBar.add(menu);
    menuItem = new JMenuItem("Recipes");
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem("Meals");
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu("View");
    menuBar.add(menu);
    menuItem = new JMenuItem("Shopping List");
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem("Process");
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu("Tools");
    menuBar.add(menu);
    menuItem = new JMenuItem("Calorie Calculator");
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem("Units Converter");
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu("Configure");
    menuBar.add(menu);
    menuItem = new JMenuItem("Preferences");
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem("Shortcuts");
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu("Help");
    menuBar.add(menu);
    menuItem = new JMenuItem("About");
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem("User Guide");
    menu.add(menuItem);
    menuItem.addActionListener(controller);


    // Creates the logo in the main panel
    JPanel logoPanel = new JPanel();
    // JLabel logo = new JLabel(ImageUtilities.getColoredIconAndScale("img/KILowBites_Logo.png", Color.BLACK, 200, 50));
    JLabel logo = new JLabel(ImageUtilities.getImage("img/KILowBites_Logo.png", 200, 80));
    logoPanel.add(logo);

    // Layout the GUI
    contentPane.add(logoPanel, BorderLayout.CENTER);

    frame.setSize(600, 200);
    // frame.pack();
    frame.setVisible(true);
  }
}

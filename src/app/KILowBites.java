package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.KILowBitesController;
import utilities.Foods;
import utilities.ImageUtilities;
import utilities.Units;

/**
 * KILowBites main class. Runs the program and the main window.
 *
 * @author f24team3d
 * @version 10/23/24
 */
public class KILowBites implements Runnable
{
  public static JMenuItem openCalc;
  public static JMenuItem openConvert;

  public static final Foods FOODS = new Foods();
  public static final Units UNITS = new Units();

  private static final String FILE = "File";
  private static final String MAIN_WINDOW = "KILowBites Main Window";
  private static final String EXIT = "Exit";
  private static final String EDIT = "Edit";
  private static final String RECIPE = "Recipe";
  private static final String MEAL = "Meal";
  private static final String VIEW = "View";
  private static final String PROCESS = "Process";
  private static final String TOOLS = "Tools";
  private static final String CALORIE_CALCULATOR = "Calorie Calculator";
  private static final String UNITS_CONVERTER = "Units Converter";

  private String[] args;

  /**
   * The entry point of the application.
   *
   * @param args
   *          The command-line arguments (ignored)
   * @throws InterruptedException
   *           If the system is interrupted
   * @throws InvocationTargetException
   *           If there is a problem starting the system
   */
  public static void main(String[] args) throws InterruptedException, InvocationTargetException
  {
    // Perform all of the setup activities in the event dispatch thread
    SwingUtilities.invokeAndWait(new KILowBites(args));
  }

  /**
   * Explicit Value Constructor.
   *
   * @param args
   *          The command-line arguments
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
    JFrame frame = new JFrame(MAIN_WINDOW);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel contentPane = (JPanel) frame.getContentPane();
    contentPane.setLayout(new BorderLayout());

    // Set the background color of the main content pane to white
    contentPane.setBackground(Color.WHITE);

    // Construct the controller
    KILowBitesController controller = new KILowBitesController();

    // creates the ribbon
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    // adds ribbon items
    JMenu menu;
    JMenuItem menuItem;

    menu = new JMenu(FILE);
    menuBar.add(menu);
    menuItem = new JMenuItem(EXIT);
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu(EDIT);
    menuBar.add(menu);
    menuItem = new JMenuItem(RECIPE);
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem(MEAL);
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    // menu = new JMenu("Search");
    // menuBar.add(menu);
    // menuItem = new JMenuItem("Recipes");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);
    // menuItem = new JMenuItem("Meals");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);

    menu = new JMenu(VIEW);
    menuBar.add(menu);
    // menuItem = new JMenuItem("Shopping List");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);
    menuItem = new JMenuItem(PROCESS);
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu(TOOLS);
    menuBar.add(menu);
    openCalc = new JMenuItem(CALORIE_CALCULATOR);
    menu.add(openCalc);
    openCalc.addActionListener(controller);
    openConvert = new JMenuItem(UNITS_CONVERTER);
    openConvert.addActionListener(controller);
    menu.add(openConvert);

    menu = new JMenu("Configure");
    menuBar.add(menu);
    menuItem = new JMenuItem("Preferences");
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    // menuItem = new JMenuItem("Shortcuts");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);
    menuItem = new JMenuItem("Add Ingredient");
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    // menu = new JMenu("Help");
    // menuBar.add(menu);
    // menuItem = new JMenuItem("About");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);
    // menuItem = new JMenuItem("User Guide");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);

    // Creates the logo in the main panel
    JPanel logoPanel = new JPanel();
    JLabel logo = new JLabel(ImageUtilities.getImage("img/KILowBites_Logo.png", 200, 80));
    logoPanel.add(logo);

    // Set the background color of the logo panel to white to match the content pane
    logoPanel.setBackground(Color.WHITE);

    // Layout the GUI
    contentPane.add(logoPanel, BorderLayout.CENTER);

    frame.setSize(600, 200);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

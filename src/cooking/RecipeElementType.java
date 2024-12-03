package cooking;

import java.util.ResourceBundle;

import app.KILowBites;

/**
 * Enum for RecipeElementType.
 * Contains the types of RecipeElements.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public enum RecipeElementType {
  INGREDIENT("INGREDIENTS", "INGREDIENT_DELETE"),
  STEP("STEP", "STEP_DELETE"),
  UTENSIL("UTENSIL", "UTENSIL_DELETE");

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private final String labelKey;
  private final String deleteCommandKey;

  RecipeElementType(String labelKey, String deleteCommandKey) {
      this.labelKey = labelKey;
      this.deleteCommandKey = deleteCommandKey;
  }

  public String getLabel(boolean capitalize) {
      String label = STRINGS.getString(labelKey);
      return capitalize ? capitalizeFirstLetter(label) : label;
  }

  public String getDeleteCommand() {
      return STRINGS.getString(deleteCommandKey);
  }

  private String capitalizeFirstLetter(String str) {
      if (str == null || str.isEmpty()) {
          return str;
      }
      return str.substring(0, 1).toUpperCase() + str.substring(1);
  }
}
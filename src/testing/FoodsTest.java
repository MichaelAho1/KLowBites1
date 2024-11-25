package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import utilities.Foods;

/**
 * Test cases for Foods class.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

class FoodsTest
{
  @Test
  void testFoods()
  {
    Foods foods = new Foods();
    foods.getDefaultFoods();
    assertEquals(83, foods.getSize());

    String[] foodNames = foods.getFoodNames();
    assertEquals(84, foodNames.length);
    System.out.println();

    assertEquals("apple juice", foodNames[5]);

    assertEquals(83, foods.getFoods().size());

    double density = foods.getDensity("apple");

    assertEquals(0.56, density);
  }

}

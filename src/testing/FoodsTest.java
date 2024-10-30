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
    assertEquals(77, foods.getSize());

    String[] foodNames = foods.getFoodNames();
    assertEquals(78, foodNames.length);
    System.out.println();

    assertEquals("Apple juice", foodNames[5]);

    assertEquals(77, foods.getFoods().size());

    double density = foods.getDensity("Apple");

    assertEquals(0.56, density);
  }

}

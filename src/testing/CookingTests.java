package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cooking.Ingredients;


/**
 * Test cases for Cooking package.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

class CookingTests
{
  @Test
  void testIngredients()
  {
	  Ingredients ing = new Ingredients();
	  assertEquals(0.0, ing.getAmount());
	  assertEquals("", ing.getUnit());
	  assertEquals("", ing.getDetails());
	  assertEquals("", ing.getName());
  }
  @Test
  void testIngredients1()
  {
	  Ingredients ing = new Ingredients(3, "individual", "(sliced ripe)", "bananas");
	  assertEquals(3.0, ing.getAmount());
	  assertEquals("individual", ing.getUnit());
	  assertEquals("(sliced ripe)", ing.getDetails());
	  assertEquals("bananas", ing.getName());
  }
  @Test
  void testIngredients2()
  {
	  Ingredients ing = new Ingredients();
	  ing.setAmount(3.0);
	  ing.setUnit("individual");
	  ing.setDetails("(sliced ripe)");
	  ing.setName("bananas");
	  assertEquals(3.0, ing.getAmount());
	  assertEquals("individual", ing.getUnit());
	  assertEquals("(sliced ripe)", ing.getDetails());
	  assertEquals("bananas", ing.getName());
	  
	  assertEquals("3.0 individual (sliced ripe) bananas", ing.toString());
	  ing.add();
  }
}

package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cooking.RecipeElementType;
import cooking.StepSource;
import cooking.Steps;
import cooking.Utensils;

class StepsTest {

    private Steps steps;
    private StepSource source;
    private Utensils destination;

    @BeforeEach
    void setUp() {
        // Mock StepSource and Utensils for testing purposes
        source = new MockStepSource("Source Utensil", "Some details", RecipeElementType.UTENSIL);
        destination = new Utensils();
        destination.setName("Destination Utensil");
        destination.setDetails("Destination details");

        steps = new Steps("Mix", source, destination, "gently");
    }

    @Test
    void testGetAction() {
        assertEquals("Mix", steps.getAction());
    }

    @Test
    void testSetAction() {
        steps.setAction("Stir");
        assertEquals("Stir", steps.getAction());
    }

    @Test
    void testGetSource() {
        assertEquals(source, steps.getSource());
    }

    @Test
    void testSetSource() {
        StepSource newSource = new MockStepSource("New Source", "New details", RecipeElementType.INGREDIENT);
        steps.setSource(newSource);
        assertEquals(newSource, steps.getSource());
    }

    @Test
    void testGetDestination() {
        assertEquals(destination, steps.getDestination());
    }

    @Test
    void testSetDestination() {
        Utensils newDestination = new Utensils();
        newDestination.setName("New Destination");
        newDestination.setDetails("New details");
        steps.setDestination(newDestination);
        assertEquals(newDestination, steps.getDestination());
    }

    @Test
    void testGetDetails() {
        assertEquals("gently", steps.getDetails());
    }

    @Test
    void testSetDetails() {
        steps.setDetails("vigorously");
        assertEquals("vigorously", steps.getDetails());
    }

    @Test
    void testToStringUtensilToUtensilSameName() {
        // Test case where source and destination are the same utensil
        destination.setName("Source Utensil"); // Set the same name as source
        String result = steps.toString();
        assertEquals("Mix the contents of the Source Utensil gently", result);
    }

    @Test
    void testToStringUtensilToUtensilDifferentName() {
        // Test case where source and destination are different utensils
        String result = steps.toString();
        assertEquals("Mix the contents of the Source Utensil in the Destination Utensil gently", result);
    }

    @Test
    void testToStringWithDifferentTypes() {
        // Test case for a different source type (e.g., an ingredient instead of a utensil)
        StepSource newSource = new MockStepSource("Ingredient", "Fresh", RecipeElementType.INGREDIENT);
        steps.setSource(newSource);
        String result = steps.toString();
        assertEquals("Mix the Ingredient in the Destination Utensil gently", result);
    }

    @Test
    void testGetType() {
        assertEquals(RecipeElementType.STEP, steps.getType());
    }

    @Test
    void testGetName() {
        // The `getName()` method should return "oops" as per the current logic.
        assertEquals("oops", steps.getName());
    }
}

// Mock class for StepSource
class MockStepSource implements StepSource {

    private String name;
    private String details;
    private RecipeElementType type;

    public MockStepSource(String name, String details, RecipeElementType type) {
        this.name = name;
        this.details = details;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDetails() {
        return details;
    }

    @Override
    public RecipeElementType getType() {
        return type;
    }

    @Override
    public String toString() {
        return details + name;
    }
}

package planner.test;

import planner.*;
import org.junit.Assert;

import org.junit.Test;
import org.junit.Before;

/**
 * Basic tests for the {@link Corridor} implementation class.
 * 
 * Write your own junit4 tests for the class here.
 */
public class CorridorTest {
    // Instance variables:
    private Location[] locations;
    private Corridor[] corridors;

    @Before
    public void setup(){
        // Set up Locations to test:
        locations = new Location[5];
        locations[0] = new Location("l0");
        locations[1] = new Location("l1");
        locations[2] = new Location("l2");
        locations[3] = new Location("l3");
        locations[4] = new Location("l4");

        // Set up Corridors to test:
        corridors = new Corridor[5];
        corridors[0] = new Corridor(locations[0], locations[1], 100);
        corridors[1] = new Corridor(locations[1], locations[2], 200);
        corridors[2] = new Corridor(locations[2], locations[3], 250);
        corridors[3] = new Corridor(locations[3], locations[4], 175);
        corridors[4] = new Corridor(locations[4], locations[0], 50);
    }

    /**
     * Test the constructor method with a null as start Location.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNullPointerAtStart(){
        new Corridor(null, locations[2], 100);
    }

    /**
     * Test the constructor method with a null as end Location.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNullPointerAtEnd(){
        new Corridor(locations[3], null, 100);
    }

    /**
     * Test the constructor with a zero maximum capacity.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgumentZero(){
        new Corridor(locations[1], locations[2], 0);
    }

    /**
     * Test the constructor with a negative maximum capacity.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgumentNegative(){
        new Corridor(locations[1], locations[2], -2);
    }

    /**
     * Test a constructor with equal start and end Locations.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgumentSameLocations(){
        new Corridor(locations[1], locations[1], 100);
    }

    /**
     * Test the toString methods for some Corridors.
     */
    @Test
    public void testToString(){
        // Expected results:
        String expected1 = "Corridor l0 to l1 (100)";
        String expected2 = "Corridor l1 to l2 (200)";
        String expected3 = "Corridor l2 to l3 (250)";
        String expected4 = "Corridor l3 to l4 (175)";
        String expected5 = "Corridor l4 to l0 (50)";

        // Check against generated Corridors:
        Assert.assertEquals(expected1, corridors[0].toString());
        Assert.assertEquals(expected2, corridors[1].toString());
        Assert.assertEquals(expected3, corridors[2].toString());
        Assert.assertEquals(expected4, corridors[3].toString());
        Assert.assertEquals(expected5, corridors[4].toString());
    }

    /**
     * Test the overriding of the equals method.
     */
    @Test
    public void testEquals(){
        // Test if equal Corridors are equal:
        Assert.assertEquals(corridors[0], new Corridor(locations[0],
            locations[1], 100));
        Assert.assertEquals(corridors[1], new Corridor(locations[1],
            locations[2], 200));

        // Test if Objects that are not Corridors are unequal:
        Assert.assertNotEquals("This is not a Corridor", corridors[2]);
        Assert.assertNotEquals(corridors[3], new Object());

        // Test if Corridors with different start Locations are unequal:
        Assert.assertNotEquals(corridors[0], new Corridor(locations[2],
            locations[1], 100));
        Assert.assertNotEquals(corridors[4], new Corridor(locations[3],
            locations[0], 50));

        // Test if Corridors with different end Locations are unequal:
        Assert.assertNotEquals(corridors[1], new Corridor(locations[1],
           locations[3], 200));
        Assert.assertNotEquals(corridors[3], new Corridor(locations[3],
           locations[1], 175));

        // Test if Corridors with different capacities are unequal:
        Assert.assertNotEquals(corridors[2], new Corridor(locations[2],
            locations[3], 200));
        Assert.assertNotEquals(corridors[4], new Corridor(locations[4],
            locations[0], 250));
    }

    /**
     * Test the hashCode method.
     */
    @Test
    public void testHashCode(){
        // Test objects with equal hash codes
        Assert.assertEquals(corridors[0].hashCode(),
            (new Corridor(locations[0], locations[1], 100)).hashCode());
        Assert.assertEquals(corridors[1].hashCode(),
            (new Corridor(locations[1], locations[2], 200)).hashCode());
        Assert.assertEquals(corridors[2].hashCode(),
            (new Corridor(locations[2], locations[3], 250)).hashCode());
        Assert.assertEquals(corridors[3].hashCode(),
            (new Corridor(locations[3], locations[4], 175)).hashCode());
        Assert.assertEquals(corridors[4].hashCode(),
            (new Corridor(locations[4], locations[0], 50)).hashCode());
    }

    /**
     * Test the compareTo method.
     */
    @Test
    public void testCompareTo(){
        // Test for strictly less than:
        Assert.assertTrue(corridors[0].compareTo(new Corridor(locations[1],
            locations[2], 100)) < 0);
        Assert.assertTrue(corridors[0].compareTo(new Corridor(locations[0],
            locations[2], 100)) < 0);
        Assert.assertTrue(corridors[0].compareTo(new Corridor(locations[0],
            locations[1], 160)) < 0);

        // Test for strictly equal to:
        Assert.assertEquals(0, corridors[1].compareTo(new Corridor(locations[1],
            locations[2], 200)));
        Assert.assertEquals(0, corridors[4].compareTo(new Corridor(locations[4],
            locations[0], 50)));
        Assert.assertEquals(0, corridors[2].compareTo(new Corridor(locations[2],
            locations[3], 250)));

        // Test for strictly larger than:
        Assert.assertTrue(corridors[3].compareTo(new Corridor(locations[2],
            locations[3], 175)) > 0);
        Assert.assertTrue(corridors[3].compareTo(new Corridor(locations[3],
            locations[2], 175)) > 0);
    }

    /**
     * Test the method to check invariants:
     */
    @Test
    public void testCheckInvariant(){
        // Test Corridors that obey the invariant:
        Assert.assertTrue(corridors[0].checkInvariant());
        Assert.assertTrue(corridors[1].checkInvariant());
        Assert.assertTrue(corridors[2].checkInvariant());
        Assert.assertTrue(corridors[3].checkInvariant());
        Assert.assertTrue(corridors[4].checkInvariant());
    }
}
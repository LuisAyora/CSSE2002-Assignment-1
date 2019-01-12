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
        locations[0] = new Location("l0");
        locations[1] = new Location("l1");
        locations[2] = new Location("l2");
        locations[3] = new Location("l3");
        locations[4] = new Location("l4");

        // Set up Corridors to test
        corridors[0] = new Corridor(locations[0], locations[1], 100);
        corridors[1] = new Corridor(locations[1], locations[2], 200);
        corridors[2] = new Corridor(locations[2], locations[3], 250);
        corridors[3] = new Corridor(locations[3], locations[4], 175);
        corridors[4] = new Corridor(locations[4], locations[0], 50);
    }

    /*
    @Test
    public void testConstructor() {

    }
    */

    @Test(expected = NullPointerException.class)
    public void testConstructorNullPointer(){
        //Corridor invalidCorridor1 = new Corridor(null, locations[0], 100);
        //Corridor invalidCorridor2 = new Corridor(locations[3], null, 100);
    }
}
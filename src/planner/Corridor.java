package planner;

import java.util.Collection;

/**
 * <p>
 * An immutable class representing a traffic corridor from a start location in
 * the municipality to an end location in the municipality.
 * </p>
 * 
 * <p>
 * Each traffic corridor has a maximum capacity: an integer greater than zero
 * that represents the maximum number of people who can use the corridor at the
 * same time. The start and end location in a traffic corridor cannot be equal.
 * </p>
 */
public class Corridor implements Comparable<Corridor> {
    // Instance variables:
    private int capacity;
    private Location start;
    private Location end;


    /*
     * Class invariant:
     * maxCapacity >= 0 &&
     * start != null && end != null
     * start != end
     */

    /**
     * Creates a new traffic corridor with the given start and end locations,
     * and maximum capacity.
     * 
     * @param start
     *            the start location of the traffic corridor
     * @param end
     *            the end location of the traffic corridor
     * @param capacity
     *            the maximum capacity of the traffic corridor
     * @throws NullPointerException
     *             if either start or end are null
     * @throws IllegalArgumentException
     *             if the start location is equal to the end location (according
     *             to the equals method of the Location class), or if capacity
     *             is less than or equal to zero
     */
    public Corridor(Location start, Location end, int capacity) {
        if((start == null) || (end == null)){
            throw new NullPointerException("Locations cannot be null");
        }
        else if(capacity <= 0){
            throw new IllegalArgumentException("Capacity must be larger than zero");
        }
        else if(start.equals(end)){
            throw new IllegalArgumentException("Start and End corridors must be different");
        }
        this.start = start;
        this.end = end;
        this.capacity = capacity;
    }

    /**
     * Returns the start location of this traffic corridor.
     * 
     * @return the start location
     */
    public Location getStart() {
        return start;
    }

    /**
     * Returns the end location of this traffic corridor.
     * 
     * @return the end location
     */
    public Location getEnd() {
        return end;
    }

    /**
     * Returns the maximum capacity of the traffic corridor.
     * 
     * @return the maximum capacity of this traffic corridor
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * <p>
     * This method returns a string of the form: <br>
     * <br>
     * 
     * "Corridor START to END (CAPACITY)" <br>
     * <br>
     * 
     * where START and END are the toString() representations of the start and
     * end location of this corridor, respectively, and CAPACITY is the maximum
     * capacity of this traffic corridor. For example, the string representation
     * of a corridor having a start location with name "Annerly", end location
     * with name "City" and capacity 20 is "Corridor Annerly to City (20)".
     * </p>
     */
    @Override
    public String toString() {
        return "Corridor " + start.toString() + " to " + end.toString() +
            " (" + capacity + ")";
    }

    /**
     * <p>
     * This method returns true if and only if the given object (i) is an
     * instance of the class Corridor, (ii) has a start location equal to the
     * start location of this corridor, (iii) an end location equal to the end
     * location of this corridor, and (iv) a maximum capacity equal to the
     * maximum capacity of this corridor.
     * </p>
     * 
     * <p>
     * In the above description the equality of locations is determined using
     * the equals method of the Location class.
     * </p>
     */
    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Corridor)){
            return false;
        }
        Corridor other = (Corridor) object;
        return (other.start == start) && (other.end == end) &&
            (other.capacity == capacity);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = (23 * result) + result;
        result = (23 * result) + start.hashCode();
        result = (23 * result) + end.hashCode();
        return result;
    }

    /**
     * <p>
     * Corridors are ordered primarily by their start location (in ascending
     * order using the natural ordering defined in the Location class), and then
     * (for corridors with equal start locations) by their end location (in
     * ascending order using the natural ordering defined in the Location
     * class), and then (for corridors with equal start locations and equal end
     * locations) by the (ascending order) of their capacity.
     * </p>
     * 
     * <p>
     * For example, here is a list of corridors in order: <br>
     * <br>
     * 
     * Corridor Annerly to City (20)<br>
     * Corridor Annerly to City (30)<br>
     * Corridor Bardon to Ascot (40)<br>
     * Corridor Bardon to City (10)<br>
     * Corridor Bardon to Toowong (20)<br>
     * Corridor City to Bardon (10)<br>
     * 
     * </p>
     */
    @Override
    public int compareTo(Corridor other) {
        // Compare Start Corridors
        if(start.compareTo(other.start) == 0){
            // Equal Start Corridors - Compare End Corridors
            if(end.compareTo(other.end) == 0){
                // Equal End Corridors - Compare Capacities
                return Integer.compare(capacity, other.capacity);
            }
            return end.compareTo(other.end); //
        }
        return start.compareTo(other.start);
    }

    /**
     * <p>
     * Determines whether this class is internally consistent (i.e. it satisfies
     * its class invariant).
     * </p>
     * 
     * <p>
     * NOTE: This method is only intended for testing purposes.
     * </p>
     * 
     * @return true if this class is internally consistent, and false otherwise.
     */
    public boolean checkInvariant() {
        if(capacity <= 0){
            return false;
        }
        else if((start == null) || (end == null)){
            return false;
        }
        return !start.equals(end);
    }
}

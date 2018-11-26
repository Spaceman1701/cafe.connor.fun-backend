package fun.connor.cafe.domain;

/**
 * A specific location in the real world. Distances can be calculated
 * between locations.
 */
public interface Location {

    /**
     * Calculate the distance to another location
     * @param other the other location
     * @return the distance
     */
    Distance distanceTo(Location other);

}

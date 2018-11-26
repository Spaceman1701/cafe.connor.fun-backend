package fun.connor.cafe.domain.cafe;

import fun.connor.cafe.domain.Distance;
import fun.connor.cafe.domain.Location;
import xyz.morphia.annotations.Entity;

/**
 * An implementation of {@link Location} for street-addresses.
 *
 * See {@link Location}
 */
@Entity
public class Address implements Location {

    private String address; //TODO: make this a more useful structure


    /**
     * No-arg constructor for ODM
     */
    private Address() {}

    /**
     * Construct a new address from the given street address string
     * @param address the street address as a string
     */
    public Address(String address) {
        this.address = address;
    }

    @Override
    public Distance distanceTo(Location other) {
        return null;
    }
}

package fun.connor.cafe.domain.cafe;

import fun.connor.cafe.domain.Distance;
import fun.connor.cafe.domain.Location;
import xyz.morphia.annotations.Entity;

@Entity
public class Address implements Location {

    private String address; //TODO: make this a more useful structure


    private Address() {}

    public Address(String address) {
        this.address = address;
    }

    @Override
    public Distance distanceTo(Location other) {
        return null;
    }
}

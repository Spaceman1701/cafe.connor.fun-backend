package fun.connor.cafe.domain.cafe;

import fun.connor.cafe.domain.Distance;
import fun.connor.cafe.domain.Location;

public class Address implements Location {
    private String address; //TODO: make this a more useful structure

    @Override
    public Distance distanceTo(Location other) {
        return null;
    }
}

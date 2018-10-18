package fun.connor.cafe.domain;

public class PreciseLocation implements Location {

    private float latitude;
    private float longitude;

    @Override
    public Distance distanceTo(Location other) {
        return null;
    }
}

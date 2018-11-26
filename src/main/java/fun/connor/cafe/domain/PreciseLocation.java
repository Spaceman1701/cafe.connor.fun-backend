package fun.connor.cafe.domain;

/**
 * A precise Latitude-Longitude location.
 *
 * See {@link Location}
 */
public class PreciseLocation implements Location {

    private float latitude;
    private float longitude;

    @Override
    public Distance distanceTo(Location other) {
        return null;
    }
}

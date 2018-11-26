package fun.connor.cafe.domain;

import fun.connor.cafe.domain.cafe.Cafe;
import xyz.morphia.annotations.Embedded;
import xyz.morphia.annotations.Entity;

import java.util.UUID;

/**
 * Represents a calculateSingleScore visit to a cafe. Each visit occurs between a specific
 * {@link Account}, representing the user, and a specific {@link Cafe} at a
 * specific time.
 *
 * Visits are individually addressable in the universe with unique identification.
 */
@Entity
public class CafeVisit {

    private UUID uuid;

    private long timestamp;

    @Embedded
    private Cafe cafe;

    private UUID accountId;

    /**
     * No-arg constructor for ODM
     */
    private CafeVisit() {}

    /**
     * Construct a new CafeVisit
     * @param uuid the unique id for this visit. This must be unique in the universe.
     * @param timestamp The Unix time that the visit occured
     * @param cafe the Cafe that was visited
     * @param accountId the ID of the account which visited the Cafe
     */
    public CafeVisit(UUID uuid, long timestamp, Cafe cafe, UUID accountId) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.cafe = cafe;
        this.accountId = accountId;
    }

    /**
     * @return this visit's unique Id
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * @return the time when the visit occured
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return the visited {@link Cafe}
     */
    public Cafe getCafe() {
        return cafe;
    }

    /**
     * @return the Id of the visiting {@link Account}
     */
    public UUID getAccountId() {
        return accountId;
    }
}

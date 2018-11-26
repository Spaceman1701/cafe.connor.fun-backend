package fun.connor.cafe.domain;

import xyz.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * A user account. This object contains all of the user's data
 * in the application. Every account is associated with exactly
 * one {@link fun.connor.cafe.security.authentication.Subject} via Id.
 *
 * Since user's profiles are provided by a Client-ID service, user's
 * do not have usernames or secrets in the application.
 */
@Entity
@Indexes(
        @Index(value = "ownerId", fields = @Field("ownerId"))
)
public class Account {

    @Id
    private UUID uuid;

    @Embedded
    private List<CafeVisit> cafeVisits;


    private String ownerId;

    /**
     * No-arg constructor for ODM
     */
    private Account() {
        cafeVisits = new ArrayList<>();
    } //no-arg constructor for db

    /**
     * Construct a new account with no visits
     * @param uuid a unique id for this account
     * @param ownerId the {@link fun.connor.cafe.security.authentication.Subject} Id for the owner of this account
     */
    public Account(UUID uuid,  String ownerId) {
        this.uuid = uuid;
        this.cafeVisits = new ArrayList<>();
        this.ownerId = ownerId;
    }

    /**
     * @return the account Id
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * @return a list of all of this account's {@link CafeVisit}s
     */
    public List<CafeVisit> getCafeVisits() {
        return cafeVisits;
    }

    /**
     * @return the {@link fun.connor.cafe.security.authentication.Subject} Id for the owner of this account
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Record a {@link CafeVisit} from this account.
     * @param visit the CafeVisit to record
     */
    public void addCafeVisit(CafeVisit visit) {
        cafeVisits.add(visit);
    }

    /**
     * Find one of this account's CafeVisits by the visit's Id
     * @param uuid the id of the {@link CafeVisit}
     * @return either the CafeVisit or empty if no visit with the given Id is found
     */
    public Optional<CafeVisit> getVisitById(UUID uuid) {
        for (CafeVisit cafeVisit : cafeVisits) {
            if (cafeVisit.getUUID().equals(uuid)) {
                return Optional.of(cafeVisit);
            }
        }
        return Optional.empty();
    }

}

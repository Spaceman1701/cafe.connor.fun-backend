package fun.connor.cafe.domain;

import xyz.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    private Account() {
        cafeVisits = new ArrayList<>();
    } //no-arg constructor for db

    public Account(UUID uuid, List<CafeVisit> cafeVisits, String ownerId) {
        this.uuid = uuid;
        this.cafeVisits = cafeVisits;
        this.ownerId = ownerId;
    }

    public UUID getUUID() {
        return uuid;
    }

    public List<CafeVisit> getCafeVisits() {
        return cafeVisits;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void addCafeVisit(CafeVisit visit) {
        cafeVisits.add(visit);
    }

    public Optional<CafeVisit> getVisitById(UUID uuid) {
        for (CafeVisit cafeVisit : cafeVisits) {
            if (cafeVisit.getUUID().equals(uuid)) {
                return Optional.of(cafeVisit);
            }
        }
        return Optional.empty();
    }

}

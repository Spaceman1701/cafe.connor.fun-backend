package fun.connor.cafe.domain;

import fun.connor.cafe.domain.cafe.Cafe;
import xyz.morphia.annotations.Embedded;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;
import xyz.morphia.annotations.Reference;

import java.util.UUID;

@Entity
public class CafeVisit {

    private UUID uuid;

    private long timestamp;

    @Embedded
    private Cafe cafe;

    private UUID accountId;

    private CafeVisit() {}

    public CafeVisit(UUID uuid, long timestamp, Cafe cafe, UUID accountId) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.cafe = cafe;
        this.accountId = accountId;
    }

    public UUID getUUID() {
        return uuid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public UUID getAccountId() {
        return accountId;
    }
}

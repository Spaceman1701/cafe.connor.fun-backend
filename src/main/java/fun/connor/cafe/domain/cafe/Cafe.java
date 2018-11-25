package fun.connor.cafe.domain.cafe;

import fun.connor.cafe.domain.Account;
import fun.connor.cafe.domain.CafeVisit;
import xyz.morphia.annotations.Embedded;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;
import xyz.morphia.annotations.Reference;

import java.util.Map;
import java.util.UUID;

@Entity
public class Cafe {

    @Id
    private UUID uuid;

    @Embedded
    private Address address;

    private String name;

    private Cafe() {}

    public Cafe(UUID uuid, Address address, String name) {
        this.uuid = uuid;
        this.address = address;
        this.name = name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}

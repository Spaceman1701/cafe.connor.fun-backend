package fun.connor.cafe.domain.cafe;

import xyz.morphia.annotations.Embedded;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;

import java.util.UUID;

/**
 * A Cafe. Cafe's represent physical places. Each Cafe has a name, representing the
 * name of the Cafe. Each cafe also has an address to encode it's location. Cafe names
 * are not necessarily unique.
 *
 * For searchability, Cafe's are assigned a unique-id at creation.
 */
@Entity
public class Cafe {

    @Id
    private UUID uuid;

    @Embedded
    private Address address;

    private String name;

    /**
     * No-arg constructor for ODM
     */
    private Cafe() {}

    /**
     * Construct a new cafe
     * @param uuid a unique Id for this Cafe
     * @param address the address of this Cafe
     * @param name the name of this Cafe
     */
    public Cafe(UUID uuid, Address address, String name) {
        this.uuid = uuid;
        this.address = address;
        this.name = name;
    }

    /**
     * @return this Cafe's Id
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * @return this Cafe's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return this Cafe's name
     */
    public String getName() {
        return name;
    }
}

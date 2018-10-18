package fun.connor.cafe.domain.cafe;

import java.util.Map;
import java.util.UUID;

public class Cafe {

    private UUID uuid;
    private Address address;
    private CafeChain chain;
    private String name;

    private Map<ItemType, ItemReview> reviews;

    public Cafe() {

    }
}

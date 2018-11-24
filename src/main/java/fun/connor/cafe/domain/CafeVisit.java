package fun.connor.cafe.domain;

import fun.connor.cafe.domain.cafe.Cafe;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;

import java.util.UUID;

@Entity
public class CafeVisit {

    private UUID uuid;
    private long timestamp;
    private Cafe cafe;
    private Account account;


}

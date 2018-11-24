package fun.connor.cafe.domain;

import xyz.morphia.annotations.Id;

import java.util.UUID;

public class User {

    @Id
    private UUID id;


    public User(UUID id) {
        this.id = id;
    }


    public UUID getId() {
        return id;
    }


}

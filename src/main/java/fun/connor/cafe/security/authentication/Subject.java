package fun.connor.cafe.security.authentication;

import fun.connor.lighter.handler.RequestGuard;

import java.util.List;

public class Subject implements RequestGuard {

    private String id;

    public Subject(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Role> getRoles() {
        return null;
    }

}

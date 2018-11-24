package fun.connor.cafe.security.authentication;

import fun.connor.cafe.security.authorization.Permission;
import fun.connor.lighter.handler.RequestGuard;
import xyz.morphia.annotations.Embedded;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;

import java.util.List;

@Entity
public class Subject implements RequestGuard {

    @Id
    private String id;

    @Embedded
    private List<Role> roles;

    public Subject(String id, List<Role> roles) {
        this.id = id;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }
}

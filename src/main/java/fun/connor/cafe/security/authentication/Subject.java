package fun.connor.cafe.security.authentication;

import fun.connor.lighter.handler.RequestGuard;
import xyz.morphia.annotations.Embedded;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;

import java.util.List;

/**
 * Authentication Subject. Subject's represent the actors that can take
 * action within the domain of the service. All authenticated actions that
 * the service performs are performed <strong>on the behalf of</strong> a subject.
 *
 * Examples of subjects include: users, other services, or the service itself
 */
@Entity
public class Subject implements RequestGuard {

    @Id
    private String id;

    @Embedded
    private List<Role> roles;

    /**
     * Creates a new subject with a unique ID and a list of {@link Role}s.
     * @param id a unique String id for this subject.
     * @param roles the list of roles for this subject
     */
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

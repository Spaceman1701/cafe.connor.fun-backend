package fun.connor.cafe.security.authentication;

import java.util.Objects;

/**
 * A role to be consumed by authorization logic. Roles define broad categories of
 * permissions for subjects.
 */
public class Role {

    /**
     * Default role for normal, signed-in users.
     */
    public static final Role DEFAULT = new Role("default");

    /**
     * Anonymous role for clients that have no attempted sign-in
     */
    public static final Role ANON = new Role("anon");

    /**
     * Admin role for service administrators
     */
    public static final Role ADMIN = new Role("admin");

    private final String name;

    /**
     * Creates a new role
     * @param name the role name
     */
    Role(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * @return this Role's name. Cannot be null.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Role)) return false;
        Role other = (Role) o;
        return other.name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

package fun.connor.cafe.security.authentication;

import java.util.Objects;

public class Role {

    public static final Role DEFAULT = new Role("default");
    public static final Role ANON = new Role("anon");
    public static final Role ADMIN = new Role("admin");

    private final String name;

    Role(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

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

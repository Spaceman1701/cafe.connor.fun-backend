package fun.connor.cafe.security.authentication;

import fun.connor.lighter.handler.RequestGuard;

import java.util.List;

public interface Subject extends RequestGuard {
    String getId();
    List<Role> getRoles();
    boolean hasRole(Role role);
}

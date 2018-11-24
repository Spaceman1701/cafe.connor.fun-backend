package fun.connor.cafe.security;

import fun.connor.cafe.security.authentication.Subject;
import fun.connor.cafe.security.authorization.Claim;
import fun.connor.cafe.security.authorization.Permission;

import java.util.Optional;

public interface SecurityRepository {

    void createNewUser(Subject subject);
    void updateSubject(Subject subject);
    Optional<Subject> getSubjectById(String id);
}

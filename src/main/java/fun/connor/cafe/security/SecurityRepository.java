package fun.connor.cafe.security;

import fun.connor.cafe.security.authentication.Subject;

import java.util.Optional;

public interface SecurityRepository {

    void createNewUser(Subject subject);
    void updateSubject(Subject subject);
    Optional<Subject> getSubjectById(String id);
}

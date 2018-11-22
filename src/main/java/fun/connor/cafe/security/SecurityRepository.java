package fun.connor.cafe.security;

import fun.connor.cafe.security.authentication.Subject;
import fun.connor.cafe.security.authorization.Claim;

public interface SecurityRepository {

    void createNewUser(Subject subject);
    void addClaim(Subject subject, Claim claim);
    Subject getSubjectById(String id);
}

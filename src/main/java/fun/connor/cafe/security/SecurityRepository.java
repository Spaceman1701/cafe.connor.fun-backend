package fun.connor.cafe.security;

import fun.connor.cafe.security.authentication.Subject;

import java.util.Optional;

/**
 * DAO for auth resources. The top level object of this repository
 * is the {@link Subject}.
 */
public interface SecurityRepository {

    /**
     * Creates a new Subject in the repository. NoOp if the entry already exists
     * @param subject the subject to store
     */
    void createNewUser(Subject subject);

    /**
     * Updates a subject in the repository. Creates a new subject if the
     * entry does not exist
     * @param subject the updated subject
     */
    void updateSubject(Subject subject);

    /**
     * Find a subject by it's Id string in the repository
     * @param id the subject Id
     * @return A subject if one exists with the given ID or {@code empty} if no subject is found
     */
    Optional<Subject> getSubjectById(String id);
}

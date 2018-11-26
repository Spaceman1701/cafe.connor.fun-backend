package fun.connor.cafe.persistance;

import fun.connor.cafe.domain.Account;

import java.util.Optional;
import java.util.UUID;

/**
 * DAO for user accounts
 */
public interface AccountRepository {

    /**
     * Creates a new account in the repository. NoOp if account already exists
     * @param account the account to create
     */
    void create(Account account);

    /**
     * Updates an account in the repository. Creates a new account the given account
     * does not exist
     * @param account the updated account
     */
    void update(Account account);

    /**
     * Finds an account by its id
     * @param id the account id to find
     * @return Either the account or {@code empty} if it does not exist
     */
    Optional<Account> get(UUID id);

    /**
     * Finds an account by the ID of the {@link fun.connor.cafe.security.authentication.Subject}
     * that owns it.
     * @param subjectId The ID of the owner
     * @return Either the account or {@code empty} if it does not exist
     */
    Optional<Account> getBySubjectId(String subjectId);
}

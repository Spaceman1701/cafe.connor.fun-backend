package fun.connor.cafe.persistance;

import fun.connor.cafe.domain.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    void create(Account account);
    void update(Account account);
    Optional<Account> get(UUID id);
    Optional<Account> getBySubjectId(String subjectId);
}

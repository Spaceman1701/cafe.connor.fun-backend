package fun.connor.cafe.persistance;

import fun.connor.cafe.domain.Account;

import java.util.Optional;

public interface AccountRepository {

    void create(Account account);
    void update(Account account);
    Optional<Account> getBySubjectId(String subjectId);
}

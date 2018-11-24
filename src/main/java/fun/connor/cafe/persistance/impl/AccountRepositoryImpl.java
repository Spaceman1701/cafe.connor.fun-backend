package fun.connor.cafe.persistance.impl;

import fun.connor.cafe.domain.Account;
import fun.connor.cafe.persistance.AccountRepository;
import xyz.morphia.Datastore;
import xyz.morphia.query.Query;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {

    private Datastore datastore;

    @Inject
    public AccountRepositoryImpl(@Named("accountDatastore") Datastore datastore) {
        this.datastore = datastore;
    }


    @Override
    public void create(Account account) {
        datastore.save(account);
    }

    @Override
    public Optional<Account> getBySubjectId(String subjectId) {

        Query<Account> query = datastore.createQuery(Account.class)
                .field("subjectId")
                .equal(subjectId);

        return Optional.ofNullable(query.get());
    }
}

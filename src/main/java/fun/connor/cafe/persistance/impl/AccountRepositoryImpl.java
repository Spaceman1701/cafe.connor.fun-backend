package fun.connor.cafe.persistance.impl;

import fun.connor.cafe.domain.Account;
import fun.connor.cafe.persistance.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.morphia.Datastore;
import xyz.morphia.Key;
import xyz.morphia.query.Query;
import xyz.morphia.query.UpdateOperations;
import xyz.morphia.query.UpdateResults;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

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
    public void update(Account account) {
        Key<Account> key = datastore.getKey(account);

        UpdateOperations<Account> update = datastore.createUpdateOperations(Account.class)
                .set("cafeVisits", account.getCafeVisits());

        UpdateResults results = datastore.update(key, update);
        logger.info("updated {} accounts", results.getUpdatedCount());
    }

    @Override
    public Optional<Account> getBySubjectId(String subjectId) {

        Query<Account> query = datastore.createQuery(Account.class)
                .field("ownerId")
                .equal(subjectId);

        return Optional.ofNullable(query.get());
    }
}

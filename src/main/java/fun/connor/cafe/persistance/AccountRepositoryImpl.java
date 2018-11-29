package fun.connor.cafe.persistance;

import fun.connor.cafe.domain.Account;
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
import java.util.UUID;

/**
 * MongoDb implementation of {@link AccountRepository}. See {@link Account}
 */
public class AccountRepositoryImpl implements AccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    private Datastore datastore;

    /**
     * Create a new repository.
     * @param datastore Morphia datastore for this repository
     */
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
    public Optional<Account> get(UUID id) {
        return Optional.ofNullable(datastore.get(Account.class, id));
    }

    @Override
    public Optional<Account> getBySubjectId(String subjectId) {

        Query<Account> query = datastore.createQuery(Account.class)
                .field("ownerId")
                .equal(subjectId);

        return Optional.ofNullable(query.get());
    }
}

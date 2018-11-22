package fun.connor.cafe.security;

import fun.connor.cafe.security.authentication.Subject;
import fun.connor.cafe.security.authorization.Claim;
import xyz.morphia.Datastore;

import javax.inject.Inject;

public class MongoSecurityRepository implements SecurityRepository {

    private Datastore datastore;


    @Inject
    public MongoSecurityRepository(Datastore securityDatastore) {
        this.datastore = securityDatastore;
    }


    @Override
    public void createNewUser(Subject subject) {

    }

    @Override
    public void addClaim(Subject subject, Claim claim) {

    }

    @Override
    public Subject getSubjectById(String id) {
        return null;
    }
}

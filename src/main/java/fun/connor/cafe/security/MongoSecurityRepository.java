package fun.connor.cafe.security;

import fun.connor.cafe.security.authentication.Subject;
import xyz.morphia.Datastore;

import javax.inject.Inject;
import java.util.Optional;

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
    public void updateSubject(Subject subject) {

    }

    @Override
    public Optional<Subject> getSubjectById(String id) {
        Subject s = datastore.get(Subject.class, id);
        return Optional.ofNullable(s);
    }
}

package fun.connor.cafe.persistance.impl;

import fun.connor.cafe.domain.User;
import fun.connor.cafe.persistance.UserRepository;
import xyz.morphia.Datastore;

import javax.inject.Inject;
import javax.inject.Named;

public class UserRepositoryImpl implements UserRepository {

    private Datastore datastore;

    @Inject
    public UserRepositoryImpl(@Named("userDatastore") Datastore userDatastore) {
        this.datastore = userDatastore;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}

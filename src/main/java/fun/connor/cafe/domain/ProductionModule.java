package fun.connor.cafe.domain;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.MongoClient;
import fun.connor.cafe.persistance.CafeRepository;
import fun.connor.cafe.persistance.UserRepository;
import fun.connor.cafe.persistance.impl.CafeRepositoryImpl;
import fun.connor.cafe.persistance.impl.UserRepositoryImpl;
import fun.connor.cafe.security.MongoSecurityRepository;
import fun.connor.cafe.security.SecurityRepository;
import fun.connor.cafe.security.authentication.GoogleSubjectFactory;
import fun.connor.cafe.security.authentication.SubjectFactory;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

import javax.inject.Singleton;

public class ProductionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CafeRepository.class).to(CafeRepositoryImpl.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(SubjectFactory.class).to(GoogleSubjectFactory.class);
        bind(SecurityRepository.class).to(MongoSecurityRepository.class);
    }

    @Provides public MongoClient mongoClient() {
        return new MongoClient(); //TODO: configuration
    }

    @Provides @Singleton public Morphia morphia() {
        return new Morphia(); //TODO: configuration
    }

    @Provides @Singleton public Datastore securityDatastore(MongoClient client, Morphia morphia) {
        return morphia.createDatastore(client, "security_data");
    }

    @Provides @Singleton public Datastore userDatastore(MongoClient client, Morphia morphia) {
        return morphia.createDatastore(client, "user_data");
    }
}

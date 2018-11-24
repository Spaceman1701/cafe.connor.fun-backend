package fun.connor.cafe.domain;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.MongoClient;
import fun.connor.cafe.persistance.AccountRepository;
import fun.connor.cafe.persistance.CafeRepository;
import fun.connor.cafe.persistance.impl.AccountRepositoryImpl;
import fun.connor.cafe.persistance.impl.CafeRepositoryImpl;
import fun.connor.cafe.security.MongoSecurityRepository;
import fun.connor.cafe.security.SecurityRepository;
import fun.connor.cafe.security.authentication.GoogleSubjectFactory;
import fun.connor.cafe.security.authentication.SubjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

import javax.inject.Named;
import javax.inject.Singleton;

public class ProductionModule extends AbstractModule {
    private static final Logger logger = LoggerFactory.getLogger(ProductionModule.class);
    @Override
    protected void configure() {
        bind(CafeRepository.class).to(CafeRepositoryImpl.class);
        bind(AccountRepository.class).to(AccountRepositoryImpl.class);
        bind(SubjectFactory.class).to(GoogleSubjectFactory.class);
        bind(SecurityRepository.class).to(MongoSecurityRepository.class);
    }

    @Provides @Singleton public MongoClient mongoClient() {
        long start = System.currentTimeMillis();
        MongoClient client = new MongoClient();
        long end = System.currentTimeMillis();
        logger.info("took {} ms to init mongo client", end - start);
        return client; //TODO: configuration
    }

    @Provides @Singleton public Morphia morphia() {
        return new Morphia(); //TODO: configuration
    }

    @Provides @Singleton public Datastore securityDatastore(MongoClient client, Morphia morphia) {
        return morphia.createDatastore(client, "security_data");
    }

    @Named("accountDatastore")
    @Provides @Singleton public Datastore accountDatastore(MongoClient client, Morphia morphia) {
        return morphia.createDatastore(client, "account_data");
    }

    @Provides public HttpTransport clientTransport() {
        return new NetHttpTransport();
    }

    @Provides public JsonFactory jsonFactory() {
        return new JacksonFactory();
    }

    @Provides public GoogleIdTokenVerifier googleIdTokenVerifier(HttpTransport transport, JsonFactory jsonFactory) {
        return new GoogleIdTokenVerifier(transport, jsonFactory);
    }

}

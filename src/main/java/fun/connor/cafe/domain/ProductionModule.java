package fun.connor.cafe.domain;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.MongoClient;
import fun.connor.cafe.domain.score.ScoreCalculator;
import fun.connor.cafe.domain.score.SimpleScoreCalculator;
import fun.connor.cafe.persistance.AccountRepository;
import fun.connor.cafe.persistance.AccountRepositoryImpl;
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

/**
 * The Guice {@link com.google.inject.Module} for the production configuration of
 * the application. The production configuration includes MongoDB implementations for
 * all persistent data stores, Google Client-ID based authentication, and a default
 * implementation of the score calculator
 */
public class ProductionModule extends AbstractModule {
    private static final Logger logger = LoggerFactory.getLogger(ProductionModule.class);
    @Override
    protected void configure() {
        bind(AccountRepository.class).to(AccountRepositoryImpl.class);
        bind(SubjectFactory.class).to(GoogleSubjectFactory.class);
        bind(SecurityRepository.class).to(MongoSecurityRepository.class);
        bind(ScoreCalculator.class).to(SimpleScoreCalculator.class);
    }

    /**
     * Provider method for the {@link MongoClient}. Used only by other
     * providers
     * @return The configured {@link MongoClient}
     */
    @Provides @Singleton public MongoClient mongoClient() {
        long start = System.currentTimeMillis();
        MongoClient client = new MongoClient();
        long end = System.currentTimeMillis();
        logger.info("took {} ms to init mongo client", end - start);
        return client; //TODO: configuration
    }

    /**
     * Provider method for the {@link Morphia}. Used only by other
     * providers
     * @return The configured {@link Morphia}
     */
    @Provides @Singleton public Morphia morphia() {
        Morphia morphia = new Morphia()
                .mapPackage("fun.connor.cafe");
        morphia.getMapper().getOptions().setStoreEmpties(true);
        return morphia; //TODO: configuration
    }

    /**
     * Provider method for the security {@link Datastore} repository. This singleton object
     * provides persistence for security related objects. The security datastore should not
     * be used in normal handler methods as it exists outside the "normal" domain of the
     * application.
     *
     * The datastore is thread safe.
     * @param client The MongoDb client. See {@link ProductionModule#mongoClient()}
     * @param morphia The Morphia singleton. See {@link ProductionModule#morphia()}
     * @return The security datastore singleton.
     */
    @Provides @Singleton public Datastore securityDatastore(MongoClient client, Morphia morphia) {
        return morphia.createDatastore(client, "security_data");
    }

    /**
     * Provider method for the account {@link Datastore}. This singleton provides low-level
     * persistence for account data. This is the main datastore for the application.
     *
     * The datastore is thread safe.
     * @param client The MongoDb client. See {@link ProductionModule#mongoClient()}
     * @param morphia The Morphia singleton. See {@link ProductionModule#morphia()}
     * @return The security datastore singleton.
     */
    @Named("accountDatastore")
    @Provides @Singleton public Datastore accountDatastore(MongoClient client, Morphia morphia) {
        return morphia.createDatastore(client, "account_data");
    }

    /**
     * Provider method the the {@link HttpTransport} for use with Google client libraries
     * @return a new HttpTransport object
     */
    @Provides public HttpTransport clientTransport() {
        return new NetHttpTransport();
    }

    /**
     * Provider method for {@link JsonFactory} objects for use with Google Client Libraries.
     * @return a new JsonFactory object.
     */
    @Provides public JsonFactory jsonFactory() {
        return new JacksonFactory();
    }

    /**
     * Provider method for a {@link GoogleIdTokenVerifier} object. This is used to verify Google Client-ID tokens.
     * @param transport Pluggable HttpTransport. See {@link ProductionModule#clientTransport()}
     * @param jsonFactory See {@link ProductionModule#jsonFactory()}
     * @return a new GoogleIdTokenVerifier object
     */
    @Provides public GoogleIdTokenVerifier googleIdTokenVerifier(HttpTransport transport, JsonFactory jsonFactory) {
        return new GoogleIdTokenVerifier(transport, jsonFactory);
    }

}

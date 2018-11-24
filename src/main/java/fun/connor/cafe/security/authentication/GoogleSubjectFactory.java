package fun.connor.cafe.security.authentication;

import fun.connor.cafe.security.SecurityRepository;
import fun.connor.lighter.adapter.TypeAdapterFactory;
import fun.connor.lighter.handler.Request;
import fun.connor.lighter.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class GoogleSubjectFactory implements SubjectFactory {

    private static final Logger logger = LoggerFactory.getLogger(GoogleSubjectFactory.class);

    private final GoogleSignInAuthenticator authenticator;
    private final SecurityRepository securityRepository;

    @Inject
    public GoogleSubjectFactory(GoogleSignInAuthenticator authenticator, SecurityRepository securityRepository) {
        this.authenticator = authenticator;
        this.securityRepository = securityRepository;
    }

    @Override
    public Subject newInstance
            (Map<String, String> pathParams, Map<String, String> queryParams, Request request,
             TypeAdapterFactory typeAdapterFactory) {
        logger.info("creating new subject instance");
        String authHeader = request.getHeaderValue(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            return new Subject("anon", Collections.singletonList(Role.ANON));
        }
        String bearerToken = extractBearer(authHeader);
        if (bearerToken == null) {
            return null; //error case
        }

        String id;
        try {
            id = authenticator.doAuthentication(bearerToken).orElse(null );
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
        if (id == null) {
            return null;
        }

        Optional<Subject> subject = securityRepository.getSubjectById(bearerToken);
        if (!subject.isPresent()) {
            Subject newSubject = createNewSubject(id);
            securityRepository.createNewUser(newSubject);
            return newSubject;
        }
        return subject.get();
    }

    private Subject createNewSubject(String id) {
        return new Subject(id, Collections.singletonList(Role.DEFAULT));
    }

    private String extractBearer(String authHeader) {
        String[] parts = authHeader.split(":");
        if (parts.length != 2 || !parts[0].equals("Bearer")) {
            return null;
        }
        return parts[1];
    }
}

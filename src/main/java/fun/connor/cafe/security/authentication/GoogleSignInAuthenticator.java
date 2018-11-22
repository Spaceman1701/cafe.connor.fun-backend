package fun.connor.cafe.security.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import javax.inject.Inject;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

public class GoogleSignInAuthenticator {

    private final GoogleIdTokenVerifier verifier;

    @Inject
    public GoogleSignInAuthenticator(final GoogleIdTokenVerifier verifier) {
        this.verifier = verifier;
    }

    public Optional<String> doAuthentication(String idToken) throws GeneralSecurityException, IOException {
        GoogleIdToken token = verifier.verify(idToken);
        if (token == null) {
            return Optional.empty();
        }

        String userId = token.getPayload().getSubject();
        return Optional.of(userId);
    }
}

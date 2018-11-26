package fun.connor.cafe.security.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import javax.inject.Inject;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

/**
 * The {@code GoogleSignInAuthenticator} authenticates Google-generated user
 * credentials (in the form of a signed-id token) and extracts the user id
 * string.
 */
public class GoogleSignInAuthenticator {

    private final GoogleIdTokenVerifier verifier;

    @Inject
    public GoogleSignInAuthenticator(final GoogleIdTokenVerifier verifier) {
        this.verifier = verifier;
    }

    /**
     * Authenticates that the ID token is up-to-date and was legally issued from the Google Client-ID service
     * @param idToken the raw ID token string
     * @return Either the user ID iff the token is valid or {@code empty} if it is not
     * @throws GeneralSecurityException In the event of a critical error
     * @throws IOException In the event that the Google Client-ID service cannot be contacted
     */
    public Optional<String> doAuthentication(String idToken) throws GeneralSecurityException, IOException {
        GoogleIdToken token = verifier.verify(idToken);
        if (token == null) {
            return Optional.empty();
        }

        String userId = token.getPayload().getSubject();
        return Optional.of(userId);
    }
}

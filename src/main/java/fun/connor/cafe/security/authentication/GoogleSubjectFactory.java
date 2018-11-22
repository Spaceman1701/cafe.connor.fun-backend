package fun.connor.cafe.security.authentication;

import fun.connor.cafe.security.SecurityRepository;
import fun.connor.lighter.adapter.TypeAdapterFactory;
import fun.connor.lighter.handler.Request;

import javax.inject.Inject;
import java.util.Map;

public class GoogleSubjectFactory implements SubjectFactory {

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
        return null;
    }
}

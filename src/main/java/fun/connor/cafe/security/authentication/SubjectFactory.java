package fun.connor.cafe.security.authentication;

import fun.connor.lighter.adapter.TypeAdapterFactory;
import fun.connor.lighter.declarative.ProducesRequestGuard;
import fun.connor.lighter.handler.Request;
import fun.connor.lighter.handler.RequestGuardFactory;

import java.util.Map;

/**
 * {@link RequestGuardFactory} for {@link Subject} classes. Generates only authenticated
 * Subjects.
 */
@ProducesRequestGuard(Subject.class)
public interface SubjectFactory extends RequestGuardFactory<Subject> {
    /**
     * Create a new instance of an authenticated {@link Subject} from the current
     * request
     * @param pathParams Request path params
     * @param queryParams Request query params
     * @param request raw request object
     * @param typeAdapterFactory Lighter's global {@link TypeAdapterFactory} for this request.
     * @return An authenticated subject. If no-credentials are provided in the request, an anonymous Subject
     * is created. If bad credentials are provided, {@code null}.
     */
    @Override
    Subject newInstance(Map<String, String> pathParams, Map<String, String> queryParams,
                        Request request, TypeAdapterFactory typeAdapterFactory);
}

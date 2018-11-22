package fun.connor.cafe.security.authentication;

import fun.connor.cafe.security.authentication.Subject;
import fun.connor.lighter.adapter.TypeAdapterFactory;
import fun.connor.lighter.declarative.ProducesRequestGuard;
import fun.connor.lighter.handler.Request;
import fun.connor.lighter.handler.RequestGuardFactory;

import java.util.Map;

@ProducesRequestGuard(Subject.class)
public interface SubjectFactory extends RequestGuardFactory<Subject> {
    @Override
    Subject newInstance(Map<String, String> pathParams, Map<String, String> queryParams,
                        Request request, TypeAdapterFactory typeAdapterFactory);
}

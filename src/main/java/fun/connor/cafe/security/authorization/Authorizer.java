package fun.connor.cafe.security.authorization;

public interface Authorizer<T> {
    ResourceIdentifier getIdentifier();
}

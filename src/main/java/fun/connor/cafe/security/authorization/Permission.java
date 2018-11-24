package fun.connor.cafe.security.authorization;

public class Permission {

    

    public boolean grantsAccess(Action action, Authorizer<?> resource) {
        return false;
    }
}

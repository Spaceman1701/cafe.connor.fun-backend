package fun.connor.cafe.persistance;

import fun.connor.cafe.domain.User;

public interface UserRepository {
    void create(User user);
    User get(String username);
    void update(User user);
    void delete(User user);
}

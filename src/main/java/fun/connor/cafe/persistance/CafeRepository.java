package fun.connor.cafe.persistance;

import fun.connor.cafe.domain.cafe.Cafe;

public interface CafeRepository {

    void create(Cafe cafe);
    void getByName(String name);
}

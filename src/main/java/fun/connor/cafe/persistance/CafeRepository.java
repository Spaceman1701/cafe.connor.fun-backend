package fun.connor.cafe.persistance;

import fun.connor.cafe.domain.cafe.Cafe;
import fun.connor.cafe.domain.cafe.CafeChain;

public interface CafeRepository {

    void create(Cafe cafe);
    void getByName(String name);
}

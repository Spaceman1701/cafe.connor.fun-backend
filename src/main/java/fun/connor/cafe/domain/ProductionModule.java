package fun.connor.cafe.domain;

import com.google.inject.AbstractModule;
import fun.connor.cafe.persistance.CafeRepository;
import fun.connor.cafe.persistance.UserRepository;
import fun.connor.cafe.persistance.impl.CafeRepositoryImpl;
import fun.connor.cafe.persistance.impl.UserRepositoryImpl;

public class ProductionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CafeRepository.class).to(CafeRepositoryImpl.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);
    }
}

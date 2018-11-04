package fun.connor.cafe.controllers;

import com.google.inject.Inject;
import fun.connor.cafe.domain.CafeVisit;
import fun.connor.cafe.domain.cafe.Cafe;
import fun.connor.cafe.persistance.CafeRepository;
import fun.connor.lighter.declarative.Get;
import fun.connor.lighter.declarative.Post;
import fun.connor.lighter.declarative.QueryParams;
import fun.connor.lighter.declarative.ResourceController;
import fun.connor.lighter.handler.Response;

import java.util.List;
import java.util.UUID;

@ResourceController("/cafe")
public class CafeController {

    @Inject
    public CafeRepository cafeRepository;


    @Get("/nearest") @QueryParams("count")
    public Response<List<Cafe>> getNearestCafes(Integer count) {
        return null;
    }

    @Post("/{cafe}/visit")
    public Response<CafeVisit> visitCafe(UUID visit) {
        return null;
    }
}

package fun.connor.cafe.controllers;

import com.google.inject.Inject;
import fun.connor.cafe.domain.cafe.Cafe;
import fun.connor.cafe.persistance.CafeRepository;
import fun.connor.lighter.declarative.Get;
import fun.connor.lighter.declarative.QueryParams;
import fun.connor.lighter.declarative.ResourceController;

import javax.xml.ws.Response;
import java.util.List;

@ResourceController("/cafe")
public class CafeController {

    @Inject
    public CafeRepository cafeRepository;


    @Get("/nearest") @QueryParams("count")
    public Response<List<Cafe>> getNearestCafes(int count) {
        return null;
    }


}

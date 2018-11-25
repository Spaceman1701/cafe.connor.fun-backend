package fun.connor.cafe.controllers;

import com.google.inject.Inject;
import fun.connor.cafe.domain.Account;
import fun.connor.cafe.domain.CafeVisit;
import fun.connor.cafe.domain.score.Score;
import fun.connor.cafe.domain.score.ScoreCalculator;
import fun.connor.cafe.security.authentication.Subject;
import fun.connor.lighter.declarative.Body;
import fun.connor.lighter.declarative.Get;
import fun.connor.lighter.declarative.ResourceController;
import fun.connor.lighter.response.Response;
import fun.connor.lighter.response.Responses;


@ResourceController("/score")
public class ScoreController {

    private ScoreCalculator scoreCalculator;

    @Inject
    public ScoreController(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }


    @Get("/single")
    public Response<Score> singleCafe(@Body CafeVisit cafeVisit) {
        Score score = scoreCalculator.single(cafeVisit);

        return Responses.json(score);
    }

    @Get("/account")
    public Response<Score> accountScore(@Body Account account) {
        Score score = scoreCalculator.calculateScore(account.getCafeVisits());
        return Responses.json(score);
    }
}

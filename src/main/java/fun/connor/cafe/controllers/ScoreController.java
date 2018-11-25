package fun.connor.cafe.controllers;

import com.google.inject.Inject;
import fun.connor.cafe.domain.Account;
import fun.connor.cafe.domain.CafeVisit;
import fun.connor.cafe.domain.score.Score;
import fun.connor.cafe.domain.score.ScoreCalculator;
import fun.connor.cafe.persistance.AccountRepository;
import fun.connor.lighter.declarative.Body;
import fun.connor.lighter.declarative.Get;
import fun.connor.lighter.declarative.ResourceController;
import fun.connor.lighter.http.StatusCodes;
import fun.connor.lighter.response.Response;
import fun.connor.lighter.response.Responses;

import java.util.Optional;
import java.util.UUID;


@ResourceController("/score")
public class ScoreController {

    private ScoreCalculator scoreCalculator;

    private AccountRepository accountRepository;

    @Inject
    public ScoreController(ScoreCalculator scoreCalculator, AccountRepository accountRepository) {
        this.scoreCalculator = scoreCalculator;
        this.accountRepository = accountRepository;
    }


    @Get("/single/{accountId}/{visitId}")
    public Response<Score> singleCafe(UUID accountId, UUID visitId) {
        Optional<Account> maybeAccount = accountRepository.get(accountId);
        if (!maybeAccount.isPresent()) {
            return Responses.noContent(StatusCodes.NOT_FOUND);
        }

        Optional<CafeVisit> maybeVisit = maybeAccount.get().getVisitById(visitId);
        if (!maybeVisit.isPresent()) {
            return Responses.noContent(StatusCodes.NOT_FOUND);
        }

        Score score = scoreCalculator.single(maybeVisit.get());
        return Responses.json(score);
    }

    @Get("/account/{accountId}")
    public Response<Score> accountScore(UUID accountId) {
        Optional<Account> maybeAccount = accountRepository.get(accountId);
        if (!maybeAccount.isPresent()) {
            return Responses.noContent(StatusCodes.NOT_FOUND);
        }
        Score score = scoreCalculator.calculateScore(maybeAccount.get().getCafeVisits());
        return Responses.json(score);
    }
}

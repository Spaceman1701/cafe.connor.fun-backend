package fun.connor.cafe.controllers;

import com.google.inject.Inject;
import fun.connor.cafe.domain.Account;
import fun.connor.cafe.domain.CafeVisit;
import fun.connor.cafe.domain.cafe.Cafe;
import fun.connor.cafe.domain.score.Score;
import fun.connor.cafe.domain.score.ScoreCalculator;
import fun.connor.cafe.persistance.AccountRepository;
import fun.connor.cafe.security.authentication.Role;
import fun.connor.cafe.security.authentication.Subject;
import fun.connor.lighter.declarative.Body;
import fun.connor.lighter.declarative.Get;
import fun.connor.lighter.declarative.Post;
import fun.connor.lighter.declarative.ResourceController;
import fun.connor.lighter.http.StatusCodes;
import fun.connor.lighter.response.Response;
import fun.connor.lighter.response.Responses;

import java.util.Optional;
import java.util.UUID;


/**
 * Lighter resource controller for {@link Score} related operations
 */
@ResourceController("/score")
public class ScoreController {

    private ScoreCalculator scoreCalculator;

    private AccountRepository accountRepository;

    /**
     * Create a new ScoreController with the given {@link ScoreCalculator} strategy and the
     * given repository for persistance
     * @param scoreCalculator the score calculation strategy
     * @param accountRepository the DAO for persistence
     */
    @Inject
    public ScoreController(ScoreCalculator scoreCalculator, AccountRepository accountRepository) {
        this.scoreCalculator = scoreCalculator;
        this.accountRepository = accountRepository;
    }


    /**
     * Lighter endpoint method for calculating the score of a single {@link CafeVisit}
     * @param accountId The {@link Account} Id that has that visit to be scored
     * @param visitId The {@link CafeVisit} Id of the visit to be scored
     * @return The result of the score calculation, if it was successful. If
     * the account or visit Ids are invalid, <code>404 Not Found</code> is returned
     */
    @Get("/calculateSingleScore/{accountId}/{visitId}")
    public Response<Score> singleCafe(UUID accountId, UUID visitId) {
        Optional<Account> maybeAccount = accountRepository.get(accountId);
        if (!maybeAccount.isPresent()) {
            return Responses.noContent(StatusCodes.NOT_FOUND);
        }

        Optional<CafeVisit> maybeVisit = maybeAccount.get().getVisitById(visitId);
        if (!maybeVisit.isPresent()) {
            return Responses.noContent(StatusCodes.NOT_FOUND);
        }

        Score score = scoreCalculator.calculateSingleScore(maybeVisit.get());
        return Responses.json(score);
    }

    /**
     * Lighter endpoint method for calculating the total score earned by an {@link Account}.
     * @param accountId the Id of the {@link Account} to score.
     * @return the result of the score calculation, if successful. If the account cannot be found,
     * a <code>404 Not Found</code> is returned
     */
    @Get("/account/{accountId}")
    public Response<Score> accountScore(UUID accountId) {
        Optional<Account> maybeAccount = accountRepository.get(accountId);
        if (!maybeAccount.isPresent()) {
            return Responses.noContent(StatusCodes.NOT_FOUND);
        }
        Score score = scoreCalculator.calculateScore(maybeAccount.get().getCafeVisits());
        return Responses.json(score);
    }

    /**
     * Lighter endpoint method for calculating the potential score earned by visiting a Cafe.
     * @param cafe the cafe being visited
     * @param accountId the account that is visiting
     * @return a json encoded {@link Score}
     */
    @Post("/potential/{accountId}")
    public Response<Score> getPotentialScore(@Body Cafe cafe, UUID accountId) {
        CafeVisit visit = new CafeVisit(null, System.currentTimeMillis() / 1000L, cafe, accountId);
        Score score  = scoreCalculator.calculateSingleScore(visit);
        return Responses.json(score);
    }
}

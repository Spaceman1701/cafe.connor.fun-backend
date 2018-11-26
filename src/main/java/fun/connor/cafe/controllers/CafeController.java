package fun.connor.cafe.controllers;

import com.google.inject.Inject;
import fun.connor.cafe.domain.Account;
import fun.connor.cafe.domain.CafeVisit;
import fun.connor.cafe.domain.cafe.Address;
import fun.connor.cafe.domain.cafe.Cafe;
import fun.connor.cafe.persistance.AccountRepository;
import fun.connor.cafe.security.authentication.Subject;
import fun.connor.lighter.declarative.*;
import fun.connor.lighter.http.StatusCodes;
import fun.connor.lighter.response.Response;
import fun.connor.lighter.response.Responses;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ResourceController("/cafe")
public class CafeController {

    private AccountRepository accountRepository;

    @Inject
    public CafeController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Get("/nearest") @QueryParams("count")
    public Response<List<Cafe>> getNearestCafes(Integer count) {

        List<Cafe> cafes = Arrays.asList(
                new Cafe(UUID.nameUUIDFromBytes("Cafe1".getBytes()), new Address("123 AStreet"), "Cafe1"),
                new Cafe(UUID.nameUUIDFromBytes("Cafe2".getBytes()), new Address("456 AStreet"), "Cafe2"),
                new Cafe(UUID.nameUUIDFromBytes("Cafe3".getBytes()), new Address("789 AStreet"), "Cafe3")
        );
        return Responses.json(cafes);
    }

    @Post("/visit")
    public Response<CafeVisit> visitCafe(@Body Cafe cafe, Subject subject) {
        Optional<Account> maybeAccount = accountRepository.getBySubjectId(subject.getId());
        if (!maybeAccount.isPresent()) {
            return Responses.noContent(StatusCodes.UNAUTHORIZED);
        }
        Account account = maybeAccount.get();
        CafeVisit visit =
                new CafeVisit(UUID.randomUUID(), System.currentTimeMillis() / 1000L, cafe, account.getUUID());

        account.addCafeVisit(visit);

        accountRepository.update(account);

        return Responses.json(visit, StatusCodes.CREATED);
    }
}

package fun.connor.cafe.controllers;


import com.google.inject.Inject;
import fun.connor.cafe.domain.Account;
import fun.connor.cafe.persistance.AccountRepository;
import fun.connor.cafe.security.authentication.Role;
import fun.connor.cafe.security.authentication.Subject;
import fun.connor.lighter.declarative.Get;
import fun.connor.lighter.declarative.ResourceController;
import fun.connor.lighter.http.StatusCodes;
import fun.connor.lighter.response.Response;
import fun.connor.lighter.response.Responses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/**
 * Lighter resource controller for {@link Account} operations
 */
@ResourceController("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountRepository accountRepository;

    /**
     * Construct an AccountController around the given repository
     * @param repository the persistence DAO
     */
    @Inject
    public AccountController(AccountRepository repository) {
        this.accountRepository = repository;
    }

    /**
     * Lighter Endpoint-Method for retrieving account details. Responds to HTTP-GET
     * requests
     * <br>
     * This method takes no parameters from the request. Instead, it depends on a
     * {@link Subject} {@link fun.connor.lighter.handler.RequestGuard} to provide
     * details on the subject making the request. If the subject is anonymous (not signed-in),
     * this method responds with a <code>401 Unauthorized</code> response.
     * <br>
     * Otherwise, the method responds with the Subject's {@link Account}. If the account
     * does not exist, a new one is silently created and saved in the repository.
     * @param subject An authenticated {@link Subject} request guard
     * @return the Subject's {@link Account} as a JSON response or an error (in the Subject is not authenticated)
     */
    @Get
    public Response<Account> getAccountDetails(Subject subject) {
        logger.info("getting account details: {}", subject.toString());
        if (subject.getRoles().contains(Role.ANON)) {
            return Responses.noContent(StatusCodes.UNAUTHORIZED);
        }

        Optional<Account> createdAccount = accountRepository.getBySubjectId(subject.getId());
        if (createdAccount.isPresent()) {
            return Responses.json(createdAccount.get());
        }

        Account newAccount = new Account(UUID.randomUUID(), subject.getId());
        accountRepository.create(newAccount);

        return Responses.json(newAccount, StatusCodes.CREATED);
    }


}

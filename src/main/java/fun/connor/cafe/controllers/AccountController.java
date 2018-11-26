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

@ResourceController("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountRepository accountRepository;

    @Inject
    public AccountController(AccountRepository repository) {
        this.accountRepository = repository;
    }

    @Get
    public Response<Account> getAccountDetails(Subject subject) {
        logger.info("getting account details: {}", subject.toString());
        if (subject.getRoles().contains(Role.ANON)) {
            return Responses.noContent(400);
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

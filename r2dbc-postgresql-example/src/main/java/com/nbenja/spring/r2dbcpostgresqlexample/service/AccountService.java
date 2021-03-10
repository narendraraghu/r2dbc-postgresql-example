package com.nbenja.spring.r2dbcpostgresqlexample.service;

import com.nbenja.spring.r2dbcpostgresqlexample.domain.AccountDetails;
import com.nbenja.spring.r2dbcpostgresqlexample.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Flux<AccountDetails> getAllAccount(String id) {
        return accountRepository.findAll();
    }


    public Mono<AccountDetails> getAccountById(String id) {
        return accountRepository.findById(id);
    }
}

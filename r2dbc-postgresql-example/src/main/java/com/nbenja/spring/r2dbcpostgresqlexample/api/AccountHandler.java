package com.nbenja.spring.r2dbcpostgresqlexample.api;

import com.nbenja.spring.r2dbcpostgresqlexample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Locale;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.from;

@Component
public class AccountHandler {

    @Autowired
    AccountService accountService;

    public Mono<ServerResponse> getAccount(ServerRequest serverRequest) {
        return from(accountService.getAccountById(serverRequest.pathVariable("id")))
                .flatMap(u -> ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromObject(u.getAccount_name().toLowerCase(Locale.ROOT))))
                .switchIfEmpty(notFound().build());
    }
}

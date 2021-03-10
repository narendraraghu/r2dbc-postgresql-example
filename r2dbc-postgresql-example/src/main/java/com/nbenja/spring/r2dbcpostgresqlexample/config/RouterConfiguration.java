package com.nbenja.spring.r2dbcpostgresqlexample.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.nbenja.spring.r2dbcpostgresqlexample.api.AccountHandler;
import com.nbenja.spring.r2dbcpostgresqlexample.api.UserHandler;
import com.nbenja.spring.r2dbcpostgresqlexample.domain.AccountDetails;
import com.nbenja.spring.r2dbcpostgresqlexample.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class RouterConfiguration {

  @Autowired
  AccountRepository accountRepository;

  @Bean
  public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
    return route()
        .POST("/users", userHandler::createUser)
        .GET("/users", userHandler::getUsers)
        .GET("/users/{id}", userHandler::getUser)
        .build();
  }

  @Bean
  RouterFunction<ServerResponse> getAccountByIdRoute() {

    return route(GET("/account/{id}"),
            req -> ok().body(
                    accountRepository.findById(req.pathVariable("id")), AccountDetails.class));
  }

  @Bean
  RouterFunction<ServerResponse> getALl() {

    return route(GET("/account"),
            req -> ok().body(
                    accountRepository.findAll(), AccountDetails.class));
  }

  @Bean
  RouterFunction<ServerResponse> getAccountByPhone(AccountHandler accountHandler) {
    return nest (
            path("/acc"),
            nest(   accept(MediaType.ALL),
                    route(GET("/{id}"), accountHandler::getAccount)
            )
    );

  }

}

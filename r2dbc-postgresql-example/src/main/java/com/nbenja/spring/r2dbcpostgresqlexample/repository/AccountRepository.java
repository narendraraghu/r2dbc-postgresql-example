package com.nbenja.spring.r2dbcpostgresqlexample.repository;

import com.nbenja.spring.r2dbcpostgresqlexample.domain.AccountDetails;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<AccountDetails, String> {
}

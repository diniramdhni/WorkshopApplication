package com.carproject.application.repository;

import com.carproject.application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = """
            SELECT COUNT(*)
            FROM Account AS acc
            WHERE acc.username = :username
            """,
    nativeQuery = true)
    Long count(@Param("username") String username);
}

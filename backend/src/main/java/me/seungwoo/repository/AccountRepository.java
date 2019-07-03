package me.seungwoo.repository;

import me.seungwoo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-03
 * Time: 10:08
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "select * from ACCOUNT where username = ?1", nativeQuery = true)
    Account findByName(String name);
}

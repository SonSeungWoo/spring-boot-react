package me.seungwoo;

import me.seungwoo.domain.Account;
import me.seungwoo.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-02
 * Time: 15:36
 */
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    public static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(new Account("seungwoo", passwordEncoder.encode("0000"), "ROLE_ADMIN"));
            List<Account> accountList = accountRepository.findAll();
            System.out.println(accountList);
        };
    }
}

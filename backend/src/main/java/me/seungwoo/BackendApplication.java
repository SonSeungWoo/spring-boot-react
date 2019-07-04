package me.seungwoo;

import me.seungwoo.domain.Account;
import me.seungwoo.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-02
 * Time: 15:36
 */
@SpringBootApplication
@EnableSwagger2
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

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .host("http://localhost:8080")
                .apiInfo(new ApiInfoBuilder()
                        .title("swagger API 문서")
                        //.description("테스트")
                        .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open") //
                        .license("Apache License Version 2.0")
                        .licenseUrl("https://github.com/IBM-Cloud/news-aggregator/blob/master/LICENSE")
                        .version("1.0")
                        .build())
                .select()
                .paths(regex("/*.*"))
                .apis(RequestHandlerSelectors.basePackage("me.seungwoo.controller"))
                .build();
    }
}

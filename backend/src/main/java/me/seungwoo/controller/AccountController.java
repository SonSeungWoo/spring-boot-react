package me.seungwoo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.seungwoo.domain.Account;
import me.seungwoo.dto.AccountDto;
import me.seungwoo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-04
 * Time: 11:01
 */
@RestController
@RequiredArgsConstructor
@Api(value = "Account", description = "사용자관리", tags = {"Account"})
public class AccountController {


    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    @GetMapping("/accounts")
    @ApiOperation(
            value = "사용자 목록 조회",
            responseReference = "List<AccountDto.Account>"
    )
    public List<AccountDto.Account> getAccountList() {
        List<AccountDto.Account> accountList =
                modelMapper.map(accountRepository.findAll(), new TypeToken<List<AccountDto.Account>>() {}.getType());
        return accountList;
    }

    @GetMapping("/account/{id}")
    @ApiOperation(
            value = "사용자 조회",
            responseReference = "AccountDto.Account"
    )
    public AccountDto.Account getAccount(@PathVariable Long id) {
        Account account = accountRepository.findById(id).orElse(new Account());
        return modelMapper.map(account, AccountDto.Account.class);
    }

}

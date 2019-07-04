package me.seungwoo.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.seungwoo.domain.Account;
import me.seungwoo.dto.AccountDto;
import me.seungwoo.jwt.JwtTokenProvider;
import me.seungwoo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-03
 * Time: 14:51
 */
@RestController
@RequiredArgsConstructor
@Api(value = "Login", description = "로그인관리", tags = {"Login"})
public class LoginController {

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @GetMapping("/token")
    public ResponseEntity<String> login(@ModelAttribute Account account, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        account.getUsername(),
                        account.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/singUp/user")
    public ResponseEntity<String> userSingUp(@RequestBody AccountDto.AccountCreate account) {
        account.setRoles(new String[]{"ROLE_USER"});
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(modelMapper.map(account, Account.class));
        return ResponseEntity.ok("USER JOIN SUCCESS");
    }

    @PostMapping("/singUp/admin")
    public ResponseEntity<String> adminSingUp(@RequestBody AccountDto.AccountCreate account) {
        account.setRoles(new String[]{"ROLE_USER", "ROLE_ADMIN"});
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(modelMapper.map(account, Account.class));
        return ResponseEntity.ok("USER JOIN SUCCESS");
    }
}

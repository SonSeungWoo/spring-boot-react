package me.seungwoo.controller;

import lombok.RequiredArgsConstructor;
import me.seungwoo.domain.Account;
import me.seungwoo.jwt.JwtTokenProvider;
import me.seungwoo.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-03
 * Time: 14:51
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

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
    public ResponseEntity<String> userSingUp(@RequestBody Account account){
        account.setRoles(new String[]{"ROLE_USER"});
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return ResponseEntity.ok("USER JOIN SUCCESS");
    }

    @PostMapping("/singUp/admin")
    public ResponseEntity<String> adminSingUp(@RequestBody Account account){
        account.setRoles(new String[]{"ROLE_USER","ROLE_ADMIN"});
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return ResponseEntity.ok("USER JOIN SUCCESS");
    }

    @GetMapping("/users")
    public List<Account> getAccountList() {
        return accountRepository.findAll();
    }
}

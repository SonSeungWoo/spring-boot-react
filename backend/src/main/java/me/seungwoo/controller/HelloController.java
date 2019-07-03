package me.seungwoo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-02
 * Time: 10:18
 */
@RestController
@RequestMapping("/auth")
public class HelloController {

    @GetMapping("/hello")
    public String hello()  {
        return "Hello!!!!!";
    }

}

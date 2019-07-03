package me.seungwoo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-03
 * Time: 10:05
 */
@Data
@Entity
@ToString(exclude = "password")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String[] roles;

    public Account() {

    }

    public Account(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}

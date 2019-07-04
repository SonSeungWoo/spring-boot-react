package me.seungwoo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-07-04
 * Time: 10:45
 */
public class AccountDto {

    @Data
    @ApiModel(value = "AccountDto.Account")
    public static class Account {
        @JsonIgnore
        private Long id;
        @ApiModelProperty(value = "사용자명", example = "seungwoo")
        private String username;
        @ApiModelProperty(value = "사용자 권한", example = "[ROLE_ADMIN]")
        private String[] roles;
    }

    @Data
    @ApiModel(value = "AccountDto.AccountCreate")
    public static class AccountCreate {
        @ApiModelProperty(value = "사용자명", example = "seungwoo")
        private String username;
        @ApiModelProperty(value = "패스워드", example = "password")
        private String password;
        @JsonIgnore
        private String[] roles;
    }
}

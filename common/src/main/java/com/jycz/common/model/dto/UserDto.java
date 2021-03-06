package com.jycz.common.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author ling
 * @data 2020/4/8 12:15
 */
@Data
public class UserDto {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 32, message = "用户名长度为4-32位")
    private String username;
    @Length(min = 2, max = 48, message = "昵称长度为2-48位")
    private String nickname;
    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为4-16位")
    @Pattern(regexp = "^[0-9a-zA-Z_]+$", message = "密码格式不正确")
    private String password;
    @Email
    private String email;

}

package com.car.park.entities.dtos;

import com.car.park.web.support.validation.annotations.PasswordMatch;
import com.car.park.web.support.validation.annotations.UniqueLogin;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * DTO, contains information of user, and splits it on different forms
 * @see ValidationCreate
 * @see ValidationUpdate
 * @see ValidationPasswordUpdate
 */
@UniqueLogin(message = "{validation.user.login.exist}", groups = {UserDto.ValidationCreate.class})
@PasswordMatch(message = "{validation.user.password.old.incorrect}", groups = {UserDto.ValidationPasswordUpdate.class})
public class UserDto {

    public static final String USER_LOGIN_REGEX = "^[a-zA-Z0-9]{1,20}$";
    public static final String USER_PASSWORD_REGEX = "^[a-zA-Z0-9]{1,20}$";

    private Long id;

    @NotBlank(message = "{validation.user.login.empty}", groups = {ValidationCreate.class})
    @Pattern(message = "{validation.user.login.invalid}", regexp = USER_LOGIN_REGEX, groups = {ValidationCreate.class})
    private String login;

    @NotBlank(message = "{validation.user.password.empty}", groups = {ValidationCreate.class, ValidationPasswordUpdate.class})
    @Pattern(message = "{validation.user.password.invalid}", regexp = USER_PASSWORD_REGEX, groups = {ValidationCreate.class, ValidationPasswordUpdate.class})
    private String password;

    @NotBlank(message = "{validation.user.password.new.empty}", groups = {ValidationPasswordUpdate.class})
    @Pattern(message = "{validation.user.password.new.invalid}", regexp = USER_PASSWORD_REGEX, groups = {ValidationPasswordUpdate.class})
    private String newPassword;

    @NotBlank(message = "{validation.user.email.empty}", groups = {ValidationUpdate.class})
    @Email(message = "{validation.user.email.invalid}", groups = {ValidationUpdate.class})
    private String email;

    @NotBlank(message = "{validation.user.phone.empty}", groups = {ValidationUpdate.class})
    @Pattern(message = "{validation.user.phone.invalid}", regexp = "\\d{10}", groups = {ValidationUpdate.class})
    private String phone;

    @Pattern(message = "{validation.user.name.invalid}", regexp = "(^$|^[a-zA-Z\\s]{1,20}$)", groups = {ValidationUpdate.class})
    private String name;

    @NotBlank(message = "{validation.user.age.empty}", groups = {ValidationUpdate.class})
    @Pattern(message = "{validation.user.age.invalid}", regexp = "^[1-9]\\d?$", groups = {ValidationUpdate.class})
    private String age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Interface to group fields for validation for user creation form
     */
    public interface ValidationCreate {}

    /**
     * Interface to group fields for validation for user update form
     */
    public interface ValidationUpdate {}

    /**
     * Interface to group fields for validation for user update password form
     */
    public interface ValidationPasswordUpdate {}
}

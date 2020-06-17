package com.car.park.entities;

import com.car.park.web.support.validation.annotations.UniqueLogin;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.readAll", query = "select u from User u"),
        @NamedQuery(name = "User.readByLogin", query = "select u from User u where u.login = :login")
})
public class User {

    public static final String USER_LOGIN_REGEX = "^[a-zA-Z0-9]{1,20}$";
    public static final String USER_PASSWORD_REGEX = "^[a-zA-Z0-9]{1,20}$";

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_login", unique = true, nullable = false)
    @NotBlank(message = "{validation.user.login.empty}", groups = {ValidationBasic.class})
    @Pattern(message = "{validation.user.login.invalid}", regexp = USER_LOGIN_REGEX, groups = {ValidationBasic.class})
    @UniqueLogin(message = "{validation.user.login.exist}", groups = {ValidationBasic.class})
    private String login;

    @Column(name = "user_password")
    @NotBlank(message = "{validation.user.password.empty}", groups = {ValidationBasic.class})
    @Pattern(message = "{validation.user.password.invalid}", regexp = USER_PASSWORD_REGEX, groups = {ValidationBasic.class})
    private String password;

    @Column(name = "user_email")
    @NotBlank(message = "{validation.user.email.empty}", groups = {ValidationAdditional.class})
    @Email(message = "{validation.user.email.invalid}", groups = {ValidationAdditional.class})
    private String email;

    @Column(name = "user_phone")
    @NotBlank(message = "{validation.user.phone.empty}", groups = {ValidationAdditional.class})
    @Pattern(message = "{validation.user.phone.invalid}", regexp = "\\d{10}", groups = {ValidationAdditional.class})
    private String phone;

    @Column(name = "user_name")
    @Pattern(message = "{validation.user.name.invalid}", regexp = "(^$|^[a-zA-Z\\s]{1,20}$)", groups = {ValidationAdditional.class})
    private String name;

    @Column(name = "user_age")
    @NotNull(message = "{validation.user.age.empty}", groups = {ValidationAdditional.class})
    @Min(message = "{validation.user.age.invalid}", value = 1, groups = {ValidationAdditional.class})
    @Max(message = "{validation.user.age.invalid}", value = 99, groups = {ValidationAdditional.class})
    private Integer age;

    @Column(name = "user_creation_time")
    private LocalDateTime creationTime;

    @Column(name = "user_access_role")
    @Enumerated(EnumType.STRING)
    private UserRole accessRole;

    @OneToOne(mappedBy = "driver")
    private Assignment assignment;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public UserRole getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(UserRole accessRole) {
        this.accessRole = accessRole;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public interface ValidationBasic {
    }

    public interface ValidationAdditional {
    }
}

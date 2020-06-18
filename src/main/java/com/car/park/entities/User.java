package com.car.park.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.readAll", query = "select u from User u"),
        @NamedQuery(name = "User.readByLogin", query = "select u from User u where u.login = :login")
})
public class User {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_login", unique = true, nullable = false)
    private String login;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone")
    private String phone;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_age")
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
}

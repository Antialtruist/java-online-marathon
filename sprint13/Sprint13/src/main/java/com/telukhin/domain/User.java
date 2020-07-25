package com.telukhin.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


import lombok.ToString;

@Entity
@Table(name = "users")
public class User {

    public enum Role {MENTOR, STUDENT}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20,
            message = "Length of last name should be between 2 and 20")
    private String lastName;

    @NotNull
    @Pattern(regexp = "[_a-zA-Z0-9\\-]+@[a-z]+.[a-z]{2,3}",
            message = "Wrong email format")
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Marathon> marathons;

  @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<Progress> progresses;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<Marathon> getMarathons() {
        return marathons;
    }

    public List<Progress> getProgresses() {
        return progresses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setMarathons(List<Marathon> marathons) {
        this.marathons = marathons;
    }

    public void setProgresses(List<Progress> progresses) {
        this.progresses = progresses;
    }


}
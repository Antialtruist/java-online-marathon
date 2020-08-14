package com.softserve.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest {
    
    @NotEmpty
    private String login;
    
    @NotEmpty
    private String password;

    private String firstName;
    private String lastName;

    public UserRequest() {
    }

    public UserRequest(@NotEmpty String login, @NotEmpty String password) {
        this.login = login;
        this.password = password;
    }

    public UserRequest(@NotEmpty String login, @NotEmpty String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

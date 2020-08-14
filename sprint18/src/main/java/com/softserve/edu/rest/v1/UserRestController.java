package com.softserve.edu.rest.v1;

import com.softserve.edu.dto.OperationResponce;
import com.softserve.edu.dto.TokenResponse;
import com.softserve.edu.dto.UserRequest;
import com.softserve.edu.dto.UserResponce;
import com.softserve.edu.security.jwt.JwtProvider;
import com.softserve.edu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public OperationResponce signUp() {
        return new OperationResponce(true);
    }

    @PostMapping("/signup")
    public OperationResponce signUp(@RequestBody UserRequest user) {
        UserRequest userRequest = new UserRequest(user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName());
        return new OperationResponce(userService.saveUser(userRequest));
    }

    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody UserRequest user) {
        UserRequest userRequest = new UserRequest(user.getLogin(), user.getPassword());
        UserResponce userResponce = userService.findByLoginAndPassword(userRequest);
        return new TokenResponse(jwtProvider.generateToken(userResponce.getLogin()));
    }
}

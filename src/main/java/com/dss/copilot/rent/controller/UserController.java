package com.dss.copilot.rent.controller;

import com.dss.copilot.rent.model.request.UserRegisterRequest;
import com.dss.copilot.rent.model.response.ResponseUserRegister;
import com.dss.copilot.rent.model.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController class.
 * add @RestController annotation
 * add method: login with @PostMapping("/login") annotation and @RequestBody annotation for UserLoginRequest object. Return ResponseEntity with ResponseUserLogin object.
 * add method: register with @PostMapping("/register") annotation and @RequestBody annotation for UserRegisterRequest object. Return ResponseEntity with ResponseUserRegister object.
 * inject UserService using constructor injection.
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * register method.
     * @param userRegisterRequest UserRegisterRequest object.
     * @return ResponseEntity with ResponseUserRegister object.
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseUserRegister> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ResponseUserRegister registerResponse = new ResponseUserRegister();
            registerResponse.setStatus("error");
            registerResponse.setMessage("Invalid input");
            return new ResponseEntity<>(registerResponse, HttpStatus.BAD_REQUEST);
        }
        ResponseUserRegister register = userService.register(userRegisterRequest);
        return new ResponseEntity<>(register, register.getStatus().equals("error") ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

}

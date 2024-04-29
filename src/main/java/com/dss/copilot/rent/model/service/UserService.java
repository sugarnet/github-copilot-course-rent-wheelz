package com.dss.copilot.rent.model.service;

/**
 * add methods: login and register.
 */

import com.dss.copilot.rent.model.request.UserRegisterRequest;
import com.dss.copilot.rent.model.response.ResponseUserRegister;

public interface UserService {

    /**
     * register method.
     *
     * @param userRegisterRequest UserRegisterRequest object.
     * @return ResponseUserRegister object.
     */
    ResponseUserRegister register(UserRegisterRequest userRegisterRequest);
}

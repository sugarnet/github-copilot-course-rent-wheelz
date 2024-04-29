package com.dss.copilot.rent.model.service.impl;

/**
 * UserServiceImpl class.
 * add methods: login and register.
 * add @Service annotation.
 * implement UserService interface.
 * inject UserDao using constructor injection.
 * inject RoleDao using constructor injection.
 * inject PasswordEncoder using constructor injection.
 * add Logger object.
 */

import com.dss.copilot.rent.model.entity.Role;
import com.dss.copilot.rent.model.entity.User;
import com.dss.copilot.rent.model.repository.RoleDao;
import com.dss.copilot.rent.model.repository.UserDao;
import com.dss.copilot.rent.model.request.UserRegisterRequest;
import com.dss.copilot.rent.model.response.ResponseUserRegister;
import com.dss.copilot.rent.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * register method.
     * @param userRegisterRequest UserRegisterRequest object.
     * @return ResponseUserRegister object.
     */
    @Override
    public ResponseUserRegister register(UserRegisterRequest userRegisterRequest) {
        // create User object
        // get fields from userRegisterRequest and set values to User object fields using builder pattern
        // save user object to database
        // return ResponseEntity with ResponseUserRegister object
        // surround with try-catch block and catch Exception
        // validate if user is already registered
        // validate if userEmail is already registered

        try {
            Role role = roleDao.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));

            User userNameExists = userDao.findByUserName(userRegisterRequest.getUserName()).orElse(null);

            if (userNameExists != null) {
                ResponseUserRegister response = ResponseUserRegister.builder().build();
                response.setMessage("User Name already registered");
                response.setStatus("error");

                return response;
            }

            User userEmailExists = userDao.findByUserEmail(userRegisterRequest.getUserEmail()).orElse(null);

            if (userEmailExists != null) {
                ResponseUserRegister response = ResponseUserRegister.builder().build();
                response.setMessage("User Email already registered");
                response.setStatus("error");

                return response;
            }

            User user = User.builder()
                    .userName(userRegisterRequest.getUserName())
                    .userPassword(passwordEncoder.encode(userRegisterRequest.getUserPassword()))
                    .userEmail(userRegisterRequest.getUserEmail())
                    .proofId(userRegisterRequest.getProofId())
                    .roles(List.of(role))
                    .build();

            userDao.save(user);

            ResponseUserRegister response = ResponseUserRegister.builder().build();
            response.setMessage("User registered successfully");
            response.setStatus("success");

            return response;
        } catch (Exception e) {
            LOGGER.error("Error registering user", e);
            ResponseUserRegister response = ResponseUserRegister.builder().build();
            response.setMessage("Error registering user");
            response.setStatus("error");

            return response;
        }
    }
}

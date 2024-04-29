package com.dss.copilot.rent.model.repository;

/**
 * CarDao interface extends JpaRepository with User entity and String as generic type.
 * add findByUserEmail method with String userEmail as parameter and return Optional of User.
 * add findByUserName method with String userName as parameter and return Optional of User.
 */
import com.dss.copilot.rent.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {
    /**
     * findByUserName method.
     * @param userEmail String object.
     * @return Optional of User object.
     */
    Optional<User> findByUserEmail(String userEmail);

    /**
     * findByUserName method.
     * @param userName String object.
     * @return Optional of User object.
     */
    Optional<User> findByUserName(String userName);
}
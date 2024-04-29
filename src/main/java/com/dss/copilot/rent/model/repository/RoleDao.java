package com.dss.copilot.rent.model.repository;

/**
 * RoleDao interface.
 * extends JpaRepository with Role entity and Long as generic type.
 * add findByName method with String name as parameter and return Optional of Role.
 */
import com.dss.copilot.rent.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Long> {
    /**
     * findByName method.
     * @param name String object.
     * @return Optional of Role object.
     */
    Optional<Role> findByName(String name);
}

package com.dss.copilot.rent.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * User entity with attributes: userName, userEmail, userPassword, proofId
 * add @Entity annotation
 * add @Builder annotation
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 *
 * add @Id annotation
 * add @GeneratedValue(strategy = GenerationType.IDENTITY) annotation
 * add field roles with @ManyToMany annotation relationship with Role entity and @JoinTable annotation with name, joinColumns, inverseJoinColumns attributes and uniqueConstraints attribute and add @JsonIgnoreProperties annotation with handler, hibernateLazyInitializer attributes
 *
 *
 *
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String userEmail;
    @Column(unique = true)
    private String userPassword;
    @Column(unique = true)
    private String proofId;

    @JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_name"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_name", "role_id"})
    )
    private List<Role> roles;
}

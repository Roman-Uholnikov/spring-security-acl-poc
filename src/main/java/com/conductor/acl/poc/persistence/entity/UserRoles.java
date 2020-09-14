package com.conductor.acl.poc.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @Column
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column
    private String role;

    public Integer getId() {
        return id;
    }

    public UserRoles setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserRoles setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRoles setRole(String role) {
        this.role = role;
        return this;
    }
}

package com.conductor.acl.poc.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
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

}

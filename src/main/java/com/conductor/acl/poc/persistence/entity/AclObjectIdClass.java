package com.conductor.acl.poc.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "acl_class")
public class AclObjectIdClass {

    @Id
    @Column
    private Integer id;

    @Column(name = "class")
    private String className;
}

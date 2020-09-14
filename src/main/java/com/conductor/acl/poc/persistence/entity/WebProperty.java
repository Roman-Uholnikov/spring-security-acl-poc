package com.conductor.acl.poc.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "web_property")
public class WebProperty {
    @Id
    @Column
    private Integer id;

    @Column
    private String name;

}

package com.conductor.acl.poc.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "editor_change")
public class LiveEditorChange {

    @Id
    @Column
    private Integer id;

    @Column(name = "change_content")
    private String changeContent;

    @ManyToOne(fetch = FetchType.EAGER)
    private WebProperty webProperty;
}
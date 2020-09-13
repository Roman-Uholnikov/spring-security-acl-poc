package com.conductor.acl.poc.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "editor_change")
public class LiveEditorChange {

    @Id
    @Column
    private Integer id;

    @Column(name = "change_content")
    private String changeContent;

    @Column
    private String domain;

    public Integer getId() {
        return id;
    }

    public LiveEditorChange setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public LiveEditorChange setChangeContent(String changeContent) {
        this.changeContent = changeContent;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public LiveEditorChange setDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
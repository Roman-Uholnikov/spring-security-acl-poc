package com.conductor.acl.poc.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "editor_change")
public class LiveEditorChange {

    @Id
    @Column
    private Integer id;

    @Column(name = "change_content")
    private String changeContent;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "web_property_id")
    private WebProperty webProperty;

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

    public WebProperty getWebProperty() {
        return webProperty;
    }

    public LiveEditorChange setWebProperty(WebProperty webProperty) {
        this.webProperty = webProperty;
        return this;
    }
}
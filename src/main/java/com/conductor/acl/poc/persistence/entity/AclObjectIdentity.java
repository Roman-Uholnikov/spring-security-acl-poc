package com.conductor.acl.poc.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acl_object_identity")
public class AclObjectIdentity {

    @Id
    @Column
    private Integer id;

    @JoinColumn(name = "object_id_class")
    @ManyToOne(fetch = FetchType.EAGER)
    private AclObjectIdClass objectIdClass;

    @Column(name = "object_id_identity")
    private Integer objectIdIdentity;

    @JoinColumn(name = "parent_object")
    @ManyToOne(fetch = FetchType.LAZY)
    private AclObjectIdentity parent;

    @Column(name = "owner_sid")
    private Integer ownerSid;

    @Column(name = "entries_inheriting")
    private Boolean entriesInheriting;

}

package com.conductor.acl.poc.persistence.dao;

import com.conductor.acl.poc.persistence.entity.AclObjectIdClass;
import com.conductor.acl.poc.persistence.entity.AclObjectIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AclObjectIdentityRepository extends JpaRepository<AclObjectIdentity, Integer> {

    List<AclObjectIdentity> findAll();

    List<AclObjectIdentity> findByObjectIdClassAndObjectIdIdentity(AclObjectIdClass aclClass, Integer id);

    AclObjectIdentity save(@Param("param") AclObjectIdentity param);
}

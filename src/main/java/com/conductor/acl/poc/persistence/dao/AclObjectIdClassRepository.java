package com.conductor.acl.poc.persistence.dao;

import com.conductor.acl.poc.persistence.entity.AclObjectIdClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AclObjectIdClassRepository extends JpaRepository<AclObjectIdClass, Integer> {

    List<AclObjectIdClass> findAll();

    List<AclObjectIdClass> findByClassName(String name);
}

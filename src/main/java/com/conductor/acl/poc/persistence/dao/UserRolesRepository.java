package com.conductor.acl.poc.persistence.dao;

import com.conductor.acl.poc.persistence.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    List<UserRoles> findAll();

    List<UserRoles> findByUserId(Integer id);

    UserRoles save(@Param("UserRoles") UserRoles UserRoles);
}

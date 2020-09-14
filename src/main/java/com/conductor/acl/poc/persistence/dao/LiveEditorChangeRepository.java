package com.conductor.acl.poc.persistence.dao;

import java.util.List;

import com.conductor.acl.poc.persistence.entity.LiveEditorChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LiveEditorChangeRepository extends JpaRepository<LiveEditorChange, Long>{
    
    @PostFilter("hasPermission(filterObject.webProperty, 'READ')")
    List<LiveEditorChange> findAll();
    
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    LiveEditorChange findById(Integer id);
    
    @SuppressWarnings("unchecked")
    @PreAuthorize("hasPermission(#liveEditorChange.webProperty, 'READ') " +
            "and hasAnyRole('ROLE_PUBLISHER','ROLE_ADMIN')")
    LiveEditorChange save(@Param("liveEditorChange") LiveEditorChange liveEditorChange);
}

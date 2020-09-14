package com.conductor.acl.poc.service;

import com.conductor.acl.poc.persistence.dao.LiveEditorChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LiveEditorService {

    @Autowired
    LiveEditorChangeRepository changeRepository;


}

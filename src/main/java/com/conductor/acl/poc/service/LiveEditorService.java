package com.conductor.acl.poc.service;

import com.conductor.acl.poc.persistence.dao.AclObjectIdClassRepository;
import com.conductor.acl.poc.persistence.dao.AclObjectIdentityRepository;
import com.conductor.acl.poc.persistence.dao.LiveEditorChangeRepository;
import com.conductor.acl.poc.persistence.entity.AclObjectIdClass;
import com.conductor.acl.poc.persistence.entity.AclObjectIdentity;
import com.conductor.acl.poc.persistence.entity.LiveEditorChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LiveEditorService {

    @Autowired
    LiveEditorChangeRepository changeRepository;
    @Autowired
    AclObjectIdentityRepository objectIdentityRepository;
    @Autowired
    AclObjectIdClassRepository objectIdClassRepository;

    public AclObjectIdentity createAclIdentityRecord(LiveEditorChange change){
        List<AclObjectIdClass> byClassName = objectIdClassRepository.findByClassName(change.getClass().getCanonicalName());
        List<AclObjectIdClass> parentClass = objectIdClassRepository.findByClassName(change.getWebProperty().getClass().getCanonicalName());
        List<AclObjectIdentity> parentIdentity = objectIdentityRepository.findByObjectIdClassAndObjectIdIdentity(parentClass.get(0), change.getWebProperty().getId());
        //todo proper id generation should be used
        return objectIdentityRepository.save(new AclObjectIdentity(change.getId()+100, byClassName.get(0), change.getId(),
                parentIdentity.get(0), 1, true));
    }

    public LiveEditorChange createChange(LiveEditorChange change){
        LiveEditorChange saved = changeRepository.save(change);
        createAclIdentityRecord(change);
        return saved;
    }

    public List<LiveEditorChange> findAll(){
        return changeRepository.findAll();
    }


}

package com.conductor.acl.poc.service;

import com.conductor.acl.poc.persistence.dao.LiveEditorChangeRepository;
import com.conductor.acl.poc.persistence.entity.LiveEditorChange;
import com.conductor.acl.poc.persistence.entity.WebProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LiveEditorService {
    @Autowired
    MutableAclService aclService;
    @Autowired
    LiveEditorChangeRepository changeRepository;


    public LiveEditorChange createChange(LiveEditorChange change){
        LiveEditorChange saved = changeRepository.save(change);
        updateAcl(change);
        return saved;
    }


    /**
     * Update ACL Identity for the object. Permissions are inherited from web_property
     * @param change
     */
    private MutableAcl updateAcl(LiveEditorChange change) {
        final ObjectIdentity oi = new ObjectIdentityImpl(change.getClass(), change.getId());

        MutableAcl acl = null;
        try {
            acl = (MutableAcl) aclService.readAclById(oi);
        } catch (final NotFoundException nfe) {
            acl = aclService.createAcl(oi);
        }
        acl.setEntriesInheriting(true);
        WebProperty parentAclObject = change.getWebProperty();
        acl.setParent(aclService.readAclById(new ObjectIdentityImpl(parentAclObject.getClass(), parentAclObject.getId())));
        aclService.updateAcl(acl);
        return acl;
    }


    public List<LiveEditorChange> findAll(){
        return changeRepository.findAll();
    }


}

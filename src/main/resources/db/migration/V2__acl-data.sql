# see details https://docs.spring.io/spring-security/site/docs/4.0.x/reference/html/domain-acls.html
# The first table is ACL_CLASS, which store class name of the domain object, columns include:

# ID
# CLASS: the class name of secured domain objects, for example: com.baeldung.acl.persistence.entity.NoticeMessage
# Secondly, we need the ACL_SID table which allows us to universally identify any principle or authority in the system. The table needs:
#
# ID
# SID: which is the username or role name. SID stands for Security Identity
# PRINCIPAL: 0 or 1, to indicate that the corresponding SID is a principal (user, such as mary, mike, jack…) or an authority (role, such as ROLE_ADMIN, ROLE_USER, ROLE_EDITOR…)
# Next table is ACL_OBJECT_IDENTITY, which stores information for each unique domain object:
#
# ID
# OBJECT_ID_CLASS: define the domain object class, links to ACL_CLASS table
# OBJECT_ID_IDENTITY: domain objects can be stored in many tables depending on the class. Hence, this field store the target object primary key
# PARENT_OBJECT: specify parent of this Object Identity within this table
# OWNER_SID: ID of the object owner, links to ACL_SID table
# ENTRIES_INHERITTING: whether ACL Entries of this object inherits from the parent object (ACL Entries are defined in ACL_ENTRY table)
# Finally, the ACL_ENTRY store individual permission assigns to each SID on an Object Identity:
#
# ID
# ACL_OBJECT_IDENTITY: specify the object identity, links to ACL_OBJECT_IDENTITY table
# ACE_ORDER: the order of current entry in the ACL entries list of corresponding Object Identity
# SID: the target SID which the permission is granted to or denied from, links to ACL_SID table
# MASK: the integer bit mask that represents the actual permission being granted or denied
# GRANTING: value 1 means granting, value 0 means denying
# AUDIT_SUCCESS and AUDIT_FAILURE: for auditing purpose

INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'manager'),
(2, 1, 'hr'),
(3, 0, 'ROLE_EDITOR'),
(3, 0, 'ROLE_ADMIN'),
(4, 1, 'roman');

INSERT INTO acl_class (id, class) VALUES
(1, 'com.conductor.acl.poc.persistence.entity.NoticeMessage');

INSERT INTO system_message(id,content) VALUES 
(1,'First Level Message'),
(2,'Second Level Message'),
(3,'Third Level Message');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0),
(3, 1, 3, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 1, 2, 1, 1, 1),
(3, 1, 3, 3, 1, 1, 1, 1),
(4, 2, 1, 2, 1, 1, 1, 1),
(5, 2, 2, 3, 1, 1, 1, 1),
(6, 3, 1, 3, 1, 1, 1, 1),
(7, 3, 2, 3, 2, 1, 1, 1);
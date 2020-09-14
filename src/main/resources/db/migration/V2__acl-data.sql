




























INSERT INTO web_property(id, name) VALUES
(1,'example.com'),
(2,'apple.com'),
(3,'conductor.com');

INSERT INTO editor_change(id ,web_property_id, change_content) VALUES
(1,3,'change1 for conductor'),
(2,3, 'change2 for conductor'),
(3,2, 'change1 for apple'),
(4,1, 'change1 for example');


INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, '1'),
(2, 1, '2'),
(3, 1, '3'),
(4, 0, 'ROLE_CONDUCTOR'),
(5, 0, 'ROLE_PUBLISHER'),
(6, 0, 'ROLE_ADMIN');

INSERT INTO user_roles (id,user_id,role) VALUES
(1, 1, 'ROLE_PUBLISHER'),
(2, 2, 'ROLE_CONDUCTOR'),
(3, 3, 'ROLE_ADMIN');

INSERT INTO acl_class (id, class) VALUES
(1, 'com.conductor.acl.poc.persistence.entity.LiveEditorChange'),
(2, 'com.conductor.acl.poc.persistence.entity.WebProperty');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 2, 1, NULL, 3, 0), 
(2, 2, 2, NULL, 3, 0), 
(3, 2, 3, NULL, 3, 0), 
(4, 1, 1, 3, 3, 1), 
(5, 1, 2, 3, 3, 1), 
(6, 1, 3, 2, 3, 1), 
(7, 1, 4, 1, 3, 1); 

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 3, 1, 1, 1, 1, 1, 1),
(2, 2, 2, 1, 1, 1, 1, 1),
(3, 3, 3, 2, 1, 1, 1, 1),
(4, 1, 4, 3, 1, 1, 1, 1),
(5, 2, 5, 3, 1, 1, 1, 1),
(6, 3, 6, 3, 1, 1, 1, 1),
(21, 3, 21, 1, 0, 1, 1, 1),
(22, 2, 22, 1, 0, 1, 1, 1),
(23, 3, 23, 2, 0, 1, 1, 1),
(24, 1, 24, 3, 0, 1, 1, 1),
(25, 2, 25, 3, 0, 1, 1, 1),
(26, 3, 26, 3, 0, 1, 1, 1);
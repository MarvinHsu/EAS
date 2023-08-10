INSERT INTO TB_MODULES (ID, NAME, CODE, SEQUENCE, SHOWED) VALUES( '10000003-0000-0000-0000-000000000001', 'Management Module', 'SYSTEM',1, 1);

INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000001', 'Home', 'home', 'home', '10000003-0000-0000-0000-000000000001', 0, 0);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000002', 'Function item Config', 'functionItem', 'functionItem', '10000003-0000-0000-0000-000000000001', 1, 1);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000003', 'Function Config', 'function', 'function', '10000003-0000-0000-0000-000000000001', 2, 1);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000004', 'Group Config', 'group', 'group', '10000003-0000-0000-0000-000000000001', 3, 1);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000005', 'User Config', 'user', 'user', '10000003-0000-0000-0000-000000000001', 4, 1);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000006', 'Module Config', 'module', 'module', '10000003-0000-0000-0000-000000000001', 5, 1);

INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000000', 'BackOffice', 'default', '/default.jsf', '10000002-0000-0000-0000-000000000001');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000001', 'Query function item', 'query', '/kernel/functionItem/readFunctionItem.jsf', '10000002-0000-0000-0000-000000000002');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000002', 'Create function item', 'create', '/kernel/functionItem/createFunctionItem.jsf', '10000002-0000-0000-0000-000000000002');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000003', 'Update function item', 'update', '/kernel/functionItem/updateFunctionItem.jsf', '10000002-0000-0000-0000-000000000002');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000004', 'Delete function item', 'delete', '/kernel/functionItem/updateFunctionItem.jsf', '10000002-0000-0000-0000-000000000002');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000005', 'Query function', 'query', '/kernel/function/readFunction.jsf', '10000002-0000-0000-0000-000000000003');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000006', 'Create function', 'create', '/kernel/function/createFunction.jsf', '10000002-0000-0000-0000-000000000003');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000007', 'Update function', 'update', '/kernel/function/updateFunction.jsf', '10000002-0000-0000-0000-000000000003');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000008', 'Delete function', 'delete', '/kernel/function/updateFunction.jsf', '10000002-0000-0000-0000-000000000003');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000009', 'Query group', 'query', '/kernel/group/readGroup.jsf', '10000002-0000-0000-0000-000000000004');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000010', 'Create group', 'create', '/kernel/group/createGroup.jsf', '10000002-0000-0000-0000-000000000004');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000011', 'Update group', 'update', '/kernel/group/updateGroup.jsf', '10000002-0000-0000-0000-000000000004');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000012', 'Delete group', 'delete', '/kernel/group/updateGroup.jsf', '10000002-0000-0000-0000-000000000004');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000013', 'Query user', 'query', '/kernel/user/readUser.jsf', '10000002-0000-0000-0000-000000000005');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000014', 'Create user', 'create', '/kernel/user/createUser.jsf', '10000002-0000-0000-0000-000000000005');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000015', 'Update user', 'update', '/kernel/user/updateUser.jsf', '10000002-0000-0000-0000-000000000005');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000016', 'Delete user', 'delete', '/kernel/user/updateUser.jsf', '10000002-0000-0000-0000-000000000005');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000017', 'Query module', 'query', '/kernel/module/readModule.jsf', '10000002-0000-0000-0000-000000000006');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000018', 'Create module', 'create', '/kernel/module/createModule.jsf', '10000002-0000-0000-0000-000000000006');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000019', 'Update module', 'update', '/kernel/module/updateModule.jsf', '10000002-0000-0000-0000-000000000006');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000020', 'Delete module', 'delete', '/kernel/module/updateModule.jsf', '10000002-0000-0000-0000-000000000006');

INSERT INTO TB_GROUPS (ID, NAME, CODE) VALUES( '10000005-0000-0000-0000-000000000001', 'Management', 'MANAGE');

INSERT INTO TB_USERS (ID, NAME, ACCOUNT, PASSWORD, ENABLED, EMAIL) VALUES( '10000006-0000-0000-0000-000000000001', 'ADMIN', 'ADMIN',  '63d8d42475634ac109d7d1192fc1510c3c410b49edeae2cf53f7e6c141da63295a1d406d02cb909e88b61e992db9ea9703d0b1780822d5aab58b21a4c4171770',1, 'abc@sample.com');

INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000001', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000001', '10000001-0000-0000-0000-000000000000');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000002', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000002', '10000001-0000-0000-0000-000000000001');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000003', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000002', '10000001-0000-0000-0000-000000000002');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000004', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000002', '10000001-0000-0000-0000-000000000003');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000005', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000002', '10000001-0000-0000-0000-000000000004');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000006', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000003', '10000001-0000-0000-0000-000000000005');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000007', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000003', '10000001-0000-0000-0000-000000000006');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000008', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000003', '10000001-0000-0000-0000-000000000007');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000009', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000003', '10000001-0000-0000-0000-000000000008');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000010', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000004', '10000001-0000-0000-0000-000000000009');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000011', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000004', '10000001-0000-0000-0000-000000000010');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000012', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000004', '10000001-0000-0000-0000-000000000011');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000013', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000004', '10000001-0000-0000-0000-000000000012');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000014', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000005', '10000001-0000-0000-0000-000000000015');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000015', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000005', '10000001-0000-0000-0000-000000000013');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000016', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000005', '10000001-0000-0000-0000-000000000014');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000017', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000005', '10000001-0000-0000-0000-000000000016');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000018', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000006', '10000001-0000-0000-0000-000000000017');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000019', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000006', '10000001-0000-0000-0000-000000000018');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000020', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000006', '10000001-0000-0000-0000-000000000019');
INSERT INTO TB_GROUPS_FUNCTIONS (ID, TB_GROUPS_ID, TB_FUNCTIONS_ID,TB_FUNCTIONS_ITEMS_ID) VALUES('10000007-0000-0000-0000-000000000021', '10000005-0000-0000-0000-000000000001', '10000002-0000-0000-0000-000000000006', '10000001-0000-0000-0000-000000000020');

INSERT INTO TB_USERS_GROUPS (TB_USERS_ID, TB_GROUPS_ID) VALUES( '10000006-0000-0000-0000-000000000001', '10000005-0000-0000-0000-000000000001');

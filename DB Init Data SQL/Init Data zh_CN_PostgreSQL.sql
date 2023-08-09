﻿INSERT INTO TB_MODULES (ID, NAME, CODE, SEQUENCE, SHOWED) VALUES( '10000003-0000-0000-0000-000000000001', '系统管理模块', 'SYSTEM',1, 1);

INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000001', 'Home', 'home', 'home', '10000003-0000-0000-0000-000000000001', 0, 0);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000002', '系统功能项目设定', 'functionItem', 'functionItem', '10000003-0000-0000-0000-000000000001', 1, 1);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000003', '系统功能设定', 'function', 'function', '10000003-0000-0000-0000-000000000001', 2, 1);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000004', '系统群组设定', 'group', 'group', '10000003-0000-0000-0000-000000000001', 3, 1);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000005', '系统账号设定', 'user', 'user', '10000003-0000-0000-0000-000000000001', 4, 1);
INSERT INTO TB_FUNCTIONS (ID, NAME, CODE, OUTCOME, TB_MODULES_ID, SEQUENCE, SHOWED) VALUES( '10000002-0000-0000-0000-000000000006', '系统模块设定', 'module', 'module', '10000003-0000-0000-0000-000000000001', 5, 1);

INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000000', 'BackOffice', 'default', '/default.jsf', '10000002-0000-0000-0000-000000000001');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000001', '查询功能项目', 'query', '/kernel/item/readItem.jsf', '10000002-0000-0000-0000-000000000002');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000002', '新增功能项目', 'create', '/kernel/item/createItem.jsf', '10000002-0000-0000-0000-000000000002');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000003', '修改功能项目', 'update', '/kernel/item/updateItem.jsf', '10000002-0000-0000-0000-000000000002');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000004', '删除功能项目', 'delete', '/kernel/item/updateItem.jsf', '10000002-0000-0000-0000-000000000002');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000005', '查询功能', 'query', '/kernel/function/readFunction.jsf', '10000002-0000-0000-0000-000000000003');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000006', '新增功能', 'create', '/kernel/function/createFunction.jsf', '10000002-0000-0000-0000-000000000003');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000007', '修改功能', 'update', '/kernel/function/updateFunction.jsf', '10000002-0000-0000-0000-000000000003');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000008', '删除功能', 'delete', '/kernel/function/updateFunction.jsf', '10000002-0000-0000-0000-000000000003');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000009', '查询群组', 'query', '/kernel/group/readGroup.jsf', '10000002-0000-0000-0000-000000000004');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000010', '新增群组', 'create', '/kernel/group/createGroup.jsf', '10000002-0000-0000-0000-000000000004');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000011', '修改群组', 'update', '/kernel/group/updateGroup.jsf', '10000002-0000-0000-0000-000000000004');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000012', '删除群组', 'delete', '/kernel/group/updateGroup.jsf', '10000002-0000-0000-0000-000000000004');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000013', '查询使用者', 'query', '/kernel/user/readUser.jsf', '10000002-0000-0000-0000-000000000005');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000014', '新增使用者', 'create', '/kernel/user/createUser.jsf', '10000002-0000-0000-0000-000000000005');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000015', '修改使用者', 'update', '/kernel/user/updateUser.jsf', '10000002-0000-0000-0000-000000000005');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000016', '删除使用者', 'delete', '/kernel/user/updateUser.jsf', '10000002-0000-0000-0000-000000000005');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000017', '查询模块', 'query', '/kernel/module/readModule.jsf', '10000002-0000-0000-0000-000000000006');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000018', '新增模块', 'create', '/kernel/module/createModule.jsf', '10000002-0000-0000-0000-000000000006');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000019', '修改模块', 'update', '/kernel/module/updateModule.jsf', '10000002-0000-0000-0000-000000000006');
INSERT INTO TB_FUNCTIONS_ITEMS (ID, NAME, CODE, URL, TB_FUNCTIONS_ID) VALUES( '10000001-0000-0000-0000-000000000020', '删除模块', 'delete', '/kernel/module/updateModule.jsf', '10000002-0000-0000-0000-000000000006');

INSERT INTO TB_GROUPS (ID, NAME, CODE) VALUES( '10000005-0000-0000-0000-000000000001', '系统管理', 'MANAGE');

INSERT INTO TB_USERS (ID, NAME, ACCOUNT, PASSWORD, ENABLED, EMAIL) VALUES( '10000006-0000-0000-0000-000000000001', 'ADMIN', 'ADMIN',  '63d8d42475634ac109d7d1192fc1510c3c410b49edeae2cf53f7e6c141da63295a1d406d02cb909e88b61e992db9ea9703d0b1780822d5aab58b21a4c4171770', true, 'abc@sample.com');

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

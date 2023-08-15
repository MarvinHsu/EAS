/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     2023/8/15 ¤U¤È 06:00:37                        */
/*==============================================================*/


drop table TB_FUNCTIONS;

drop table TB_FUNCTIONS_ITEMS;

drop table TB_GROUPS;

drop table TB_GROUPS_FUNCTIONS;

drop table TB_MODULES;

drop table TB_USERS;

drop table TB_USERS_GROUPS;

/*==============================================================*/
/* Table: TB_FUNCTIONS                                          */
/*==============================================================*/
create table TB_FUNCTIONS (
   ID                   VARCHAR(36)          not null,
   TB_MODULES_ID        VARCHAR(36)          null,
   NAME                 VARCHAR(40)          not null,
   OUTCOME              VARCHAR(200)         null,
   CREATE_DATE          DATE                 not null default current_date,
   UPDATE_DATE          DATE                 null,
   CODE                 VARCHAR(40)          not null,
   SEQUENCE             INT2                 null,
   SHOWED               INT2                 null default 1,
   constraint TBCL_FUNCTIONS_PK primary key (ID),
   constraint TBCL_FUNCTIONS_UK1 unique (TB_MODULES_ID, CODE)
);

comment on table TB_FUNCTIONS is
'Function table';

comment on column TB_FUNCTIONS.ID is
'ID';

comment on column TB_FUNCTIONS.TB_MODULES_ID is
'Module''s ID';

comment on column TB_FUNCTIONS.NAME is
'Name';

comment on column TB_FUNCTIONS.OUTCOME is
'Outcome';

comment on column TB_FUNCTIONS.CREATE_DATE is
'System create date time';

comment on column TB_FUNCTIONS.UPDATE_DATE is
'System update date time';

comment on column TB_FUNCTIONS.CODE is
'Code';

comment on column TB_FUNCTIONS.SEQUENCE is
'Sort sequence';

comment on column TB_FUNCTIONS.SHOWED is
'Show in menu';

/*==============================================================*/
/* Table: TB_FUNCTIONS_ITEMS                                    */
/*==============================================================*/
create table TB_FUNCTIONS_ITEMS (
   ID                   VARCHAR(36)          not null,
   TB_FUNCTIONS_ID      VARCHAR(36)          not null,
   NAME                 VARCHAR(50)          not null,
   CODE                 VARCHAR(20)          not null,
   URL                  VARCHAR(200)         null,
   constraint TBCL_FUNCTIONS_ITEMS_PK primary key (ID),
   constraint TB_FUNCTIONS_ITEMS_UK1 unique (TB_FUNCTIONS_ID, CODE)
);

comment on table TB_FUNCTIONS_ITEMS is
'Function item table';

comment on column TB_FUNCTIONS_ITEMS.ID is
'ID';

comment on column TB_FUNCTIONS_ITEMS.TB_FUNCTIONS_ID is
'Function''s ID';

comment on column TB_FUNCTIONS_ITEMS.NAME is
'Name';

comment on column TB_FUNCTIONS_ITEMS.CODE is
'Code';

comment on column TB_FUNCTIONS_ITEMS.URL is
'URL';

/*==============================================================*/
/* Table: TB_GROUPS                                             */
/*==============================================================*/
create table TB_GROUPS (
   ID                   VARCHAR(36)          not null,
   NAME                 VARCHAR(40)          not null,
   CODE                 VARCHAR(20)          not null,
   ENABLED              NUMERIC(1,0)         not null default 1,
   CREATE_DATE          DATE                 not null default current_date,
   UPDATE_DATE          DATE                 null,
   constraint TBCL_GROUPS_PK primary key (ID),
   constraint TBCL_GROUPS_UK1 unique (CODE)
);

comment on table TB_GROUPS is
'Group table';

comment on column TB_GROUPS.ID is
'ID';

comment on column TB_GROUPS.NAME is
'Name';

comment on column TB_GROUPS.CODE is
'Code';

comment on column TB_GROUPS.ENABLED is
'Is enable';

comment on column TB_GROUPS.CREATE_DATE is
'System crate data time';

comment on column TB_GROUPS.UPDATE_DATE is
'System update data time';

/*==============================================================*/
/* Table: TB_GROUPS_FUNCTIONS                                   */
/*==============================================================*/
create table TB_GROUPS_FUNCTIONS (
   ID                   VARCHAR(36)          not null,
   TB_GROUPS_ID         VARCHAR(36)          not null,
   TB_FUNCTIONS_ID      VARCHAR(36)          not null,
   TB_FUNCTIONS_ITEMS_ID VARCHAR(36)          not null,
   constraint TBCL_GROUPS_FUNCTIONS_PK primary key (ID),
   constraint TB_GROUPS_FUNCTIONS_UK1 unique (TB_GROUPS_ID, TB_FUNCTIONS_ID, TB_FUNCTIONS_ITEMS_ID)
);

comment on table TB_GROUPS_FUNCTIONS is
'Group, function and functionItem join table';

comment on column TB_GROUPS_FUNCTIONS.ID is
'ID';

comment on column TB_GROUPS_FUNCTIONS.TB_GROUPS_ID is
'Group''s ID';

comment on column TB_GROUPS_FUNCTIONS.TB_FUNCTIONS_ID is
'Function''s ID';

comment on column TB_GROUPS_FUNCTIONS.TB_FUNCTIONS_ITEMS_ID is
'FunctionItem''s ID';

/*==============================================================*/
/* Table: TB_MODULES                                            */
/*==============================================================*/
create table TB_MODULES (
   ID                   VARCHAR(36)          not null,
   NAME                 VARCHAR(50)          not null,
   CODE                 VARCHAR(20)          not null,
   SEQUENCE             INT2                 null,
   SHOWED               INT2                 null default 1,
   constraint TB_MODULES_PK primary key (ID),
   constraint TB_MODULES_UK1 unique (CODE)
);

comment on table TB_MODULES is
'Module table';

comment on column TB_MODULES.ID is
'ID';

comment on column TB_MODULES.NAME is
'Name';

comment on column TB_MODULES.CODE is
'Code';

comment on column TB_MODULES.SEQUENCE is
'Sort sequence';

comment on column TB_MODULES.SHOWED is
'Show in menu';

/*==============================================================*/
/* Table: TB_USERS                                              */
/*==============================================================*/
create table TB_USERS (
   ID                   VARCHAR(36)          not null,
   NAME                 VARCHAR(40)          not null,
   ACCOUNT              VARCHAR(40)          not null,
   PASSWORD             VARCHAR(255)         null,
   ENABLED              BOOL                 not null default true,
   EMAIL                VARCHAR(40)          not null,
   CREATE_DATE          DATE                 not null default current_date,
   UPDATE_DATE          DATE                 null,
   constraint TBCL_USERS_PK primary key (ID),
   constraint TBCL_USERS_UK1 unique (ACCOUNT)
);

comment on table TB_USERS is
'User Table';

comment on column TB_USERS.ID is
'ID';

comment on column TB_USERS.NAME is
'Name';

comment on column TB_USERS.ACCOUNT is
'Account';

comment on column TB_USERS.PASSWORD is
'Password';

comment on column TB_USERS.ENABLED is
'Is enable';

comment on column TB_USERS.EMAIL is
'EMAIL';

comment on column TB_USERS.CREATE_DATE is
'System create data time';

comment on column TB_USERS.UPDATE_DATE is
'System update data time';

/*==============================================================*/
/* Table: TB_USERS_GROUPS                                       */
/*==============================================================*/
create table TB_USERS_GROUPS (
   TB_USERS_ID          VARCHAR(36)          not null,
   TB_GROUPS_ID         VARCHAR(36)          not null,
   constraint PK_TB_USERS_GROUPS primary key (TB_USERS_ID, TB_GROUPS_ID)
);

comment on table TB_USERS_GROUPS is
'User and group join table';

comment on column TB_USERS_GROUPS.TB_USERS_ID is
'User''s ID';

comment on column TB_USERS_GROUPS.TB_GROUPS_ID is
'Group''s ID';

alter table TB_FUNCTIONS
   add constraint FK_TB_0004 foreign key (TB_MODULES_ID)
      references TB_MODULES (ID)
      on delete restrict on update restrict;

alter table TB_FUNCTIONS_ITEMS
   add constraint FK_TB_0002 foreign key (TB_FUNCTIONS_ID)
      references TB_FUNCTIONS (ID)
      on delete restrict on update restrict;

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0001 foreign key (TB_GROUPS_ID)
      references TB_GROUPS (ID)
      on update restrict;

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0003 foreign key (TB_FUNCTIONS_ID)
      references TB_FUNCTIONS (ID)
      on update restrict;

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0007 foreign key (TB_FUNCTIONS_ITEMS_ID)
      references TB_FUNCTIONS_ITEMS (ID)
      on delete restrict on update restrict;

alter table TB_USERS_GROUPS
   add constraint FK_TB_0005 foreign key (TB_GROUPS_ID)
      references TB_GROUPS (ID)
      on delete restrict on update restrict;

alter table TB_USERS_GROUPS
   add constraint FK_TB_0006 foreign key (TB_USERS_ID)
      references TB_USERS (ID)
      on delete restrict on update restrict;


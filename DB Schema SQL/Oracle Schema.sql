/*==============================================================*/
/* DBMS name:      ORACLE Version 12c                           */
/* Created on:     2022/10/13 PM 06:07:43                       */
/*==============================================================*/


alter table TB_FUNCTIONS
   drop constraint FK_TB_0004;

alter table TB_FUNCTIONS_ITEMS
   drop constraint FK_TB_0002;

alter table TB_GROUPS_FUNCTIONS
   drop constraint FK_TB_0001;

alter table TB_GROUPS_FUNCTIONS
   drop constraint FK_TB_0003;

alter table TB_GROUPS_FUNCTIONS
   drop constraint FK_TB_0007;

alter table TB_USERS_GROUPS
   drop constraint FK_TB_0005;

alter table TB_USERS_GROUPS
   drop constraint FK_TB_0006;

drop table TB_FUNCTIONS cascade constraints;

drop table TB_FUNCTIONS_ITEMS cascade constraints;

drop table TB_GROUPS cascade constraints;

drop table TB_GROUPS_FUNCTIONS cascade constraints;

drop table TB_MODULES cascade constraints;

drop table TB_USERS cascade constraints;

drop table TB_USERS_GROUPS cascade constraints;

/*==============================================================*/
/* Table: TB_FUNCTIONS                                          */
/*==============================================================*/
create table TB_FUNCTIONS (
   ID                   VARCHAR2(36)          not null,
   TB_MODULES_ID        VARCHAR2(36),
   NAME                 NVARCHAR2(40)         not null,
   OUTCOME              VARCHAR2(200),
   CREATE_DATE          DATE                 default SYSDATE  not null,
   UPDATE_DATE          DATE,
   CODE                 VARCHAR2(40)          not null,
   SEQUENCE             SMALLINT,
   SHOWED               SMALLINT             default 1,
   constraint TBCL_FUNCTIONS_PK primary key (ID),
   constraint TBCL_FUNCTIONS_UK1 unique (NAME)
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
   ID                   VARCHAR2(36)          not null,
   TB_FUNCTIONS_ID      VARCHAR2(36)          not null,
   NAME                 NVARCHAR2(50)         not null,
   CODE                 VARCHAR2(20)          not null,
   URL                  VARCHAR2(200),
   constraint TBCL_FUNCTIONS_ITEMS_PK primary key (ID)
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
   ID                   VARCHAR2(36)          not null,
   NAME                 NVARCHAR2(40)         not null,
   CODE                 VARCHAR2(20)          not null,
   ENABLED              NUMBER(1,0)          default 1  not null,
   CREATE_DATE          DATE                 default SYSDATE  not null,
   UPDATE_DATE          DATE,
   constraint TBCL_GROUPS_PK primary key (ID),
   constraint TBCL_GROUPS_UK1 unique (NAME)
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
   ID                   VARCHAR2(36)          not null,
   TB_GROUPS_ID         VARCHAR2(36)          not null,
   TB_FUNCTIONS_ID      VARCHAR2(36)          not null,
   TB_FUNCTIONS_ITEMS_ID VARCHAR2(36)          not null,
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
   ID                   VARCHAR2(36)          not null,
   NAME                 VARCHAR2(50)          not null,
   CODE                 VARCHAR2(20)          not null,
   SEQUENCE             SMALLINT,
   SHOWED               SMALLINT             default 1,
   constraint TB_MODULES_PK primary key (ID)
);

comment on table TB_MODULES is
'Modulet table';

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
   ID                   VARCHAR2(36)          not null,
   NAME                 NVARCHAR2(40)         not null,
   ACCOUNT              NVARCHAR2(40)         not null,
   PASSWORD             VARCHAR2(255),
   ENABLED              NUMBER(1,0)          default 1  not null,
   EMAIL                VARCHAR2(40)          not null,
   CREATE_DATE          DATE                 default SYSDATE  not null,
   UPDATE_DATE          DATE,
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
   TB_USERS_ID          VARCHAR2(36)          not null,
   TB_GROUPS_ID         VARCHAR2(36)          not null,
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
      references TB_MODULES (ID);

alter table TB_FUNCTIONS_ITEMS
   add constraint FK_TB_0002 foreign key (TB_FUNCTIONS_ID)
      references TB_FUNCTIONS (ID);

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0001 foreign key (TB_GROUPS_ID)
      references TB_GROUPS (ID)
      not deferrable;

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0003 foreign key (TB_FUNCTIONS_ID)
      references TB_FUNCTIONS (ID)
      not deferrable;

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0007 foreign key (TB_FUNCTIONS_ITEMS_ID)
      references TB_FUNCTIONS_ITEMS (ID);

alter table TB_USERS_GROUPS
   add constraint FK_TB_0005 foreign key (TB_GROUPS_ID)
      references TB_GROUPS (ID);

alter table TB_USERS_GROUPS
   add constraint FK_TB_0006 foreign key (TB_USERS_ID)
      references TB_USERS (ID);


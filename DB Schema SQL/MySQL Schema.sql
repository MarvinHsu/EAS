/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/8/15 ¤U¤È 05:58:45                        */
/*==============================================================*/


alter table TB_FUNCTIONS 
   drop foreign key FK_TB_0004;

alter table TB_FUNCTIONS_ITEMS 
   drop foreign key FK_TB_0002;

alter table TB_GROUPS_FUNCTIONS 
   drop foreign key FK_TB_0001;

alter table TB_GROUPS_FUNCTIONS 
   drop foreign key FK_TB_0003;

alter table TB_GROUPS_FUNCTIONS 
   drop foreign key FK_TB_0007;

alter table TB_USERS_GROUPS 
   drop foreign key FK_TB_0005;

alter table TB_USERS_GROUPS 
   drop foreign key FK_TB_0006;


alter table TB_FUNCTIONS 
   drop foreign key FK_TB_0004;

drop table if exists TB_FUNCTIONS;


alter table TB_FUNCTIONS_ITEMS 
   drop foreign key FK_TB_0002;

drop table if exists TB_FUNCTIONS_ITEMS;

drop table if exists TB_GROUPS;


alter table TB_GROUPS_FUNCTIONS 
   drop foreign key FK_TB_0003;

alter table TB_GROUPS_FUNCTIONS 
   drop foreign key FK_TB_0001;

alter table TB_GROUPS_FUNCTIONS 
   drop foreign key FK_TB_0007;

drop table if exists TB_GROUPS_FUNCTIONS;

drop table if exists TB_MODULES;

drop table if exists TB_USERS;


alter table TB_USERS_GROUPS 
   drop foreign key FK_TB_0005;

alter table TB_USERS_GROUPS 
   drop foreign key FK_TB_0006;

drop table if exists TB_USERS_GROUPS;

/*==============================================================*/
/* Table: TB_FUNCTIONS                                          */
/*==============================================================*/
create table TB_FUNCTIONS
(
   ID                   varchar(36) not null  comment 'ID',
   TB_MODULES_ID        varchar(36)  comment 'Module''s ID',
   NAME                 varchar(40) not null  comment 'Name',
   OUTCOME              varchar(200)  comment 'Outcome',
   CREATE_DATE          datetime not null default CURRENT_TIMESTAMP  comment 'System create date time',
   UPDATE_DATE          datetime  comment 'System update date time',
   CODE                 varchar(40) not null  comment 'Code',
   SEQUENCE             smallint  comment 'Sort sequence',
   SHOWED               smallint default 1  comment 'Show in menu',
   primary key (ID),
   key TBCL_FUNCTIONS_UK1 (CODE)
);

alter table TB_FUNCTIONS comment 'Function table';

/*==============================================================*/
/* Table: TB_FUNCTIONS_ITEMS                                    */
/*==============================================================*/
create table TB_FUNCTIONS_ITEMS
(
   ID                   varchar(36) not null  comment 'ID',
   TB_FUNCTIONS_ID      varchar(36) not null  comment 'Function''s ID',
   NAME                 varchar(50) not null  comment 'Name',
   CODE                 varchar(20) not null  comment 'Code',
   URL                  varchar(200)  comment 'URL',
   primary key (ID),
   key TB_FUNCTIONS_ITEMS_UK1 (TB_FUNCTIONS_ID, CODE)
);

alter table TB_FUNCTIONS_ITEMS comment 'Function item table';

/*==============================================================*/
/* Table: TB_GROUPS                                             */
/*==============================================================*/
create table TB_GROUPS
(
   ID                   varchar(36) not null  comment 'ID',
   NAME                 varchar(40) not null  comment 'Name',
   CODE                 varchar(20) not null  comment 'Code',
   ENABLED              numeric(1,0) not null default 1  comment 'Is enable',
   CREATE_DATE          datetime not null default CURRENT_TIMESTAMP  comment 'System crate data time',
   UPDATE_DATE          datetime  comment 'System update data time',
   primary key (ID),
   key TBCL_GROUPS_UK1 (CODE)
);

alter table TB_GROUPS comment 'Group table';

/*==============================================================*/
/* Table: TB_GROUPS_FUNCTIONS                                   */
/*==============================================================*/
create table TB_GROUPS_FUNCTIONS
(
   ID                   varchar(36) not null  comment 'ID',
   TB_GROUPS_ID         varchar(36) not null  comment 'Group''s ID',
   TB_FUNCTIONS_ID      varchar(36) not null  comment 'Function''s ID',
   TB_FUNCTIONS_ITEMS_ID varchar(36) not null  comment 'FunctionItem''s ID',
   primary key (ID),
   key TB_GROUPS_FUNCTIONS_UK1 (TB_GROUPS_ID, TB_FUNCTIONS_ID, TB_FUNCTIONS_ITEMS_ID)
);

alter table TB_GROUPS_FUNCTIONS comment 'Group, function and functionItem join table';

/*==============================================================*/
/* Table: TB_MODULES                                            */
/*==============================================================*/
create table TB_MODULES
(
   ID                   varchar(36) not null  comment 'ID',
   NAME                 varchar(50) not null  comment 'Name',
   CODE                 varchar(20) not null  comment 'Code',
   SEQUENCE             smallint  comment 'Sort sequence',
   SHOWED               smallint default 1  comment 'Show in menu',
   primary key (ID),
   key TB_MODULES_UK1 (CODE)
);

alter table TB_MODULES comment 'Module table';

/*==============================================================*/
/* Table: TB_USERS                                              */
/*==============================================================*/
create table TB_USERS
(
   ID                   varchar(36) not null  comment 'ID',
   NAME                 varchar(40) not null  comment 'Name',
   ACCOUNT              varchar(40) not null  comment 'Account',
   PASSWORD             varchar(255)  comment 'Password',
   ENABLED              numeric(1,0) not null default 1  comment 'Is enable',
   EMAIL                varchar(40) not null  comment 'EMAIL',
   CREATE_DATE          datetime not null default CURRENT_TIMESTAMP  comment 'System create data time',
   UPDATE_DATE          datetime  comment 'System update data time',
   primary key (ID),
   key TBCL_USERS_UK1 (ACCOUNT)
);

alter table TB_USERS comment 'User Table';

/*==============================================================*/
/* Table: TB_USERS_GROUPS                                       */
/*==============================================================*/
create table TB_USERS_GROUPS
(
   TB_USERS_ID          varchar(36) not null  comment 'User''s ID',
   TB_GROUPS_ID         varchar(36) not null  comment 'Group''s ID',
   primary key (TB_USERS_ID, TB_GROUPS_ID)
);

alter table TB_USERS_GROUPS comment 'User and group join table';

alter table TB_FUNCTIONS add constraint FK_TB_0004 foreign key (TB_MODULES_ID)
      references TB_MODULES (ID) on delete restrict on update restrict;

alter table TB_FUNCTIONS_ITEMS add constraint FK_TB_0002 foreign key (TB_FUNCTIONS_ID)
      references TB_FUNCTIONS (ID) on delete restrict on update restrict;

alter table TB_GROUPS_FUNCTIONS add constraint FK_TB_0001 foreign key (TB_GROUPS_ID)
      references TB_GROUPS (ID) on update restrict;

alter table TB_GROUPS_FUNCTIONS add constraint FK_TB_0003 foreign key (TB_FUNCTIONS_ID)
      references TB_FUNCTIONS (ID) on update restrict;

alter table TB_GROUPS_FUNCTIONS add constraint FK_TB_0007 foreign key (TB_FUNCTIONS_ITEMS_ID)
      references TB_FUNCTIONS_ITEMS (ID) on delete restrict on update restrict;

alter table TB_USERS_GROUPS add constraint FK_TB_0005 foreign key (TB_GROUPS_ID)
      references TB_GROUPS (ID) on delete restrict on update restrict;

alter table TB_USERS_GROUPS add constraint FK_TB_0006 foreign key (TB_USERS_ID)
      references TB_USERS (ID) on delete restrict on update restrict;


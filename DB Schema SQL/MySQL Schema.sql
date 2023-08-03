/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/10/13 下午 05:50:03                       */
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

/*Quartz Table*/
DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

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
   key TBCL_FUNCTIONS_UK1 (NAME)
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
   primary key (ID)
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
   key TBCL_GROUPS_UK1 (NAME)
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
   primary key (ID)
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


CREATE TABLE QRTZ_JOB_DETAILS(
SCHED_NAME VARCHAR(120) NOT NULL,
JOB_NAME VARCHAR(190) NOT NULL,
JOB_GROUP VARCHAR(190) NOT NULL,
DESCRIPTION VARCHAR(250) NULL,
JOB_CLASS_NAME VARCHAR(250) NOT NULL,
IS_DURABLE VARCHAR(1) NOT NULL,
IS_NONCONCURRENT VARCHAR(1) NOT NULL,
IS_UPDATE_DATA VARCHAR(1) NOT NULL,
REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
JOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
JOB_NAME VARCHAR(190) NOT NULL,
JOB_GROUP VARCHAR(190) NOT NULL,
DESCRIPTION VARCHAR(250) NULL,
NEXT_FIRE_TIME BIGINT(13) NULL,
PREV_FIRE_TIME BIGINT(13) NULL,
PRIORITY INTEGER NULL,
TRIGGER_STATE VARCHAR(16) NOT NULL,
TRIGGER_TYPE VARCHAR(8) NOT NULL,
START_TIME BIGINT(13) NOT NULL,
END_TIME BIGINT(13) NULL,
CALENDAR_NAME VARCHAR(190) NULL,
MISFIRE_INSTR SMALLINT(2) NULL,
JOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
REPEAT_COUNT BIGINT(7) NOT NULL,
REPEAT_INTERVAL BIGINT(12) NOT NULL,
TIMES_TRIGGERED BIGINT(10) NOT NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_CRON_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
CRON_EXPRESSION VARCHAR(120) NOT NULL,
TIME_ZONE_ID VARCHAR(80),
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_SIMPROP_TRIGGERS
  (
    SCHED_NAME VARCHAR(120) NOT NULL,
    TRIGGER_NAME VARCHAR(190) NOT NULL,
    TRIGGER_GROUP VARCHAR(190) NOT NULL,
    STR_PROP_1 VARCHAR(512) NULL,
    STR_PROP_2 VARCHAR(512) NULL,
    STR_PROP_3 VARCHAR(512) NULL,
    INT_PROP_1 INT NULL,
    INT_PROP_2 INT NULL,
    LONG_PROP_1 BIGINT NULL,
    LONG_PROP_2 BIGINT NULL,
    DEC_PROP_1 NUMERIC(13,4) NULL,
    DEC_PROP_2 NUMERIC(13,4) NULL,
    BOOL_PROP_1 VARCHAR(1) NULL,
    BOOL_PROP_2 VARCHAR(1) NULL,
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
    REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_BLOB_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
BLOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_CALENDARS (
SCHED_NAME VARCHAR(120) NOT NULL,
CALENDAR_NAME VARCHAR(190) NOT NULL,
CALENDAR BLOB NOT NULL,
PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))
ENGINE=InnoDB;

CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_FIRED_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
ENTRY_ID VARCHAR(95) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
INSTANCE_NAME VARCHAR(190) NOT NULL,
FIRED_TIME BIGINT(13) NOT NULL,
SCHED_TIME BIGINT(13) NOT NULL,
PRIORITY INTEGER NOT NULL,
STATE VARCHAR(16) NOT NULL,
JOB_NAME VARCHAR(190) NULL,
JOB_GROUP VARCHAR(190) NULL,
IS_NONCONCURRENT VARCHAR(1) NULL,
REQUESTS_RECOVERY VARCHAR(1) NULL,
PRIMARY KEY (SCHED_NAME,ENTRY_ID))
ENGINE=InnoDB;

CREATE TABLE QRTZ_SCHEDULER_STATE (
SCHED_NAME VARCHAR(120) NOT NULL,
INSTANCE_NAME VARCHAR(190) NOT NULL,
LAST_CHECKIN_TIME BIGINT(13) NOT NULL,
CHECKIN_INTERVAL BIGINT(13) NOT NULL,
PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))
ENGINE=InnoDB;

CREATE TABLE QRTZ_LOCKS (
SCHED_NAME VARCHAR(120) NOT NULL,
LOCK_NAME VARCHAR(40) NOT NULL,
PRIMARY KEY (SCHED_NAME,LOCK_NAME))
ENGINE=InnoDB;

CREATE INDEX IDX_QRTZ_J_REQ_RECOVERY ON QRTZ_JOB_DETAILS(SCHED_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_J_GRP ON QRTZ_JOB_DETAILS(SCHED_NAME,JOB_GROUP);

CREATE INDEX IDX_QRTZ_T_J ON QRTZ_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_JG ON QRTZ_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_C ON QRTZ_TRIGGERS(SCHED_NAME,CALENDAR_NAME);
CREATE INDEX IDX_QRTZ_T_G ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_T_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_G_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NEXT_FIRE_TIME ON QRTZ_TRIGGERS(SCHED_NAME,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

CREATE INDEX IDX_QRTZ_FT_TRIG_INST_NAME ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME);
CREATE INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_FT_J_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_JG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_T_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_FT_TG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2014                    */
/* Created on:     2016/12/18 PM 08:51:52                       */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_FUNCTIONS') and o.name = 'FK_TB_0004')
alter table TB_FUNCTIONS
   drop constraint FK_TB_0004
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_FUNCTIONS_ITEMS') and o.name = 'FK_TB_0002')
alter table TB_FUNCTIONS_ITEMS
   drop constraint FK_TB_0002
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_GROUPS_FUNCTIONS') and o.name = 'FK_TB_0001')
alter table TB_GROUPS_FUNCTIONS
   drop constraint FK_TB_0001
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_GROUPS_FUNCTIONS') and o.name = 'FK_TB_0003')
alter table TB_GROUPS_FUNCTIONS
   drop constraint FK_TB_0003
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_GROUPS_FUNCTIONS') and o.name = 'FK_TB_0007')
alter table TB_GROUPS_FUNCTIONS
   drop constraint FK_TB_0007
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_USERS_GROUPS') and o.name = 'FK_TB_0005')
alter table TB_USERS_GROUPS
   drop constraint FK_TB_0005
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_USERS_GROUPS') and o.name = 'FK_TB_0006')
alter table TB_USERS_GROUPS
   drop constraint FK_TB_0006
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_FUNCTIONS')
            and   type = 'U')
   drop table TB_FUNCTIONS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_FUNCTIONS_ITEMS')
            and   type = 'U')
   drop table TB_FUNCTIONS_ITEMS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_GROUPS')
            and   type = 'U')
   drop table TB_GROUPS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_GROUPS_FUNCTIONS')
            and   type = 'U')
   drop table TB_GROUPS_FUNCTIONS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_MODULES')
            and   type = 'U')
   drop table TB_MODULES
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_USERS')
            and   type = 'U')
   drop table TB_USERS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_USERS_GROUPS')
            and   type = 'U')
   drop table TB_USERS_GROUPS
go

/*==============================================================*/
/* Table: TB_FUNCTIONS                                          */
/*==============================================================*/
create table TB_FUNCTIONS (
   ID                   varchar(36)          not null,
   TB_MODULES_ID        varchar(36)          null,
   NAME                 nvarchar(40)         not null,
   OUTCOME              varchar(200)         null,
   CREATE_DATE          datetime             not null default getdate(),
   UPDATE_DATE          datetime             null,
   CODE                 varchar(40)          not null,
   SEQUENCE             smallint             null,
   constraint TBCL_FUNCTIONS_PK primary key nonclustered (ID),
   constraint TBCL_FUNCTIONS_UK1 unique (NAME)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_FUNCTIONS') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Function table', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_MODULES_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'TB_MODULES_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Module''s ID',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'TB_MODULES_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'OUTCOME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'OUTCOME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Outcome',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'OUTCOME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_DATE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'CREATE_DATE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'System create date time',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'CREATE_DATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_DATE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'UPDATE_DATE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'System update date time',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'UPDATE_DATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'CODE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Code',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'CODE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SEQUENCE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'SEQUENCE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Sort sequence',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS', 'column', 'SEQUENCE'
go

/*==============================================================*/
/* Table: TB_FUNCTIONS_ITEMS                                    */
/*==============================================================*/
create table TB_FUNCTIONS_ITEMS (
   ID                   varchar(36)          not null,
   TB_FUNCTIONS_ID      varchar(36)          not null,
   NAME                 nvarchar(50)         not null,
   CODE                 varchar(20)          not null,
   URL                  varchar(200)         null,
   constraint TBCL_FUNCTIONS_ITEMS_PK primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_FUNCTIONS_ITEMS') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Function item table', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS_ITEMS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS_ITEMS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_FUNCTIONS_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'TB_FUNCTIONS_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Function''s ID',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'TB_FUNCTIONS_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS_ITEMS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS_ITEMS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'CODE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Code',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'CODE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_FUNCTIONS_ITEMS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'URL')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'URL'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'URL',
   'user', @CurrentUser, 'table', 'TB_FUNCTIONS_ITEMS', 'column', 'URL'
go

/*==============================================================*/
/* Table: TB_GROUPS                                             */
/*==============================================================*/
create table TB_GROUPS (
   ID                   varchar(36)          not null,
   NAME                 nvarchar(40)         not null,
   CODE                 varchar(20)          not null,
   ENABLED              numeric(1,0)         not null default 1,
   CREATE_DATE          datetime             not null default getdate(),
   UPDATE_DATE          datetime             null,
   constraint TBCL_GROUPS_PK primary key nonclustered (ID),
   constraint TBCL_GROUPS_UK1 unique (NAME)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_GROUPS') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_GROUPS' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Group table', 
   'user', @CurrentUser, 'table', 'TB_GROUPS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'CODE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Code',
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'CODE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ENABLED')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'ENABLED'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Is enable',
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'ENABLED'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_DATE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'CREATE_DATE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'System crate data time',
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'CREATE_DATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_DATE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'UPDATE_DATE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'System update data time',
   'user', @CurrentUser, 'table', 'TB_GROUPS', 'column', 'UPDATE_DATE'
go

/*==============================================================*/
/* Table: TB_GROUPS_FUNCTIONS                                   */
/*==============================================================*/
create table TB_GROUPS_FUNCTIONS (
   TB_GROUPS_ID         varchar(36)          not null,
   TB_FUNCTIONS_ID      varchar(36)          not null,
   TB_FUNCTIONS_ITEMS_ID varchar(36)          not null,
   constraint TBCL_GROUPS_FUNCTIONS_PK primary key nonclustered (TB_GROUPS_ID, TB_FUNCTIONS_ID, TB_FUNCTIONS_ITEMS_ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_GROUPS_FUNCTIONS') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_GROUPS_FUNCTIONS' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Group, function and functionItem join table', 
   'user', @CurrentUser, 'table', 'TB_GROUPS_FUNCTIONS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_GROUPS_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS_FUNCTIONS', 'column', 'TB_GROUPS_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Group''s ID',
   'user', @CurrentUser, 'table', 'TB_GROUPS_FUNCTIONS', 'column', 'TB_GROUPS_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_FUNCTIONS_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS_FUNCTIONS', 'column', 'TB_FUNCTIONS_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Function''s ID',
   'user', @CurrentUser, 'table', 'TB_GROUPS_FUNCTIONS', 'column', 'TB_FUNCTIONS_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_GROUPS_FUNCTIONS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_FUNCTIONS_ITEMS_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_GROUPS_FUNCTIONS', 'column', 'TB_FUNCTIONS_ITEMS_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'FunctionItem''s ID',
   'user', @CurrentUser, 'table', 'TB_GROUPS_FUNCTIONS', 'column', 'TB_FUNCTIONS_ITEMS_ID'
go

/*==============================================================*/
/* Table: TB_MODULES                                            */
/*==============================================================*/
create table TB_MODULES (
   ID                   varchar(36)          not null,
   NAME                 varchar(50)          not null,
   CODE                 varchar(20)          not null,
   SEQUENCE             smallint             null,
   constraint PK_TB_MODULES primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_MODULES') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_MODULES' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Modulet table', 
   'user', @CurrentUser, 'table', 'TB_MODULES'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MODULES')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MODULES', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_MODULES', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MODULES')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MODULES', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_MODULES', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MODULES')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MODULES', 'column', 'CODE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Code',
   'user', @CurrentUser, 'table', 'TB_MODULES', 'column', 'CODE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MODULES')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SEQUENCE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MODULES', 'column', 'SEQUENCE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Sort sequence',
   'user', @CurrentUser, 'table', 'TB_MODULES', 'column', 'SEQUENCE'
go

/*==============================================================*/
/* Table: TB_USERS                                              */
/*==============================================================*/
create table TB_USERS (
   ID                   varchar(36)          not null,
   NAME                 nvarchar(40)         not null,
   ACCOUNT              nvarchar(40)         not null,
   PASSWORD             varchar(255)         null,
   ENABLED              numeric(1,0)         not null default 1,
   EMAIL                varchar(40)          not null,
   CREATE_DATE          datetime             not null default getdate(),
   UPDATE_DATE          datetime             null,
   constraint TBCL_USERS_PK primary key nonclustered (ID),
   constraint TBCL_USERS_UK1 unique (ACCOUNT)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_USERS') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_USERS' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'User Table', 
   'user', @CurrentUser, 'table', 'TB_USERS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACCOUNT')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'ACCOUNT'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Account',
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'ACCOUNT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PASSWORD')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'PASSWORD'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Password',
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'PASSWORD'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ENABLED')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'ENABLED'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Is enable',
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'ENABLED'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'EMAIL')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'EMAIL'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'EMAIL',
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'EMAIL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_DATE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'CREATE_DATE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'System create data time',
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'CREATE_DATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_DATE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'UPDATE_DATE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'System update data time',
   'user', @CurrentUser, 'table', 'TB_USERS', 'column', 'UPDATE_DATE'
go

/*==============================================================*/
/* Table: TB_USERS_GROUPS                                       */
/*==============================================================*/
create table TB_USERS_GROUPS (
   TB_USERS_ID          varchar(36)          not null,
   TB_GROUPS_ID         varchar(36)          not null,
   constraint PK_TB_USERS_GROUPS primary key nonclustered (TB_USERS_ID, TB_GROUPS_ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_USERS_GROUPS') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_USERS_GROUPS' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'User and group join table', 
   'user', @CurrentUser, 'table', 'TB_USERS_GROUPS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS_GROUPS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_USERS_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS_GROUPS', 'column', 'TB_USERS_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'User''s ID',
   'user', @CurrentUser, 'table', 'TB_USERS_GROUPS', 'column', 'TB_USERS_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_USERS_GROUPS')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_GROUPS_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_USERS_GROUPS', 'column', 'TB_GROUPS_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Group''s ID',
   'user', @CurrentUser, 'table', 'TB_USERS_GROUPS', 'column', 'TB_GROUPS_ID'
go

alter table TB_FUNCTIONS
   add constraint FK_TB_0004 foreign key (TB_MODULES_ID)
      references TB_MODULES (ID)
go

alter table TB_FUNCTIONS_ITEMS
   add constraint FK_TB_0002 foreign key (TB_FUNCTIONS_ID)
      references TB_FUNCTIONS (ID)
go

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0001 foreign key (TB_GROUPS_ID)
      references TB_GROUPS (ID)
go

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0003 foreign key (TB_FUNCTIONS_ID)
      references TB_FUNCTIONS (ID)
go

alter table TB_GROUPS_FUNCTIONS
   add constraint FK_TB_0007 foreign key (TB_FUNCTIONS_ITEMS_ID)
      references TB_FUNCTIONS_ITEMS (ID)
go

alter table TB_USERS_GROUPS
   add constraint FK_TB_0005 foreign key (TB_GROUPS_ID)
      references TB_GROUPS (ID)
go

alter table TB_USERS_GROUPS
   add constraint FK_TB_0006 foreign key (TB_USERS_ID)
      references TB_USERS (ID)
go


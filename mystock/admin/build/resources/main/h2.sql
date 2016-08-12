/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015-09-19 23:56:59                          */
/*==============================================================*/


/*==============================================================*/
/* Table: Organ                                                 */
/*==============================================================*/
create table Organ
(
   id                   char(36) not null,
   parentId             char(36),
   name                 varchar(60) not null,
   enabled              varchar(3) not null comment '0.停用 1.启用',
   ordinal              int not null,
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id),
   constraint FK_Organ_parentId foreign key (parentId)
      references Organ (id) on delete cascade on update restrict
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   id                   char(36) not null,
   name                 varchar(20) not null,
   username             varchar(20) not null,
   password             varchar(120) not null,
   enabled              varchar(3) not null comment '0.停用 1.启用',
   ordinal              int not null,
   defaultActorId       char(36),
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   id                   char(36) not null,
   name                 varchar(60) not null,
   permissions          varchar(800) not null,
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Actor                                                 */
/*==============================================================*/
create table Actor
(
   id                   char(36) not null,
   organId              char(36) not null,
   userId               char(36) not null,
   roleId               char(36) not null,
   name                 varchar(60) not null,
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id),
   constraint FK_Actor_organId foreign key (organId)
      references Organ (id) on delete cascade on update restrict,
   constraint FK_Actor_userId foreign key (userId)
      references User (id) on delete cascade on update restrict,
   constraint FK_Actor_roleId foreign key (roleId)
      references Role (id) on delete cascade on update restrict
);

/*==============================================================*/
/* Table: BnLog                                                 */
/*==============================================================*/
create table BnLog
(
   id                   char(36) not null,
   creator              varchar(20) not null,
   createDate           datetime not null,
   message              varchar(800) not null,
   entityId             char(36),
   origData             varchar(2000),
   newData              varchar(2000),
   primary key (id)
);

/*==============================================================*/
/* Table: Company                                               */
/*==============================================================*/
create table Company
(
   id                   char(36) not null,
   name                 varchar(60) not null,
   foundDate            datetime not null comment '成立时间',
   enabled              bool not null,
   extendInfo           varchar(800) comment '扩展信息',
   primary key (id)
);

/*==============================================================*/
/* Table: Employee                                              */
/*==============================================================*/
create table Employee
(
   id                   char(36) not null,
   companyId            char(36) not null comment '关联公司ID',
   name                 varchar(20) not null,
   age                  int not null comment '年龄',
   sex                  varchar(3) not null comment '性别',
   interests            varchar(120) comment '兴趣爱好',
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id),
   constraint FK_Employee_companyId foreign key (companyId)
      references Company (id) on delete cascade on update restrict
);

-- 创建系统根机构
insert into Organ (id, parentId, name, enabled, ordinal, creatorId, createDate, modifierId, modifyDate)
values ('ADMINOID-0000-0000-0000-000000000000', null, '系统根机构', '1', 0, 'ADMINUID-0000-0000-0000-000000000000', NOW(), 'ADMINUID-0000-0000-0000-000000000000', NOW());

-- 创建系统管理员用户
insert into User (id, username, password, name, enabled, ordinal, defaultActorId, creatorId, createDate, modifierId, modifyDate)
values ('ADMINUID-0000-0000-0000-000000000000', 'admin', 'j6Xdj208bRekjd8a9acwQJL5IbHiws3ncNkgO6Gh9HY=', '系统管理员', '1', 0, 'ADMINAID-0000-0000-0000-000000000000', 'ADMINUID-0000-0000-0000-000000000000', NOW(), 'ADMINUID-0000-0000-0000-000000000000', NOW());

-- 创建系统管理员角色
insert into Role (id, name, permissions, creatorId, createDate, modifierId, modifyDate)
values('ADMINRID-0000-0000-0000-000000000000', '系统管理员', '111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 'ADMINUID-0000-0000-0000-000000000000', NOW(), 'ADMINUID-0000-0000-0000-000000000000', NOW());

-- 创建系统根机构管理员职务
insert into Actor (id, organId, userId, roleId, name, creatorId, createDate, modifierId, modifyDate)
values ('ADMINAID-0000-0000-0000-000000000000', 'ADMINOID-0000-0000-0000-000000000000', 'ADMINUID-0000-0000-0000-000000000000', 'ADMINRID-0000-0000-0000-000000000000', '系统根机构管理员', 'ADMINUID-0000-0000-0000-000000000000', NOW(), 'ADMINUID-0000-0000-0000-000000000000', NOW());
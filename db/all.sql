/**
 * 业务代码
 */
drop table if EXISTS t_business_type;
CREATE TABLE t_business_type (
	business_id int(10) not null primary key  ,
	business_name varchar(100) not null
);
ALTER TABLE t_business_type ADD UNIQUE (business_id);

insert into t_business_type(business_id,business_name) value (1,'机构');
insert into t_business_type(business_id,business_name) value (2,'菜单');
insert into t_business_type(business_id,business_name) value (3,'角色');
insert into t_business_type(business_id,business_name) value (4,'权限');
insert into t_business_type(business_id,business_name) value (5,'用户');


drop table if EXISTS t_system_organization;
CREATE TABLE t_system_organization (
	id int(10) primary key not null auto_increment,
	name varchar(200) NULL COMMENT '机构名称',
	parent_id int(10) not null,
  parent_ids varchar(1000)  null,
  level_num int(4) DEFAULT 0 null ,
	org_type char(1) NOT NULL COMMENT '机构类型(1:机构 2:部门 3:组)',
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);


/**
 * 用户
 */
drop table if EXISTS t_system_user;
CREATE TABLE t_system_user  (
	id int(10) primary key not null auto_increment ,
	login_id varchar(30) not NULL COMMENT '登录名',
	login_pwd varchar(300) not NULL COMMENT '登录密码',
	name varchar(200) NULL COMMENT '用户名称',
	parent_id int(10) not null,
	org_id int(10) not null COMMENT '机构ID',
    parent_ids varchar(1000)  null,
    level_num int(4)  null COMMENT '用户层级',
	type_code char(1) NOT NULL default '1' ,
	status_code char(1) NOT NULL default '1' COMMENT '用户状态码 (1:可用   2:锁定  3:过期)' ,
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);

CREATE UNIQUE INDEX t_system_user_login_id_idx USING BTREE ON t_system_user (login_id) ;

/**
 * 初使化超级管理员帐号
 */
insert into t_system_user(id,login_id,login_pwd,name,parent_id,
parent_ids,level_num,org_id,type_code,status_code,
create_date,creator,is_del)
values
(1,'root','aea11564be1f672dcade343f7f886980','超级管理员',0,
'/0/1/',1,0,'1','1',
now(),1,0);



/**
 * 菜单
 */
drop table if EXISTS t_system_menu;
CREATE TABLE t_system_menu (
	id int(10) primary key not null auto_increment,
	menu_name varchar(200) NULL COMMENT '菜单名称',
	parent_id int(10) not null,
	menu_url varchar(200)  not null COMMENT '菜单url',
    parent_ids varchar(1000)  null,
	type_code char(1) NOT NULL COMMENT '菜单类型(1:菜单2:权限节点)',
	level_num int(4)  null COMMENT '菜单是第几级',
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	update_date DATETIME NOT NULL,
	updator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);

INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(6, '业务一', 0, '业务一', '/0/6/', '1', 1, '2018-02-08 14:42:10.000', 2, '2018-02-08 14:42:10.000', 2, 0, '业务二');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(7, '业务二', 0, '业务二', '/0/7/', '1', 1, '2018-02-08 14:42:22.000', 2, '2018-02-08 14:42:22.000', 2, 0, '业务二');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(8, '业务三', 0, '#', '/0/8/', '1', 1, '2018-02-08 14:42:35.000', 2, '2018-02-08 14:42:35.000', 2, 0, '业务二');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(9, '财务', 0, '#', '/0/9/', '1', 1, '2018-02-08 14:44:03.000', 2, '2018-02-08 14:44:03.000', 2, 0, '');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(12, '系统设置', 0, '#', '/0/12/', '1', 1, '2018-02-08 14:46:19.000', 2, '2018-02-08 14:46:19.000', 2, 0, '');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(10, '考勤', 9, '/finance/check/index', '/0/9/10/', '1', 2, '2018-02-08 14:45:48.000', 2, '2018-02-08 14:45:48.000', 2, 0, '');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(11, '工资', 9, '/common/admin/system_manager/user/index', '/0/9/11/', '1', 2, '2018-02-08 14:46:10.000', 2, '2018-02-08 14:46:10.000', 2, 0, '');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(13, '机构管理', 12, '/common/admin/system_manager/organization/listView', '/0/12/13/', '1', 2, '2018-02-08 14:47:36.000', 2, '2018-02-08 14:47:36.000', 2, 0, '');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(14, '用户管理', 12, '/common/admin/system_manager/user/listView', '/0/12/14/', '1', 2, '2018-02-08 14:47:47.000', 2, '2018-02-08 14:47:47.000', 2, 0, '');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(15, '菜单管理', 12, '/common/admin/system_manager/menu/listView', '/0/12/15/', '1', 2, '2018-02-08 14:47:56.000', 2, '2018-02-08 14:47:56.000', 2, 0, '');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(16, '角色管理', 12, '/common/admin/system_manager/role/listView', '/0/12/16/', '1', 2, '2018-02-08 14:48:03.000', 2, '2018-02-08 14:48:03.000', 2, 0, '');
INSERT INTO t_system_menu
(id, menu_name, parent_id, menu_url, parent_ids, type_code, level_num, create_date, creator, update_date, updator, is_del, remark)
VALUES(17, '权限管理', 12, '/common/admin/system_manager/permission/listView', '/0/12/17/', '1', 2, '2018-02-08 14:48:29.000', 2, '2018-02-08 14:48:29.000', 2, 0, '');

/**
 * 权限
 */
drop table if EXISTS t_system_permission;
CREATE TABLE t_system_permission (
	id int(10) primary key not null auto_increment ,
	permission_name varchar(300) not NULL COMMENT '权限名称',
	permission_code varchar(300) not NULL COMMENT '权限代码',
	menu_id int(10) not null COMMENT '菜单id' ,
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	update_date DATETIME NOT NULL,
	updator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);



INSERT INTO t_system_permission
(id, permission_name, permission_code, menu_id, create_date, creator, update_date, updator, is_del, remark)
VALUES(11, '增加', 'system.setting.permission.manager.add', 17, '2018-02-08 15:47:25.000', 2, '2018-02-08 15:52:21.000', 2, 0, 'a');
INSERT INTO t_system_permission
(id, permission_name, permission_code, menu_id, create_date, creator, update_date, updator, is_del, remark)
VALUES(12, '查询', 'system.setting.permission.manager.list', 17, '2018-02-08 15:52:45.000', 2, '2018-02-08 15:52:45.000', 2, 0, 'list');
INSERT INTO t_system_permission
(id, permission_name, permission_code, menu_id, create_date, creator, update_date, updator, is_del, remark)
VALUES(13, '删除', 'system.setting.permission.manager.delete', 17, '2018-02-08 15:53:07.000', 2, '2018-02-08 15:53:07.000', 2, 0, 'delete');
INSERT INTO t_system_permission
(id, permission_name, permission_code, menu_id, create_date, creator, update_date, updator, is_del, remark)
VALUES(14, '编辑', 'system.setting.permission.manager.edite', 17, '2018-02-08 15:53:34.000', 2, '2018-02-08 15:53:34.000', 2, 0, 'edite');
INSERT INTO t_system_permission
(id, permission_name, permission_code, menu_id, create_date, creator, update_date, updator, is_del, remark)
VALUES(15, '菜单视图', 'admin:systemmanager:menu:listView', 15, '2018-02-11 14:34:16.000', 1, '2018-02-11 14:34:16.000', 1, 0, '菜单视图');
INSERT INTO t_system_permission
(id, permission_name, permission_code, menu_id, create_date, creator, update_date, updator, is_del, remark)
VALUES(16, '菜单展示', 'admin:systemmanager:menu:jsonList', 15, '2018-02-11 14:35:04.000', 1, '2018-02-11 14:35:04.000', 1, 0, '');



/**
 * 角色
 */
drop table if EXISTS t_system_role;
CREATE TABLE t_system_role (
	id int(10) primary key not null auto_increment ,
	role_name varchar(300) not NULL COMMENT '角色名',
	role_code varchar(300) not NULL COMMENT '角色代码',
	org_id int(10) not null COMMENT '机构ID',
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	update_date DATETIME NOT NULL,
	updator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);


/**
 * 角色权限关联表
 */
drop table if EXISTS t_system_role_permission;
CREATE TABLE t_system_role_permission (
	role_id int(10) not null  ,
	permission_id int(10) not null,
	business_id int(10) not null ,
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	update_date DATETIME NOT NULL,
	updator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注',
	 primary key (role_id,permission_id,business_id)
);



/**
 * 用户角色关联表
 */
drop table if EXISTS t_system_user_role;
CREATE TABLE t_system_user_role (
    user_id int(10)  not null,
	role_id int(10) not null  ,
	business_id int(10) not null ,
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	update_date DATETIME NOT NULL,
	updator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注',
	 primary key (user_id,role_id,business_id)
);
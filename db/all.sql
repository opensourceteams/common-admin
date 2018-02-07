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
CREATE TABLE t_system_user (
	id int(10) primary key not null ,
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
(2,'root','root','超级管理员',0,
'/0/2/',1,0,'1','1',
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
	levle_num int(4)  null COMMENT '菜单是第几级',
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	update_date DATETIME NOT NULL,
	updator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);
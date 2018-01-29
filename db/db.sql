/**
 * 机构
 */
drop table if EXISTS t_system_organization;
CREATE TABLE t_system_organization (
	id int(10) primary key not null auto_increment,
	name varchar(200) NULL COMMENT '机构名称',
	parent_id int(10) not null,
  parent_ids varchar(1000)  null,
	org_type char(1) NOT NULL COMMENT '机构类型(1:机构 2:部门 3:组)',
	create_date DATETIME NOT NULL,
	creator int(10) NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);
/**
初使数据机构
 */
INSERT INTO t_system_organization
(id, name, parent_id, parent_ids, org_type, create_date, creator, is_del, remark)
VALUES(1, '总公司', 0, '/0/1/', '1', '2018-01-28 21:26:14.000', 0, 0, '1');

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
drop index  t_system_user_login_id_idx   on  t_system_user;
CREATE UNIQUE INDEX t_system_user_login_id_idx USING BTREE ON t_system_user (login_id) ;
select * from t_system_user;

/**
 * 初使化超级管理员帐号
 */
insert into t_system_user(id,login_id,login_pwd,name,parent_id,parent_ids,level_num,org_id,type_code,status_code,create_date,creator,is_del)
values
(2,'root','root','超级管理员',0,'/0/2/',1,1,'1','1',now(),1,0);
/**
 * 考勤
 */
drop table if EXISTS t_business_check;
CREATE TABLE t_business_check (
	id int(10) primary key not null auto_increment,
	depart_name varchar(200) NULL COMMENT '分部门',
	employee_name varchar(200) NOT NULL COMMENT '职员姓名',
	check_should  decimal(10,2) not null COMMENT '应出考天数(日)',
	check_actual   decimal(10,2) not null COMMENT '实际出勤天数(日)',
	overtime_meal_days   decimal(10,2) not null COMMENT '餐补天数(日)',
	check_absenteeism_days   decimal(10,2) not null COMMENT '入离职缺勤(日)',
	check_sick_leave_days   decimal(10,2) not null COMMENT '病假(日)',
	check_absence_leave_days   decimal(10,2) not null COMMENT '事假(日)',
	check_funeral_leave_days   decimal(10,2) not null COMMENT '丧假(日)',
	check_year_leave_days   decimal(10,2) not null COMMENT '年假(日)',
	check_marry_leave_days   decimal(10,2) not null COMMENT '婚假(日)',
	create_date DATETIME NOT NULL,
	creator int(10) not null ,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);




drop table if EXISTS t_business_show;
CREATE TABLE t_business_show (
	id int(10) primary key not null auto_increment,
	business_id char(4) NOT NULL COMMENT '监控业务代码',
	business_name varchar(200) NULL COMMENT '监控业务名称',
	create_date DATETIME NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);

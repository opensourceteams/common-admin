

drop table if EXISTS t_business_show;
CREATE TABLE t_business_show (
	id int(10) primary key not null auto_increment,
	business_id char(4) NOT NULL COMMENT '监控业务代码',
	business_name varchar(200) NULL COMMENT '监控业务名称',
	create_date DATETIME NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
);
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

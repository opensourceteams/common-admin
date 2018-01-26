

drop table if EXISTS t_business_show;
CREATE TABLE t_business_show (
	id int(10) primary key not null auto_increment,
	business_id char(4) NOT NULL COMMENT '监控业务代码',
	business_name varchar(200) NULL COMMENT '监控业务名称',
	create_date DATETIME NOT NULL,
	is_del bit(1) NOT NULL default 0 ,
	remark varchar(100) NULL COMMENT '备注'
)
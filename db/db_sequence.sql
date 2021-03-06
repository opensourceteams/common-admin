
drop table if EXISTS tb_sequence;
create table tb_sequence(
			name varchar(100) not null,
			current_value int not null,
			_increment int not null default 1,
			primary key(name)
		);



DELIMITER //
create function _nextval(n varchar(50)) returns integer
begin
declare _cur int;
set _cur=(select current_value from tb_sequence where name= n);
update tb_sequence
 set current_value = _cur + _increment
 where name=n ;
return _cur;
end;
//

select _nextval('t_system_organization');
select _nextval('t_system_user');
select * from tb_sequence;






insert into tb_sequence values('t_system_organization',1,10);
insert into tb_sequence values('t_system_user',2,10);
insert into tb_sequence values('t_system_role',3,10);

/**
 * 独立主键
 */
insert into tb_sequence values('t_system_menu',1,1);

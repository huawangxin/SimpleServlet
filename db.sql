create table t_emp_wangxin(
	id number(4) primary key,
	name varchar2(20),
	salary number(7,2),
	age number(3)
)
create sequence emp_id_seq_wangxin increment by 1 start with 1
drop sequence emp_id_seq_wangxin
insert into t_emp_wangxin values(emp_id_seq_wangxin.nextval,'уехЩ',3000,20)
select * from t_emp_wangxin;
delete t_emp_wangxin where id=8 or id=9
SELECT * FROM t_emp_wangxin where name like '%уе%' order by id asc
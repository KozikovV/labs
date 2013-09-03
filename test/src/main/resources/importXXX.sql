insert into role (id, name) values(1, 'ROLE_USER');
insert into role (id, name) values(2, 'ROLE_ADMIN');

insert into user
	(id)
	values (1);
insert into user
	(id, first_name, last_name, login, email, password, birth_date, role_id)
	values (1, 'fn1', 'ln1', 'log1', 'qwe@asd.fg', 'pas1','2012-07-24', 1 );
insert into user 
	(id, first_name, last_name, login, email, password, birth_date, role_id)	
	values(2, 'fn2', 'ln2', 'log2', 'zxc@vbn.mj', 'pas2','2012-07-22', 2 );
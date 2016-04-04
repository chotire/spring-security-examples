insert into users values ('admin', '1234', 1);
insert into users values ('user', '1234', 1);

insert into authorities values ('admin', 'ROLE_ADMIN');
insert into authorities values ('user', 'ROLE_USER'); 

--insert into account (username, password, role, nickname) values ('chotire', '1234', 'ROLE_ADMIN', '조타이어');
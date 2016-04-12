insert into account (username, password, nickname) values ('admin', '1234', '관리자');
insert into account (username, password, nickname) values ('user', '1234', '사용자');

insert into role (id, remark) values ('ROLE_USER', '일반사용자');
insert into role (id, remark) values ('ROLE_ADMIN', '관리자');

insert into authority (username, role_id) values ('admin', 'ROLE_ADMIN');
insert into authority (username, role_id) values ('admin', 'ROLE_USER');
insert into authority (username, role_id) values ('user', 'ROLE_USER');

insert into menu (url, name, sort_order) values ('/flot', 'Flot', 1);
insert into menu (url, name, sort_order) values ('/morris', 'Morris', 2);
insert into menu (url, name, sort_order) values ('/tables', 'Tables', 3);

insert into permission (menu_url, role_id, create, read, update, delete) values ('/flot', 'ROLE_USER', 1, 1, 1, 1);
insert into permission (menu_url, role_id, create, read, update, delete) values ('/flot', 'ROLE_ADMIN', 1, 1, 1, 1);
insert into permission (menu_url, role_id, create, read, update, delete) values ('/morris', 'ROLE_USER', 1, 1, 1, 1);
insert into permission (menu_url, role_id, create, read, update, delete) values ('/morris', 'ROLE_ADMIN', 1, 1, 1, 1);
insert into permission (menu_url, role_id, create, read, update, delete) values ('/tables', 'ROLE_ADMIN', 1, 1, 1, 1);
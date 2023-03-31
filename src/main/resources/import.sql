insert into cuisine (id, name) values (1, 'Thai');
insert into cuisine (id, name) values (2, 'Indian');

insert into restaurant (name, shipping_fee, cuisine_id) values ('Thai Gourmet', 5.00, 1);
insert into restaurant (name, shipping_fee, cuisine_id) values ('Indian Fusion Cuisine', 7.00, 2);
insert into restaurant (name, shipping_fee, cuisine_id) values ('Best Indian Cuisine', 8.00, 2);

insert into state (name) values ('Phuket');
insert into state (name) values ('Delhi');

insert into city (name, state_id) values ('Phuket Town', 1);
insert into city (name, state_id) values ('Kamala', 1);
insert into city (name, state_id) values ('New Delhi', 2);

insert into role (name, description) values ('C-LEVEL', 'Maximum permission possible');
insert into role (name, description) values ('DEPARTMENT BOSS', 'Medium permission');
insert into role (name, description) values ('EMPLOYEE', 'Minimal permission');


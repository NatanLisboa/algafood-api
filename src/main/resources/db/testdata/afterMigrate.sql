-- Disable foreign key checks
set foreign_key_checks = 0;

-- Delete all from tables
delete from city;
delete from cuisine;
delete from state;
delete from payment_method;
delete from user_group;
delete from user_group_permission;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment_method;
delete from user;
delete from user_user_group;
delete from restaurant_responsible_user;
delete from `order`;
delete from order_item;

-- Enable foreign key checks again
set foreign_key_checks = 1;

-- Set auto_increment from ids to 1 again
alter table city auto_increment = 1;
alter table cuisine auto_increment = 1;
alter table state auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table user_group auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table user auto_increment = 1;
alter table `order` auto_increment = 1;
alter table order_item auto_increment = 1;

-- Insert testing data
insert into cuisine (id, name) values (1, 'Thai');
insert into cuisine (id, name) values (2, 'Indian');

insert into state (name) values ('Phuket');
insert into state (name) values ('Delhi');

insert into city (name, state_id) values ('Phuket Town', 1);
insert into city (name, state_id) values ('Kamala', 1);
insert into city (name, state_id) values ('New Delhi', 2);

insert into restaurant (name, shipping_fee, cuisine_id, address_city_id, address_zip_code, address_street_name, address_number, address_district, register_datetime, last_update_datetime, active, open) values ('Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', utc_timestamp, utc_timestamp, true, false);
insert into restaurant (name, shipping_fee, cuisine_id, register_datetime, last_update_datetime, active, open) values ('Indian Fusion Cuisine', 7.00, 2, utc_timestamp, utc_timestamp, true, false);
insert into restaurant (name, shipping_fee, cuisine_id, register_datetime, last_update_datetime, active, open) values ('Best Indian Cuisine', 8.00, 2, utc_timestamp, utc_timestamp, true, false);

insert into product (name, description, price, active, restaurant_id) values ('Tom Yum Goong', 'Spicy shrimp soup', 19.90, false, 1);
insert into product (name, description, price, active, restaurant_id) values ('Tom Kha Gai', 'Chicken in coconut soup', 23.90, true, 1);
insert into product (name, description, price, active, restaurant_id) values ('Spicy curry cod', 'Fresh cod with curry and Ghost Jolokia pepper', 20.00, true, 2);
insert into product (name, description, price, active, restaurant_id) values ('Tikka masala chicken', 'Chicken with a rich seasoned sauce', 25.00, true, 3);

insert into permission (name, description) values ('GET_CUISINES', 'Allow get cuisines');
insert into permission (name, description) values ('EDIT_CUISINES', 'Allow edit cuisines');

insert into user_group (name) values ('Manager');
insert into user_group (name) values ('Seller');
insert into user_group (name) values ('Secretary');
insert into user_group (name) values ('Register officer');

insert into user_group_permission(user_group_id, permission_id) values (1, 1);
insert into user_group_permission(user_group_id, permission_id) values (1, 2);
insert into user_group_permission(user_group_id, permission_id) values (2, 1);
insert into user_group_permission(user_group_id, permission_id) values (3, 2);

insert into payment_method (description) values ('Credit card');
insert into payment_method (description) values ('Debit card');
insert into payment_method (description) values ('Cash');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (3, 1), (3, 2), (3, 3);

insert into user (name, email, password, register_datetime) values
('João da Silva', 'joao.man@algafood.com', '123', utc_timestamp),
('Maria Joaquina', 'maria.sel@algafood.com', '123', utc_timestamp),
('José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
('Sebastião Martins', 'sebastiao.reg@algafood.com', '123', utc_timestamp);

insert into user_user_group(user_id, user_group_id) values (1, 1), (1, 2), (2, 2), (3, 3), (3, 2), (4, 4);

insert into restaurant_responsible_user(restaurant_id, user_id) values (1, 1), (2, 2), (3, 3), (3, 2);

insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, subtotal, shipping_fee, total_value)
values (1, '522be832-c93b-4164-a390-e62114e6177d', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brazil',
        'CREATED', utc_timestamp, 63.70, 10.00, 73.70);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (1, 1, 1, 2, 19.90, 39.80, 'Less spicy, please');

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (2, 1, 2, 1, 23.90, 23.90, null);

insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, subtotal, shipping_fee, total_value)
values (2, 'ce814877-e9b3-4dab-bc65-76e3ff9ed672', 3, 2, 2, 1, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'CREATED', utc_timestamp, 75.00, 0.00, 75.00);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (3, 2, 4, 3, 25.00, 75.00, 'More concentrated sauce');

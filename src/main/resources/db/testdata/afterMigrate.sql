-- Disable foreign key checks
set foreign_key_checks = 0;

-- Lock writing in all tables (to prevent concurrent access from two Docker containers)
lock tables
city write,
cuisine write,
state write,
payment_method write,
user_group write,
user_group_permission write,
permission write,
product write,
restaurant write,
restaurant_payment_method write,
user write,
user_user_group write,
restaurant_responsible_user write,
`order` write,
order_item write,
product_photo write,
oauth2_registered_client write;

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
delete from product_photo;
delete from oauth2_registered_client;

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
insert into cuisine (id, name) values (3, 'Brazilian');

insert into state (name) values ('Phuket');
insert into state (name) values ('Delhi');
insert into state (name) values ('São Paulo');

insert into city (name, state_id) values ('Phuket Town', 1);
insert into city (name, state_id) values ('Kamala', 1);
insert into city (name, state_id) values ('New Delhi', 2);
insert into city (name, state_id) values ('São Paulo', 3);

insert into restaurant (name, shipping_fee, cuisine_id, address_city_id, address_zip_code, address_street_name, address_number, address_district, register_datetime, last_update_datetime, active, open) values ('Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', utc_timestamp, utc_timestamp, true, false);
insert into restaurant (name, shipping_fee, cuisine_id, register_datetime, last_update_datetime, active, open) values ('Indian Fusion Cuisine', 7.00, 2, utc_timestamp, utc_timestamp, true, false);
insert into restaurant (name, shipping_fee, cuisine_id, register_datetime, last_update_datetime, active, open) values ('Best Indian Cuisine', 8.00, 2, utc_timestamp, utc_timestamp, true, false);
insert into restaurant (name, shipping_fee, cuisine_id, register_datetime, last_update_datetime, active, open) values ('Restaurante do José Roguedes', 10.00, 3, utc_timestamp, utc_timestamp, true, false);

insert into product (name, description, price, active, restaurant_id) values ('Tom Yum Goong', 'Spicy shrimp soup', 19.90, false, 1);
insert into product (name, description, price, active, restaurant_id) values ('Tom Kha Gai', 'Chicken in coconut soup', 23.90, true, 1);
insert into product (name, description, price, active, restaurant_id) values ('Spicy curry cod', 'Fresh cod with curry and Ghost Jolokia pepper', 20.00, true, 2);
insert into product (name, description, price, active, restaurant_id) values ('Tikka masala chicken', 'Chicken with a rich seasoned sauce', 25.00, true, 3);
insert into product (name, description, price, active, restaurant_id) values ('Hambúrguer gostosississississississimo', 'Cheeseburger dipped in cheese fondue', 110.00, true, 4);
insert into product (name, description, price, active, restaurant_id) values ('Coxinha de abóbora', 'Fried cone-shaped dough filled with pumpkin', 15.00, true, 4);

insert into permission (id, name, description) values (1, 'EDIT_CUISINES', 'Allow edit cuisines');
insert into permission (id, name, description) values (2, 'EDIT_PAYMENT_METHODS', 'Allow edit payment methods');
insert into permission (id, name, description) values (3, 'EDIT_CITIES', 'Allow edit cities');
insert into permission (id, name, description) values (4, 'EDIT_STATES', 'Allow edit states');
insert into permission (id, name, description) values (5, 'GET_USERS_USER_GROUPS_PERMISSIONS', 'Allow get users, user groups and permissions');
insert into permission (id, name, description) values (6, 'EDIT_USERS_USER_GROUPS_PERMISSIONS', 'Allow edit users, user groups and permissions');
insert into permission (id, name, description) values (7, 'EDIT_RESTAURANTS', 'Allow edit restaurants');
insert into permission (id, name, description) values (8, 'GET_ORDERS', 'Allow get orders');
insert into permission (id, name, description) values (9, 'MANAGE_ORDERS', 'Allow manage orders');
insert into permission (id, name, description) values (10, 'GENERATE_REPORTS', 'Allow generate reports');

insert into user_group (name) values ('Manager');
insert into user_group (name) values ('Seller');
insert into user_group (name) values ('Secretary');
insert into user_group (name) values ('Register officer');

# Add permissions to the manager group
insert into user_group_permission (user_group_id, permission_id)
select 1, id from permission;

# Add permissions to the seller group
insert into user_group_permission (user_group_id, permission_id)
select 2, id from permission where name like 'GET_%';

insert into user_group_permission (user_group_id, permission_id)
select 2, id from permission where name = 'EDIT_RESTAURANTS';

# Add permissions to the secretary group
insert into user_group_permission (user_group_id, permission_id)
select 3, id from permission where name like 'GET_%';

# Add permissions to the register officer group
insert into user_group_permission (user_group_id, permission_id)
select 4, id from permission where name like '%_RESTAURANTS' or name like '%_PRODUCTS';

insert into payment_method (description, last_update_datetime) values ('Credit card', utc_timestamp);
insert into payment_method (description, last_update_datetime) values ('Debit card', utc_timestamp);
insert into payment_method (description, last_update_datetime) values ('Cash', utc_timestamp);

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (2, 2), (3, 1), (3, 2),
(4, 1), (4, 2), (4, 3);

insert into user (name, email, password, register_datetime) values
('João da Silva', 'joao.man@algafood.com', '$2a$12$dBKguAPVAy70zx2hk6BJT.iKGVlPoDi31CnfREidU6Xj49qbjo07a', utc_timestamp),
('Maria Joaquina', 'maria.sel@algafood.com', '$2a$12$dBKguAPVAy70zx2hk6BJT.iKGVlPoDi31CnfREidU6Xj49qbjo07a', utc_timestamp),
('José Souza', 'jose.aux@algafood.com', '$2a$12$dBKguAPVAy70zx2hk6BJT.iKGVlPoDi31CnfREidU6Xj49qbjo07a', utc_timestamp),
('Sebastião Martins', 'sebastiao.reg@algafood.com', '$2a$12$dBKguAPVAy70zx2hk6BJT.iKGVlPoDi31CnfREidU6Xj49qbjo07a', utc_timestamp),
('Leonardo Narita', 'senhor.war14+leonardo@gmail.com', '$2a$12$dBKguAPVAy70zx2hk6BJT.iKGVlPoDi31CnfREidU6Xj49qbjo07a', utc_timestamp),
('Natan Lisboa', 'senhor.war14+natan@gmail.com', '$2a$12$dBKguAPVAy70zx2hk6BJT.iKGVlPoDi31CnfREidU6Xj49qbjo07a', utc_timestamp),
('Naoki', 'naoki.own@gmail.com', '$2a$12$dBKguAPVAy70zx2hk6BJT.iKGVlPoDi31CnfREidU6Xj49qbjo07a', utc_timestamp);

insert into user_user_group(user_id, user_group_id) values (1, 1), (1, 2), (2, 2), (3, 3), (4, 4);

insert into restaurant_responsible_user(restaurant_id, user_id) values (1, 1), (2, 2), (3, 3), (3, 2), (1, 7), (4, 7);

insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, delivery_datetime, subtotal, shipping_fee, total_value)
values (1, '522be832-c93b-4164-a390-e62114e6177d', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brazil',
        'CREATED', str_to_date('2023-07-08 13:00:00', '%Y-%c-%e %T'), str_to_date('2023-07-10 15:00:00', '%Y-%c-%e %T'), 63.70, 10.00, 73.70);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (1, 1, 1, 2, 19.90, 39.80, 'Less spicy, please');

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (2, 1, 2, 1, 23.90, 23.90, null);

insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, delivery_datetime, subtotal, shipping_fee, total_value)
values (2, 'ce814877-e9b3-4dab-bc65-76e3ff9ed672', 3, 2, 2, 1, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'DELIVERED', str_to_date('2023-07-08 09:00:00', '%Y-%c-%e %T'), str_to_date('2023-07-10 09:00:00', '%Y-%c-%e %T'), 75.00, 0.00, 75.00);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (3, 2, 4, 3, 25.00, 75.00, 'More concentrated sauce');


insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, confirmation_datetime, subtotal, shipping_fee, total_value)
values (3, '3b6abc4e-14c3-4508-bdc1-bcf0250e6657', 2, 2, 2, 2, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'CONFIRMED', str_to_date('2023-07-06 09:00:00', '%Y-%c-%e %T'), str_to_date('2023-07-06 09:30:00', '%Y-%c-%e %T'), 100.00, 7.00, 107.00);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (4, 3, 3, 5, 20.00, 100.00, 'More spicy, please');


insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, subtotal, shipping_fee, total_value)
values (4, '0ee1fe06-d7ba-419f-980b-0e0030ea4f84', 3, 4, 2, 2, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'CREATED', str_to_date('2023-07-08 11:00:00', '%Y-%c-%e %T'), 25.00, 8.00, 33.00);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (5, 4, 4, 1, 25.00, 25.00, 'More sauce');

insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, delivery_datetime, subtotal, shipping_fee, total_value)
values (5, '4bfe22ed-a7fa-431a-8b86-918bc7c5faab', 1, 1, 1, 1, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'DELIVERED', str_to_date('2023-07-09 01:00:00', '%Y-%c-%e %T'), str_to_date('2023-07-10 11:30:00', '%Y-%c-%e %T'), 63.70, 10.00, 73.70);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (6, 5, 1, 2, 19.90, 39.80, 'More spicy, please');

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (7, 5, 2, 1, 23.90, 23.90, null);


insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, subtotal, shipping_fee, total_value)
values (6, '0d8f95f5-6d97-41c4-ae24-12a1b00465ce', 1, 5, 1, 1, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'CONFIRMED', utc_timestamp, 63.70, 10.00, 73.70);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (8, 6, 1, 2, 19.90, 39.80, 'More spicy, please');

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (9, 6, 2, 1, 23.90, 23.90, null);


insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, subtotal, shipping_fee, total_value)
values (7, '053fe35a-9eb1-4b81-b8d7-092df804fdfa', 1, 6, 1, 1, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'CREATED', utc_timestamp, 63.70, 10.00, 73.70);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (10, 7, 1, 2, 19.90, 39.80, 'More spicy, please');

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (11, 7, 2, 1, 23.90, 23.90, null);


insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, subtotal, shipping_fee, total_value)
values (8, '5c9c50b1-f078-43e1-951c-22b6833ce91a', 4, 5, 1, 1, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'CREATED', utc_timestamp, 330.00, 10.00, 340.00);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (12, 8, 5, 3, 110.00, 330.00, null);

insert into `order` (id, code, restaurant_id, customer_id, payment_method_id, address_city_id, address_zip_code,
                    address_street_name, address_number, address_complement, address_district,
	                status, creation_datetime, subtotal, shipping_fee, total_value)
values (9, 'dfd70411-ee92-487a-b41a-88d5de02a002', 4, 5, 1, 1, '38400-111', 'St Acre', '300', 'House number 2', 'Centre',
        'CREATED', utc_timestamp, 45.00, 10.00, 55.00);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, note)
values (13, 9, 6, 3, 15.00, 45.00, null);

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('1', 'algafood-backend', '2022-11-29 18:58:12', '$2a$10$trk401po.Wx9JXXMs2xCFeB.eXU7qENFquETcr04a0hDJxGV3ge0.', NULL, 'algafood-backend', 'client_secret_basic', 'client_credentials', '', 'READ', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('2', 'algafood-web', '2022-11-29 18:58:12', '$2a$10$/Lx1cVKanXiCkpYtdA369OZ78x8aHwx51RTxC.4pqEiuZRzQh0e/i', NULL, 'algafood-web', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html,http://127.0.0.1:8080/authorized,http://127.0.0.1:80/swagger-ui/oauth2-redirect.html,http://127.0.0.1:80/authorized,http://algafood-api:8080/swagger-ui/oauth2-redirect.html,http://algafood-api:8080/authorized,https://www.algafoodapi.com.br/authorized,https://www.algafoodapi.com.br/swagger-ui/oauth2-redirect.html', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",900.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('3', 'food-analytics', '2022-11-29 18:58:12', '$2a$10$LQOU54Ta7zV7TxTXSk7DEeZUx/P9PwKGH5CTIOLNGWgIP29QHdq4K', NULL, 'food-analytics', 'client_secret_basic', 'authorization_code', 'http://www.foodanalytics.local:8082', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

unlock tables;
insert into cuisine (id, name) values (1, 'Thai');
insert into cuisine (id, name) values (2, 'Indian');

insert into state (name) values ('Phuket');
insert into state (name) values ('Delhi');

insert into city (name, state_id) values ('Phuket Town', 1);
insert into city (name, state_id) values ('Kamala', 1);
insert into city (name, state_id) values ('New Delhi', 2);

insert into restaurant (name, shipping_fee, cuisine_id, address_city_id, address_zip_code, address_street_name, address_number, address_district, register_datetime, last_update_datetime) values ('Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', utc_timestamp, utc_timestamp);
insert into restaurant (name, shipping_fee, cuisine_id, register_datetime, last_update_datetime) values ('Indian Fusion Cuisine', 7.00, 2, utc_timestamp, utc_timestamp);
insert into restaurant (name, shipping_fee, cuisine_id, register_datetime, last_update_datetime) values ('Best Indian Cuisine', 8.00, 2, utc_timestamp, utc_timestamp);

insert into product (name, description, price, active, restaurant_id) values ('Tom Yum Goong', 'Spicy shrimp soup', 19.90, true, 1)
insert into product (name, description, price, active, restaurant_id) values ('Tom Kha Gai', 'Chicken in coconut soup', 23.90, true, 1)
insert into product (name, description, price, active, restaurant_id) values ('Spicy curry cod', 'Fresh cod with curry and Ghost Jolokia pepper', 20.00, true, 2)
insert into product (name, description, price, active, restaurant_id) values ('Tikka masala chicken', 'Chicken with a rich seasoned sauce', 25.00, true, 3)

insert into role (name, description) values ('C-LEVEL', 'Maximum permission possible');
insert into role (name, description) values ('DEPARTMENT BOSS', 'Medium permission');
insert into role (name, description) values ('EMPLOYEE', 'Minimal permission');

insert into payment_method (description) values ('Credit card');
insert into payment_method (description) values ('Debit card');
insert into payment_method (description) values ('Cash');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (3, 1), (3, 2), (3, 3);

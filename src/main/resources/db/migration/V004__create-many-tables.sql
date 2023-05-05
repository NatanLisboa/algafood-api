create table payment_method (
	id bigint not null auto_increment,
	description varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table permission_group (
	id bigint not null auto_increment,
	name varchar(60) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table permission_group_permission (
	permission_group_id bigint not null,
	permission_id bigint not null,

	primary key (permission_group_id, permission_id)
) engine=InnoDB default charset=utf8;

create table permission (
	id bigint not null auto_increment,
	description varchar(60) not null,
	name varchar(100) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table product (
	id bigint not null auto_increment,
	restaurant_id bigint not null,
	name varchar(80) not null,
	description text not null,
	price decimal(10,2) not null,
	active tinyint(1) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment,
	cuisine_id bigint not null,
	nome varchar(80) not null,
	shipping_fee decimal(10,2) not null,
	update_datetime datetime not null,
	register_datetime datetime not null,

	address_city_id bigint,
	address_zip_code varchar(9),
	address_street_name varchar(100),
	address_number varchar(20),
	address_complement varchar(60),
	address_district varchar(60),

	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_method (
	restaurant_id bigint not null,
	payment_method_id bigint not null,

	primary key (restaurant_id, payment_method_id)
) engine=InnoDB default charset=utf8;

create table user (
	id bigint not null auto_increment,
	name varchar(80) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	register_datetime datetime not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table user_permission_group (
	user_id bigint not null,
	permission_group_id bigint not null,

	primary key (user_id, permission_group_id)
) engine=InnoDB default charset=utf8;


alter table permission_group_permission add constraint fk_permission_group_permission_permission
foreign key (permission_id) references permission(id);

alter table permission_group_permission add constraint fk_permission_group_permission_permission_group
foreign key (permission_group_id) references permission_group (id);

alter table product add constraint fk_product_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint fk_restaurant_cuisine
foreign key (cuisine_id) references cuisine (id);

alter table restaurant add constraint fk_restaurant_city
foreign key (address_city_id) references city (id);

alter table restaurant_payment_method add constraint fk_restaurant_payment_method_payment_method
foreign key (payment_method_id) references payment_method (id);

alter table restaurant_payment_method add constraint fk_restaurant_payment_method_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table user_permission_group add constraint fk_user_permission_group_permission_group
foreign key (permission_group_id) references permission_group (id);

alter table user_permission_group add constraint fk_user_permission_group_user
foreign key (user_id) references user(id);

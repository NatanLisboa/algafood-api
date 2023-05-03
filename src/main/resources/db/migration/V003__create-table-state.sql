-- CREATE TABLE STATE
create table state (
	id bigint not null auto_increment,
    name varchar(80) not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;

-- INSERT STATE NAME FROM CITY(STATE_NAME) TO STATE(NAME)
insert into state (name) 
select distinct state_name from city;

-- ADD COLUMN STATE_ID INTO CITY TABLE
alter table city add column state_id bigint not null;

-- ADD COLUMN STATE_ID INTO CITY TABLE
update city c set c.state_id =
(select s.id from state s where s.name = c.state_name); 

-- ADD COLUMN STATE_ID INTO CITY TABLE
alter table city add constraint fk_city_state
foreign key (state_id) references state(id);

-- DROP COLUMN STATE_NAME FROM CITY
alter table city drop column state_name;

-- RENAME COLUMN CITY_NAME FROM CITY TABLE TO NAME
alter table city change city_name name varchar(80) not null;
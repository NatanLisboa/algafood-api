alter table payment_method add last_update_datetime datetime null;
update payment_method set last_update_datetime = utc_timestamp;
alter table payment_method modify last_update_datetime datetime not null;
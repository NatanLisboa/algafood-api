alter table restaurant add `active` tinyint not null;
update restaurant set `active` = true;
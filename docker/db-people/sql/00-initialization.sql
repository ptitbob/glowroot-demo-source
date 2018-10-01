drop table if exists person;
drop sequence if exists seq_person;
drop index if exists ux_people_login;
create sequence seq_person start with 1;
create table person (
  person_id bigint not null default nextval('seq_person'),
  firstname varchar(255),
  lastname varchar(255),
  login varchar(100),
  zipcode varchar(50)
);
alter table person
  add constraint pk_person primary key (person_id);
create unique index ux_people_login
  on person(login);

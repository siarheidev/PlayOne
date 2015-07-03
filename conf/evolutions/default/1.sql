# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table field (
  id                        integer not null,
  label                     varchar(255),
  type_field                varchar(16),
  required                  boolean,
  active                    boolean,
  constraint ck_field_type_field check (type_field in ('SINGLE_LINE_TEXT','MULTI_LINE_TEXT','RADIO_BUTTON','CHECK_BOX','COMBO_BOX','DATE')),
  constraint pk_field primary key (id))
;

create table option (
  id                        integer not null,
  name                      varchar(255),
  field_id                  integer,
  constraint pk_option primary key (id))
;

create table response (
  id                        integer not null,
  constraint pk_response primary key (id))
;

create table value (
  id                        integer not null,
  value                     varchar(255),
  field_id                  integer,
  response_id               integer,
  constraint pk_value primary key (id))
;

create sequence field_seq;

create sequence option_seq;

create sequence response_seq;

create sequence value_seq;

alter table option add constraint fk_option_field_1 foreign key (field_id) references field (id);
create index ix_option_field_1 on option (field_id);
alter table value add constraint fk_value_field_2 foreign key (field_id) references field (id);
create index ix_value_field_2 on value (field_id);
alter table value add constraint fk_value_response_3 foreign key (response_id) references response (id);
create index ix_value_response_3 on value (response_id);



# --- !Downs

drop table if exists field cascade;

drop table if exists option cascade;

drop table if exists response cascade;

drop table if exists value cascade;

drop sequence if exists field_seq;

drop sequence if exists option_seq;

drop sequence if exists response_seq;

drop sequence if exists value_seq;


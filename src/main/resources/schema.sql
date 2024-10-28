create schema if not exists PUBLIC;
create table if not exists PUBLIC.Taco_Order (
  id identity,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null
);

create table if not exists Public.Taco (
  id identity,
  name varchar(50) not null,
  taco_order bigint not null,
  taco_order_key bigint not null,
  created_at timestamp not null
);

create table if not exists Public.Ingredient_Ref (
  ingredient varchar(4) not null,
  taco bigint not null,
  taco_key bigint
);


create table if not exists Public.Ingredient (
  id varchar(4) not null primary key,
  name varchar(25) not null,
  type varchar(10) not null
);


alter table Public.Taco
    add foreign key (taco_order) references Public.Taco_Order(id);
alter table Public.Ingredient_Ref
    add foreign key (ingredient) references Public.Ingredient(id);


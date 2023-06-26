create table country(
     id bigint not null,
    name varchar (100),
    primary key (id)
);

create table region(
    id bigint not null,
    country_id bigint not null references country(id),
    name varchar (200),
    primary key (id)
);

create table city (
    id bigint not null,
    lat double precision not null,
    lon double precision not null,
    name varchar(100),
    region_id bigint not null references region(id),
    primary key (id)
);




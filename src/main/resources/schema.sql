create table IF NOT EXISTS subscribers (
    id serial primary key,
    email varchar(100) not null
);
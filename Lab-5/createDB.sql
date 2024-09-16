create sequence owners_id_seq as integer;

alter sequence owners_id_seq owner to "CpyBAgy";

create sequence users_id_seq as integer;

alter sequence users_id_seq owner to "CpyBAgy";

create type color as enum ('WHITE', 'BLACK', 'RED', 'BLUE');

alter type color owner to "CpyBAgy";

create table if not exists owners
(
    id integer default nextval('owners_id_seq'::regclass) not null constraint users_pkey primary key,
    name text not null,
    birthday timestamp (6) not null
);

alter table owners owner to "CpyBAgy";

alter sequence owners_id_seq owned by owners.id;

create table if not exists cats
(
    id serial primary key,
    name text not null,
    birthday timestamp(6) not null,
    breed text not null,
    color text not null,
    owner_id integer constraint cats_user_id_fkey references owners
);

alter table cats owner to "CpyBAgy";

create index if not exists cats_user_id_idx on cats (owner_id);

create table if not exists friends
(
    id serial primary key,
    cat1_id integer references cats,
    cat2_id integer references cats
);

alter table friends owner to "CpyBAgy";

create index if not exists friends_cat1_id_idx on friends (cat1_id);

create index if not exists friends_cat2_id_idx on friends (cat2_id);

create table if not exists users
(
    id integer default nextval('users_id_seq'::regclass) not null constraint clients_pkey primary key,
    login text not null,
    password text not null,
    role text not null,
    owner_id integer constraint clients_user_id_fkey references owners
);

alter table users owner to "CpyBAgy";

alter sequence users_id_seq owned by users.id;


create table users(
    id serial primary key,
    login text unique,
    email text,
    password text
);

create table tokens(
    id serial primary key,
    login text,
    token text,
    foreign key (login) references users(login)
);



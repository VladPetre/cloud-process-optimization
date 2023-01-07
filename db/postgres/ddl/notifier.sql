CREATE USER notifier WITH PASSWORD 'swBAetMEoAUYffo3mzKY7';

CREATE DATABASE notifier WITH OWNER notifier;

create table commands
(
    id         bigserial
        primary key,
    cmd        varchar(255),
    created_on timestamp(6),
    executed   boolean,
    reason     varchar(255),
    retries    integer
);

alter table commands
    owner to notifier;

create table notifications
(
    id                bigserial
        primary key,
    created_on        timestamp(6),
    message           varchar(255),
    notification_type varchar(255),
    retries           integer,
    send_to           varchar(255),
    sent              boolean
);

alter table notifications
    owner to notifier;


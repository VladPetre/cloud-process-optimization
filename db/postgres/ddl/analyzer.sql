CREATE USER analyzer WITH PASSWORD 'wqU6hKLJtqwksDJs63ajz';

CREATE DATABASE analyzer WITH OWNER analyzer;

CREATE TABLE alert_config
(
    cid       bigserial not null
        constraint alert_config_pkey primary key,
    ctype     varchar(15),
    threshold int,
    cmds      varchar(100)
);
CREATE USER enhancer WITH PASSWORD 'Qvr4gthjdW2JBEAZda5yv';

CREATE DATABASE enhancer WITH OWNER enhancer;

CREATE TABLE sensors
(
    sid          uuid not null
        constraint sensors_pkey primary key,
    sensor_type  varchar(15),
    measure_type varchar(3),
    measure_unit varchar(3)
);

CREATE TABLE kits
(
    kid          uuid not null
        constraint kits_pkey primary key,
    kit_name     varchar(15),
    kit_location varchar(100)
);

CREATE TABLE commands_configs
(
    id          bigserial
        primary key,
    cmd         varchar(10),
    cmd_rule    varchar(10),
    cmd_type    varchar(15),
    location    varchar(100),
    multiplier  double precision,
    ref_value   double precision,
    sensor_type varchar(15),
    priority    varchar(7)
);

alter table commands_configs
    owner to enhancer;


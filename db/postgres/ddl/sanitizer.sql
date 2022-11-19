CREATE USER sanitizer WITH PASSWORD 'ipRTw4YbT3TneVFUJZEy4';

CREATE DATABASE sanitizer WITH OWNER sanitizer;

CREATE TABLE sensors_data
(
    id           uuid not null
        constraint sensors_data_pkey primary key,
    sid          uuid not null,
    int_value    int,
    double_value double precision,
    updated_on   timestamp
);

CREATE TABLE sensors_metadata
(
    id            uuid not null
        constraint sensors_metadata_pkey primary key,
    sid           uuid not null,
    stype         varchar(15),
    status        int2,
    battery_level double precision,
    updated_on    timestamp
);
CREATE
USER processor WITH PASSWORD 'Ak4X6HYmZmM5pfd7hBtyj';

CREATE
DATABASE processor WITH OWNER processor;

CREATE TABLE crt_sensors_data
(
    sid          uuid not null
        constraint crt_sensors_data_pkey primary key,
    int_value    int,
    double_value double precision,
    updated_on   timestamp
);

CREATE TABLE crt_sensors_metadata
(
    sid           uuid not null
        constraint crt_sensors_metadata_pkey primary key,
    stype         varchar(15),
    status        int2,
    battery_level double precision,
    updated_on    timestamp
);
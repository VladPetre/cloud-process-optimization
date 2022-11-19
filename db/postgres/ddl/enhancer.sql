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
    kid       uuid not null
        constraint kits_pkey primary key,
    kit_name     varchar(15),
    kit_location varchar(100)
);

-- CREATE TABLE kits_sensors
-- (
--     id  bigserial not null
--         constraint alert_config_pkey primary key,
--     kit_id uuid,
--     sensor_id uuid
-- );

-- CREATE UNIQUE INDEX kits_sensors_sid_idx ON kits_sensors (sensor_id);
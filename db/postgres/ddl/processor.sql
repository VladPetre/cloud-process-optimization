CREATE
USER processor WITH PASSWORD 'Ak4X6HYmZmM5pfd7hBtyj';

CREATE
DATABASE processor WITH OWNER processor;

create table crt_sensor_data
(
    sid        uuid not null
        primary key,
    dvalue     double precision,
    updated_on timestamp
);

alter table crt_sensor_data
    owner to processor;

create table crt_sensor_metadata
(
    sid             uuid not null
        primary key,
    battery_level   double precision,
    sensor_type     varchar(15),
    signal_strength double precision,
    status          integer,
    update_on       timestamp
);

alter table crt_sensor_metadata
    owner to processor;

create table sensor_data
(
    id              bigserial primary key,
    sid        uuid not null,
    dvalue     double precision,
    updated_on timestamp
);

alter table sensor_data
    owner to processor;

create table sensor_metadata
(
    id              bigserial primary key,
    sid             uuid not null,
    battery_level   double precision,
    sensor_type     varchar(15),
    signal_strength double precision,
    status          integer,
    update_on       timestamp
);

alter table sensor_metadata
    owner to processor;

CREATE INDEX sensor_data_upd_on_idx ON sensor_data (updated_on);
CREATE INDEX sensor_metadata_upd_on_idx ON sensor_metadata (updated_on);


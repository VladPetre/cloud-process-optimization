CREATE USER authorizer WITH PASSWORD '5jdKChJXCpQucQ4uXhWXD';

CREATE DATABASE authorizer WITH OWNER authorizer;
--
-- CREATE TABLE registered_devices
-- (
--     sid          uuid not null
--         constraint registered_devices_pkey primary key,
--     api_key varchar(36), --will be a jwt
--     is_valid boolean
-- );

DROP DATABASE IF EXISTS weatherapp;
CREATE DATABASE weatherapp;
USE weatherapp;

DROP TABLE IF EXISTS weatherapp.cn_station;
CREATE TABLE weatherapp.cn_station (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  code varchar(50) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  api_key varchar(500) DEFAULT NULL,
  latitude decimal(17,15) DEFAULT NULL,
  longitude decimal(18,15) DEFAULT NULL,
  altitude int(5) DEFAULT NULL,
  last_activity timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  inactivity_threshold_hours int(5) NOT NULL DEFAULT 3,
  inactivity_issue_active boolean NOT NULL DEFAULT false,
  responsible_email varchar(100) DEFAULT NULL,
  disabled boolean NOT NULL DEFAULT false
);

DROP TABLE IF EXISTS weatherapp.cn_station_media;
CREATE TABLE weatherapp.cn_station_media (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name varchar(50) DEFAULT NULL,
  path varchar(500) DEFAULT NULL,
  station_id BIGINT DEFAULT NULL,
  creation_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS weatherapp.cn_station_parameter;
CREATE TABLE weatherapp.cn_station_parameter (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  code varchar(50) DEFAULT NULL,
  value varchar(500) DEFAULT NULL,
  station_id BIGINT DEFAULT NULL
);

DROP TABLE IF EXISTS weatherapp.we_weather;
CREATE TABLE weatherapp.we_weather (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  temperature decimal(3,1) DEFAULT NULL,
  humidity int(3) DEFAULT NULL,
  rain_delta decimal(11,1) DEFAULT NULL,
  rain_rate decimal(11,1) DEFAULT NULL,
  wind decimal(11,1) DEFAULT NULL,
  wind_direction int(3) DEFAULT NULL,
  pressure decimal(11,1) DEFAULT NULL,
  solar_radiation decimal(11,1) DEFAULT NULL,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  station_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS weatherapp.so_soil;
CREATE TABLE weatherapp.so_soil (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  temperature50cm decimal(3,1) DEFAULT NULL,
  temperature100cm decimal(3,1) DEFAULT NULL,
  temperature200cm decimal(3,1) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

ALTER TABLE weatherapp.cn_station
  ADD CONSTRAINT c_cn_station_unique UNIQUE (code);

ALTER TABLE weatherapp.cn_station_media
  ADD CONSTRAINT c_cn_station_media_unique UNIQUE (name, station_id),
  ADD CONSTRAINT c_cn_station_media_station_id FOREIGN KEY (station_id) REFERENCES weatherapp.cn_station (id) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE weatherapp.cn_station_parameter
  ADD CONSTRAINT c_cn_station_parameter_unique UNIQUE (code, station_id),
  ADD CONSTRAINT c_cn_station_parameter_station_id FOREIGN KEY (station_id) REFERENCES weatherapp.cn_station (id) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE weatherapp.we_weather
  ADD CONSTRAINT c_we_weather_unique UNIQUE (timestamp, station_id),
  ADD CONSTRAINT c_we_weather_station_id FOREIGN KEY (station_id) REFERENCES weatherapp.cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE weatherapp.so_soil
  ADD CONSTRAINT c_so_soil_unique UNIQUE (timestamp, station_id),
  ADD CONSTRAINT c_so_soil_station_id FOREIGN KEY (station_id) REFERENCES weatherapp.cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

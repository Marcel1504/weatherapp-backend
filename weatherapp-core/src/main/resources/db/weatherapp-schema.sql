DROP DATABASE IF EXISTS weatherapp;
CREATE DATABASE weatherapp;
USE weatherapp;

DROP TABLE IF EXISTS as_chat;
CREATE TABLE as_chat (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  last_activity timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  total_tokens_consumed BIGINT NOT NULL DEFAULT 0,
  max_tokens_per_request BIGINT NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS as_chat_message;
CREATE TABLE as_chat_message (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  chat_id BIGINT NOT NULL,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  role varchar(50) NOT NULL,
  type varchar(50) NOT NULL,
  content TEXT DEFAULT NULL,
  openai_message TEXT DEFAULT NULL
);

DROP TABLE IF EXISTS cn_station;
CREATE TABLE cn_station (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  code varchar(50) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  api_key varchar(500) DEFAULT NULL,
  type varchar(50) DEFAULT NULL,
  latitude decimal(17,15) DEFAULT NULL,
  longitude decimal(18,15) DEFAULT NULL,
  altitude int(5) DEFAULT NULL,
  last_activity timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  inactivity_threshold_hours int(5) NOT NULL DEFAULT 3,
  inactivity_issue_active boolean NOT NULL DEFAULT false,
  responsible_email varchar(100) DEFAULT NULL,
  disabled boolean NOT NULL DEFAULT false
);

DROP TABLE IF EXISTS cn_station_media;
CREATE TABLE cn_station_media (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name varchar(50) DEFAULT NULL,
  path varchar(500) DEFAULT NULL,
  station_id BIGINT DEFAULT NULL,
  creation_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS cn_station_parameter;
CREATE TABLE cn_station_parameter (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  code varchar(50) DEFAULT NULL,
  value varchar(500) DEFAULT NULL,
  station_id BIGINT DEFAULT NULL
);

DROP TABLE IF EXISTS cn_usage;
CREATE TABLE cn_usage (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  ip_address TEXT NOT NULL,
  module_name varchar(20) NOT NULL,
  last_activity timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  total_requests BIGINT NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS we_weather;
CREATE TABLE we_weather (
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

DROP TABLE IF EXISTS we_weather_hour;
CREATE TABLE we_weather_hour (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  amount int(10) DEFAULT NULL,
  temperature_avg decimal(3,1) DEFAULT NULL,
  temperature_min decimal(3,1) DEFAULT NULL,
  temperature_max decimal(3,1) DEFAULT NULL,
  humidity_avg int(3) DEFAULT NULL,
  humidity_min int(3) DEFAULT NULL,
  humidity_max int(3) DEFAULT NULL,
  rain_total decimal(11,1) DEFAULT NULL,
  rain_rate_max decimal(11,1) DEFAULT NULL,
  wind_max decimal(11,1) DEFAULT NULL,
  wind_direction_cluster varchar(2) DEFAULT NULL,
  pressure_avg decimal(11,1) DEFAULT NULL,
  pressure_min decimal(11,1) DEFAULT NULL,
  pressure_max decimal(11,1) DEFAULT NULL,
  solar_radiation_avg decimal(11,1) DEFAULT NULL,
  solar_radiation_min decimal(11,1) DEFAULT NULL,
  solar_radiation_max decimal(11,1) DEFAULT NULL,
  hour varchar(2) DEFAULT NULL,
  day varchar(10) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS we_weather_day;
CREATE TABLE we_weather_day (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  amount int(10) DEFAULT NULL,
  temperature_avg decimal(3,1) DEFAULT NULL,
  temperature_min decimal(3,1) DEFAULT NULL,
  temperature_max decimal(3,1) DEFAULT NULL,
  humidity_avg int(3) DEFAULT NULL,
  humidity_min int(3) DEFAULT NULL,
  humidity_max int(3) DEFAULT NULL,
  rain_total decimal(11,1) DEFAULT NULL,
  rain_rate_max decimal(11,1) DEFAULT NULL,
  wind_max decimal(11,1) DEFAULT NULL,
  wind_direction_cluster varchar(2) DEFAULT NULL,
  pressure_avg decimal(11,1) DEFAULT NULL,
  pressure_min decimal(11,1) DEFAULT NULL,
  pressure_max decimal(11,1) DEFAULT NULL,
  solar_radiation_avg decimal(11,1) DEFAULT NULL,
  solar_radiation_min decimal(11,1) DEFAULT NULL,
  solar_radiation_max decimal(11,1) DEFAULT NULL,
  day varchar(10) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS we_weather_month;
CREATE TABLE we_weather_month (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  amount int(10) DEFAULT NULL,
  temperature_avg decimal(3,1) DEFAULT NULL,
  temperature_min decimal(3,1) DEFAULT NULL,
  temperature_max decimal(3,1) DEFAULT NULL,
  humidity_avg int(3) DEFAULT NULL,
  humidity_min int(3) DEFAULT NULL,
  humidity_max int(3) DEFAULT NULL,
  rain_total decimal(11,1) DEFAULT NULL,
  rain_rate_max decimal(11,1) DEFAULT NULL,
  wind_max decimal(11,1) DEFAULT NULL,
  wind_direction_cluster varchar(2) DEFAULT NULL,
  pressure_avg decimal(11,1) DEFAULT NULL,
  pressure_min decimal(11,1) DEFAULT NULL,
  pressure_max decimal(11,1) DEFAULT NULL,
  solar_radiation_avg decimal(11,1) DEFAULT NULL,
  solar_radiation_min decimal(11,1) DEFAULT NULL,
  solar_radiation_max decimal(11,1) DEFAULT NULL,
  month varchar(2) DEFAULT NULL,
  year varchar(4) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS we_weather_year;
CREATE TABLE we_weather_year (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  amount int(10) DEFAULT NULL,
  temperature_avg decimal(3,1) DEFAULT NULL,
  temperature_min decimal(3,1) DEFAULT NULL,
  temperature_max decimal(3,1) DEFAULT NULL,
  humidity_avg int(3) DEFAULT NULL,
  humidity_min int(3) DEFAULT NULL,
  humidity_max int(3) DEFAULT NULL,
  rain_total decimal(11,1) DEFAULT NULL,
  rain_rate_max decimal(11,1) DEFAULT NULL,
  wind_max decimal(11,1) DEFAULT NULL,
  wind_direction_cluster varchar(2) DEFAULT NULL,
  pressure_avg decimal(11,1) DEFAULT NULL,
  pressure_min decimal(11,1) DEFAULT NULL,
  pressure_max decimal(11,1) DEFAULT NULL,
  solar_radiation_avg decimal(11,1) DEFAULT NULL,
  solar_radiation_min decimal(11,1) DEFAULT NULL,
  solar_radiation_max decimal(11,1) DEFAULT NULL,
  year varchar(4) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS so_soil;
CREATE TABLE so_soil (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  temperature50cm decimal(3,1) DEFAULT NULL,
  temperature100cm decimal(3,1) DEFAULT NULL,
  temperature200cm decimal(3,1) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS so_soil_day;
CREATE TABLE so_soil_day (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  amount int(10) DEFAULT NULL,
  temperature50cm_avg decimal(3,1) DEFAULT NULL,
  temperature50cm_min decimal(3,1) DEFAULT NULL,
  temperature50cm_max decimal(3,1) DEFAULT NULL,
  temperature100cm_avg decimal(3,1) DEFAULT NULL,
  temperature100cm_min decimal(3,1) DEFAULT NULL,
  temperature100cm_max decimal(3,1) DEFAULT NULL,
  temperature200cm_avg decimal(3,1) DEFAULT NULL,
  temperature200cm_min decimal(3,1) DEFAULT NULL,
  temperature200cm_max decimal(3,1) DEFAULT NULL,
  day varchar(10) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS so_soil_month;
CREATE TABLE so_soil_month (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  amount int(10) DEFAULT NULL,
  temperature50cm_avg decimal(3,1) DEFAULT NULL,
  temperature50cm_min decimal(3,1) DEFAULT NULL,
  temperature50cm_max decimal(3,1) DEFAULT NULL,
  temperature100cm_avg decimal(3,1) DEFAULT NULL,
  temperature100cm_min decimal(3,1) DEFAULT NULL,
  temperature100cm_max decimal(3,1) DEFAULT NULL,
  temperature200cm_avg decimal(3,1) DEFAULT NULL,
  temperature200cm_min decimal(3,1) DEFAULT NULL,
  temperature200cm_max decimal(3,1) DEFAULT NULL,
  month varchar(2) DEFAULT NULL,
  year varchar(4) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS so_soil_year;
CREATE TABLE so_soil_year (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  amount int(10) DEFAULT NULL,
  temperature50cm_avg decimal(3,1) DEFAULT NULL,
  temperature50cm_min decimal(3,1) DEFAULT NULL,
  temperature50cm_max decimal(3,1) DEFAULT NULL,
  temperature100cm_avg decimal(3,1) DEFAULT NULL,
  temperature100cm_min decimal(3,1) DEFAULT NULL,
  temperature100cm_max decimal(3,1) DEFAULT NULL,
  temperature200cm_avg decimal(3,1) DEFAULT NULL,
  temperature200cm_min decimal(3,1) DEFAULT NULL,
  temperature200cm_max decimal(3,1) DEFAULT NULL,
  year varchar(4) DEFAULT NULL,
  station_id BIGINT NOT NULL
);

ALTER TABLE as_chat_message
  ADD CONSTRAINT c_as_chat_message_chat_id FOREIGN KEY (chat_id) REFERENCES as_chat (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE cn_station
  ADD CONSTRAINT c_cn_station_unique UNIQUE (code);

ALTER TABLE cn_station_media
  ADD CONSTRAINT c_cn_station_media_unique UNIQUE (name, station_id),
  ADD CONSTRAINT c_cn_station_media_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE cn_station_parameter
  ADD CONSTRAINT c_cn_station_parameter_unique UNIQUE (code, station_id),
  ADD CONSTRAINT c_cn_station_parameter_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE cn_usage
  ADD CONSTRAINT c_cn_usage_unique UNIQUE (ip_address, module_name);

ALTER TABLE we_weather
  ADD CONSTRAINT c_we_weather_unique UNIQUE (timestamp, station_id),
  ADD CONSTRAINT c_we_weather_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE we_weather_hour
  ADD CONSTRAINT c_we_weather_hour_unique UNIQUE (hour, day, station_id),
  ADD CONSTRAINT c_we_weather_hour_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE we_weather_day
  ADD CONSTRAINT c_we_weather_day_unique UNIQUE (day, station_id),
  ADD CONSTRAINT c_we_weather_day_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE we_weather_month
  ADD CONSTRAINT c_we_weather_month_unique UNIQUE (month, year, station_id),
  ADD CONSTRAINT c_we_weather_month_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE we_weather_year
  ADD CONSTRAINT c_we_weather_year_unique UNIQUE (year, station_id),
  ADD CONSTRAINT c_we_weather_year_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE so_soil
  ADD CONSTRAINT c_so_soil_unique UNIQUE (timestamp, station_id),
  ADD CONSTRAINT c_so_soil_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE so_soil_day
  ADD CONSTRAINT c_so_soil_day_unique UNIQUE (day, station_id),
  ADD CONSTRAINT c_so_soil_day_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE so_soil_month
  ADD CONSTRAINT c_so_soil_month_unique UNIQUE (month, year, station_id),
  ADD CONSTRAINT c_so_soil_month_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE so_soil_year
  ADD CONSTRAINT c_so_soil_year_unique UNIQUE (year, station_id),
  ADD CONSTRAINT c_so_soil_year_station_id FOREIGN KEY (station_id) REFERENCES cn_station (id) ON DELETE RESTRICT ON UPDATE CASCADE;
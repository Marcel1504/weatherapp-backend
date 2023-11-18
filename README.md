# WeatherApp-Backend

Backend of the WeatherApp that contains several spring boot services to manage the WeatherApp

## Modules

### weatherapp-api

Application that handles WeatherApp-UI requests

### weatherapp-aggregator

Application that summarizes raw data by hour, day, month, and year

### weatherapp-assistant

Application that provides an AI chatbot for assistance related to the WeatherApp

### weatherapp-consumer

Application that consumes raw data and media sent by stations

### weatherapp-core

Core library of weather backend application modules

## Releases

| Release | Date       | Description                                                                                                   |
|---------|------------|---------------------------------------------------------------------------------------------------------------|
| 3.0.0   | 2023-08-08 | Combine the old backend and consumer application into a single project and add the new aggregator application |

## Releases of legacy applications

| Release | Date       | Description                                                                                                |
|---------|------------|------------------------------------------------------------------------------------------------------------|
| 2.3.0   | 2023-01-31 | Add station inactivity notifications, Update to Spring Boot 3, Add Humidity evaluation for weather summary |
| 2.2.0   | 2022-08-24 | Upgrade applications for usage with docker                                                                 |
| 2.1.1   | 2021-11-01 | Fix synchronization query                                                                                  |
| 2.1.0   | 2021-10-31 | Increase performance of applications                                                                       |
| 2.0.0   | 2021-09-28 | Refactoring of applications to utilize the new WeatherApp Flutter UI, Remove user management               |
| 1.2.0   | 2021-05-19 | Add functionality to receive and handle data of soil stations                                              |
| 1.1.0   | 2021-01-02 | Add multi station feature, Add filter and sort features                                                    |
| 1.0.0   | 2020-10-05 | Initial Release including user management, media and weather features                                      |

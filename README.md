# MQTT WeatherApp
Current Weather Application Using MQTT Protocol, we developed a Java application which fetches the weather details of a city in real-time, saves it in a database and displays it on a web interface.

Put the html files in 'htdocs' folder.

### Requirements:
*  Apache Server
*  MySQL Database
*  Mosquitto Server for MQTT Protocol
*  PHP
*  JDK 8+
*  OpenWeather API key
*  OpenWeather Java API integration (https://github.com/iamashks/OWM-JAPIs)

## 4.1 MQTT Protocol
MQTT is a client-server publish and subscribe messaging transport protocol. The protocol is used mostly in the Internet of Things context and works over TCP/IP protocol. It basically works on the Publish-Subscribe paradigm and also has a low power requirement. It is easy to implement in small devices and has low latency. The connected devices in the MQTT protocol are known as “clients,” which communicate with a server referred to as the “broker.” The broker handles the task of data transmission between clients. It operates in real-time and also maintains a QoS (Quality of Service) level while publishing.

## 4.2 Java
Using Maven project management tools available for Java, we used the Paho library in the project. The Paho Java Client is an MQTT client library in Java for developing applications that run on the JVM or other Java platforms. The Eclipse Paho project provides open-source client implementations of MQTT and MQTT-SN messaging protocols aimed at new, existing, and emerging applications for the Internet of Things (IoT). To implement the MQTT Protocol, we use Mosquitto. Eclipse Mosquitto is an open-source (EPL/EDL licensed) message broker that implements the MQTT protocol.

## 4.3 Use of Weather API
OpenWeather API is used for fetching of weather data of different cities and adding it to the database. Java Library for OpenWeather API is OWM Java API. The OWM Java API lets you develop weather-aware applications for Android, Kotlin, and Java platforms in minimum time. It is an easy-to-use, well- documented wrapper library for OpenWeatherMap.org’s Weather APIs. You can easily retrieve and use weather data in your applications using this library.

## 4.4 Implementation
Apache Server and MySQL database are connected using XAMPP Server. The MySQL contains the database named ”city” in which the table ”temperature” takes the input of details of current weather of cities declared in the array. MySQL Connector/J is the official JDBC driver for MySQL which was used to integrate Java with MySQL.

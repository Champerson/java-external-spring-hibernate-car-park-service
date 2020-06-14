CREATE DATABASE  IF NOT EXISTS `car_park_service_db` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `car_park_service_db`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: car_park_service_db
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignments`
--

SET GLOBAL time_zone = '+2:00';

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
  `assignment_id` bigint NOT NULL AUTO_INCREMENT,
  `assignment_accepted` tinyint NOT NULL,
  `assignment_creation_time` datetime NOT NULL,
  `assignment_start_date` date DEFAULT NULL,
  `assignment_bus_id` bigint DEFAULT NULL,
  `assignment_route_id` bigint DEFAULT NULL,
  `assignment_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  UNIQUE KEY `id_UNIQUE` (`assignment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buses`
--

DROP TABLE IF EXISTS `buses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buses` (
  `bus_id` bigint NOT NULL AUTO_INCREMENT,
  `bus_number` varchar(255) NOT NULL,
  `bus_model` varchar(255) DEFAULT NULL,
  `bus_passengers_capacity` int DEFAULT NULL,
  `bus_mileage` int DEFAULT NULL,
  `bus_creation_time` datetime NOT NULL,
  `bus_colour_en` varchar(255) DEFAULT NULL,
  `bus_colour_ua` varchar(255) DEFAULT NULL,
  `bus_notes_en` varchar(255) DEFAULT NULL,
  `bus_notes_ua` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bus_id`),
  UNIQUE KEY `id_UNIQUE` (`bus_id`),
  UNIQUE KEY `number_UNIQUE` (`bus_number`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes` (
  `route_id` bigint NOT NULL AUTO_INCREMENT,
  `route_number` varchar(255) NOT NULL,
  `route_length` int DEFAULT NULL,
  `route_creation_time` datetime NOT NULL,
  `route_description_en` varchar(255) DEFAULT NULL,
  `route_description_ua` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`route_id`),
  UNIQUE KEY `number_UNIQUE` (`route_number`),
  UNIQUE KEY `id_UNIQUE` (`route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_login` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_age` int DEFAULT NULL,
  `user_creation_time` datetime NOT NULL,
  `user_access_role` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`user_login`),
  UNIQUE KEY `id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-12 22:50:39

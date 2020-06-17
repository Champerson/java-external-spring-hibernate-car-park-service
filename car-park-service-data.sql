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
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (19,1,'2020-05-10 23:54:23','2020-05-06',10,19,13),(22,1,'2020-06-02 12:26:14','2020-05-25',11,19,16),(24,0,'2020-06-02 15:35:29','2020-05-31',8,19,11),(25,0,'2020-06-12 22:12:32','2020-06-08',9,19,14),(26,0,'2020-06-12 22:12:37','2020-06-11',12,19,NULL),(27,0,'2020-06-12 22:12:39','2020-06-11',15,19,NULL),(28,0,'2020-06-12 22:13:06','2020-06-10',16,20,25),(29,0,'2020-06-12 22:13:08','2020-06-11',17,20,NULL);
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `buses`
--

LOCK TABLES `buses` WRITE;
/*!40000 ALTER TABLE `buses` DISABLE KEYS */;
INSERT INTO `buses` VALUES (8,'AA4170HX','Mersedes',60,25000,'2020-05-10 23:06:28','red','червоний','',''),(9,'AA1221BN','Brabus',45,12000,'2020-05-10 23:08:05','yellow','жовтый','Need to pass inspection','Потребує техогляду'),(10,'AA6703MH','Bogdan',65,37000,'2020-05-10 23:09:33','red','червоний','',''),(11,'AA0056FN','Mersedes',60,45000,'2020-05-10 23:10:37','white','білий','',''),(12,'AA3245AB','Bogdan',65,10000,'2020-05-10 23:11:22','white','білий','',''),(15,'AE4270HX','Mersedes',60,45000,'2020-06-12 22:04:01','red','червоний','',''),(16,'AE3245XR','Bogdan',45,110000,'2020-06-12 22:07:49','white','білий','',''),(17,'AH1122OR','Mersedes',60,250000,'2020-06-12 22:08:40','black','чорний','',''),(18,'AX2233BA','Bogdan',45,170000,'2020-06-12 22:10:01','white','білий','',''),(19,'HX3045AB','Bogdan',45,34000,'2020-06-12 22:10:44','red','червоний','',''),(20,'AN1265VI','Bogdan',65,5000,'2020-06-12 22:11:18','black','чорний','',''),(21,'AA9945HM','Mersedes',60,457000,'2020-06-12 22:12:01','white','білий','','');
/*!40000 ALTER TABLE `buses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (19,'445',10,'2020-05-10 22:54:28','Darnytsia - Pozniaky','Дарниця - Позняки'),(20,'418',11,'2020-05-10 23:16:11','Popudrenka - Liskivska','Попудренка - Лісківська'),(21,'513',10,'2020-06-02 11:23:12','Poliska - Pozniaky','Поліська - Позняки');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (11,'Andrew','8aae3a73a9a43ee6b04dfd986fe9d136','andrew@gmail.com','0637774411','Andrew',20,'2020-05-10 22:21:56','ROLE_DRIVER'),(12,'admin','21232f297a57a5a743894a0e4a801fc3','admin@gmail.com','0638882233','Oleg',20,'2020-05-10 23:02:44','ROLE_ADMIN'),(13,'Alex','a08372b70196c21a9229cf04db6b7ceb','alex@ukr.net','0634445599','Alex',31,'2020-05-10 23:17:48','ROLE_DRIVER'),(14,'Tanya','4163d678e68f506fc9cfb79ef4292a03','tanya@gmail.com','0635651937','Tanya',32,'2020-05-10 23:18:34','ROLE_DRIVER'),(16,'Kolia','52c23bdd8cdb3b2ce7f82f63be8fccc7','kolia@gmail.com','0635554423','Kolia',22,'2020-05-10 23:56:37','ROLE_DRIVER'),(23,'John','61409aa1fd47d4a5332de23cbf59a36f','John@John','0632224455','John',21,'2020-06-10 15:00:35','ROLE_ADMIN'),(24,'user','ee11cbb19052e40b07aac0ca060c23ee','user@gmail.com','0634451237','User',27,'2020-06-12 22:31:29','ROLE_DRIVER'),(25,'Bogdan','cd63c2c553dcae978dc895629d1c6049','bogdan@ukr.net','0667212200','Bogdan',39,'2020-06-12 22:32:09','ROLE_DRIVER'),(26,'Eugen','5f76f08e69a5287251b299cf1ee4e248','eugen@gmail.com','0935774529','Eugen',25,'2020-06-12 22:32:47','ROLE_DRIVER'),(27,'Svitlana','3b336fdb8dbe91ffa18010ce366a2d9c','svitlana@ukr.net','0954765412','Svitlana',41,'2020-06-12 22:33:25','ROLE_DRIVER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-12 23:41:43

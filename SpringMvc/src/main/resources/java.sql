-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: java
-- ------------------------------------------------------
-- Server version	5.6.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `UserConnection`
--

DROP TABLE IF EXISTS `UserConnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserConnection` (
  `userid` varchar(255) NOT NULL,
  `provideruserid` varchar(255) NOT NULL,
  `providerid` varchar(255) NOT NULL,
  `accesstoken` varchar(512) DEFAULT NULL,
  `displayname` varchar(255) DEFAULT NULL,
  `expiretime` bigint(20) DEFAULT NULL,
  `imageurl` varchar(512) DEFAULT NULL,
  `profileurl` varchar(512) DEFAULT NULL,
  `rank` int(11) NOT NULL,
  `refreshtoken` varchar(512) DEFAULT NULL,
  `secret` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`userid`,`provideruserid`,`providerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserConnection`
--

LOCK TABLES `UserConnection` WRITE;
/*!40000 ALTER TABLE `UserConnection` DISABLE KEYS */;
INSERT INTO `UserConnection` VALUES ('3c278098-046c-4289-ac2d-c147eea67518','115918920813268970788','google','ya29.ImWbB-U0tWnQHJYcBCGS2k1FOg9_jRA0wbGylmKyMEwshE70bMUn8BKmpjsLcB7w1lFtUxPwiQPXLvG5kIlWFBqaXioLTV1D5GAawUhtSmYqp-SniM5WwhySvGUYC1vFwqjBq_9VKQ','duc nguyen tuan',1571072715460,'https://lh3.googleusercontent.com/a-/AAuE7mCtAAAh8qEyy1rcbtBCWXe4kPPHFevkN1F7gJ6a=s50',NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `UserConnection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_Accounts`
--

DROP TABLE IF EXISTS `User_Accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Accounts` (
  `id` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(20) NOT NULL,
  `enabled` varchar(1) NOT NULL DEFAULT 'Y'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Accounts`
--

LOCK TABLES `User_Accounts` WRITE;
/*!40000 ALTER TABLE `User_Accounts` DISABLE KEYS */;
INSERT INTO `User_Accounts` VALUES ('3c278098-046c-4289-ac2d-c147eea67518','nguyentuanduc18@gmail.com','115918920813268970788','duc','nguyen tuan','11','ROLE_USER','Y');
/*!40000 ALTER TABLE `User_Accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(128) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `encryted_password` varchar(128) NOT NULL,
  `first_name` varchar(36) DEFAULT NULL,
  `last_name` varchar(36) DEFAULT NULL,
  `user_name` varchar(36) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `APP_USER_UK` (`user_name`),
  UNIQUE KEY `APP_USER_UK2` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `authority` varchar(60) NOT NULL,
  `username` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_authorities_user` (`user_id`),
  CONSTRAINT `fk_authorities_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,1,'ROLE_USER','hero'),(2,2,'ROLE_ADMIN','admin'),(3,2,'ROLE_USER','admin');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` varchar(20) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('IT'),('Java'),('Tool');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `BIRTH_DATE` datetime DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`FIRST_NAME`,`LAST_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (3,'1980-07-30 00:00:00','Hero','Java',1),(4,'1980-07-30 00:00:00','Hero1','hibernate',1),(5,'1980-07-30 00:00:00','Hero1','Java2',2),(6,'1980-07-30 00:00:00','Hero2','Java2',2),(7,'1980-07-30 00:00:00','Hero3','Java4',3);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_hobby_detail`
--

DROP TABLE IF EXISTS `contact_hobby_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_hobby_detail` (
  `contact_id` int(11) NOT NULL,
  `hobby_id` varchar(20) NOT NULL,
  PRIMARY KEY (`contact_id`,`hobby_id`),
  KEY `FK_CONTACT_HOBBY_DETAIL_2` (`hobby_id`),
  CONSTRAINT `FK_CONTACT_HOBBY_DETAIL_1` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_CONTACT_HOBBY_DETAIL_2` FOREIGN KEY (`hobby_id`) REFERENCES `hobby` (`hobby_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_hobby_detail`
--

LOCK TABLES `contact_hobby_detail` WRITE;
/*!40000 ALTER TABLE `contact_hobby_detail` DISABLE KEYS */;
INSERT INTO `contact_hobby_detail` VALUES (3,'Movies'),(3,'Swimming'),(4,'Swimming');
/*!40000 ALTER TABLE `contact_hobby_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_insert`
--

DROP TABLE IF EXISTS `contact_insert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_insert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_CONTACT_1` (`first_name`,`last_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_insert`
--

LOCK TABLES `contact_insert` WRITE;
/*!40000 ALTER TABLE `contact_insert` DISABLE KEYS */;
INSERT INTO `contact_insert` VALUES (1,'SQL','My','1980-07-30',0),(2,'SQL','M1y','1980-07-30',0);
/*!40000 ALTER TABLE `contact_insert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_new`
--

DROP TABLE IF EXISTS `contact_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_new` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_CONTACT_1` (`first_name`,`last_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_new`
--

LOCK TABLES `contact_new` WRITE;
/*!40000 ALTER TABLE `contact_new` DISABLE KEYS */;
INSERT INTO `contact_new` VALUES (5,'SQL','M1y','1980-07-30',0);
/*!40000 ALTER TABLE `contact_new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_tel_detail`
--

DROP TABLE IF EXISTS `contact_tel_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_tel_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTACT_ID` int(11) NOT NULL,
  `TEL_TYPE` varchar(20) NOT NULL,
  `TEL_NUMBER` varchar(20) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UQ_CONTACT_TEL_DETAIL_1` (`CONTACT_ID`,`TEL_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_tel_detail`
--

LOCK TABLES `contact_tel_detail` WRITE;
/*!40000 ALTER TABLE `contact_tel_detail` DISABLE KEYS */;
INSERT INTO `contact_tel_detail` VALUES (1,3,'Mobile','1234567890',0),(2,3,'Home','5555',0),(3,4,'Mobile','66666',0);
/*!40000 ALTER TABLE `contact_tel_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hobby`
--

DROP TABLE IF EXISTS `hobby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hobby` (
  `hobby_id` varchar(20) NOT NULL,
  PRIMARY KEY (`hobby_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hobby`
--

LOCK TABLES `hobby` WRITE;
/*!40000 ALTER TABLE `hobby` DISABLE KEYS */;
INSERT INTO `hobby` VALUES ('11'),('12'),('13'),('Jogging'),('Movies'),('Programming'),('Reading'),('Swimming');
/*!40000 ALTER TABLE `hobby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category_detail`
--

DROP TABLE IF EXISTS `product_category_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category_detail` (
  `product_id` int(11) NOT NULL,
  `category_id` varchar(20) NOT NULL,
  PRIMARY KEY (`product_id`,`category_id`),
  KEY `fk_product_category_detail_2` (`category_id`),
  CONSTRAINT `fk_product_category_detail_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_product_category_detail_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category_detail`
--

LOCK TABLES `product_category_detail` WRITE;
/*!40000 ALTER TABLE `product_category_detail` DISABLE KEYS */;
INSERT INTO `product_category_detail` VALUES (2,'IT'),(3,'IT'),(8,'Java'),(14,'Java'),(17,'Java'),(7,'Tool');
/*!40000 ALTER TABLE `product_category_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `unit_price` decimal(10,0) DEFAULT NULL,
  `condition_type` varchar(50) NOT NULL,
  `units_in_stock` bigint(20) DEFAULT NULL,
  `units_in_order` bigint(20) DEFAULT NULL,
  `discontinued` tinyint(1) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `disable` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (2,'Collection Book','111',12,'Refurbished',0,0,0,'2019-10-22 07:03:04','2019-09-23 07:12:00','2019-09-20 07:12:46',0),(3,'Pro Spring 5 ','Pro Spring 5 ',1,'New',10,1,1,'2019-10-24 02:48:39','2019-09-24 07:12:00','2019-09-20 07:12:46',0),(6,'12','123131',12,'New',12,12,1,'2019-10-22 04:10:00','2019-09-25 07:12:00','2019-09-20 07:12:46',1),(7,'eclipse','how to use eclipse for developer',100,'New',10,1,1,'2019-09-30 08:03:52','2019-09-26 07:12:00','2019-09-20 07:12:46',0),(8,'Learn java in one day','good book',40,'New',5,1,1,'2019-09-30 08:03:52','2019-09-20 07:12:46','2019-09-20 07:12:46',0),(11,'Java Event a1','Java Event',23,'New',12,1,1,'2019-09-30 16:06:39','2019-09-30 16:04:16','0000-00-00 00:00:00',0),(14,'Spring 5 Recipes','solution',23,'Old',11,1,1,'2019-10-17 08:13:49','2019-10-14 08:31:12','0000-00-00 00:00:00',0),(16,'Hibernate','aaaa',33,'New',11,2,1,'2019-10-22 04:09:45','2019-10-15 07:14:00','0000-00-00 00:00:00',0),(17,'Spring 5 Recipes 5','111',12,'New',11,1,1,'2019-10-20 16:10:26','2019-10-15 10:14:15','0000-00-00 00:00:00',1),(18,'Java In Easy Step','no des',1,'New',10,1,1,'2019-10-22 03:44:49','2019-10-19 05:33:14','0000-00-00 00:00:00',0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publish`
--

DROP TABLE IF EXISTS `publish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publish` (
  `publish_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`publish_id`),
  KEY `fk_author_1` (`product_id`),
  CONSTRAINT `fk_author_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publish`
--

LOCK TABLES `publish` WRITE;
/*!40000 ALTER TABLE `publish` DISABLE KEYS */;
INSERT INTO `publish` VALUES (6,6,'OReilly'),(7,7,'SoftWare'),(8,8,'OReilly'),(27,14,'aaa3'),(31,17,'Apress 1'),(36,2,'Apress'),(37,3,'Apress');
/*!40000 ALTER TABLE `publish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(60) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'hero','hero@gmail.com','$2a$10$OQdevgdQwkXQeC25.nF.UOhcafR1CmW7guISRGftMuDVx72qgAsu2','hero','king',NULL),(2,'admin','admin@gmail.com','$2a$10$OQdevgdQwkXQeC25.nF.UOhcafR1CmW7guISRGftMuDVx72qgAsu2',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varchars`
--

DROP TABLE IF EXISTS `varchars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `varchars` (
  `varcharType` varchar(4) DEFAULT NULL,
  `varType` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varchars`
--

LOCK TABLES `varchars` WRITE;
/*!40000 ALTER TABLE `varchars` DISABLE KEYS */;
INSERT INTO `varchars` VALUES ('ab  ','ab'),('abcd','abcd');
/*!40000 ALTER TABLE `varchars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vc`
--

DROP TABLE IF EXISTS `vc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vc` (
  `v` varchar(4) DEFAULT NULL,
  `c` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vc`
--

LOCK TABLES `vc` WRITE;
/*!40000 ALTER TABLE `vc` DISABLE KEYS */;
INSERT INTO `vc` VALUES ('ab  ','ab');
/*!40000 ALTER TABLE `vc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-25  9:55:45

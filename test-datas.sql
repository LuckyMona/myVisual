-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: 192.168.220.146    Database: test
-- ------------------------------------------------------
-- Server version	5.6.36

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

CREATE SCHEMA IF NOT EXISTS `uetest` DEFAULT CHARACTER SET utf8 ;
USE `uetest` ;

--
-- Table structure for table `active_user`
--

DROP TABLE IF EXISTS `active_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `active_user` (
  `model` text,
  `app` text,
  `version` text,
  `day_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `imei` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `active_user`
--

LOCK TABLES `active_user` WRITE;
/*!40000 ALTER TABLE `active_user` DISABLE KEYS */;
INSERT INTO `active_user` VALUES ('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-07 19:00:00','1111111111'),('TP902A','com.cyanogenmod.trebuchet','7.0','2017-05-01 19:00:00','919678617293412'),('TP904A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:47:54','171242077256104'),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-04 19:00:00','1111111111'),('TP904A','com.cyanogenmod.trebuchet','7.0','2017-05-01 19:00:00','1111111111'),('TP904A','com.cyanogenmod.trebuchet','7.0','2017-05-02 01:00:00','688796212439698'),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-11 19:00:00','1111111111'),('TP904A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:55:23','686240520366369'),('TP904A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:47:54','866528143227430'),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00','1111111111'),('TP904A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:55:23','401989884213655'),('TP904A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:47:54','429386332891684'),('TP903A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:55:25','650488725680856'),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-03 19:00:00','1111111111'),('TP904A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:47:54','919678617293412'),('TP903A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00','1111111111'),('TP904A','com.cyanogenmod.trebuchet','7.0','2017-05-01 20:00:00','919678617293412'),('TP902A','com.cyanogenmod.trebuchet','7.0','2017-05-01 22:00:00','485756757873995'),('TP902A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00','1111111111'),('TP902A','com.cyanogenmod.trebuchet','7.0','2017-05-01 19:00:00','963935099398922'),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-10 19:00:00','1111111111'),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-02 00:00:00','1111111111'),('TP904A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:55:19','603122490110168'),('TP902A','com.cyanogenmod.trebuchet','7.0','2017-05-01 20:00:00','866528143227430');
/*!40000 ALTER TABLE `active_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_data_use`
--

DROP TABLE IF EXISTS `app_data_use`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_data_use` (
  `project` text,
  `app` text,
  `version` text,
  `day_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `avg_data_use` double DEFAULT NULL,
  `max_data_use` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_data_use`
--

LOCK TABLES `app_data_use` WRITE;
/*!40000 ALTER TABLE `app_data_use` DISABLE KEYS */;
INSERT INTO `app_data_use` VALUES ('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-10 19:00:00',140,'300'),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-11 19:00:00',300,'300');
/*!40000 ALTER TABLE `app_data_use` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_proc`
--

DROP TABLE IF EXISTS `app_proc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_proc` (
  `project` text,
  `app` text,
  `version` text,
  `day_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `avg_proc` double DEFAULT NULL,
  `max_proc` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_proc`
--

LOCK TABLES `app_proc` WRITE;
/*!40000 ALTER TABLE `app_proc` DISABLE KEYS */;
INSERT INTO `app_proc` VALUES ('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-03 19:00:00',2.3,'5.4 '),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00',2.7000000000000006,'4.8 '),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-07 19:00:00',2.3,'5.4 '),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-04 19:00:00',2.3,'5.4 '),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-02 19:00:00',2.3666666666666667,'5.8 '),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-10 19:00:00',2.3,'5.4 ');
/*!40000 ALTER TABLE `app_proc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_start`
--

DROP TABLE IF EXISTS `app_start`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_start` (
  `project` text,
  `app` text,
  `version` text,
  `app_start_count` double DEFAULT NULL,
  `hour_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_start`
--

LOCK TABLES `app_start` WRITE;
/*!40000 ALTER TABLE `app_start` DISABLE KEYS */;
INSERT INTO `app_start` VALUES ('TP904A','com.cyanogenmod.trebuchet','7.0',5,'2017-05-01 20:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0',50,'2016-05-01 19:00:00'),('TP902A','com.cyanogenmod.trebuchet','7.0',15,'2016-05-01 19:00:00'),('TP902A','com.cyanogenmod.trebuchet','7.0',10,'2017-05-01 19:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0',5,'2017-05-02 01:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0',5,'2017-05-01 19:00:00'),('TP903A','com.cyanogenmod.trebuchet','7.0',5,'1970-01-18 00:55:25'),('TP902A','com.cyanogenmod.trebuchet','7.0',5,'2017-05-01 20:00:00'),('TP903A','com.cyanogenmod.trebuchet','7.0',35,'2016-05-01 19:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0',35,'1970-01-18 00:47:54'),('TP902A','com.cyanogenmod.trebuchet','7.0',5,'2017-05-01 22:00:00');
/*!40000 ALTER TABLE `app_start` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avg_use_time`
--

DROP TABLE IF EXISTS `avg_use_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `avg_use_time` (
  `project` text,
  `app` text,
  `version` text,
  `day_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `avg_use_time` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avg_use_time`
--

LOCK TABLES `avg_use_time` WRITE;
/*!40000 ALTER TABLE `avg_use_time` DISABLE KEYS */;
INSERT INTO `avg_use_time` VALUES ('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00',30),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-02 00:00:00',30);
/*!40000 ALTER TABLE `avg_use_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_count`
--

DROP TABLE IF EXISTS `event_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_count` (
  `project` text,
  `app` text,
  `version` text,
  `hour_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `event_id` text,
  `count` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_count`
--

LOCK TABLES `event_count` WRITE;
/*!40000 ALTER TABLE `event_count` DISABLE KEYS */;
INSERT INTO `event_count` VALUES ('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-10 19:00:00','get_package_proc_usage',1),('TP902A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00','appStart',3),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-02 19:00:00','get_package_proc_usage',3),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00','get_package_proc_usage',3),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-02 00:00:00','appStart',2),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-10 19:00:00','get_package_data_usage',5),('TP904A','com.cyanogenmod.trebuchet','7.0','2017-05-02 01:00:00','appStart',1),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-11 19:00:00','get_package_data_usage',1),('TP904A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:47:54','appStart',7),('TP902A','com.cyanogenmod.trebuchet','7.0','2017-05-01 22:00:00','appStart',1),('TP902A','com.cyanogenmod.trebuchet','7.0','2017-05-01 19:00:00','appStart',2),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00','app_use_time',2),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-04 19:00:00','get_package_proc_usage',2),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00','appStart',13),('TP904A','com.cyanogenmod.trebuchet','7.0','2017-05-01 20:00:00','appStart',1),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-07 19:00:00','get_package_proc_usage',1),('TP904A','com.cyanogenmod.trebuchet','7.0','2016-05-03 19:00:00','get_package_proc_usage',1),('TP903A','com.cyanogenmod.trebuchet','7.0','2016-05-01 19:00:00','appStart',7),('TP903A','com.cyanogenmod.trebuchet','7.0','1970-01-18 00:55:25','appStart',1),('TP904A','com.cyanogenmod.trebuchet','7.0','2017-05-01 19:00:00','appStart',1),('TP902A','com.cyanogenmod.trebuchet','7.0','2017-05-01 20:00:00','appStart',1);
/*!40000 ALTER TABLE `event_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter`
--

DROP TABLE IF EXISTS `filter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter` (
  `project` text,
  `app` text,
  `version` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter`
--

LOCK TABLES `filter` WRITE;
/*!40000 ALTER TABLE `filter` DISABLE KEYS */;
INSERT INTO `filter` VALUES ('TP904A','com.cyanogenmod.trebuchet2','8.0'),('TP903A','com.cyanogenmod.trebuchet','7.0'),('TP902A','com.cyanogenmod.trebuchet','7.0'),('TP904A','com.cyanogenmod.trebuchet','7.0');
/*!40000 ALTER TABLE `filter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_user`
--

DROP TABLE IF EXISTS `new_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new_user` (
  `project` text,
  `app` text,
  `version` text,
  `imei` text,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_user`
--

LOCK TABLES `new_user` WRITE;
/*!40000 ALTER TABLE `new_user` DISABLE KEYS */;
INSERT INTO `new_user` VALUES ('TP904A','com.cyanogenmod.trebuchet','7.0','919678617293412','1970-01-18 00:47:54'),('TP904A','com.cyanogenmod.trebuchet','7.0','686240520366369','1970-01-18 00:55:23'),('TP903A','com.cyanogenmod.trebuchet','7.0','1111111111','2016-05-01 19:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0','688796212439698','2017-05-02 01:00:00'),('TP902A','com.cyanogenmod.trebuchet','7.0','1111111111','2016-05-01 19:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0','401989884213655','1970-01-18 00:55:23'),('TP902A','com.cyanogenmod.trebuchet','7.0','485756757873995','2017-05-01 22:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0','603122490110168','1970-01-18 00:55:19'),('TP904A','com.cyanogenmod.trebuchet','7.0','429386332891684','1970-01-18 00:47:54'),('TP904A','com.cyanogenmod.trebuchet','7.0','1111111111','2016-05-01 19:00:00'),('TP902A','com.cyanogenmod.trebuchet','7.0','963935099398922','2017-05-01 19:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0','171242077256104','1970-01-18 00:47:54'),('TP903A','com.cyanogenmod.trebuchet','7.0','650488725680856','1970-01-18 00:55:25'),('TP902A','com.cyanogenmod.trebuchet','7.0','866528143227430','2017-05-01 20:00:00'),('TP902A','com.cyanogenmod.trebuchet','7.0','919678617293412','2017-05-01 19:00:00'),('TP904A','com.cyanogenmod.trebuchet','7.0','866528143227430','1970-01-18 00:47:54');
/*!40000 ALTER TABLE `new_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-04 21:58:02

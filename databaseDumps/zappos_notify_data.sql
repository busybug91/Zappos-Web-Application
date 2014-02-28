CREATE DATABASE  IF NOT EXISTS `zappos` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `zappos`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: zappos
-- ------------------------------------------------------
-- Server version	5.6.11-ndb-7.3.2-cluster-gpl

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
-- Table structure for table `notify_data`
--

DROP TABLE IF EXISTS `notify_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notify_data` (
  `emailID` varchar(100) DEFAULT NULL,
  `productID` varchar(100) DEFAULT NULL,
  `s_num` int(11) NOT NULL AUTO_INCREMENT,
  `notify_status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`s_num`),
  KEY `productID_idx` (`productID`),
  CONSTRAINT `productID` FOREIGN KEY (`productID`) REFERENCES `product_data` (`productID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notify_data`
--

LOCK TABLES `notify_data` WRITE;
/*!40000 ALTER TABLE `notify_data` DISABLE KEYS */;
INSERT INTO `notify_data` VALUES ('nitin4ahuja@gmail.com','7590514',1,0),('nitin4ahuja@gmail.com','7167142',2,0),('nitin4ahuja@gmail.com','7151832',3,0),('nitin4ahuja@gmail.com','7867997',4,0),('nitin4ahuja@gmail.com','8046201',5,0),('nitin4ahuja@gmail.com','7555268',6,0),('nitin4ahuja@gmail.com','7555262',7,0),('nitin4ahuja@gmail.com','7787264',8,0),('nitin4ahuja@gmail.com','8207387',9,0),('nitin4ahuja@gmail.com','7555268',10,0),('nitin4ahuja@gmail.com','7770945',11,0),('nitin4ahuja@gmail.com','7590514',12,0),('nitin4ahuja@gmail.com','7167142',13,0),('nitin4ahuja@gmail.com','7151832',14,0),('nitin4ahuja@gmail.com','7867997',15,0),('ahuja.nitin91@gmail.com','8046201',16,0),('nitin4ahuja@gmail.com','7555268',17,0),('nitin4ahuja@gmail.com','7555262',18,0),('nitin4ahuja@gmail.com','8207387',20,0),('nitin4ahuja@gmail.com','7555268',21,0),('nitin4ahuja@gmail.com','7770945',22,0),('nitin4ahuja@gmail.com','8230652',24,0),('nitin4ahuja@gmail.com','7555271',25,0),('nitin4ahuja@gmail.com','7555271',26,0),('nitin4ahuja@gmail.com','7555272',27,0);
/*!40000 ALTER TABLE `notify_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-28  2:03:25

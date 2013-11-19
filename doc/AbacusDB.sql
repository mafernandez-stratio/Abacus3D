-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: AbacusDB
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

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
-- Table structure for table `App`
--

DROP TABLE IF EXISTS `App`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `App` (
  `idApp` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT 'available',
  `instDate` datetime DEFAULT NULL,
  `logo` varchar(180) DEFAULT 'blankLogo.gif',
  `instUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idApp`),
  UNIQUE KEY `idApp_UNIQUE` (`idApp`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `insUser_idx` (`instUser`),
  CONSTRAINT `insUser` FOREIGN KEY (`instUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `App`
--

LOCK TABLES `App` WRITE;
/*!40000 ALTER TABLE `App` DISABLE KEYS */;
INSERT INTO `App` VALUES (1,'Octave','regular','installed','2013-07-01 10:00:01','octaveLogo.png',1),(2,'OpenFOAM','regular','available','0000-00-00 00:00:00','openFOAMlogo.gif',NULL),(3,'javaTest','test','available','2013-07-01 10:00:01','blankLogo.gif',1),(4,'cppTest','test','available','2013-07-01 10:03:41','blankLogo.gif',2),(5,'mainTest','test','available','2013-07-01 10:06:01','blankLogo.gif',1),(6,'linpack','test','available','2013-07-01 10:07:51','blankLogo.gif',3),(7,'heartbeat','test','available','2013-07-01 10:11:09','blankLogo.gif',4),(8,'octaveTest','test','available','2013-07-01 10:22:22','blankLogo.gif',1);
/*!40000 ALTER TABLE `App` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Node`
--

DROP TABLE IF EXISTS `Node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Node` (
  `idNode` int(11) NOT NULL AUTO_INCREMENT,
  `hostname` varchar(45) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `cpu` varchar(45) DEFAULT NULL,
  `mem` varchar(45) DEFAULT NULL,
  `disk` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idNode`),
  UNIQUE KEY `idNode_UNIQUE` (`idNode`),
  UNIQUE KEY `hostname_UNIQUE` (`hostname`),
  UNIQUE KEY `ip_UNIQUE` (`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Node`
--

LOCK TABLES `Node` WRITE;
/*!40000 ALTER TABLE `Node` DISABLE KEYS */;
INSERT INTO `Node` VALUES (1,'master','192.168.1.25','ok','ok','ok','warning'),(2,'node1','192.168.1.26','ok','ok','ok','ok'),(3,'node2','192.168.1.27','ok','ok','ok','ok'),(4,'node3','192.168.1.28','ok','ok','ok','warning'),(5,'node4','192.168.1.29','error','error','error','error');
/*!40000 ALTER TABLE `Node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Process`
--

DROP TABLE IF EXISTS `Process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Process` (
  `idProcess` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `priority` int(11) NOT NULL DEFAULT '0',
  `status` varchar(45) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idProcess`),
  UNIQUE KEY `idProcess_UNIQUE` (`idProcess`),
  KEY `idUser_idx` (`idUser`),
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Process`
--

LOCK TABLES `Process` WRITE;
/*!40000 ALTER TABLE `Process` DISABLE KEYS */;
INSERT INTO `Process` VALUES (1,'javaTest','test',0,'finished','2013-07-01 10:00:05','2013-07-01 10:00:07',1),(2,'cppTest','test',10,'finished','2013-07-01 10:00:06','2013-07-01 10:00:09',2),(3,'mainTest','test',5,'running','2013-07-01 10:00:04',NULL,1),(13,'apple_attack.png','Octave',5,'running','2013-09-12 13:42:38',NULL,1),(15,'joke.jpg','Octave',8,'waiting',NULL,NULL,1),(16,'tmp','OpenFoam',2,'finished','2013-07-01 10:09:05','2013-07-01 11:40:35',3),(17,'linpack','test',7,'running','2013-07-01 10:09:08',NULL,4),(18,'heartbeat','test',1,'waiting',NULL,NULL,5),(19,'montecarlo','Octave',3,'waiting','0000-00-00 00:00:00','0000-00-00 00:00:00',4),(20,'render','OpenFoam',6,'finished','2013-07-02 10:09:05','2013-07-02 09:12:54',3),(21,'mncars','Octave',9,'waiting','0000-00-00 00:00:00','0000-00-00 00:00:00',2),(30,'matrixExample','test',10,'finished','2013-10-14 17:03:12','2013-10-14 17:03:36',1),(37,'testExample','test',1,'finished','2013-10-15 15:29:19','2013-10-15 15:29:41',1),(38,'matrixMultoctaveScript','test',10,'finished','2013-10-15 15:30:29','2013-10-15 15:30:51',1),(106,'matrixOctaveProcessorLocal','test',1,'finished','2013-10-21 17:43:14','2013-10-21 17:43:38',1);
/*!40000 ALTER TABLE `Process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE KEY `idRole_UNIQUE` (`idRole`),
  UNIQUE KEY `roleName_UNIQUE` (`roleName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Admin'),(3,'Developer'),(4,'Researcher'),(2,'User');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RunProcs`
--

DROP TABLE IF EXISTS `RunProcs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RunProcs` (
  `idRunProcs` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT 'custom',
  PRIMARY KEY (`idRunProcs`),
  UNIQUE KEY `idRunProcs_UNIQUE` (`idRunProcs`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RunProcs`
--

LOCK TABLES `RunProcs` WRITE;
/*!40000 ALTER TABLE `RunProcs` DISABLE KEYS */;
INSERT INTO `RunProcs` VALUES (1,'2013-07-01 10:00:05',5,'octave'),(2,'2013-07-01 10:00:05',12,'nbody'),(3,'2013-07-01 10:00:05',7,'custom'),(4,'2013-07-01 10:00:05',8,'test'),(5,'2013-07-01 10:00:05',6,'init'),(6,'2013-07-01 10:00:05',11,'math'),(7,'2013-07-01 10:00:06',29,'test'),(8,'2013-07-01 10:00:06',25,'octave'),(9,'2013-07-01 10:00:07',29,'test'),(10,'2013-07-01 10:00:07',26,'octave'),(11,'2013-07-01 10:00:08',51,'math');
/*!40000 ALTER TABLE `RunProcs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `created` datetime DEFAULT NULL,
  `lastConnection` datetime DEFAULT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'admin','c93ccd78b2076528346216b3b2f701e6','2013-07-01 10:00:00','2013-10-21 16:32:29',NULL),(2,'cediant','a6cecbb0a432d33ead56f7687a6c436f','2013-07-01 10:00:00','2013-08-16 10:16:51','Cediant user'),(3,'tabacus','098f6bcd4621d373cade4e832627b4f6','2013-07-01 10:00:00','2013-09-04 15:59:08','Test abacus'),(4,'user','ee11cbb19052e40b07aac0ca060c23ee','2013-07-01 10:00:00','2013-07-05 12:48:33',NULL),(5,'cuax','a6cecbb0a432d33ead56f7687a6c436f','2013-07-01 10:00:00','2013-09-04 11:57:57',NULL);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UsersRole`
--

DROP TABLE IF EXISTS `UsersRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UsersRole` (
  `idUsersRole` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) DEFAULT NULL,
  `idRole` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsersRole`),
  UNIQUE KEY `idUsersRole_UNIQUE` (`idUsersRole`),
  KEY `fk_roleId_idx` (`idRole`),
  KEY `fk_userId_idx` (`idUser`),
  CONSTRAINT `fk_roleId` FOREIGN KEY (`idRole`) REFERENCES `Role` (`idRole`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userId` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsersRole`
--

LOCK TABLES `UsersRole` WRITE;
/*!40000 ALTER TABLE `UsersRole` DISABLE KEYS */;
INSERT INTO `UsersRole` VALUES (1,1,1),(2,1,2),(3,2,2),(4,2,3),(5,2,4),(8,4,4),(37,5,2),(41,3,4),(42,3,2);
/*!40000 ALTER TABLE `UsersRole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-19 17:29:33

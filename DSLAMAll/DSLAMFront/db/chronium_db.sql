CREATE DATABASE  IF NOT EXISTS `dslamdbtest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dslamdbtest`;
-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: dslamdbtest
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.12.04.1

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
-- Table structure for table `Node`
--

DROP TABLE IF EXISTS `Node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Node` (
  `nodeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `nodeIp` varchar(255) DEFAULT NULL,
  `nodeName` varchar(255) DEFAULT NULL,
  `nodeType` varchar(255) DEFAULT NULL,
  `savedTime` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `machinePropertiesId` bigint(20) DEFAULT NULL,
  `processId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`nodeId`),
  KEY `FK252222707AC139` (`machinePropertiesId`),
  KEY `FK2522228BE06F18` (`processId`),
  CONSTRAINT `FK252222707AC139` FOREIGN KEY (`machinePropertiesId`) REFERENCES `MachineProperties` (`machinePropertiesId`),
  CONSTRAINT `FK2522228BE06F18` FOREIGN KEY (`processId`) REFERENCES `Process` (`processId`)
) ENGINE=InnoDB AUTO_INCREMENT=938 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Node`
--

LOCK TABLES `Node` WRITE;
/*!40000 ALTER TABLE `Node` DISABLE KEYS */;
INSERT INTO `Node` VALUES (921,NULL,'localhost','MIGUELTURRA','ISAM-FD','2014-09-22 10:41:22',0,1,2),(922,NULL,'localhost','LP-TELDE','ISAM-FD','2014-09-22 10:41:22',0,1,2),(923,NULL,'localhost','SANJAVIER','ISAM-FD','2014-09-22 10:41:22',0,1,2),(924,NULL,'localhost','LP-SANCRISTOBAL','ISAM-FD','2014-09-22 10:41:22',0,1,2),(925,NULL,'localhost','M-ALMENARA3','ISAM-XD','2014-09-22 10:41:22',0,2,2),(926,NULL,'localhost','B-ABRERA','ISAM-XD','2014-09-22 10:41:22',0,2,2),(927,NULL,'localhost','SANTSADURNIDANOIA','ISAM-FD','2014-09-22 10:41:22',0,1,2),(928,NULL,'localhost','ALBERCA','ISAM-FD','2014-09-22 10:41:22',0,1,2),(929,'2014-09-23 11:52:06','localhost','MIGUELTURRA','ISAM-FD','2014-09-23 11:52:06',0,1,1),(930,'2014-09-23 11:52:06','127.0.0.1','MARIO','ISAM-FD','2014-09-23 11:52:06',0,1,1),(931,'2014-09-23 11:52:06','localhost','LP-TELDE','ISAM-FD','2014-09-23 11:52:06',0,1,1),(932,'2014-09-23 11:52:06','localhost','SANJAVIER','ISAM-FD','2014-09-23 11:52:06',0,1,1),(933,'2014-09-23 11:52:06','localhost','LP-SANCRISTOBAL','ISAM-FD','2014-09-23 11:52:06',0,1,1),(934,'2014-09-23 11:52:06','localhost','M-ALMENARA3','ISAM-XD','2014-09-23 11:52:06',0,2,1),(935,'2014-09-23 11:52:06','localhost','B-ABRERA','ISAM-XD','2014-09-23 11:52:06',0,2,1),(936,'2014-09-23 11:52:06','localhost','SANTSADURNIDANOIA','ISAM-FD','2014-09-23 11:52:06',0,1,1),(937,'2014-09-23 11:52:06','localhost','ALBERCA','ISAM-FD','2014-09-23 11:52:06',0,1,1);
/*!40000 ALTER TABLE `Node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DSLAMBOProcess_variableList`
--

DROP TABLE IF EXISTS `DSLAMBOProcess_variableList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DSLAMBOProcess_variableList` (
  `DSLAMBOProcess_processId` bigint(20) NOT NULL,
  `variableName` varchar(255) DEFAULT NULL,
  `variableScope` int(11) NOT NULL,
  `variableType` int(11) NOT NULL,
  `variableValue` varchar(255) DEFAULT NULL,
  KEY `FK94D986C09230AF52` (`DSLAMBOProcess_processId`),
  CONSTRAINT `FK94D986C09230AF52` FOREIGN KEY (`DSLAMBOProcess_processId`) REFERENCES `Process` (`processId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DSLAMBOProcess_variableList`
--

LOCK TABLES `DSLAMBOProcess_variableList` WRITE;
/*!40000 ALTER TABLE `DSLAMBOProcess_variableList` DISABLE KEYS */;
INSERT INTO `DSLAMBOProcess_variableList` VALUES (2,'limit',0,1,'4'),(1,'limit',0,1,'3');
/*!40000 ALTER TABLE `DSLAMBOProcess_variableList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MachineProperties`
--

DROP TABLE IF EXISTS `MachineProperties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MachineProperties` (
  `machinePropertiesId` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `machineDescription` varchar(255) DEFAULT NULL,
  `machineName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `promptRegEx` varchar(255) DEFAULT NULL,
  `protocolType` int(11) NOT NULL,
  `saveTime` datetime DEFAULT NULL,
  `timeout` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `closeConnectionScriptId` bigint(20) DEFAULT NULL,
  `initConnectionScriptId` bigint(20) DEFAULT NULL,
  `preferencesId` bigint(20) DEFAULT NULL,
  `passwordPromptRegEx` varchar(255) DEFAULT NULL,
  `usernamePromptRegEx` varchar(255) DEFAULT NULL,
  `rollbackConditionRegEx` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`machinePropertiesId`),
  KEY `FK5D3D80BA40995195` (`preferencesId`),
  KEY `FK5D3D80BAE9D60E11` (`initConnectionScriptId`),
  KEY `FK5D3D80BA3A851CF9` (`closeConnectionScriptId`),
  CONSTRAINT `FK5D3D80BA3A851CF9` FOREIGN KEY (`closeConnectionScriptId`) REFERENCES `File` (`fileId`),
  CONSTRAINT `FK5D3D80BA40995195` FOREIGN KEY (`preferencesId`) REFERENCES `Preferences` (`preferencesId`),
  CONSTRAINT `FK5D3D80BAE9D60E11` FOREIGN KEY (`initConnectionScriptId`) REFERENCES `File` (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MachineProperties`
--

LOCK TABLES `MachineProperties` WRITE;
/*!40000 ALTER TABLE `MachineProperties` DISABLE KEYS */;
INSERT INTO `MachineProperties` VALUES (1,'2014-07-21 13:01:11',NULL,'ISAM-FD','.o7ws5mb','gaelhosteneo@gaelhosteneo-K53SD:.+\\$',4,'2014-07-30 17:58:26',30000,'gaelhosteneo',50,3,4,1,'','','command not found'),(2,'2014-07-21 13:12:35',NULL,'ISAM-XD','.o7ws5mb','gaelhosteneo@gaelhosteneo-K53SD:.+\\$',4,'2014-07-30 17:58:26',30000,'gaelhosteneo',47,5,6,1,'','','command not found');
/*!40000 ALTER TABLE `MachineProperties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Process`
--

DROP TABLE IF EXISTS `Process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Process` (
  `processId` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `processName` varchar(255) DEFAULT NULL,
  `savedTime` datetime DEFAULT NULL,
  `synchronous` bit(1) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`processId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Process`
--

LOCK TABLES `Process` WRITE;
/*!40000 ALTER TABLE `Process` DISABLE KEYS */;
INSERT INTO `Process` VALUES (1,'2014-07-21 12:54:56','Process','2014-09-23 11:52:06','',206),(2,'2014-07-22 17:31:38','Process','2014-09-22 10:41:22','',1390);
/*!40000 ALTER TABLE `Process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Preferences`
--

DROP TABLE IF EXISTS `Preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Preferences` (
  `preferencesId` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `savedTime` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `userPreferencesId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`preferencesId`),
  KEY `FKDA0486D8E7DD1DF` (`userPreferencesId`),
  CONSTRAINT `FKDA0486D8E7DD1DF` FOREIGN KEY (`userPreferencesId`) REFERENCES `UserPreferences` (`userPreferencesId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Preferences`
--

LOCK TABLES `Preferences` WRITE;
/*!40000 ALTER TABLE `Preferences` DISABLE KEYS */;
INSERT INTO `Preferences` VALUES (1,'2014-07-17 12:12:12','2014-07-30 17:58:26',51,1);
/*!40000 ALTER TABLE `Preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Project`
--

DROP TABLE IF EXISTS `Project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Project` (
  `projectId` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `machineType` int(11) NOT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `savedTime` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `mainScriptId` bigint(20) DEFAULT NULL,
  `processId` bigint(20) DEFAULT NULL,
  `rollBackScriptId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`projectId`),
  KEY `FK50C8E2F93088E707` (`rollBackScriptId`),
  KEY `FK50C8E2F9A9C148DC` (`mainScriptId`),
  KEY `FK50C8E2F98BE06F18` (`processId`),
  CONSTRAINT `FK50C8E2F93088E707` FOREIGN KEY (`rollBackScriptId`) REFERENCES `File` (`fileId`),
  CONSTRAINT `FK50C8E2F98BE06F18` FOREIGN KEY (`processId`) REFERENCES `Process` (`processId`),
  CONSTRAINT `FK50C8E2F9A9C148DC` FOREIGN KEY (`mainScriptId`) REFERENCES `File` (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Project`
--

LOCK TABLES `Project` WRITE;
/*!40000 ALTER TABLE `Project` DISABLE KEYS */;
INSERT INTO `Project` VALUES (1,'2014-07-21 12:54:56',0,'NewVLAN','2014-09-23 11:52:08',20,1,1,2),(2,'2014-07-22 17:31:38',0,'UnixCMD','2014-09-22 10:41:23',102,15,2,16);
/*!40000 ALTER TABLE `Project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CRONIOBOMachineProperties_connectionVariables`
--

DROP TABLE IF EXISTS `CRONIOBOMachineProperties_connectionVariables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CRONIOBOMachineProperties_connectionVariables` (
  `CRONIOBOMachineProperties_machinePropertiesId` bigint(20) NOT NULL,
  `variableName` varchar(255) DEFAULT NULL,
  `variableScope` int(11) NOT NULL,
  `variableType` int(11) NOT NULL,
  `variableValue` varchar(255) DEFAULT NULL,
  KEY `FKCCB1653B5E4B353` (`CRONIOBOMachineProperties_machinePropertiesId`),
  CONSTRAINT `FKCCB1653B5E4B353` FOREIGN KEY (`CRONIOBOMachineProperties_machinePropertiesId`) REFERENCES `MachineProperties` (`machinePropertiesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CRONIOBOMachineProperties_connectionVariables`
--

LOCK TABLES `CRONIOBOMachineProperties_connectionVariables` WRITE;
/*!40000 ALTER TABLE `CRONIOBOMachineProperties_connectionVariables` DISABLE KEYS */;
INSERT INTO `CRONIOBOMachineProperties_connectionVariables` VALUES (2,'promptRegEx',3,2,' \\s>\\s'),(2,'pwdPrompt',3,2,'Password:\\s'),(2,'userPrompt',3,2,'Login:\\s'),(1,'promptRegEx',3,2,'\\s>\\s'),(1,'pwdPrompt',3,2,'Password:\\s'),(1,'userPrompt',3,2,'Login:\\s');
/*!40000 ALTER TABLE `CRONIOBOMachineProperties_connectionVariables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DSLAMBOProcess_scheduleList`
--

DROP TABLE IF EXISTS `DSLAMBOProcess_scheduleList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DSLAMBOProcess_scheduleList` (
  `DSLAMBOProcess_processId` bigint(20) NOT NULL,
  `scheduleList` datetime DEFAULT NULL,
  KEY `FKF057C15B9230AF52` (`DSLAMBOProcess_processId`),
  CONSTRAINT `FKF057C15B9230AF52` FOREIGN KEY (`DSLAMBOProcess_processId`) REFERENCES `Process` (`processId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DSLAMBOProcess_scheduleList`
--

LOCK TABLES `DSLAMBOProcess_scheduleList` WRITE;
/*!40000 ALTER TABLE `DSLAMBOProcess_scheduleList` DISABLE KEYS */;
INSERT INTO `DSLAMBOProcess_scheduleList` VALUES (1,'2014-07-30 13:00:00'),(1,'2014-08-12 13:00:00');
/*!40000 ALTER TABLE `DSLAMBOProcess_scheduleList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `lastSessionId` varchar(255) DEFAULT NULL,
  `pendingAccept` bit(1) NOT NULL,
  `resetId` varchar(255) DEFAULT NULL,
  `rolType` int(11) NOT NULL,
  `socialProviderId` varchar(255) DEFAULT NULL,
  `socialValidatedId` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `preferencesId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK285FEB40995195` (`preferencesId`),
  CONSTRAINT `FK285FEB40995195` FOREIGN KEY (`preferencesId`) REFERENCES `Preferences` (`preferencesId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'dummy@imotionfactory.com','3cd56495dc4b99cd0d21b946618893de','2014-07-10 12:12:12','sid1','\0','',1,'','','dummy@imotionfactory.com',4,1);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `File`
--

DROP TABLE IF EXISTS `File`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `File` (
  `fileId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `contentType` int(11) NOT NULL,
  `creationTime` datetime DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `savedTime` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `compiledContent` text,
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `File`
--

LOCK TABLES `File` WRITE;
/*!40000 ALTER TABLE `File` DISABLE KEYS */;
INSERT INTO `File` VALUES (1,'for $i in (1 .. #limit) {\n   > show environment #id $i;\n}\n\nif (#continue == 1) {\n    > configure environment  #id interface #card;\n}\n\n$next = 1;\n$counter = 0;\nwhile ($next == 1) {\n    > configure interface #card;\n    if ($counter == 10) {\n      $next = 0;\n    } else {\n         $counter = $counter + 1;  \n    }\n}\n\n$list = {\"alpha\", \"beta\", \"gamma\"};\n\nforeach ($item : $list) {\n    > configure cli $item;\n}',0,'2014-07-21 12:54:56','Main Script','2014-09-23 11:52:06',21,'for $i in (1 .. #limit) {\n> \"show\" .\" \". \"environment\" .\" \". #id .\" \". $i;\n}\n\nif (#continue == 1) {\n> \"configure\" .\" \". \"environment\" .\" \". #id .\" \". \"interface\" .\" \". #card;\n}\n\n$next = 1 ;\n$counter = 0 ;\nwhile ($next == 1) {\n> \"configure\" .\" \". \"interface\" .\" \". #card;\nif ($counter == 10) {\n$next = 0 ;\n} else {\n$counter = $counter + 1 ;\n}\n}\n\n$list = {\"alpha\", \"beta\", \"gamma\"} ;\n\nforeach ($item : $list) {\n> \"configure\" .\" \". \"cli\" .\" \". $item;\n}\n'),(2,'',0,'2014-07-21 12:54:56','Rollback Script','2014-09-23 11:52:06',20,''),(3,'>> \"exit\";',0,'2014-07-21 13:01:11','Disconnection Script','2014-07-30 17:58:26',44,'>> \"exit\";\n'),(4,'\n\n\n',0,'2014-07-21 13:01:11','Connection Script','2014-07-30 17:58:26',44,''),(5,'>> \"exit\";',0,'2014-07-21 13:12:35','Disconnection Script','2014-07-30 17:58:26',44,'>> \"exit\";\n'),(6,'',0,'2014-07-21 13:12:35','Connection Script','2014-07-30 17:58:26',44,''),(7,NULL,0,'2014-07-21 13:29:22','Disconnection Script','2014-07-21 13:29:22',0,NULL),(8,NULL,0,'2014-07-21 13:29:22','Connection Script','2014-07-21 13:29:22',0,NULL),(9,NULL,0,'2014-07-21 13:40:47','Disconnection Script','2014-07-21 13:40:47',0,NULL),(10,NULL,0,'2014-07-21 13:40:47','Connection Script','2014-07-21 13:40:47',0,NULL),(11,NULL,0,'2014-07-21 13:52:50','Disconnection Script','2014-07-21 13:52:50',0,NULL),(12,NULL,0,'2014-07-21 13:52:50','Connection Script','2014-07-21 13:52:50',0,NULL),(13,NULL,0,'2014-07-21 13:58:00','Disconnection Script','2014-07-21 13:58:00',0,NULL),(14,NULL,0,'2014-07-21 13:58:00','Connection Script','2014-07-21 13:58:00',0,NULL),(15,'> pwd; \n \n//tag \"change dir\";\n//$next = 1;\n//while ($next == 1) {\n//    > cd .. ;\n//    if (@PROMPT == \"[imotion@shell /]$ \") {\n//        $next = 0;\n//    }\n//}\ntag \"echo it\";\nfor $i in (1 .. #limit) {\n    > echo \"iteracion del for \" $i;\n}\n\ntag \"ifcfg\";\n> ifconfig;\n//match \"(.)+(:\\sflags)\" > @RESPONSE;\n//$interface = @MATCH[1];\n$interface = \"eth0\";\n\ntag \"ifup\";\n> ifconfig $interface up;\n//match \"(.+:\\s)(.+)\";\n//$error = @MATCH[2];\n$error = \"permission denied\";\nif ($error == \"permission denied\") {\n    rb \"noperm\";\n}\n\n//tag \"echo continue\";\n//if (#continue == 1) {\n//     > echo \"continue is true\";\n//}\n\ntag \"command list\";\n$cmdList = {\"pwd \", \"history\", \"echo hola\"};\nforeach ($cmdItem : $cmdList) {\n    > $cmdItem;\n}\n',0,'2014-07-22 17:31:38','Main Script','2014-09-22 10:41:22',108,'> \"pwd\";\n\n//tag \"change dir\" ;\n//$next = 1 ;\n//while ($next == 1) {\n// > cd .. ;\n// if (@PROMPT == \"[imotion@shell /]$ \") {\n// $next = 0 ;\n// }\n//}\ntag \"echo it\" ;\nfor $i in (1 .. #limit) {\n> \"echo\" .\" \". \"iteracion del for \" .\" \". $i;\n}\n\ntag \"ifcfg\" ;\n> \"ifconfig\";\n//match \"(.)+(:\\sflags)\" > @RESPONSE ;\n//$interface = @MATCH[1] ;\n$interface = \"eth0\" ;\n\ntag \"ifup\" ;\n> \"ifconfig\" .\" \". $interface .\" \". \"up\";\n//match \"(.+:\\s)(.+)\" ;\n//$error = @MATCH[2] ;\n$error = \"permission denied\" ;\nif ($error == \"permission denied\") {\nrb \"noperm\" ;\n}\n\n//tag \"echo continue\" ;\n//if (#continue == 1) {\n// > echo \"continue is true\" ;\n//}\n\ntag \"command list\" ;\n$cmdList = {\"pwd \", \"history\", \"echo hola\"} ;\nforeach ($cmdItem : $cmdList) {\n> $cmdItem;\n}\n'),(16,'> echo \"probando rollback\";\n\nrbCase \"noperm\" {\n   > echo \"entró en noperm\";\n} rbDefault {\n   > echo \"entró error generico\";\n} \n',0,'2014-07-22 17:31:38','Rollback Script','2014-09-22 10:41:22',102,'> \"echo\" .\" \". \"probando rollback\";\n\nrbCase \"noperm\" {\n> \"echo\" .\" \". \"entró en noperm\";\n} rbDefault {\n> \"echo\" .\" \". \"entró error generico\";\n}\n');
/*!40000 ALTER TABLE `File` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserPreferences`
--

DROP TABLE IF EXISTS `UserPreferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserPreferences` (
  `userPreferencesId` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `downTime` int(11) NOT NULL,
  `savedTime` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userPreferencesId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserPreferences`
--

LOCK TABLES `UserPreferences` WRITE;
/*!40000 ALTER TABLE `UserPreferences` DISABLE KEYS */;
INSERT INTO `UserPreferences` VALUES (1,'2014-07-30 09:22:22',100,'2014-07-30 17:58:26',13);
/*!40000 ALTER TABLE `UserPreferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Execution`
--

DROP TABLE IF EXISTS `Execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Execution` (
  `executionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `destinationLogs` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `projectId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`executionId`),
  KEY `FK366B2AF897CE45EC` (`projectId`),
  CONSTRAINT `FK366B2AF897CE45EC` FOREIGN KEY (`projectId`) REFERENCES `Project` (`projectId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Execution`
--

LOCK TABLES `Execution` WRITE;
/*!40000 ALTER TABLE `Execution` DISABLE KEYS */;
INSERT INTO `Execution` VALUES (3,'2014-09-19 09:27:49',NULL,0,2);
/*!40000 ALTER TABLE `Execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CRONIOBONode_variableList`
--

DROP TABLE IF EXISTS `CRONIOBONode_variableList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CRONIOBONode_variableList` (
  `CRONIOBONode_nodeId` bigint(20) NOT NULL,
  `variableName` varchar(255) DEFAULT NULL,
  `variableScope` int(11) NOT NULL,
  `variableType` int(11) NOT NULL,
  `variableValue` varchar(255) DEFAULT NULL,
  KEY `FKF94BDED656E26611` (`CRONIOBONode_nodeId`),
  CONSTRAINT `FKF94BDED656E26611` FOREIGN KEY (`CRONIOBONode_nodeId`) REFERENCES `Node` (`nodeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CRONIOBONode_variableList`
--

LOCK TABLES `CRONIOBONode_variableList` WRITE;
/*!40000 ALTER TABLE `CRONIOBONode_variableList` DISABLE KEYS */;
INSERT INTO `CRONIOBONode_variableList` VALUES (921,'continue',2,1,'0'),(922,'continue',2,1,'1'),(923,'continue',2,1,'0'),(924,'continue',2,1,'1'),(925,'continue',2,1,'0'),(926,'continue',2,1,'0'),(927,'continue',2,1,'1'),(928,'continue',2,1,'1'),(930,'www',2,2,'www');
/*!40000 ALTER TABLE `CRONIOBONode_variableList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserProjects`
--

DROP TABLE IF EXISTS `UserProjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserProjects` (
  `userId` bigint(20) NOT NULL,
  `projectId` bigint(20) NOT NULL,
  KEY `FKA07EC18597CE45EC` (`projectId`),
  KEY `FKA07EC1858E6A60A7` (`userId`),
  CONSTRAINT `FKA07EC1858E6A60A7` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`),
  CONSTRAINT `FKA07EC18597CE45EC` FOREIGN KEY (`projectId`) REFERENCES `Project` (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserProjects`
--

LOCK TABLES `UserProjects` WRITE;
/*!40000 ALTER TABLE `UserProjects` DISABLE KEYS */;
INSERT INTO `UserProjects` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `UserProjects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-24 15:57:43

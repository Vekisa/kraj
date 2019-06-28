-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: xmlb
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `certificate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `common_name` varchar(30) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `is_leaf` bit(1) DEFAULT NULL,
  `locality` varchar(30) DEFAULT NULL,
  `organization_unit` varchar(30) DEFAULT NULL,
  `revoked` bit(1) DEFAULT NULL,
  `serial_number` varchar(255) DEFAULT NULL,
  `signed_by_alias` varchar(191) DEFAULT NULL,
  `signed_by_serial_number` varchar(191) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `communication_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfiq6r781sju9p519d7amc6a1n` (`communication_id`),
  KEY `FKocv8s7xaflei9onb6m5b9684p` (`company_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (1,'root',NULL,NULL,'2019-08-31 02:00:00',_binary '\0',NULL,NULL,_binary '\0','1','root','1','2019-03-22 01:00:00',NULL,NULL,1),(7,'megatravelagent','megatravelagent','Serbia','2019-07-31 19:38:00',_binary '\0','Novi Sad','Megatravel Agent',_binary '\0','1561750623727','megatravel','1','2019-06-28 19:38:00','Serbia',NULL,6),(6,'megatravel','megatravel','Serbia','2019-08-01 16:44:00',_binary '\0','Novi Sad','Megatravel',_binary '\0','1561740150248','root','1','2019-06-28 16:44:00','Serbia',NULL,6),(8,'megatravelbackend','megatravelbackend','Serbia','2019-07-31 19:43:00',_binary '\0','Novi Sad','Megatravel backend',_binary '\0','1561750879896','megatravel','1','2019-06-28 19:43:00','Serbia',NULL,6);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `communication`
--

DROP TABLE IF EXISTS `communication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `communication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first` varchar(30) DEFAULT NULL,
  `second` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `communication`
--

LOCK TABLES `communication` WRITE;
/*!40000 ALTER TABLE `communication` DISABLE KEYS */;
/*!40000 ALTER TABLE `communication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'keystores/root.p12','root'),(6,'keystores/Megatravel.p12','Megatravel');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endpoint`
--

DROP TABLE IF EXISTS `endpoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `endpoint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `method` varchar(191) DEFAULT NULL,
  `url` varchar(191) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=550 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endpoint`
--

LOCK TABLES `endpoint` WRITE;
/*!40000 ALTER TABLE `endpoint` DISABLE KEYS */;
INSERT INTO `endpoint` VALUES (549,'','/error'),(548,'','/error'),(547,'','/swagger-resources'),(546,'','/configuration/ui'),(545,'','/configuration/security'),(544,'GET','/v2/api-docs'),(543,'DELETE','/users/removeGroupFromUser'),(542,'POST','/users/addGroupToUser'),(541,'GET','/users'),(540,'DELETE','/users/removeRoleFromUser'),(539,'POST','/users/addRoleToUser'),(538,'POST','/users/{id}/disable'),(537,'POST','/users/{id}/enable'),(536,'GET','/users/{text}/search'),(535,'GET','/test/{id}'),(534,'GET','/roles/allMissingRolesUser'),(533,'GET','/roles/allRolesAddedUser'),(532,'GET','/roles/allMissing'),(531,'GET','/roles/allRolesAdded'),(530,'DELETE','/roles/removeRoleFromGroup'),(529,'POST','/roles/remove_role_from_user/{role_id}/{user_id}'),(528,'GET','/roles/all'),(527,'POST','/roles/add_role_to_user/{user_id}/{role_id}'),(526,'DELETE','/roles/delete'),(525,'DELETE','/roles/deleteEndPointFromRole'),(524,'POST','/roles/addEndPointToRole'),(523,'POST','/roles/add'),(522,'POST','/roles/edit'),(521,'GET','/groups/allUserMissingGroups'),(520,'GET','/groups/allUserGroups'),(519,'POST','/groups/addRoleToGroup'),(518,'GET','/groups/users_from_group/{id_group}'),(517,'GET','/groups/all_groups'),(516,'POST','/groups/removeUser'),(515,'POST','/groups/addUser'),(514,'PUT','/groups'),(513,'DELETE','/groups'),(512,'POST','/groups/add'),(511,'GET','/endpoint/roleMissing'),(510,'GET','/endpoint/role'),(509,'GET','/endpoint/all'),(508,'POST','/companies/addToUser'),(507,'GET','/companies/allCompanies'),(506,'GET','/communication/{serial_number}'),(505,'POST','/communication/{first_sn}/{second_sn}/create'),(504,'POST','/communication/{first_alias}/{second_alias}/disable'),(503,'GET','/certificates/allRevoke'),(502,'POST','/certificates/checkIfValid'),(501,'POST','/certificates/revoke'),(500,'GET','/certificates/all_without_root'),(499,'GET','/certificates/all'),(498,'GET','/certificates/all_without_leafs'),(497,'POST','/certificates/create_new_certificate'),(496,'POST','/certificates/createSS'),(495,'GET','/certificates/getCertificate'),(494,'GET','/certificates/{alias}/search/{leafs}/{root}'),(493,'','/api/auth/checkUser'),(492,'','/api/auth/checkIP'),(491,'','/api/auth/confirmReg'),(490,'POST','/api/auth/signup'),(489,'POST','/api/auth/signin');
/*!40000 ALTER TABLE `endpoint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endpoint_roles`
--

DROP TABLE IF EXISTS `endpoint_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `endpoint_roles` (
  `end_points_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FK3plutdw26d8jn4yymtvjmjuu5` (`roles_id`),
  KEY `FKc8ku8lrkjvjen1fk3x0m9d4iq` (`end_points_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endpoint_roles`
--

LOCK TABLES `endpoint_roles` WRITE;
/*!40000 ALTER TABLE `endpoint_roles` DISABLE KEYS */;
INSERT INTO `endpoint_roles` VALUES (495,2),(497,2),(498,2),(501,2),(502,2),(503,2),(504,2),(505,2),(506,2),(494,2),(500,2),(499,2);
/*!40000 ALTER TABLE `endpoint_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupt`
--

DROP TABLE IF EXISTS `groupt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `groupt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupt`
--

LOCK TABLES `groupt` WRITE;
/*!40000 ALTER TABLE `groupt` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupt_roles`
--

DROP TABLE IF EXISTS `groupt_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `groupt_roles` (
  `groups_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKed0r4q0atmmyn5fwvgov1y81t` (`roles_id`),
  KEY `FK5jwvmjikyfqb90udhudflwla7` (`groups_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupt_roles`
--

LOCK TABLES `groupt_roles` WRITE;
/*!40000 ALTER TABLE `groupt_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupt_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupt_users`
--

DROP TABLE IF EXISTS `groupt_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `groupt_users` (
  `group_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  KEY `FKfg6allumnn2gew2224tjex6qd` (`users_id`),
  KEY `FKn38q2p510rbw21r7n1os9uspa` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupt_users`
--

LOCK TABLES `groupt_users` WRITE;
/*!40000 ALTER TABLE `groupt_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupt_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_MAIN_ADMIN'),(2,'ROLE_ADMIN'),(3,'ROLE_REG');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_users`
--

DROP TABLE IF EXISTS `roles_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles_users` (
  `roles_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  KEY `FK4glr8k8swy5nti6n5x35ofucj` (`users_id`),
  KEY `FK2mck5s7km22t2on8h2jpn44xq` (`roles_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_users`
--

LOCK TABLES `roles_users` WRITE;
/*!40000 ALTER TABLE `roles_users` DISABLE KEYS */;
INSERT INTO `roles_users` VALUES (1,1),(3,2),(2,2);
/*!40000 ALTER TABLE `roles_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_block` datetime DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `is_verified` bit(1) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  `numf` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `verification_token_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbwv4uspmyi7xqjwcrgxow361t` (`company_id`),
  KEY `FKanlq7683oha41nbs60oo1mtag` (`verification_token_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'mailad@mail.com',_binary '','Admin',_binary '','Admin',NULL,0,'1000:5b4240333736646261:d1a3c576fa4b36b417878e6f2dc819192e024c8bfce6b4b5d3526039d755994a017c26656d903dc7beba73b1ce857698265c8421020442799c72995b728043be','admin',1,NULL),(2,NULL,'b2372946@urhen.com',_binary '','Marko',_binary '','Markovic',NULL,0,'1000:5b42403737306261366537:1e48032bad81359d9a1799462ddc3793cadb9ad458c94cbc332235b67919aa96f9606210d41c10ab642b396c89661920e6a64a9063368c79fa76b29dc06d299f','marko',6,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `verification_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime DEFAULT NULL,
  `verification_token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3asw9wnv76uxu3kr1ekq4i1ld` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
INSERT INTO `verification_token` VALUES (1,'2019-06-29 16:45:17','f7424c5f-01f4-41d1-8e55-1ca7934225e7',2);
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-28 22:15:07

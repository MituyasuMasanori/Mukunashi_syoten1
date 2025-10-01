-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: basic
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'山田太郎','東京都東京市南町1-1-1','03-9999-XXXX','yamada@example.com'),(2,'鈴木浩二','神奈川県松戸市金町2-4-5','04-1111-XXXX','suzuki@example.com'),(3,'井上春子','埼玉県浦安市本町4-7-9','04-2222-XXXX','inoue@example.com'),(4,'佐々木のぞみ','千葉県横浜市東町1-2-3','04-3333-XXXX','sasaki@example.com'),(5,'川本建三','埼玉県本庄市春日町2-4-3','04-4444-XXXX','kawamoto@example.com'),(6,'佐藤洋一','東京都南東京市白町3-1-2','03-8888-XXXX','satou@example.com'),(7,'田中洋子','茨城県武蔵野市東町4-1-2','04-5555-XXXX','tanaka@example.com'),(8,'中川雄一','茨城県横浜市芳野町2-3-2','04-6666-XXXX','nakagawa@example.com'),(9,'日村和也','東京都千葉市上町1-4-3','03-7777-XXXX','himura@example.com'),(10,'松田幸子','東京都東京市北町2-2-3','03-6666-XXXX','matsuda@example.com');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `isbn` char(17) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  `published` date DEFAULT NULL,
  `zaiko` int DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('978-4-7980-5759-0','はじめてのAndroidアプリ開発',3200,'秀和システム','2019-08-10',99),('978-4-7980-5804-7','はじめてのASP.NET Webフォームアプリ開発',3200,'秀和システム','2019-10-12',99),('978-4-7981-5112-0','独習Java新版',3150,'翔泳社','2019-05-15',100),('978-4-7981-5757-3','JavaScript逆引きレシピ',2730,'翔泳社','2018-10-15',98),('978-4-7981-6044-3','Androidアプリ開発の教科書',2850,'翔泳社','2019-07-10',100),('978-4-7981-6365-9','独習ASP.NET Webフォーム',3800,'翔泳社','2020-02-17',100),('978-4-8026-1226-5','SQLデータ分析・活用入門',2600,'ソシム','2019-09-12',100),('978-4-8156-0182-9','これからはじめるVue.js実践入門',3990,'SBクリエイティブ','2019-08-22',100),('978-4-8222-5391-2','iphoneアプリ超入門',2200,'日経BP','2020-02-28',98),('978-4-8222-8653-8','基礎からしっかり学ぶc#の教科書',2900,'日経BP','2019-12-20',100);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `cName` varchar(20) DEFAULT NULL,
  `cId` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES ('札幌',1),('羽田',2),('福岡',3),('那覇',4);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cp_master`
--

DROP TABLE IF EXISTS `cp_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cp_master` (
  `cId2` char(2) DEFAULT NULL,
  `pId` int DEFAULT NULL,
  `rDate` date DEFAULT NULL,
  `r08` tinyint(1) DEFAULT '1',
  `r09` tinyint(1) DEFAULT '1',
  `r10` tinyint(1) DEFAULT '1',
  `r11` tinyint(1) DEFAULT '1',
  `r12` tinyint(1) DEFAULT '1',
  `r13` tinyint(1) DEFAULT '1',
  `r14` tinyint(1) DEFAULT '1',
  `r15` tinyint(1) DEFAULT '1',
  `r16` tinyint(1) DEFAULT '1',
  `r17` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cp_master`
--

LOCK TABLES `cp_master` WRITE;
/*!40000 ALTER TABLE `cp_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `cp_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fac_facility`
--

DROP TABLE IF EXISTS `fac_facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fac_facility` (
  `fId` int NOT NULL,
  `fNam` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`fId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fac_facility`
--

LOCK TABLES `fac_facility` WRITE;
/*!40000 ALTER TABLE `fac_facility` DISABLE KEYS */;
INSERT INTO `fac_facility` VALUES (1,'ミーティングコーナー１'),(2,'1204会議室'),(3,'1205会議室'),(4,'1206会議室'),(5,'外来応接室');
/*!40000 ALTER TABLE `fac_facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fac_master`
--

DROP TABLE IF EXISTS `fac_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fac_master` (
  `fId` int NOT NULL,
  `rDate` date NOT NULL,
  `r08` varchar(10) DEFAULT NULL,
  `r09` varchar(10) DEFAULT NULL,
  `r10` varchar(10) DEFAULT NULL,
  `r11` varchar(10) DEFAULT NULL,
  `r12` varchar(10) DEFAULT NULL,
  `r13` varchar(10) DEFAULT NULL,
  `r14` varchar(10) DEFAULT NULL,
  `r15` varchar(10) DEFAULT NULL,
  `r16` varchar(10) DEFAULT NULL,
  `r17` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`fId`,`rDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fac_master`
--

LOCK TABLES `fac_master` WRITE;
/*!40000 ALTER TABLE `fac_master` DISABLE KEYS */;
INSERT INTO `fac_master` VALUES (1,'2024-11-20','','','','','','','','','',''),(1,'2024-11-25','khonda','','','','','','','','',''),(1,'2024-11-26','khonda','tkawamura','','','','','','','',''),(1,'2024-12-04','','','','','','','','','','khonda'),(2,'2024-11-20','','','','','','','','','',''),(2,'2024-11-25','','tkawamura','','','','','','','',''),(2,'2024-11-26','','','','','','','','','',''),(2,'2024-12-04','','','','','','','','','khonda',''),(3,'2024-11-20','','','','','','','','','',''),(3,'2024-11-25','','','','','','','','','',''),(3,'2024-11-26','','','','','','','','','',''),(3,'2024-12-04','','','','','','','','','',''),(4,'2024-11-20','','','','','','','','','',''),(4,'2024-11-25','','','','','','','','','',''),(4,'2024-11-26','','','','','','','','','',''),(4,'2024-12-04','','','','','','','','','',''),(5,'2024-11-20','','','','','','','','','',''),(5,'2024-11-25','','','','','','','','','',''),(5,'2024-11-26','','','','tkawamura','','','','','',''),(5,'2024-12-04','','','','','','','','','','');
/*!40000 ALTER TABLE `fac_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gakuseihyo`
--

DROP TABLE IF EXISTS `gakuseihyo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gakuseihyo` (
  `gakuseibango` varchar(10) NOT NULL,
  `shimei` varchar(30) DEFAULT NULL,
  `seibetu` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`gakuseibango`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gakuseihyo`
--

LOCK TABLES `gakuseihyo` WRITE;
/*!40000 ALTER TABLE `gakuseihyo` DISABLE KEYS */;
INSERT INTO `gakuseihyo` VALUES ('6724','山本　一希','男'),('6725','本山　十吾','男'),('6816','山田　百音','女'),('6817','山本　千代','女');
/*!40000 ALTER TABLE `gakuseihyo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kamokuhyo`
--

DROP TABLE IF EXISTS `kamokuhyo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kamokuhyo` (
  `kamokubango` varchar(5) NOT NULL,
  `kamokumei` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`kamokubango`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kamokuhyo`
--

LOCK TABLES `kamokuhyo` WRITE;
/*!40000 ALTER TABLE `kamokuhyo` DISABLE KEYS */;
INSERT INTO `kamokuhyo` VALUES ('K11','英語Ⅰ'),('K12','英語Ⅱ'),('K21','数学');
/*!40000 ALTER TABLE `kamokuhyo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane`
--

DROP TABLE IF EXISTS `plane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane` (
  `pId` int DEFAULT NULL,
  `pName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane`
--

LOCK TABLES `plane` WRITE;
/*!40000 ALTER TABLE `plane` DISABLE KEYS */;
INSERT INTO `plane` VALUES (1,'ANA'),(2,'JAL');
/*!40000 ALTER TABLE `plane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shouts`
--

DROP TABLE IF EXISTS `shouts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shouts` (
  `shoutsId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `writing` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`shoutsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shouts`
--

LOCK TABLES `shouts` WRITE;
/*!40000 ALTER TABLE `shouts` DISABLE KEYS */;
/*!40000 ALTER TABLE `shouts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb`
--

DROP TABLE IF EXISTS `tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb` (
  `bang` char(4) DEFAULT NULL,
  `uria` int DEFAULT NULL,
  `tuki` int DEFAULT NULL,
  KEY `bang` (`bang`),
  CONSTRAINT `tb_ibfk_1` FOREIGN KEY (`bang`) REFERENCES `tb1` (`bang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb`
--

LOCK TABLES `tb` WRITE;
/*!40000 ALTER TABLE `tb` DISABLE KEYS */;
INSERT INTO `tb` VALUES ('A103',101,4),('A102',54,5),('A104',181,4),('A101',184,4),('A103',17,5),('A101',300,5),('A102',205,6),('A104',93,5),('A103',12,6);
/*!40000 ALTER TABLE `tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb1`
--

DROP TABLE IF EXISTS `tb1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb1` (
  `bang` char(4) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `tosi` int DEFAULT NULL,
  PRIMARY KEY (`bang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb1`
--

LOCK TABLES `tb1` WRITE;
/*!40000 ALTER TABLE `tb1` DISABLE KEYS */;
INSERT INTO `tb1` VALUES ('A101','佐藤',40),('A102','高橋',28),('A103','中川',20),('A104','渡辺',23),('A105','西沢',35);
/*!40000 ALTER TABLE `tb1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokutenhyo`
--

DROP TABLE IF EXISTS `tokutenhyo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokutenhyo` (
  `gakuseibango` varchar(10) NOT NULL,
  `kamokubango` varchar(5) NOT NULL,
  `tokuten` int DEFAULT NULL,
  `jukenbi` date DEFAULT NULL,
  PRIMARY KEY (`gakuseibango`,`kamokubango`),
  KEY `kamokubango` (`kamokubango`),
  CONSTRAINT `tokutenhyo_ibfk_1` FOREIGN KEY (`gakuseibango`) REFERENCES `gakuseihyo` (`gakuseibango`),
  CONSTRAINT `tokutenhyo_ibfk_2` FOREIGN KEY (`kamokubango`) REFERENCES `kamokuhyo` (`kamokubango`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokutenhyo`
--

LOCK TABLES `tokutenhyo` WRITE;
/*!40000 ALTER TABLE `tokutenhyo` DISABLE KEYS */;
INSERT INTO `tokutenhyo` VALUES ('6724','K11',65,'2024-10-20'),('6724','K21',85,'2024-10-21'),('6725','K21',60,'2024-10-21'),('6817','K11',85,'2024-10-20'),('6817','K12',90,'2024-10-20'),('6817','K21',95,'2024-10-21');
/*!40000 ALTER TABLE `tokutenhyo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_t`
--

DROP TABLE IF EXISTS `user_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_t` (
  `userId` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `userName` varchar(64) DEFAULT NULL,
  `userAddress` varchar(128) DEFAULT NULL,
  `userMail` varchar(32) DEFAULT NULL,
  `num` int DEFAULT NULL,
  `name1` varchar(64) DEFAULT NULL,
  `address1` varchar(128) DEFAULT NULL,
  `mail1` varchar(32) DEFAULT NULL,
  `name2` varchar(64) DEFAULT NULL,
  `address2` varchar(128) DEFAULT NULL,
  `mail2` varchar(32) DEFAULT NULL,
  `name3` varchar(64) DEFAULT NULL,
  `address3` varchar(128) DEFAULT NULL,
  `mail3` varchar(32) DEFAULT NULL,
  `name4` varchar(64) DEFAULT NULL,
  `address4` varchar(128) DEFAULT NULL,
  `mail4` varchar(32) DEFAULT NULL,
  `name5` varchar(64) DEFAULT NULL,
  `address5` varchar(128) DEFAULT NULL,
  `mail5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userId`,`password`),
  CONSTRAINT `user_t_chk_1` CHECK (((`num` >= 0) and (`num` <= 5)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_t`
--

LOCK TABLES `user_t` WRITE;
/*!40000 ALTER TABLE `user_t` DISABLE KEYS */;
INSERT INTO `user_t` VALUES ('userC','9012','光安聖徳','062-0921,北海道札幌市豊平区中の島1条8-3-18','sapporo@gmail.com',1,'佐藤洋一','103-8888,東京都南東京市白町3-2-1','satou@example.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('userD','3456','聖徳太子','123-4567,奈良県飛鳥市十七町1-1-1','test2@gmail.com',3,'山田太郎','103-9999,東京都東京市南町1-1-1','yamada@example.com','鈴木浩二','504-1111,神奈川県松戸市金町2-4-5','suzuki@example.com','井上春子','904-2222,埼玉県浦安市本町4-7-9','inoue@example.com','','','','','','');
/*!40000 ALTER TABLE `user_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `loginId` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `userName` varchar(64) DEFAULT NULL,
  `icon` varchar(128) DEFAULT NULL,
  `profile` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'yamada','pass1','山田　太郎','icon-user','はじめまして'),(2,'suzuki','pass2','鈴木　花子','icon-user-female','東京都在住です'),(3,'itou','pass3','伊藤　恵','icon-bell','趣味は読書'),(132,'tanaka','pass4','田中　純次','icon-user','こんにちは');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usr`
--

DROP TABLE IF EXISTS `usr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usr` (
  `uid` char(10) NOT NULL,
  `passwd` char(128) DEFAULT NULL,
  `unam` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usr`
--

LOCK TABLES `usr` WRITE;
/*!40000 ALTER TABLE `usr` DISABLE KEYS */;
INSERT INTO `usr` VALUES ('rsuzuki','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','鈴木良子'),('sfujii','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','藤井正一'),('tyamada','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','山田太郎');
/*!40000 ALTER TABLE `usr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usr_role`
--

DROP TABLE IF EXISTS `usr_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usr_role` (
  `uid` char(10) NOT NULL,
  `role` varchar(30) NOT NULL,
  PRIMARY KEY (`uid`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usr_role`
--

LOCK TABLES `usr_role` WRITE;
/*!40000 ALTER TABLE `usr_role` DISABLE KEYS */;
INSERT INTO `usr_role` VALUES ('rsuzuki','admin_gui'),('sfujii','manager_gui'),('tyamada','admin_gui'),('tyamada','manager_gui');
/*!40000 ALTER TABLE `usr_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 13:48:46

-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: makettdb
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `billdetail`
--

DROP TABLE IF EXISTS `billdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billdetail` (
  `id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int DEFAULT NULL,
  `proprice` int DEFAULT NULL,
  `idPro` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idBill` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPro` (`idPro`),
  KEY `idBill` (`idBill`),
  CONSTRAINT `billdetail_ibfk_1` FOREIGN KEY (`idPro`) REFERENCES `product` (`id`),
  CONSTRAINT `billdetail_ibfk_2` FOREIGN KEY (`idBill`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billdetail`
--

LOCK TABLES `billdetail` WRITE;
/*!40000 ALTER TABLE `billdetail` DISABLE KEYS */;
INSERT INTO `billdetail` VALUES ('094d03ba-6d7a-4c65-8406-1d5e64f473e6',1,10000,'1','af93db00-d6c4-41e9-9940-6823f52b680b'),('20019360-72e2-4f88-bd19-691d4e60fed3',1,10000,'1','54ba764a-292a-4a5b-bee9-4ad89473ab56'),('25bb2ae1-5362-4547-a188-9ff50d4238fa',16,160000,'1','f56bdb35-8052-4a03-91bb-490b726d05eb'),('8840acf5-990b-465f-8078-28ccab598cc9',1,10000,'1','bbb1bb88-eca7-4775-8ccf-2bae18a0d9e0'),('97cdc420-4620-4339-b80f-dff3a7fef3d7',100,1000000,'2','f56bdb35-8052-4a03-91bb-490b726d05eb'),('aa36887f-0008-4b62-9367-4da88bccf4ec',1,0,NULL,'54ba764a-292a-4a5b-bee9-4ad89473ab56'),('aee2231a-094b-4af8-aff4-625ffe40ae3e',2,10000,'1','ae668833-ab73-4258-8575-aa2811a3f4e7'),('c1f37d0d-904c-45ce-8aef-0ab0b8bf1189',1,10000,'1','6cb28a5d-fcd7-4d53-8092-b46a72746394'),('ccf0b971-e114-4cd3-9bb9-ae8da1d4b565',50,0,NULL,'f56bdb35-8052-4a03-91bb-490b726d05eb'),('f9b91f85-c9ad-4cf6-ba7b-8608ef9df25a',1,10000,'1','79e6f525-11fe-4da8-ae5d-773a55e86664');
/*!40000 ALTER TABLE `billdetail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-06 15:55:02

-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bookstore_db
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `alias` varchar(64) NOT NULL,
  `enabled` int NOT NULL,
  `parent_id` int DEFAULT NULL,
  `all_parent_ids` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jx1ptm0r46dop8v0o7nmgfbeq` (`alias`),
  UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`),
  KEY `FKsaok720gsu4u2wrgbk10b5n8d` (`parent_id`),
  CONSTRAINT `FKsaok720gsu4u2wrgbk10b5n8d` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Arts','Arts',1,NULL,NULL),(2,'Biographies','Biographies',1,NULL,NULL),(3,'Business','Business',1,NULL,NULL),(4,'Comics','Comics',1,NULL,NULL),(5,'Computers','Computers',1,NULL,NULL),(6,'Cooking','Cooking',1,NULL,NULL),(7,'Education','Education',1,NULL,NULL),(8,'Health','Health',1,NULL,NULL),(9,'History','History',1,NULL,NULL),(10,'Horror','Horror',1,NULL,NULL),(11,'Kids','Kids',1,NULL,NULL),(12,'Fiction','Fiction',1,NULL,NULL),(13,'Parenting','Parenting',1,NULL,NULL),(14,'Fantasy','Fantasy',1,NULL,NULL),(15,'Science','Science',1,NULL,NULL),(16,'Teen','Teen',1,NULL,NULL),(17,'Travel','Travel',1,NULL,NULL),(18,'Westerns','Westerns',1,NULL,NULL),(19,'Home','Home',1,NULL,NULL),(20,'Mystery','Mystery',1,NULL,NULL),(21,'Asian','Asian',1,6,NULL),(22,'Baking','Baking',1,6,NULL),(23,'BBQ','BBQ',1,6,NULL),(24,'Desserts','Desserts',1,6,NULL),(25,'Ghosts','Ghosts',1,10,NULL),(26,'Paranormal','Paranormal',1,10,NULL),(27,'Supernatural','Supernatural',1,10,NULL),(28,'Vampires','Vampires',1,10,NULL),(29,'Zombies','Zombies',1,10,NULL),(30,'Anatomy','Anatomy',1,15,NULL),(31,'Animals','Animals',1,15,NULL),(32,'Astronomy','Astronomy',1,15,NULL),(33,'Biology','Biology',1,15,NULL),(34,'Mathematics','Mathematics',1,15,NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'Andorra','AD'),(2,'United Arab Emirates','AE'),(4,'Antigua and Barbuda','AG'),(5,'Anguilla','AI'),(6,'Albania','AL'),(7,'Armenia','AM'),(8,'Netherlands Antilles','AN'),(9,'Angola','AO'),(10,'Antarctica','AQ'),(11,'Argentina','AR'),(12,'American Samoa','AS'),(13,'Austria','AT'),(14,'Australia','AU'),(15,'Aruba','AW'),(16,'Åland Islands','AX'),(17,'Azerbaijan','AZ'),(18,'Bosnia and Herzegovina','BA'),(19,'Barbados','BB'),(20,'Bangladesh','BD'),(21,'Belgium','BE'),(22,'Burkina Faso','BF'),(23,'Bulgaria','BG'),(24,'Bahrain','BH'),(25,'Burundi','BI'),(26,'Benin','BJ'),(27,'Saint Barthélemy','BL'),(28,'Bermuda','BM'),(29,'Brunei','BN'),(30,'Bolivia','BO'),(31,'Bonaire, Sint Eustatius and Saba','BQ'),(32,'Brazil','BR'),(33,'Bahamas','BS'),(34,'Bhutan','BT'),(35,'Bouvet Island','BV'),(36,'Botswana','BW'),(37,'Belarus','BY'),(38,'Belize','BZ'),(39,'Canada','CA'),(40,'Cocos Islands','CC'),(41,'The Democratic Republic Of Congo','CD'),(42,'Central African Republic','CF'),(43,'Congo','CG'),(44,'Switzerland','CH'),(45,'Côte d\'Ivoire','CI'),(46,'Cook Islands','CK'),(47,'Chile','CL'),(48,'Cameroon','CM'),(49,'China','CN'),(50,'Colombia','CO'),(51,'Costa Rica','CR'),(52,'Cuba','CU'),(53,'Cape Verde','CV'),(54,'Curaçao','CW'),(55,'Christmas Island','CX'),(56,'Cyprus','CY'),(57,'Czech Republic','CZ'),(58,'Germany','DE'),(59,'Djibouti','DJ'),(60,'Denmark','DK'),(61,'Dominica','DM'),(62,'Dominican Republic','DO'),(63,'Algeria','DZ'),(64,'Ecuador','EC'),(65,'Estonia','EE'),(66,'Egypt','EG'),(67,'Western Sahara','EH'),(68,'Eritrea','ER'),(69,'Spain','ES'),(70,'Ethiopia','ET'),(71,'Finland','FI'),(72,'Fiji','FJ'),(73,'Falkland Islands','FK'),(74,'Micronesia','FM'),(75,'Faroe Islands','FO'),(76,'France','FR'),(77,'Gabon','GA'),(78,'United Kingdom','GB'),(79,'Grenada','GD'),(80,'Georgia','GE'),(81,'French Guiana','GF'),(82,'Guernsey','GG'),(83,'Ghana','GH'),(84,'Gibraltar','GI'),(85,'Greenland','GL'),(86,'Gambia','GM'),(87,'Guinea','GN'),(88,'Guadeloupe','GP'),(89,'Equatorial Guinea','GQ'),(90,'Greece','GR'),(91,'South Georgia And The South Sandwich Islands','GS'),(92,'Guatemala','GT'),(93,'Guam','GU'),(94,'Guinea-Bissau','GW'),(95,'Guyana','GY'),(96,'Hong Kong','HK'),(97,'Heard Island And McDonald Islands','HM'),(98,'Honduras','HN'),(99,'Croatia','HR'),(100,'Haiti','HT'),(101,'Hungary','HU'),(102,'Indonesia','ID'),(103,'Ireland','IE'),(104,'Israel','IL'),(105,'Isle Of Man','IM'),(106,'India','IN'),(107,'British Indian Ocean Territory','IO'),(108,'Iraq','IQ'),(109,'Iran','IR'),(110,'Iceland','IS'),(111,'Italy','IT'),(112,'Jersey','JE'),(113,'Jamaica','JM'),(114,'Jordan','JO'),(115,'Japan','JP'),(116,'Kenya','KE'),(117,'Kyrgyzstan','KG'),(118,'Cambodia','KH'),(119,'Kiribati','KI'),(120,'Comoros','KM'),(121,'Saint Kitts And Nevis','KN'),(122,'North Korea','KP'),(123,'South Korea','KR'),(124,'Kuwait','KW'),(125,'Cayman Islands','KY'),(126,'Kazakhstan','KZ'),(127,'Laos','LA'),(128,'Lebanon','LB'),(129,'Saint Lucia','LC'),(130,'Liechtenstein','LI'),(131,'Sri Lanka','LK'),(132,'Liberia','LR'),(133,'Lesotho','LS'),(134,'Lithuania','LT'),(135,'Luxembourg','LU'),(136,'Latvia','LV'),(137,'Libya','LY'),(138,'Morocco','MA'),(139,'Monaco','MC'),(140,'Moldova','MD'),(141,'Montenegro','ME'),(142,'Saint Martin','MF'),(143,'Madagascar','MG'),(144,'Marshall Islands','MH'),(145,'Macedonia','MK'),(146,'Mali','ML'),(147,'Myanmar','MM'),(148,'Mongolia','MN'),(149,'Macao','MO'),(150,'Northern Mariana Islands','MP'),(151,'Martinique','MQ'),(152,'Mauritania','MR'),(153,'Montserrat','MS'),(154,'Malta','MT'),(155,'Mauritius','MU'),(156,'Maldives','MV'),(157,'Malawi','MW'),(158,'Mexico','MX'),(159,'Malaysia','MY'),(160,'Mozambique','MZ'),(161,'Namibia','NA'),(162,'New Caledonia','NC'),(163,'Niger','NE'),(164,'Norfolk Island','NF'),(165,'Nigeria','NG'),(166,'Nicaragua','NI'),(167,'Netherlands','NL'),(168,'Norway','NO'),(169,'Nepal','NP'),(170,'Nauru','NR'),(171,'Niue','NU'),(172,'New Zealand','NZ'),(173,'Oman','OM'),(174,'Panama','PA'),(175,'Peru','PE'),(176,'French Polynesia','PF'),(177,'Papua New Guinea','PG'),(178,'Philippines','PH'),(179,'Pakistan','PK'),(180,'Poland','PL'),(181,'Saint Pierre And Miquelon','PM'),(182,'Pitcairn','PN'),(183,'Puerto Rico','PR'),(184,'Palestine','PS'),(185,'Portugal','PT'),(186,'Palau','PW'),(187,'Paraguay','PY'),(188,'Qatar','QA'),(189,'Reunion','RE'),(190,'Romania','RO'),(191,'Serbia','RS'),(192,'Russia','RU'),(193,'Rwanda','RW'),(194,'Saudi Arabia','SA'),(195,'Solomon Islands','SB'),(196,'Seychelles','SC'),(197,'Sudan','SD'),(198,'Sweden','SE'),(199,'Singapore','SG'),(200,'Saint Helena','SH'),(201,'Slovenia','SI'),(202,'Svalbard And Jan Mayen','SJ'),(203,'Slovakia','SK'),(204,'Sierra Leone','SL'),(205,'San Marino','SM'),(206,'Senegal','SN'),(207,'Somalia','SO'),(208,'Suriname','SR'),(209,'South Sudan','SS'),(210,'Sao Tome And Principe','ST'),(211,'El Salvador','SV'),(212,'Sint Maarten (Dutch part)','SX'),(213,'Syria','SY'),(214,'Swaziland','SZ'),(215,'Turks And Caicos Islands','TC'),(216,'Chad','TD'),(217,'French Southern Territories','TF'),(218,'Togo','TG'),(219,'Thailand','TH'),(220,'Tajikistan','TJ'),(221,'Tokelau','TK'),(222,'Timor-Leste','TL'),(223,'Turkmenistan','TM'),(224,'Tunisia','TN'),(225,'Tonga','TO'),(226,'Turkey','TR'),(227,'Trinidad and Tobago','TT'),(228,'Tuvalu','TV'),(229,'Taiwan','TW'),(230,'Tanzania','TZ'),(231,'Ukraine','UA'),(232,'Uganda','UG'),(233,'United States Minor Outlying Islands','UM'),(234,'United States','US'),(235,'Uruguay','UY'),(236,'Uzbekistan','UZ'),(237,'Vatican','VA'),(238,'Saint Vincent And The Grenadines','VC'),(239,'Venezuela','VE'),(240,'British Virgin Islands','VG'),(241,'U.S. Virgin Islands','VI'),(242,'Vietnam','VN'),(243,'Vanuatu','VU'),(244,'Wallis And Futuna','WF'),(245,'Samoa','WS'),(246,'Yemen','YE'),(247,'Mayotte','YT'),(248,'South Africa','ZA'),(249,'Zambia','ZM'),(250,'Zimbabwe','ZW');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currencies`
--

DROP TABLE IF EXISTS `currencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currencies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `symbol` varchar(3) NOT NULL,
  `code` varchar(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currencies`
--

LOCK TABLES `currencies` WRITE;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` VALUES (1,'United States Dollar','$','USD'),(2,'British Pound','£','GPB'),(3,'Japanese Yen','¥','JPY'),(4,'Euro','€','EUR'),(5,'Russian Ruble','₽','RUB'),(6,'South Korean Won','₩','KRW'),(7,'Chinese Yuan','¥','CNY'),(8,'Brazilian Real','B$','BRL'),(9,'Australian Dollar','$','AUD'),(10,'Canadian Dollar','$','CAD'),(11,'Vietnamese dong','₫','VND'),(12,'Indian Rupee','₹','INR');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `address_line1` varchar(245) NOT NULL,
  `address_line_2` varchar(245) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `country_id` int DEFAULT NULL,
  `postal_code` varchar(10) NOT NULL,
  `created_time` date DEFAULT NULL,
  `enabled` int NOT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rfbvkrffamfql7cjmen8v976v` (`email`),
  KEY `FK7b7p2myt0y31l4nyj1p7sk0b1` (`country_id`),
  CONSTRAINT `FK7b7p2myt0y31l4nyj1p7sk0b1` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'lehoanganhvn@gmail.com','$2a$10$ZQdbl6XeG/Z3frq.iJxnyucOhaoLhaa5BocnHnZKRPAntcZqQR9bu','Anh','Le Hoang','09123456789','30 Do Huy Uyen','Son Tra district','Da Nang','',242,'550000','2020-10-09',1,NULL),(2,'lorraine.allbright@outlook.com','$2a$10$II70xzaNTXp8WjJgGBq5k.xiDd8qiTp1yGPaCULGVeHtej/Hieb4O','Lorraine','Allbright','781-434-1947','2320  Romano Street','','Cambridge','Massachusetts',234,'2142','2020-10-09',1,NULL),(3,'nancy.bass2001@yahoo.com','$2a$10$iCc2M47GrRfzWV7/nrdZpuzkjLSxH5VEs1t02E55428eToiFo8pT6','Nancy','C Bass','519-791-4692','4703  Goyeau Ave','','Windsor','Ontario',39,'N9A 1H9','2020-10-09',0,NULL),(4,'brian.purcell3@gmail.com','$2a$10$mHz6KD.k5OTuTw9JWsa8OOZiF8gWxFRw6tjzIMxrx6GuaF2d.bvxG','Brian','Purcell','604-269-1384','4103  Tolmie St','','Vancouver','British Columbia',39,'V6M 1Y8','2020-06-09',1,NULL),(5,'tina.jamerson@gmail.com','$2a$10$zS.WEDVdrfraeTYXkh6kru0YT4dg14daWwxrPZKwImwt1S8I8Cbaa','Tina','D Jamerson','5139670375','934A  Small Street','Amish Country Byway','Berlin','Ohio',234,'44610','2020-10-09',1,NULL),(6,'christopherseldonusa@gmail.com','$2a$10$kBu.WfmGeYpcp.gNCVosx.2PY6dhUahdjPolgSOvYjY4DcUWRtnUm','Christopher ','Seldon','214-407-6337','3087 Marietta Street','','Vallejo','California',234,'94590','2020-10-10',1,NULL),(7,'alex.stevenson@outlook.com','$2a$10$og9uLgfaPjMbN0UQFPXQeeVhgfuArv3QQXLhM7ZfS4M.9j4Yp/Ksi','Alex','Stevenson','078 7586 952','56  Berkeley Rd','','Stretton-on-Dunsmore','',78,'CV23 3XN','2020-10-11',1,NULL),(8,'ethan.k.john@gmail.com','$2a$10$8Zc6kveZk1FR0HzVUQK/DuSQWFf2oGujkmDreapVXW0BtfDchP0yG','Ethan','Jones','070 2534 460','101  Gordon Terrace','','Barton','',78,'SY14 4FH','2020-10-11',1,NULL),(9,'pandu.shan03@gmail.com','$2a$10$Iybz9lJhA6FoeO7tviEWIuLaO4fC9lwjiiHwKCD0B5q4DLJ066mVW','Pandu','Shan','042375292','B 3/12, Part 1','','Delhi','Delhi',106,'110009','2020-10-11',1,NULL),(10,'ahjj.thaker2000@gmail.com','$2a$10$QKr/DFMbrX9ItxJCcTd.RuiD.H8BgsThxfZMEAmAas6yGOdJZkH9S','Ahjaja','Thaker','02228611610','Shop No 6, Gk Nagar No 3','Mahavir Darshan, Shankar Lane, Kandivali (west)','Mumbai','Maharashtra',106,'400067','2020-10-11',1,NULL),(11,'meena.gara@yahoo.com','$2a$10$SF6oz1JICybgPDOdrnTjFODxJBlCcUfj.HRF.TanU2pdjFcIT3X7S','Meena','Gara','04023775438','53 Balanagar','','Hyderabad','Andhra Pradesh',106,'500042','2020-10-11',1,NULL),(12,'jahnu.mishra@gmail.com','$2a$10$UIQGuwvF9WU3cge7cGHBge6aX6/dGRpwqD8GuCxBghTpDos9Hx9yW','Jahnu','Mishra','01125713815','112 Gagan Deep Building','Rajendra Place','Delhi','Delhi',106,'110008','2020-10-11',1,NULL),(13,'saka.prakash@yahoo.com','$2a$10$Q.Ctg6lFyvVEJ7ZnIuCIZ.UK5RlXuY2Iwwru4x.n9gtptDuhVLxRu','Saka','Prakash','02223011775','B/7/a Bharat Ngr, Grant Road','','Mumbai','Maharashtra',106,'400007','2020-10-12',0,NULL),(14,'gautam1988@gmail.com','$2a$10$nQjus79bd4dwNeEHTJ96U.Q7GIMffZQ7lqORWYv9VQQyh.9oqSgOK','Gautam','Nayak','02226200995','37 , Dhake Colony, J.p.rd, Andheri (west)','','Mumbai','Maharashtra',106,'400058','2020-10-12',1,NULL),(15,'cheema1994@gmail.com','$2a$10$NNQmIhQn6V1ZR9VpIbQL6OLpiLF.dJ.8qTDegkD3ZiDeMmwjSCyRW','Avatar','Cheema','03322684941','181 /, th Floor, M G Road, Burrabazar','','Kolkata','West Bengal',106,'700007','2020-10-12',1,NULL),(16,'jianzhou2000@gmail.com','$2a$10$QEOF7Xeue1enl9COCgB/deSu2DIewIlyEHGDm83hjpz3iNLkmD0sm','Jianhong','Zhou','65-6278 9444','456 Alexandra Road #16-02 NOL','Building Singapore 119962','Singapore','',199,'119962','2020-10-12',1,NULL),(17,'sugeng1208@outlook.com','$2a$10$CVLvsCqcbzWcsRN/ZfGcAOr5wZAUIITu9kqG7.U2lWo4MgtIdITDa','Su','Geng','062566046','Braddell Tech 13 Lorong 8 Toa Payoh #04-01 319261','','Singapore','',199,'319261','2020-10-12',1,NULL),(18,'alisa.willcox7@gmail.com','$2a$10$pBLzCgKPXfJsMiCHFB9BweUamtoJXnbPlh7eheH7zK02v/QW3UgPK','Alisa','Willcox','423-893-1283','1053  Broadway Avenue','','Chattanooga','Tennessee',234,'37421','2020-10-12',1,NULL),(19,'chelsea.greener@gmail.com','$2a$10$cyrxmp9dwNhUAEYqPiMHHO6n9vvLqgS/tbcYjaeyIMekfd35pvnOm','Chelsea','Greener','(07) 4918 39','79 Masthead Drive','','Great Keepel Island','Queensland',14,'4700','2020-10-12',1,NULL),(20,'spender.de.bavay@gmail.com','$2a$10$LQT8mw/ZGHz1W5pd4/iDyuNjwv7yJSKKJUXnXWf6xAB46M5wDUOVG','Spencer','De Bavay','(02) 6146 87','37 Grayson Street','','Matong','New South Wales',14,'2652','2020-10-12',1,NULL),(21,'konig.daniela@gmail.com','$2a$10$E6g8TKWzT6wPyGma.O3SweI4mqEMQyavjiy/qUk9ayyAQ7/FN/eq.','Daniela','Konig','08274368060','Alt Reinickendorf 61','','Schwenningen','Baden-Württemberg',58,'72477','2020-10-16',0,NULL),(22,'tannguyenminh@gmail.com','$2a$10$c9u299wIXYQ4GuNUtthJRO4/fxb/qaPXBIcgLqo.OzTqBM2e5Erm2','Tan','Nguyen Minh','0987667887','Ba Vi, Thanh Chuong','Soc Son','Ha Noi','Ha Noi',242,'142870','2020-11-12',1,NULL),(23,'trangquynh269@gmail.com','$2a$10$pFQajpIUy38PV61fucqxmugLtQerPid3N1LvP2MsSlMMuUG283.Cm','Trang','Le Thi Quynh','0922664450','30 Do Huy Uyen','An Hai Bac district','Da Nang','Da Nang',242,'552000','2021-01-14',1,NULL),(24,'nguyen.tuan.son@gmail.com','$2a$10$4YwxikvvFeDknQxWXoYHK.s0JicA2E1U4fjaomQ/tktD4Xx9R.Di6','Son','Nguyen Tuan','0984457819','123 Truc Bach','Tay Ho','Ha Noi','Hanoi',242,'127890','2021-01-10',1,NULL),(25,'minhthu20@gmail.com','$2a$10$80H.3RSBQS1iT6hCJI56suOJFE.pBUx.DyhnmDv/ZjOGiJAQxjR6K','Thu','Trinh Minh','0977882312','14H8 Phan Van Truong','Cau Giay','Ha Noi','Hanoi',242,'113000','2020-01-14',1,NULL),(26,'tran.thi.mai@gmail.com','$2a$10$YkVzbGU89318I2nL8z.NGeJN77.UJCavjY7PH7x3LfL1zIEOVzF7m','Mai','Tran Thi','0954876291','256A Vo Van Kiet','Phuoc My','Da Nang','Da Nang',242,'555000','2021-01-16',1,NULL),(27,'nguyen.hoang.bach@gmail.com','$2a$10$G3RLX9mmiMafu0MxTomJp.43lX2khhp0IOKN4oZSDK689dRt71zGq','Bach','Nguyen Hoang','0910405069','620 Bach Dang','Hai Chau','Da Nang','Da Nang',242,'554000','2021-01-16',0,NULL),(28,'ravi.kumar2009@gmail.com','$2a$10$yYkH67VJ6LVGScLHvxtyyOTCslPiY3ZM0SYArvur0JR8F6IlGy.Gu','Ravi','Kumar','026715849','M 55 2nd Floor, Part 2','','Delhi','Delhi',106,'110048','2020-05-16',1,NULL),(29,'xue.hao.chu@gmail.com','$2a$10$suovD4HuFHy632QdAQnNTOwyTwHLs4NI8mT0gARBNxavM0IXl.HdW','Xue','Hao Chu','081005992','Choa Chu Kang Crescent, 683690','','Singapore','',199,'683690','2021-01-17',1,NULL),(30,'mo.liang.237@gmail.com','$2a$10$JKIV3Hi4phMMKU/p2tqH8OXZaKNboZAwX6/nHdFoxWbeohie7NDuu','Mo','Liang','063237730','30 Robinson Road','#03-01B ROBINSON TOWERS','Singapore','',199,'048546','2020-04-12',1,NULL),(31,'chan.ding.97@gmail.com','$2a$10$KlcH7aIiy/Hvt3NhCYbDH.toghsP2CbeBY7ZO9YT03o9dLkdNluBS','Chan','Ding','6567321372','260 Orchard Road, The Heeren','04-30/31 238855','Singapore','',199,'238855','2021-01-18',1,NULL),(32,'zak.carey.91@gmail.com','$2a$10$E1IxQO8LTXWF0diVBB7bfOcwc8wwtWHu4dhRx3zkGemtcsb1Mf32G','Zak','Carey','0795602121','70 Guild Street','','London','',78,'N8 1HG','2021-01-20',0,NULL),(33,'katherin.mc.knight@live.com','$2a$10$9ZbxZO2gJul3ups2FXU9se3vd3d5zn0VRXQnp0l1bS04dq3UHy5g2','Katherine','McKnight','8153287288','922  Emeral Dreams Drive','','Chicago','Illinois',234,'60601','2020-12-26',1,NULL),(34,'pasty.aston.89@live.com','$2a$10$eISh5FVdov6DSOWeXpNeVeieJOwGe8gBC1zrLfKUKVqWrBFeIaMKy','Pasty','Aston','2129229661','1482  Rosewood Lane','','New York','New York',234,'10016','2020-05-26',1,NULL),(35,'rekha.makavana@gmail.com','$2a$10$52Ah7rPRiReZ8FsQBRATfOlJl.XQfwGGaqa3vP6Z9uBKc7ADz7yCO','Rekha','Makavana','02226493101','Shop No 2, Shobhana Bldg.','Tilak Road, Opp. Dph, Santacruz (west)','Mumbai','Maharashtra',106,'400054','2020-09-01',1,NULL),(36,'flynn.hackett.au@gmail.com','$2a$10$8oaaz3VHFE/PqpRMPnvABuWLZSl9lCT3plI8aMMejqJeVtv3bNUH.','Flynn','Hackett','073758521','19 Shirley Street','','Yatala','Queensland',14,'4207','2021-02-01',1,NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_details`
--

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnfvvq3meg4ha3u1bju9k4is3r` (`product_id`),
  CONSTRAINT `FKnfvvq3meg4ha3u1bju9k4is3r` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_details`
--

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnq71xsohugpqwf3c9gxmsuy` (`product_id`),
  CONSTRAINT `FKqnq71xsohugpqwf3c9gxmsuy` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alias` varchar(256) NOT NULL,
  `author` varchar(256) NOT NULL,
  `cost` float NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `description` varchar(1024) NOT NULL,
  `discount_percent` float DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `in_stock` bit(1) DEFAULT NULL,
  `main_image` varchar(255) NOT NULL,
  `name` varchar(256) NOT NULL,
  `price` float NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `weight` float NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8qwq8q3hk7cxkp9gruxupnif5` (`alias`),
  UNIQUE KEY `UK_1fdslyxod6pgjv8bdnb4sgt8` (`author`),
  UNIQUE KEY `UK_o61fmio5yukmmiqgnxf8pnavn` (`name`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `description` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin','manage everything'),(2,'Salesperson','manage products, categories, orders');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_users`
--

DROP TABLE IF EXISTS `roles_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_users` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKsmos02hm32191ogexm2ljik9x` (`role_id`),
  CONSTRAINT `FKlkcn1l0gnfshcn4rnmak73ta` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsmos02hm32191ogexm2ljik9x` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_users`
--

LOCK TABLES `roles_users` WRITE;
/*!40000 ALTER TABLE `roles_users` DISABLE KEYS */;
INSERT INTO `roles_users` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `roles_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `key` varchar(128) NOT NULL,
  `value` varchar(1024) NOT NULL,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES ('COPYRIGHT','Copyright (C) 2021 MyShop Ltd. ','GENERAL'),('CURRENCY_ID','5','CURRENCY'),('CURRENCY_SYMBOL','₽','CURRENCY'),('CURRENCY_SYMBOL_POSITION','Before price','CURRENCY'),('DECIMAL_DIGITS','2','CURRENCY'),('DECIMAL_POINT_TYPE','POINT','CURRENCY'),('SITE_LOGO','Bookstore.png','GENERAL'),('SITE_NAME','MyShop','GENERAL'),('THOUSANDS_POINT_TYPE','COMMA','CURRENCY');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKskkdphjml9vjlrqn4m5hi251y` (`country_id`),
  CONSTRAINT `FKskkdphjml9vjlrqn4m5hi251y` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=308 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (1,'Ha Noi',242),(2,'Da Nang',242),(3,'New York',234),(4,'California',234),(5,'Ho Chi Minh City',242),(6,'Hai Phong',242),(7,'Bac Giang',242),(8,'Son La',242),(9,'Quang Ninh',242),(10,'Nghe An',242),(12,'Utah',234),(13,'Washington',234),(14,'Florida',234),(15,'Illinois',234),(16,'Texas',234),(18,'Colorado',234),(19,'Alaska',234),(20,'Can Tho',242),(22,'Binh Duong',242),(23,'Thanh Hoa',242),(24,'Andhra Pradesh',106),(25,'Arunachal Pradesh',106),(26,'Assam',106),(27,'Bihar',106),(28,'Haryana',106),(29,'Himacha Pradesh',106),(30,'Karnataka',106),(31,'Maharashtra',106),(32,'Meghalaya',106),(33,'Punjab',106),(34,'Tamil Nadu',106),(35,'Telangana',106),(36,'Uttar Pradesh',106),(37,'West Bengal',106),(38,'Bac Ninh',242),(40,'Ha Tinh',242),(41,'Hue',242),(42,'Binh Dinh',242),(43,'An Giang',242),(44,'Blackpool',78),(45,'London',78),(46,'Liverpool',78),(47,'Manchester',78),(48,'Newcastle',78),(49,'Bristol',78),(50,'Cambridge',78),(51,'Lang Son',242),(52,'Delhi',106),(53,'Hawaii',234),(54,'Georgia',234),(55,'Pennsylvania',234),(56,'Virginia',234),(57,'Arizona',234),(59,'Kerala',106),(60,'Rajasthan',106),(61,'Odisha',106),(62,'Mizoram',106),(63,'Sikkim',106),(64,'Manipur',106),(65,'Nagaland',106),(66,'Tripura',106),(67,'Jharkhand',106),(68,'Uttarakhand',106),(69,'Goa',106),(70,'Madhya Pradesh',106),(71,'Gujarat',106),(72,'Massachusetts',234),(73,'Indiana',234),(74,'Michigan',234),(75,'Ohio',234),(76,'New Jersey',234),(77,'Minnesota',234),(78,'North Carolina',234),(79,'Oregon',234),(80,'Maryland',234),(81,'Tennessee',234),(82,'Montana',234),(83,'Maine',234),(84,'Alabama',234),(85,'Wisconsin',234),(86,'Louisiana',234),(87,'Connecticut',234),(88,'Missouri',234),(89,'South Carolina',234),(90,'Mississippi',234),(91,'New Mexico',234),(92,'Nevada',234),(93,'Kentucky',234),(94,'Arkansas',234),(95,'Wyoming',234),(96,'Kansas',234),(97,'Delaware',234),(98,'Iowa',234),(99,'Idaho',234),(100,'Nebraska',234),(101,'Rhode Island',234),(102,'Vermont',234),(103,'South Dakota',234),(104,'New Hamsphire',234),(105,'West Virginia',234),(106,'North Dakota',234),(107,'Oklahoma',234),(109,'Nam Dinh',242),(110,'Khanh Hoa',242),(112,'Tay Ninh',242),(113,'Kien Giang',242),(114,'Lai Chau',242),(115,'Lao Cai',242),(116,'Ha Giang',242),(117,'Hoa Binh',242),(118,'Thai Binh',242),(119,'Dong Nai',242),(120,'Soc Trang',242),(121,'Bac Lieu',242),(123,'Ca Mau',242),(124,'Quang Binh',242),(125,'Quang Tri',242),(126,'Quang Nam',242),(127,'Quang Ngai',242),(128,'Gia Lai',242),(129,'Vinh Phuc',242),(130,'Lam Dong',242),(131,'Dak Lak',242),(132,'Phu Yen',242),(133,'Binh Phuoc',242),(134,'Dak Nong',242),(135,'Dong Thap',242),(136,'Hau Giang',242),(137,'Ba Ria Vung Tau',242),(138,'Binh Thuan',242),(139,'Hai Duong',242),(140,'Hung Yen',242),(141,'Thai Nguyen',242),(142,'Long An',242),(143,'Ben Tre',242),(144,'Bac Kan',242),(145,'Ninh Binh',242),(146,'Kon Tum',242),(147,'Tra Vinh',242),(148,'Yen Bai',242),(149,'Tuyen Quang',242),(150,'Phu Tho',242),(151,'Vinh Long',242),(152,'New South Wales',14),(153,'Queensland',14),(154,'Tasmania',14),(155,'Western Australia',14),(156,'Victoria',14),(157,'South Australia',14),(158,'Ontario',39),(159,'Quebec',39),(160,'Nova Scotia',39),(161,'New Brunswick',39),(162,'Manitoba',39),(163,'British Columbia',39),(164,'Prince Edward Island',39),(165,'Saskatchewan',39),(166,'Alberta',39),(167,'Newfoundland and Labrador',39),(168,'Grand Est',76),(169,'Hauts-de-France',76),(170,'Normandy',76),(171,'Nouvelle-Aquitaine',76),(172,'Occitanie',76),(173,'Brittany',76),(174,'Centre-Val de Loire',76),(175,'Corsica',76),(176,'French Guiana',76),(177,'Guadeloupe',76),(178,'Île-de-France',76),(179,'Martinique',76),(180,'Mayotte',76),(181,'Pays de la Loire',76),(182,'Provence-Alpes-Côte d\'Azur',76),(183,'Réunion',76),(184,'Aichi',115),(185,'Akita',115),(186,'Chiba',115),(187,'Ehime',115),(188,'Fukui',115),(189,'Fukuoka',115),(190,'Fukushima',115),(191,'Gunma',115),(192,'Kobe',115),(193,'Hiroshima',115),(194,'Hokkaido',115),(195,'Ishikawa',115),(196,'Kagawa',115),(197,'Kochi',115),(198,'Kyoto',115),(199,'Nagasaki',115),(200,'Okinawa',115),(201,'Osaka',115),(202,'Saga',115),(203,'Tokushima',115),(204,'Tokyo',115),(205,'Yamagata',115),(206,'Yamaguchi',115),(207,'Yamanashi',115),(208,'Greater Poland',180),(209,'Kuyavia-Pomerania',180),(210,'Lesser Poland',180),(211,'Łódź',180),(212,'Lower Silesia',180),(213,'Lublin',180),(214,'Lubusz',180),(215,'Masovia',180),(216,'Opole',180),(217,'Podlaskie',180),(218,'Pomerania',180),(219,'Silesia',180),(220,'Subcarpathia',180),(221,'Holy Cross Province',180),(222,'Warmia-Masuria',180),(223,'West Pomerania',180),(224,'Baden-Württemberg',58),(225,'Bavaria',58),(226,'Berlin',58),(227,'Brandenburg',58),(228,'Bremen',58),(229,'Hamburg',58),(230,'Hesse',58),(231,'Lower Saxony',58),(232,'Mecklenburg-Vorpommern',58),(233,'North Rhine- Westphalia',58),(234,'Rhineland-Palatinate',58),(235,'Saarland',58),(236,'Saxony',58),(237,'Saxony-Anhalt',58),(238,'Schleswig-Holstein',58),(239,'Thuringia',58),(240,'Acre',32),(241,'Alagoas',32),(242,'Amapá',32),(243,'Amazonas',32),(244,'Bahia',32),(245,'Ceará',32),(246,'Distrito Federal',32),(247,'Espírito Santo',32),(248,'Goiás',32),(249,'Maranhão',32),(250,'Mato Grosso',32),(251,'Minas Gerais',32),(252,'Pará',32),(253,'Paraíba',32),(254,'Paraná',32),(255,'Pernambuco',32),(256,'Piauí',32),(257,'Rio de Janeiro',32),(258,'Rio Grande do Norte',32),(259,'Rio Grande do Sul',32),(260,'Rondônia',32),(261,'Roraima',32),(262,'Santa Catarina',32),(263,'São Paulo',32),(264,'Sergipe',32),(265,'Tocantins',32),(266,'Adana',226),(267,'Adıyaman',226),(268,'Ankara',226),(269,'Balıkesir',226),(270,'Bilecik',226),(271,'Bingöl',226),(272,'Bursa',226),(273,'Çanakkale',226),(274,'Çankırı',226),(275,'Çorum',226),(276,'Denizli',226),(277,'Diyarbakır',226),(278,'Edirne',226),(279,'Erzurum',226),(280,'Gaziantep',226),(281,'Giresun',226),(282,'Hakkâri',226),(283,'Hatay',226),(284,'Isparta',226),(285,'Istanbul',226),(286,'Konya',226),(287,'Malatya',226),(288,'Manisa',226),(289,'Iğdır',226),(290,'Karaman',226),(291,'Osmaniye',226),(292,'Tokat',226),(293,'Sivas',226),(294,'Sinop',226),(295,'Siirt',226),(296,'Batman',226),(297,'Kilis',226),(298,'Rize',226),(299,'Ordu',226),(300,'Tunceli',226),(301,'Yozgat',226),(302,'Van',226),(303,'Bayburt',226),(304,'Yalova',226),(305,'Samsun',226),(306,'Sakarya',226);
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `enabled` int NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@mail.com','Glen','Doe',1,'$2a$10$Op29XN5Se3.XsCcWzBYpuuJhZqDYG0/MZof5PIN5RpeC9cR13hXw2'),(2,'salesperson@mail.com','Ann','Watertown',1,'$2a$10$kVrVmIORCh52d0Nmn/bCLOp9Gxb7gz/OjlKCpzPqNFUfhaHu624cK');
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

-- Dump completed on 2021-12-01  9:40:46

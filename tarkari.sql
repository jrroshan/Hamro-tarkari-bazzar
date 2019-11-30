/*
SQLyog Professional v12.09 (64 bit)
MySQL - 10.4.6-MariaDB : Database - tarkaribazaar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tarkaribazaar` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tarkaribazaar`;

/*Table structure for table `dairy` */

DROP TABLE IF EXISTS `dairy`;

CREATE TABLE `dairy` (
  `did` int(100) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(2550) DEFAULT NULL,
  `Quantity` varchar(2550) DEFAULT NULL,
  `Price` varchar(300) DEFAULT NULL,
  KEY `did` (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dairy` */

/*Table structure for table `fruits` */

DROP TABLE IF EXISTS `fruits`;

CREATE TABLE `fruits` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(255) DEFAULT NULL,
  `Quantity` varchar(255) DEFAULT NULL,
  `Price` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fruits` */

/*Table structure for table `meat` */

DROP TABLE IF EXISTS `meat`;

CREATE TABLE `meat` (
  `mid` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Quantity` varchar(255) DEFAULT NULL,
  `Price` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `meat` */

/*Table structure for table `suppliers` */

DROP TABLE IF EXISTS `suppliers`;

CREATE TABLE `suppliers` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(200) DEFAULT NULL,
  `LastName` varchar(200) DEFAULT NULL,
  `Address` varchar(300) DEFAULT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `Email` varchar(300) DEFAULT NULL,
  `Phone` int(10) DEFAULT NULL,
  `ShopName` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `suppliers` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `cid` varchar(50) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

/*Table structure for table `vegetables` */

DROP TABLE IF EXISTS `vegetables`;

CREATE TABLE `vegetables` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(300) DEFAULT NULL,
  `Quantity` varchar(255) DEFAULT NULL,
  `Price` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vegetables` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

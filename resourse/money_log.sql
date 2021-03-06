/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.6.37-log : Database - money
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`money` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `money`;

/*Table structure for table `money_log` */

DROP TABLE IF EXISTS `money_log`;

CREATE TABLE `money_log` (
	`ID` int(11) NOT NULL AUTO_INCREMENT,
	`MONEY` decimal(6,2) NOT NULL COMMENT '价格',
	`USEFOR` varchar(50) NOT NULL COMMENT '用途',
	`UID` int(11) NOT NULL COMMENT '用户ID',
	`USER_NAME` varchar(50) DEFAULT NULL COMMENT '用户名',
	`CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
	`WEEKS` varchar(4) DEFAULT NULL COMMENT '一个月的第几周',
	`STATUS` tinyint(4) DEFAULT NULL COMMENT '状态;0:已结算；1未结算',
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8


/*Data for the table `money_log` */

insert  into `money_log`(`ID`,`MONEY`,`USEFOR`,`UID`,`USER_NAME`,`CREATE_TIME`,`WEEKS`,`STATUS`) values (16,'465.00','梵蒂冈',1,'胡洋','2017-09-14 14:37:22',3,1),(17,'789.00','3543',1,'胡洋','2017-09-14 14:37:23',3,1),(18,'789.00','3543',1,'胡洋','2017-09-14 14:37:24',3,1),(19,'853.00','dfgfg',1,'胡洋','2017-09-14 14:37:25',3,1),(20,'853.00','dfgfg',1,'胡洋','2017-09-14 14:37:25',2,1),(21,'45.00','sfd',1,'何晓波','2017-09-14 17:41:34',2,1),(22,'126.00','',1,'何晓波','2017-09-14 17:41:30',2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

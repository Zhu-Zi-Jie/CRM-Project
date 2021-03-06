/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.37-log : Database - crm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `crm`;

/*Table structure for table `tbl_activity` */

DROP TABLE IF EXISTS `tbl_activity`;

CREATE TABLE `tbl_activity` (
  `id` char(32) NOT NULL,
  `owner` char(32) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` char(10) DEFAULT NULL,
  `end_date` char(10) DEFAULT NULL,
  `cost` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_activity` */

/*Table structure for table `tbl_activity_remark` */

DROP TABLE IF EXISTS `tbl_activity_remark`;

CREATE TABLE `tbl_activity_remark` (
  `id` char(32) NOT NULL,
  `note_content` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_flag` char(1) DEFAULT NULL COMMENT '0表示未修改，1表示已修改',
  `activity_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_activity_remark` */

/*Table structure for table `tbl_clue` */

DROP TABLE IF EXISTS `tbl_clue`;

CREATE TABLE `tbl_clue` (
  `id` char(32) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `appellation` varchar(255) DEFAULT NULL,
  `owner` char(32) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `mphone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contact_summary` varchar(255) DEFAULT NULL,
  `next_contact_time` char(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_clue` */

/*Table structure for table `tbl_clue_activity_relation` */

DROP TABLE IF EXISTS `tbl_clue_activity_relation`;

CREATE TABLE `tbl_clue_activity_relation` (
  `id` char(32) NOT NULL,
  `clue_id` char(32) DEFAULT NULL,
  `activity_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_clue_activity_relation` */

/*Table structure for table `tbl_clue_remark` */

DROP TABLE IF EXISTS `tbl_clue_remark`;

CREATE TABLE `tbl_clue_remark` (
  `id` char(32) NOT NULL,
  `note_content` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `edit_flag` char(1) DEFAULT NULL,
  `clue_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_clue_remark` */

/*Table structure for table `tbl_contacts` */

DROP TABLE IF EXISTS `tbl_contacts`;

CREATE TABLE `tbl_contacts` (
  `id` char(32) NOT NULL,
  `owner` char(32) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `customer_id` char(32) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `appellation` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mphone` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contact_summary` varchar(255) DEFAULT NULL,
  `next_contact_time` char(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_contacts` */

/*Table structure for table `tbl_contacts_activity_relation` */

DROP TABLE IF EXISTS `tbl_contacts_activity_relation`;

CREATE TABLE `tbl_contacts_activity_relation` (
  `id` char(32) NOT NULL,
  `contacts_id` char(32) DEFAULT NULL,
  `activity_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_contacts_activity_relation` */

/*Table structure for table `tbl_contacts_remark` */

DROP TABLE IF EXISTS `tbl_contacts_remark`;

CREATE TABLE `tbl_contacts_remark` (
  `id` char(32) NOT NULL,
  `note_content` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `edit_flag` char(1) DEFAULT NULL,
  `contacts_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_contacts_remark` */

/*Table structure for table `tbl_customer` */

DROP TABLE IF EXISTS `tbl_customer`;

CREATE TABLE `tbl_customer` (
  `id` char(32) NOT NULL,
  `owner` char(32) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `contact_summary` varchar(255) DEFAULT NULL,
  `next_contact_time` char(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_customer` */

/*Table structure for table `tbl_customer_remark` */

DROP TABLE IF EXISTS `tbl_customer_remark`;

CREATE TABLE `tbl_customer_remark` (
  `id` char(32) NOT NULL,
  `note_content` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `edit_flag` char(1) DEFAULT NULL,
  `customer_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_customer_remark` */

/*Table structure for table `tbl_dic_type` */

DROP TABLE IF EXISTS `tbl_dic_type`;

CREATE TABLE `tbl_dic_type` (
  `code` varchar(255) NOT NULL COMMENT '编码是主键，不能为空，不能含有中文。',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_dic_type` */

/*Table structure for table `tbl_dic_value` */

DROP TABLE IF EXISTS `tbl_dic_value`;

CREATE TABLE `tbl_dic_value` (
  `id` char(32) NOT NULL COMMENT '主键，采用UUID',
  `value` varchar(255) DEFAULT NULL COMMENT '不能为空，并且要求同一个字典类型下字典值不能重复，具有唯一性。',
  `text` varchar(255) DEFAULT NULL COMMENT '可以为空',
  `order_no` varchar(255) DEFAULT NULL COMMENT '可以为空，但不为空的时候，要求必须是正整数',
  `type_code` varchar(255) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_dic_value` */

/*Table structure for table `tbl_tran` */

DROP TABLE IF EXISTS `tbl_tran`;

CREATE TABLE `tbl_tran` (
  `id` char(32) NOT NULL,
  `owner` char(32) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `expected_date` char(10) DEFAULT NULL,
  `customer_id` char(32) DEFAULT NULL,
  `stage` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `activity_id` char(32) DEFAULT NULL,
  `contacts_id` char(32) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contact_summary` varchar(255) DEFAULT NULL,
  `next_contact_time` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_tran` */

/*Table structure for table `tbl_tran_history` */

DROP TABLE IF EXISTS `tbl_tran_history`;

CREATE TABLE `tbl_tran_history` (
  `id` char(32) NOT NULL,
  `stage` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `expected_date` char(10) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `tran_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_tran_history` */

/*Table structure for table `tbl_tran_remark` */

DROP TABLE IF EXISTS `tbl_tran_remark`;

CREATE TABLE `tbl_tran_remark` (
  `id` char(32) NOT NULL,
  `note_content` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `edit_flag` char(1) DEFAULT NULL,
  `tran_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_tran_remark` */

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` char(32) NOT NULL COMMENT 'uuid\r\n            ',
  `login_act` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `login_pwd` varchar(255) DEFAULT NULL COMMENT '瀵嗙爜涓嶈兘閲囩敤鏄庢枃瀛樺偍锛岄噰鐢ㄥ瘑鏂囷紝MD5鍔犲瘑涔嬪悗鐨勬暟鎹?',
  `email` varchar(255) DEFAULT NULL,
  `expire_time` char(19) DEFAULT NULL COMMENT '澶辨晥鏃堕棿涓虹┖鐨勬椂鍊欒〃绀烘案涓嶅け鏁堬紝澶辨晥鏃堕棿涓?2018-10-10 10:10:10锛屽垯琛ㄧず鍦ㄨ鏃堕棿涔嬪墠璇ヨ处鎴峰彲鐢ㄣ??',
  `lock_state` char(1) DEFAULT NULL COMMENT '閿佸畾鐘舵?佷负绌烘椂琛ㄧず鍚敤锛屼负0鏃惰〃绀洪攣瀹氾紝涓?1鏃惰〃绀哄惎鐢ㄣ??',
  `deptno` char(4) DEFAULT NULL,
  `allow_ips` varchar(255) DEFAULT NULL COMMENT '鍏佽璁块棶鐨処P涓虹┖鏃惰〃绀篒P鍦板潃姘镐笉鍙楅檺锛屽厑璁歌闂殑IP鍙互鏄竴涓紝涔熷彲浠ユ槸澶氫釜锛屽綋澶氫釜IP鍦板潃鐨勬椂鍊欙紝閲囩敤鍗婅閫楀彿鍒嗛殧銆傚厑璁窱P鏄?192.168.100.2锛岃〃绀鸿鐢ㄦ埛鍙兘鍦↖P鍦板潃涓?192.168.100.2鐨勬満鍣ㄤ笂浣跨敤銆?',
  `createTime` char(19) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `edit_time` char(19) DEFAULT NULL,
  `edit_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`id`,`login_act`,`name`,`login_pwd`,`email`,`expire_time`,`lock_state`,`deptno`,`allow_ips`,`createTime`,`create_by`,`edit_time`,`edit_by`) values ('06f5fc056eac41558a964f96daa7f27c','ls','鏉庡洓','yf123','ls@163.com','2018-11-27 21:50:05','1','A001','192.168.1.1,0:0:0:0:0:0:0:1','2018-11-22 12:11:40','鏉庡洓',NULL,NULL),('40f6cdea0bd34aceb77492a1656d9fb3','zs','寮犱笁','yf123','zs@qq.com','2023-11-30 23:50:55','1','A001','192.168.1.1,192.168.1.2,127.0.0.1,0:0:0:0:0:0:0:1','2018-11-22 11:37:34','寮犱笁',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

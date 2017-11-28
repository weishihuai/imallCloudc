DROP TABLE IF EXISTS T_PT_SYS_USER;
/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.44
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_pt_sys_user` (
	`ID` bigint (20),
	`SHOP_ID` bigint (20),
	`ORG_ID` bigint (20),
	`IS_DEFAULT_ADMIN` char (3),
	`EMPLOYEE_CODE` varchar (96),
	`USER_NAME` varchar (384),
	`REAL_NAME` varchar (192),
	`EMAIL` varchar (384),
	`MOBILE` varchar (96),
	`PASSWORD` varchar (384),
	`SEX` varchar (96),
	`SALT` varchar (96),
	`STATE` varchar (96),
	`AVAILABLE` char (3),
	`IS_DELETED` char (3),
	`IDENTITY_CARD` varchar (96),
	`NATIVE_PLACE` varchar (96),
	`MARRIAGE_STAT_CODE` varchar (96),
	`BIRTHDAY` timestamp ,
	`HOME_ADDR` varchar (384),
	`SCHOOL_NM` varchar (96),
	`MAJOR` varchar (96),
	`ACADEMIC_QUALIFICATI` varchar (96),
	`GRADUATION_TIME` date ,
	`JOIN_WORK_TIME` date ,
	`TECHNOLOGY_POST_TITLE` varchar (96),
	`ENTRY_JOB_TIME` date ,
	`IS_POSTS_TRAIN` char (3),
	`LEAVE_TIME` date ,
	`LEAVE_REASON` varchar (384),
	`MARK` varchar (1536),
	`CREATE_DATE` timestamp ,
	`CREATE_BY` varchar (96),
	`last_modified_date` timestamp ,
	`last_modified_by` varchar (96),
	`VERSION` int (11)
);
insert into `t_pt_sys_user` (`ID`, `SHOP_ID`, `ORG_ID`, `IS_DEFAULT_ADMIN`, `EMPLOYEE_CODE`, `USER_NAME`, `REAL_NAME`, `EMAIL`, `MOBILE`, `PASSWORD`, `SEX`, `SALT`, `STATE`, `AVAILABLE`, `IS_DELETED`, `IDENTITY_CARD`, `NATIVE_PLACE`, `MARRIAGE_STAT_CODE`, `BIRTHDAY`, `HOME_ADDR`, `SCHOOL_NM`, `MAJOR`, `ACADEMIC_QUALIFICATI`, `GRADUATION_TIME`, `JOIN_WORK_TIME`, `TECHNOLOGY_POST_TITLE`, `ENTRY_JOB_TIME`, `IS_POSTS_TRAIN`, `LEAVE_TIME`, `LEAVE_REASON`, `MARK`, `CREATE_DATE`, `CREATE_BY`, `last_modified_date`, `last_modified_by`, `VERSION`) values('1','1','2','Y','20001','admin','admin','iloosen@imall.com','13710000000','2472ecec093995002d2e999f177f7307','SECRET','e8a343ccd00ae07b523f9d6c4a43eaec','NORMAL','Y','N','445122155652154545','广东省天河区小新塘小区生产大队','未婚','2017-07-29 09:58:22','广东省天河区小新塘小区生产大队','大力男生学院奖','计算机','本科','2017-07-29','2017-07-29','java','2017-07-29','N',NULL,NULL,NULL,'2017-07-29 09:42:34','e','2017-07-29 09:42:37','e','2');

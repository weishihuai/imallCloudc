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
insert into `t_pt_sys_user` (`ID`, `SHOP_ID`, `ORG_ID`, `IS_DEFAULT_ADMIN`, `EMPLOYEE_CODE`, `USER_NAME`, `REAL_NAME`, `EMAIL`, `MOBILE`, `PASSWORD`, `SEX`, `SALT`, `STATE`, `IS_DELETED`, `IDENTITY_CARD`, `NATIVE_PLACE`, `MARRIAGE_STAT_CODE`, `BIRTHDAY`, `HOME_ADDR`, `SCHOOL_NM`, `MAJOR`, `ACADEMIC_QUALIFICATI`, `GRADUATION_TIME`, `JOIN_WORK_TIME`, `TECHNOLOGY_POST_TITLE`, `ENTRY_JOB_TIME`, `IS_POSTS_TRAIN`, `LEAVE_TIME`, `LEAVE_REASON`, `MARK`, `CREATE_DATE`, `CREATE_BY`, `last_modified_date`, `last_modified_by`, `VERSION`) values('1',NULL,'2','Y','10010','admin','admin','portal@imall.com','13800138000','c2a6a9ae0cd922fde42fe682ea2a3ada','SECRET','23ac61870fe12b73e026b82f15521a19','NORMAL','N',NULL,NULL,'UNMARRIED','2017-07-31 20:03:53',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Y',NULL,NULL,'默认系统管理员','2015-10-22 15:53:20','portalUser','2015-10-26 09:44:43','portalUser','2');
insert into `t_pt_sys_user` (`ID`, `SHOP_ID`, `ORG_ID`, `IS_DEFAULT_ADMIN`, `EMPLOYEE_CODE`, `USER_NAME`, `REAL_NAME`, `EMAIL`, `MOBILE`, `PASSWORD`, `SEX`, `SALT`, `STATE`, `IS_DELETED`, `IDENTITY_CARD`, `NATIVE_PLACE`, `MARRIAGE_STAT_CODE`, `BIRTHDAY`, `HOME_ADDR`, `SCHOOL_NM`, `MAJOR`, `ACADEMIC_QUALIFICATI`, `GRADUATION_TIME`, `JOIN_WORK_TIME`, `TECHNOLOGY_POST_TITLE`, `ENTRY_JOB_TIME`, `IS_POSTS_TRAIN`, `LEAVE_TIME`, `LEAVE_REASON`, `MARK`, `CREATE_DATE`, `CREATE_BY`, `last_modified_date`, `last_modified_by`, `VERSION`) values('2','1','3','Y','20001','shopAdmin','shopAdmin','leshang@imall.com.cn','13800138001','2472ecec093995002d2e999f177f7307','SECRET','e8a343ccd00ae07b523f9d6c4a43eaec','NORMAL','N',NULL,NULL,'UNMARRIED','2017-07-31 20:03:53',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Y',NULL,NULL,'子公司登录帐户','2016-11-24 09:16:05','B2C_FRONEND_USER','2016-11-24 09:16:05','B2C_FRONEND_USER','0');
insert into `t_pt_sys_user` (`ID`, `SHOP_ID`, `ORG_ID`, `IS_DEFAULT_ADMIN`, `EMPLOYEE_CODE`, `USER_NAME`, `REAL_NAME`, `EMAIL`, `MOBILE`, `PASSWORD`, `SEX`, `SALT`, `STATE`, `IS_DELETED`, `IDENTITY_CARD`, `NATIVE_PLACE`, `MARRIAGE_STAT_CODE`, `BIRTHDAY`, `HOME_ADDR`, `SCHOOL_NM`, `MAJOR`, `ACADEMIC_QUALIFICATI`, `GRADUATION_TIME`, `JOIN_WORK_TIME`, `TECHNOLOGY_POST_TITLE`, `ENTRY_JOB_TIME`, `IS_POSTS_TRAIN`, `LEAVE_TIME`, `LEAVE_REASON`, `MARK`, `CREATE_DATE`, `CREATE_BY`, `last_modified_date`, `last_modified_by`, `VERSION`) values('3','78','25','Y','fsfsf','ou','ce','a@163.com','15697586126','c2a6a9ae0cd922fde42fe682ea2a3ada','SECRET','23ac61870fe12b73e026b82f15521a19','NORMAL','N',NULL,NULL,NULL,'2017-07-31 20:58:16',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Y',NULL,NULL,'子公司#法人#默认管理员','2017-07-31 20:53:55','B2C_FRONEND_USER','2017-07-31 20:57:22','B2C_FRONEND_USER','1');

CREATE TABLE `t_pt_sys_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `IS_AVAILABLE` varchar(1) NOT NULL,
  `JOB_CODE` varchar(32) NOT NULL,
  `JOB_NAME` varchar(128) NOT NULL,
  `ORDERBY` bigint(20) NOT NULL,
  `ORG_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `t_pt_sys_user_org_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `ISMAIN` varchar(1) NOT NULL,
  `JOB_ID` bigint(20) NOT NULL,
  `ORG_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


CREATE TABLE `t_pt_sys_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `JOB_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

alter table `t_pt_sys_user` drop column `ROLE_ID`;


/*岗位*/
insert into `t_pt_sys_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `DESCRIPTION`, `IS_AVAILABLE`, `JOB_CODE`, `JOB_NAME`, `ORDERBY`,`ORG_ID`) values('1','portalUser','2015-10-26 19:01:58','portalUser','2015-10-26 19:01:58','0','平台总经理','Y','10000','平台总经理','1',2);
insert into `t_pt_sys_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `DESCRIPTION`, `IS_AVAILABLE`, `JOB_CODE`, `JOB_NAME`, `ORDERBY`,`ORG_ID`) values('2','portalUser','2015-10-26 19:01:58','portalUser','2015-10-26 19:01:58','0','平台副总经理','Y','10001','平台副总经理','2',2);
insert into `t_pt_sys_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `DESCRIPTION`, `IS_AVAILABLE`, `JOB_CODE`, `JOB_NAME`, `ORDERBY`,`ORG_ID`) values('3','portalUser','2015-10-26 19:01:58','portalUser','2015-10-26 19:01:58','0','平台财务','Y','10002','平台财务','3',2);
insert into `t_pt_sys_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `DESCRIPTION`, `IS_AVAILABLE`, `JOB_CODE`, `JOB_NAME`, `ORDERBY`,`ORG_ID`) values('4','portalUser','2015-10-26 19:01:58','portalUser','2015-10-26 19:01:58','0','平台前台','Y','10003','平台前台','4',2);
insert into `t_pt_sys_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `DESCRIPTION`, `IS_AVAILABLE`, `JOB_CODE`, `JOB_NAME`, `ORDERBY`,`ORG_ID`) values('5','B2C_FRONEND_USER','2016-11-24 08:44:43','B2C_FRONEND_USER','2016-11-24 08:47:17','1','子公司总经理','Y','20001','子公司总经理','2','3');


/*用户列表，授权--分配岗位，添加服务机构与岗位与用户的关系*/
insert into `t_pt_sys_user_org_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `ISMAIN`, `JOB_ID`, `ORG_ID`, `USER_ID`) values('1','portalUser','2015-10-26 19:27:21','portalUser','2015-10-26 19:27:21','0','Y','1','2','1');
insert into `t_pt_sys_user_org_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `ISMAIN`, `JOB_ID`, `ORG_ID`, `USER_ID`) values('2','portalUser','2015-10-26 19:27:21','portalUser','2015-10-26 19:27:21','0','N','2','2','1');
insert into `t_pt_sys_user_org_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `ISMAIN`, `JOB_ID`, `ORG_ID`, `USER_ID`) values('3','portalUser','2015-10-26 19:27:21','portalUser','2015-10-26 19:27:21','0','N','3','2','1');
insert into `t_pt_sys_user_org_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `ISMAIN`, `JOB_ID`, `ORG_ID`, `USER_ID`) values('4','portalUser','2015-10-26 19:27:21','portalUser','2015-10-26 19:27:21','0','N','4','2','1');
insert into `t_pt_sys_user_org_job` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `ISMAIN`, `JOB_ID`, `ORG_ID`, `USER_ID`) values('5','B2C_FRONEND_USER','2016-11-24 09:17:07','B2C_FRONEND_USER','2016-11-24 09:17:14','1','Y','5','3','2');

/*岗位列表，授权--分配角色，添加岗位与角色的关系*/
insert into `t_pt_sys_auth` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `JOB_ID`, `ROLE_ID`) values('1','portalUser','2015-10-26 19:02:19','portalUser','2015-10-26 19:02:19','0','1','1');
insert into `t_pt_sys_auth` (`id`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `JOB_ID`, `ROLE_ID`) values('3','B2C_FRONEND_USER','2016-11-24 08:46:25','B2C_FRONEND_USER','2016-11-24 08:46:25','0','5','2');




DROP TABLE IF EXISTS `t_pt_sys_job`;
DROP TABLE IF EXISTS `t_pt_sys_user_org_job`;
DROP TABLE IF EXISTS `t_pt_sys_auth`;
ALTER TABLE `t_pt_sys_user` ADD COLUMN ROLE_ID BIGINT NOT NULL COMMENT '角色ID';
UPDATE `t_pt_sys_user` SET ROLE_ID='1' where id='1';
UPDATE `t_pt_sys_user` SET ROLE_ID='2' where id='2';

UPDATE `t_pt_sys_user` t SET t.`STATE` = 'Y';
ALTER TABLE `imall_cloudc`.`t_pt_sys_user` CHANGE `STATE` `IS_ENABLE` VARCHAR(1) CHARSET utf8 COLLATE utf8_general_ci NULL; 
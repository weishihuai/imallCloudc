
drop table if exists t_demo;

CREATE TABLE t_demo (                              
          id bigint(20) NOT NULL AUTO_INCREMENT,
          login_name varchar(255) DEFAULT NULL,
          name varchar(64) DEFAULT NULL,
          password varchar(255) DEFAULT NULL,
          email varchar(128) DEFAULT NULL,
          create_by varchar(256) DEFAULT NULL,
          create_date timestamp NULL DEFAULT NULL,
          last_modified_by varchar(256) DEFAULT NULL,
          last_modified_date timestamp NULL DEFAULT NULL,
          version bigint(20) NOT NULL,
          PRIMARY KEY (id)
);



insert into `t_demo` (`id`, `login_name`, `name`, `password`, `email`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`) values('1','yang','ygw','123123','519870018@qq.com','admin','2015-07-13 10:10:10','admin','2015-07-13 18:00:00','1');
insert into `t_demo` (`id`, `login_name`, `name`, `password`, `email`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`) values('2','zhansan','张三','123123','zhansan@163.com','admin','2015-07-13 10:10:10','admin','2015-07-13 18:00:00','1');
insert into `t_demo` (`id`, `login_name`, `name`, `password`, `email`, `create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`) values('3','lisi','李四','123123','lisi@163.com','admin','2015-07-13 10:10:10','admin','2015-07-13 18:00:00','1');
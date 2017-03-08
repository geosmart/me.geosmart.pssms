-- auto Generated on 2017-03-08 11:22:57 
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`(
    `id` BIGINT (20) NOT NULL  COMMENT '用户编号',
    `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '姓名',
    `email` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    creation_time DATETIME NOT NULL COMMENT '创建时间',
    last_modified_time DATETIME NOT NULL COMMENT '最后修改时间',
    is_deleted TINYINT DEFAULT '0' NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`用户表`';

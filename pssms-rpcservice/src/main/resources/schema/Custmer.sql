-- auto Generated on 2017-04-09 14:07:31
DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer`(
    `serial_id` bigint (20) NOT NULL AUTO_INCREMENT  COMMENT '编号',
    `customer_name` VARCHAR (32) NOT NULL  DEFAULT "1"  COMMENT '客户名称',
    `customer_name_alias` VARCHAR (32) COMMENT '客户别名（多个以,分隔）',
    `contact_name` VARCHAR (32)   COMMENT '联系人',
    `mobile_number`  VARCHAR (16)  COMMENT '联系电话',
    `wechat_code` VARCHAR (64)  COMMENT '微信号',
    `alipay_code` VARCHAR (64)  COMMENT '支付宝号',
    `address` VARCHAR (64)  COMMENT '联系地址',
    `memo` VARCHAR (512)  COMMENT '备注',
    creation_time DATETIME NOT NULL COMMENT '创建时间',
    last_modified_time DATETIME NOT NULL COMMENT '最后修改时间',
    is_deleted TINYINT DEFAULT '0' NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`serial_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '客户表';

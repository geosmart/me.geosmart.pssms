-- auto Generated on 2017-03-08 14:07:31 
DROP TABLE IF EXISTS `tb_back_order_log`;
CREATE TABLE `tb_back_order_log`(
    `serialId` VARCHAR (32) NOT NULL  AUTO_INCREMENT COMMENT '编号',
    `sale_order_id` VARCHAR (64)  COMMENT '销售单号',
    `back_order_id` VARCHAR (32) NOT NULL  COMMENT '退单编号',
    `customer_code` VARCHAR (64)  COMMENT '客户编号',
    `order_date` VARCHAR (32) NOT NULL  COMMENT '交易日期',
    `back_amount` DOUBLE (16,2)  COMMENT '退单使用金额',

    `memo` VARCHAR (512)  COMMENT '备注',
    creation_time DATETIME NOT NULL COMMENT '创建时间',
    last_modified_time DATETIME NOT NULL COMMENT '最后修改时间',
    is_deleted TINYINT DEFAULT '0' NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`serialId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '退货单使用记录';

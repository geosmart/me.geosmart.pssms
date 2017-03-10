-- auto Generated on 2017-03-08 14:07:31 
DROP TABLE IF EXISTS `tb_sale_order`;
CREATE TABLE `tb_sale_order`(
    `serialId` VARCHAR (32) NOT NULL  COMMENT '编号',
    `sale_order_id` VARCHAR (32) NOT NULL  COMMENT '销售单号',
    `order_date` VARCHAR (32) NOT NULL  COMMENT '交易日期',
    `customer_code` VARCHAR (64)  COMMENT '客户编号',
    `product_code` VARCHAR (32)  COMMENT '货号',
    `number` INT (11)  COMMENT '数量',
    `price` DOUBLE (16,2)   COMMENT '价格',
    `amount` DOUBLE (16,2)  COMMENT '金额',

    `memo` VARCHAR (512)  COMMENT '备注',
    creation_time DATETIME NOT NULL COMMENT '创建时间',
    last_modified_time DATETIME NOT NULL COMMENT '最后修改时间',
    is_deleted TINYINT DEFAULT '0' NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`serialId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '货品销售记录';

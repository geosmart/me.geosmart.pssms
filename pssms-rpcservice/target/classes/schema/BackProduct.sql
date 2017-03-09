-- auto Generated on 2017-03-08 14:07:31 
DROP TABLE IF EXISTS `tb_back_product`;
CREATE TABLE `tb_back_product`(
    `serialId` VARCHAR (32) NOT NULL  COMMENT '编号',
    `back_order_id` VARCHAR (32) NOT NULL  COMMENT '关联退单号',
    `customer_code` VARCHAR (64)  COMMENT '客户编号',
    `product_code` VARCHAR (32)  COMMENT '货号',
    `number` INT (11)  COMMENT '数量',
    `price` DOUBLE (16,2)   COMMENT '价格',
    `amount` DOUBLE (16,2)  COMMENT '金额',
    creation_time DATETIME NOT NULL COMMENT '创建时间',
    last_modified_time DATETIME NOT NULL COMMENT '最后修改时间',
    is_deleted TINYINT DEFAULT '0' NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`serialId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '退货记录';

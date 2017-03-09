-- auto Generated on 2017-03-08 14:07:31 
DROP TABLE IF EXISTS `tb_back_order`;
CREATE TABLE `tb_back_order`(
    `back_order_id` VARCHAR (32) NOT NULL  COMMENT '退单号',
    `customer_code` VARCHAR (64)  COMMENT '客户编号',
    `amount` DOUBLE (16,2)  COMMENT '退单金额',
    `parent_back_order_id` VARCHAR (32) COMMENT '父退单号',
    `back_order_status` VARCHAR (32) COMMENT '退单使用情况',

    creation_time DATETIME NOT NULL COMMENT '创建时间',
    last_modified_time DATETIME NOT NULL COMMENT '最后修改时间',
    is_deleted TINYINT DEFAULT '0' NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`back_order_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '退货单生成记录';

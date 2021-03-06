package me.geosmart.pssms.rpcs.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 货品交易（销售/退货）记录
 * </p>
 *
 * @author geosmart
 * @since 2017-03-12
 */
@TableName("tb_sale_order")
public class TbSaleOrder extends BaseEntity<TbSaleOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Long serialId;
    /**
     * 交易类型（0-退货，1-销售）
     */
    @TableField("order_type")
    private String orderType;
    /**
     * 交易单号
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 交易日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @TableField("order_date")
    private Date orderDate;
    /**
     * 客户编号
     */
    @TableField("customer_code")
    private String customerCode;
    /**
     * 货号
     */
    @TableField("product_code")
    private String productCode;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 价格
     */
    private Double price;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 备注
     */
    private String memo;


    public Long getSerialId() {
        return serialId;
    }

    public void setSerialId(Long serialId) {
        this.serialId = serialId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    protected Serializable pkVal() {
        return this.serialId;
    }

}

package me.geosmart.pssms.rpcs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import me.geosmart.pssms.rpcs.entity.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * 退货单使用记录
 * </p>
 *
 * @author geosmart
 * @since 2017-03-09
 */
@TableName("tb_back_order_log")
public class TbBackOrderLog extends BaseEntity<TbBackOrderLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	private String serialId;
    /**
     * 退单号
     */
	@TableField("back_order_id")
	private String backOrderId;
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


	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getBackOrderId() {
		return backOrderId;
	}

	public void setBackOrderId(String backOrderId) {
		this.backOrderId = backOrderId;
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

	@Override
	protected Serializable pkVal() {
		return this.serialId;
	}

}

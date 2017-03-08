package me.geosmart.pssms.rpcs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import me.geosmart.pssms.rpcs.entity.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * 货品销售记录
 * </p>
 *
 * @author geosmart
 * @since 2017-03-08
 */
@TableName("tb_sale_order")
public class TbSaleOrder extends BaseEntity<TbSaleOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 单号
     */
	@TableId("order_id")
	private String orderId;
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


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
		return this.orderId;
	}

}

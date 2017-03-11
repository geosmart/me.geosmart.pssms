package me.geosmart.pssms.rpcs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import me.geosmart.pssms.rpcs.entity.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * 退货单使用记录
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@TableName("tb_back_order_log")
public class TbBackOrderLog extends BaseEntity<TbBackOrderLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
	private String serialId;
    /**
     * 销售单号
     */
	@TableField("sale_order_id")
	private String saleOrderId;
    /**
     * 退单编号
     */
	@TableField("back_order_id")
	private String backOrderId;
    /**
     * 客户编号
     */
	@TableField("customer_code")
	private String customerCode;
    /**
     * 交易日期
     */
	@TableField("order_date")
	private String orderDate;
    /**
     * 退单使用金额
     */
	@TableField("back_amount")
	private Double backAmount;
    /**
     * 备注
     */
	private String memo;


	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Double getBackAmount() {
		return backAmount;
	}

	public void setBackAmount(Double backAmount) {
		this.backAmount = backAmount;
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

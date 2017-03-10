package me.geosmart.pssms.rpcs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import me.geosmart.pssms.rpcs.entity.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * 退货单新增记录
 * </p>
 *
 * @author geosmart
 * @since 2017-03-10
 */
@TableName("tb_back_order")
public class TbBackOrder extends BaseEntity<TbBackOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 退单编号（如果是系统生成，已-0结尾）
     */
	@TableId("back_order_id")
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
     * 退单金额
     */
	private Double amount;
    /**
     * 父退单号
     */
	@TableField("parent_back_order_id")
	private String parentBackOrderId;
    /**
     * 退单使用情况
     */
	@TableField("back_order_status")
	private String backOrderStatus;
    /**
     * 备注
     */
	private String memo;


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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getParentBackOrderId() {
		return parentBackOrderId;
	}

	public void setParentBackOrderId(String parentBackOrderId) {
		this.parentBackOrderId = parentBackOrderId;
	}

	public String getBackOrderStatus() {
		return backOrderStatus;
	}

	public void setBackOrderStatus(String backOrderStatus) {
		this.backOrderStatus = backOrderStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	protected Serializable pkVal() {
		return this.backOrderId;
	}

}

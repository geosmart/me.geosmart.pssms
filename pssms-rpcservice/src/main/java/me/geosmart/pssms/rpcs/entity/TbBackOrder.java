package me.geosmart.pssms.rpcs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import me.geosmart.pssms.rpcs.entity.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * 退货单生成记录
 * </p>
 *
 * @author geosmart
 * @since 2017-03-09
 */
@TableName("tb_back_order")
public class TbBackOrder extends BaseEntity<TbBackOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 退单号
     */
	@TableId("back_order_id")
	private String backOrderId;
    /**
     * 客户编号
     */
	@TableField("customer_code")
	private String customerCode;
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

	@Override
	protected Serializable pkVal() {
		return this.backOrderId;
	}

}

package me.geosmart.pssms.rpcs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import me.geosmart.pssms.rpcs.entity.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * 客户表
 * </p>
 *
 * @author geosmart
 * @since 2017-04-09
 */
@TableName("tb_customer")
public class TbCustomer extends BaseEntity<TbCustomer> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId("serial_id")
	private Long serialId;
    /**
     * 客户名称
     */
	@TableField("customer_name")
	private String customerName;
    /**
     * 客户别名（多个以,分隔）
     */
	@TableField("customer_name_alias")
	private String customerNameAlias;
    /**
     * 联系人
     */
	@TableField("contact_name")
	private String contactName;
    /**
     * 联系电话
     */
	@TableField("mobile_number")
	private String mobileNumber;
    /**
     * 微信号
     */
	@TableField("wechat_code")
	private String wechatCode;
    /**
     * 支付宝号
     */
	@TableField("alipay_code")
	private String alipayCode;
    /**
     * 联系地址
     */
	private String address;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNameAlias() {
		return customerNameAlias;
	}

	public void setCustomerNameAlias(String customerNameAlias) {
		this.customerNameAlias = customerNameAlias;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getWechatCode() {
		return wechatCode;
	}

	public void setWechatCode(String wechatCode) {
		this.wechatCode = wechatCode;
	}

	public String getAlipayCode() {
		return alipayCode;
	}

	public void setAlipayCode(String alipayCode) {
		this.alipayCode = alipayCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

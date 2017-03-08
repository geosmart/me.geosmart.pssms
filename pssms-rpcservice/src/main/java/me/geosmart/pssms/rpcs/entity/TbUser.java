package me.geosmart.pssms.rpcs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import me.geosmart.pssms.rpcs.entity.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * `用户表`
 * </p>
 *
 * @author geosmart
 * @since 2017-03-08
 */
@TableName("tb_user")
public class TbUser extends BaseEntity<TbUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
	private Long id;
    /**
     * 姓名
     */
	private String name;
    /**
     * 电子邮箱
     */
	private String email;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}

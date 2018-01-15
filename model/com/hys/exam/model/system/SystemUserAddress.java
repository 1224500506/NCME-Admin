/**
 *
 * <p>用户收货地址</p>
 * @author chenlaibin
 * @version 1.0 2015-8-18
 */

package com.hys.exam.model.system;

import java.util.Date;

import com.hys.exam.model.BaseObject;

@SuppressWarnings("serial")
public class SystemUserAddress extends BaseObject{

	private Long id;
	
	private Long userId;
	
	private String userName;
	
	private Long userProvinceId;

	private Long userCityId;
	
	private Long userCountiesId; 
	
	private String address;
	
	private Long mobilePhone;
	
	private Long postCode;
	
	private String description;
	
	private Date createDate;
	
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserProvinceId() {
		return userProvinceId;
	}

	public void setUserProvinceId(Long userProvinceId) {
		this.userProvinceId = userProvinceId;
	}

	public Long getUserCityId() {
		return userCityId;
	}

	public void setUserCityId(Long userCityId) {
		this.userCityId = userCityId;
	}

	public Long getUserCountiesId() {
		return userCountiesId;
	}

	public void setUserCountiesId(Long userCountiesId) {
		this.userCountiesId = userCountiesId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(Long mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Long getPostCode() {
		return postCode;
	}

	public void setPostCode(Long postCode) {
		this.postCode = postCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}



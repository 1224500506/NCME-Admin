/**
 *
 * <p>Description: 描述</p>
 * @author chenlaibin
 * @version 1.0 2015-10-23
 */

package com.hys.exam.model.system;

public class SystemUserConfig implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户类别
	 */
	private Integer userType;

	/**
	 * 用户 省厅ID
	 */
	private Long userProvinceId;

	private String userProvinceName;

	/**
	 * 用户 市(盟)
	 */
	private Long userCityId;

	private String userCityName;

	/**
	 * 用户区县ID
	 */
	private Long userCountiesId;

	private String userCountiesName;

	// 街道
	private Long userStreetId;

	private String userStreetName;

	/**
	 * 用户在站点是否可用 1.正常 -1.不可用
	 */
	private Integer isEnable;

	/**
	 * 用户岗位ID
	 */
	private Long userIndustryId;

	private String userIndustryName;

	private String address;
	
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getUserProvinceId() {
		return userProvinceId;
	}

	public void setUserProvinceId(Long userProvinceId) {
		this.userProvinceId = userProvinceId;
	}

	public String getUserProvinceName() {
		return userProvinceName;
	}

	public void setUserProvinceName(String userProvinceName) {
		this.userProvinceName = userProvinceName;
	}

	public Long getUserCityId() {
		return userCityId;
	}

	public void setUserCityId(Long userCityId) {
		this.userCityId = userCityId;
	}

	public String getUserCityName() {
		return userCityName;
	}

	public void setUserCityName(String userCityName) {
		this.userCityName = userCityName;
	}

	public Long getUserCountiesId() {
		return userCountiesId;
	}

	public void setUserCountiesId(Long userCountiesId) {
		this.userCountiesId = userCountiesId;
	}

	public String getUserCountiesName() {
		return userCountiesName;
	}

	public void setUserCountiesName(String userCountiesName) {
		this.userCountiesName = userCountiesName;
	}

	public Long getUserStreetId() {
		return userStreetId;
	}

	public void setUserStreetId(Long userStreetId) {
		this.userStreetId = userStreetId;
	}

	public String getUserStreetName() {
		return userStreetName;
	}

	public void setUserStreetName(String userStreetName) {
		this.userStreetName = userStreetName;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Long getUserIndustryId() {
		return userIndustryId;
	}

	public void setUserIndustryId(Long userIndustryId) {
		this.userIndustryId = userIndustryId;
	}

	public String getUserIndustryName() {
		return userIndustryName;
	}

	public void setUserIndustryName(String userIndustryName) {
		this.userIndustryName = userIndustryName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

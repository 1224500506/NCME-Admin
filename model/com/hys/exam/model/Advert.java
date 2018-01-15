package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;



public class Advert implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1094543104452962009L;

	private Long id;
	private	String name;   //标题
	private String url;    //广告图片地址'
	private String target_url; //点击跳转地址，链接地址',
	private String remark;  //备注
	private Integer type;      //'类型 1:PC端 2:app端',
	private Integer state;   //类型 1:未发布  2:已发布
	private Date createdate;  //发布时间
	private Long site_id; //站点id
	private List<SystemSite> siteList;  //一对多
	private String start_date;  //开始时间
	private String end_date;   //结束时间
	private String creator;    //创建人
	private Long ordernum;  //排序
	private String cover; //存放图片的路径
	private String adver_avatar ;//用户头像，原user_image废弃，YHQ 2017-02-20
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getAdver_avatar() {
		return adver_avatar;
	}
	public void setAdver_avatar(String adver_avatar) {
		this.adver_avatar = adver_avatar;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Long getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}
	
	public Long getSite_id() {
		return site_id;
	}
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}
	public List<SystemSite> getSiteList() {
		return siteList;
	}
	public void setSiteList(List<SystemSite> siteList) {
		this.siteList = siteList;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
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
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget_url() {
		return target_url;
	}
	public void setTarget_url(String target_url) {
		this.target_url = target_url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Advert [id=" + id + ", name=" + name + ", url=" + url + ", target_url=" + target_url + ", remark="
				+ remark + ", type=" + type + ", state=" + state + ", createdate=" + createdate + ", site_id=" + site_id
				+ ", siteList=" + siteList + ", start_date=" + start_date + ", end_date=" + end_date + ", creator="
				+ creator + ", ordernum=" + ordernum + "]";
	}
	

	
}

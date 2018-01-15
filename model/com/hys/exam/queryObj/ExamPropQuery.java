package com.hys.exam.queryObj;
/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-18
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPropQuery extends AbstractQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1810616435854860122L;

	
	/**
	 * 属性值ID
	 */
	private Long id;
	
	/**
	 * 属性类型
	 */
	private Integer type;
	
	/**
	 * 属性类型名称
	 */
	private String typeName;
	
	/**
	 * 属性名
	 */
	private String name;
	
	/**
	 * 属性编码
	 */
	private String code;
	
	/**
	 * 系统属性ID
	 */
	private Long sysPropId;
	
	private String sort;
	private String dir;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getSysPropId() {
		return sysPropId;
	}

	public void setSysPropId(Long sysPropId) {
		this.sysPropId = sysPropId;
	}
}

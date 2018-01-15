

import java.io.Serializable;
import java.util.Date;

public class ExpertGroup  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217567995666788022L;
	
	private Long id;
	private String name;
	private String contact;
	private Long parent;
	private String phone1;
	private String phone2;
	private String email;
	private String adress;
	private String note;
	private Date organizeDate;
	private Date breakDate;
	private String master;
	private Integer state;
	private String summary;
	
	
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Long getParent() {
		return parent;
	}
	public void setParent(Long parent) {
		this.parent = parent;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getOrganizeDate() {
		return organizeDate;
	}
	public void setOrganizeDate(Date organizeDate) {
		this.organizeDate = organizeDate;
	}
	public Date getBreakDate() {
		return breakDate;
	}
	public void setBreakDate(Date breakDate) {
		this.breakDate = breakDate;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	

}

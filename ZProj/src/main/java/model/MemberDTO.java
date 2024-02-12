package model;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String[] inters;
	private String education;
	private String selfintroduce;
	private Date regidate;
	private String email;
	
	

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public MemberDTO() {}



	public MemberDTO(String id, String pwd, String name, String gender, String education,
			String selfintroduce, Date regidate, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.education = education;
		this.selfintroduce = selfintroduce;
		this.regidate = regidate;
		this.email = email;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String[] getInters() {
		return inters;
	}



	public void setInters(String[] inters) {
		this.inters = inters;
	}



	public String getEducation() {
		return education;
	}



	public void setEducation(String education) {
		this.education = education;
	}



	public String getSelfintroduce() {
		return selfintroduce;
	}



	public void setSelfintroduce(String selfintroduce) {
		this.selfintroduce = selfintroduce;
	}



	public Date getRegidate() {
		return regidate;
	}



	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}



	@Override
	public String toString() {		
		return String.format("이름:%s,아이디:%s,비번:%s",name,id,pwd);
	}
}

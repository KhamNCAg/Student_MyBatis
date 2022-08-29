package student_registration_with_spring_n_mybatis.model;

public class Student {
	private String id,name,dob,gender,phone,education,course2find;
	private String [] courses;
	
	public String getCourse2find() {
		return course2find;
	}
	public void setCourse2find(String course2find) {
		this.course2find = course2find;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDob() {
		return dob;
	}
	public String getGender() {
		return gender;
	}
	public String getPhone() {
		return phone;
	}
	public String getEducation() {
		return education;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	

}

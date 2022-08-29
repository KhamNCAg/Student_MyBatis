package student_registration_with_spring_n_mybatis.model;

public class User {
	private String id,name,email,password,cpassword,role;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getCpassword() {
		return cpassword;
	}

	public String getRole() {
		return role;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}

package student_registration_with_spring_n_mybatis.persistent.dto;

public class UserResponseDTO {
	private String id,name,email,password,role;

	public UserResponseDTO(String id, String name,String email, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public UserResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
}

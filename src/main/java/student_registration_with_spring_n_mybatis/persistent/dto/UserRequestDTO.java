package student_registration_with_spring_n_mybatis.persistent.dto;

public class UserRequestDTO {
	private String id,name,email,password,role;

	public UserRequestDTO(String id, String name,String email, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
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
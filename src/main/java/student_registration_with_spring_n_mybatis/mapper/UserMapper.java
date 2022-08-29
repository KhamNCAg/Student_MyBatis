package student_registration_with_spring_n_mybatis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;

import student_registration_with_spring_n_mybatis.model.User;
import student_registration_with_spring_n_mybatis.persistent.dto.UserRequestDTO;
import student_registration_with_spring_n_mybatis.persistent.dto.UserResponseDTO;

public interface UserMapper {
	
	String selectLastId = "SELECT max(ID) AS ID FROM user;";
	String insert = "insert into user(ID,NAME,EMAIL,PASSWORD,ROLE) values(#{id},#{name},#{email},#{password},#{role})";
	String selectAll="SELECT * FROM user";
	String selectOne = "select * from user where ID=#{id}";
	String update = "UPDATE user SET NAME=#{name},EMAIL=#{email},PASSWORD=#{password},ROLE=#{role} where ID=#{id}";
	String delete = "delete from user where ID=#{id}";
	String search ="SELECT ID,NAME FROM user WHERE ID like #{id} OR NAME like #{name};";
	String emailSearch = "SELECT ID FROM user WHERE EMAIL=#{email};";
	String login = "SELECT * FROM user WHERE ID= #{id} AND PASSWORD= #{password}";
	
	@Select(selectLastId)
	public String getLastId();
	
	@Insert(insert)
	public int insert(UserRequestDTO dto);
	
	@Select(selectAll)
	public ArrayList<UserResponseDTO> selectAll();
	
	@Select(selectOne)
	public UserResponseDTO selectOne(String id);
	
	@Update(update)
	public int update(UserRequestDTO dto);
	
	@Delete(delete)
	public int delete(String id);
	
	@Select(search)
	public ArrayList<UserResponseDTO> search(@Param(value = "id")String id,@Param(value = "name")String name);
	
	@Select(emailSearch)
	public String searchEmail(String email);
	
	@Select(login)
	public User Login(@Param(value = "id")String id,@Param(value = "password")String password);

}

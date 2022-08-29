package student_registration_with_spring_n_mybatis.persistent.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_registration_with_spring_n_mybatis.mapper.UserMapper;
import student_registration_with_spring_n_mybatis.persistent.dto.UserRequestDTO;
import student_registration_with_spring_n_mybatis.persistent.dto.UserResponseDTO;

@Service("userDao")
public class UserDao {
	
	@Autowired
	UserMapper umapper;

	public String getLastId(){
		return umapper.getLastId();
	}
		
	public int insertData(UserRequestDTO dto){
		return umapper.insert(dto);
	}
	
	public ArrayList<UserResponseDTO> getUsers(){
		return umapper.selectAll();
	}
	
	public UserResponseDTO selectOne(String id) {
		return umapper.selectOne(id);
	}
	
	public boolean emailExists(String id, String email) {
		String idByEmail =umapper.searchEmail(email); 
				if(idByEmail!=null) {					
					if(id.equals(idByEmail)) {return false;}
					else {return true;}
				}
		return false;
	}
	
	public ArrayList<UserResponseDTO> searchForTable(String id,String name){
		String uid="";
		String uname="";
		if(!id.isEmpty()){uid = "%"+id+"%";}
		if(!name.isEmpty()){uname = "%"+name+"%";}
		return umapper.search(uid,uname);
	}
	
	public int updateData(UserRequestDTO dto){
		return umapper.update(dto);
	}
	
	public int deleteData(String id){
		return umapper.delete(id);
	}

}

package student_registration_with_spring_n_mybatis.persistent.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_registration_with_spring_n_mybatis.mapper.StudentMapper;
import student_registration_with_spring_n_mybatis.persistent.dto.StudentRequestDTO;
import student_registration_with_spring_n_mybatis.persistent.dto.StudentResponseDTO;

@Service("studentDao")
public class StudentDao {
	@Autowired
	StudentMapper smapper;
	@Autowired
	CoursesOfStudentDao csdao;
	
	public String getLastId(){
		return smapper.getLastId();
	}
	
	public int insertData(StudentRequestDTO dto){
		return smapper.insert(dto);
	}
	
	public int updateData(StudentRequestDTO dto){
		return smapper.update(dto);
	}

	public ArrayList<StudentResponseDTO> selectAllForTable(){
		ArrayList<StudentResponseDTO> result = new ArrayList<StudentResponseDTO>(); 
		smapper.selectAll();
		for(StudentResponseDTO temp: smapper.selectAll()) {
		temp.setCourses(csdao.selectNameByStudentID(temp.getId()).toArray(new String[0]));
		result.add(temp);
		}
		return result;
	}

	public ArrayList<StudentResponseDTO> searchForTable(String id,String name,String course){
		ArrayList<StudentResponseDTO> list = new ArrayList<StudentResponseDTO>();
		String uid="";
		String uname="";
		String ucur="";
		if(!id.isEmpty()){uid = "%"+id+"%";}
		if(!name.isEmpty()){uname = "%"+name+"%";}
		if(!course.isEmpty()){ucur = "%"+course+"%";}
		for(String temp : smapper.searchForTable(uid, uname, ucur)) {
			list.add(selectOne(temp));
		}
		return list;
	}
	
	public StudentResponseDTO selectOne(String id) {
		StudentResponseDTO result =smapper.selectOne(id);
		result.setCourses(csdao.selectNameByStudentID(id).toArray(new String[0]));
		return result;
	}
	
	public int deleteData(String id){
		return smapper.delete(id);
	}

}

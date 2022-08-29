package student_registration_with_spring_n_mybatis.persistent.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_registration_with_spring_n_mybatis.mapper.CourseMapper;
import student_registration_with_spring_n_mybatis.persistent.dto.CourseRequestDTO;
import student_registration_with_spring_n_mybatis.persistent.dto.CourseResponseDTO;

@Service("courseDao")
public class CourseDao {

	@Autowired
	CourseMapper cmapper;

		public int insertData(CourseRequestDTO dto){
			return cmapper.insert(dto);
		}
		
		public ArrayList<CourseResponseDTO> selectAll(){
			return cmapper.selectAll();
		}
		
		public ArrayList<String> selectAllNames(){
			return cmapper.selectNames();
		}
		
		public String getLastId(){
			return cmapper.getLastId();
		}

}
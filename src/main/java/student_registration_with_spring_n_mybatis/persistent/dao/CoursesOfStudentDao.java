package student_registration_with_spring_n_mybatis.persistent.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_registration_with_spring_n_mybatis.mapper.CoursesOfStudentMapper;

@Service("studentcourseDao")
public class CoursesOfStudentDao {
	
	@Autowired
	CoursesOfStudentMapper csmapper;
	
	public int insertData(String stu_id, String[] cur_ids){
		int result = 0;
		for(String cur_id : cur_ids) {
		result = csmapper.insert(stu_id, cur_id);
		}
		return result;
	}

	public int insertDataByCourseNames(String stu_id, String[] cur_names){
		List<String> temp = new ArrayList<String>();
		
		for(String cur_name : cur_names) {
			temp.add(csmapper.selectIdByName(cur_name));
		}
		String[] cur_ids = temp.toArray(new String[0]);
		return insertData(stu_id, cur_ids);
	}
	
	public ArrayList<String> selectNameByStudentID(String id){
		return csmapper.selectNameByStuID(id);
	}
	
	public int updateCourses(String stu_id, String[] cur_ids){
		int result = 0;
		if(0<deleteCoursesByStuID(stu_id)) {
			if(0<insertData(stu_id, cur_ids)) {
				result=1;	
			}
		}
		return result;
	}

	public int deleteCoursesByStuID(String id){
		return csmapper.delete(id);
	}
	
}
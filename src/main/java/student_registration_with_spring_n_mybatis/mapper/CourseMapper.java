package student_registration_with_spring_n_mybatis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import student_registration_with_spring_n_mybatis.persistent.dto.CourseRequestDTO;
import student_registration_with_spring_n_mybatis.persistent.dto.CourseResponseDTO;

public interface CourseMapper {
	String insert = "insert into course(ID,NAME) values(#{id},#{name});";
	String selectNames = "select NAME from course;";
	String selectAll = "select * from course;";
	String selectLastId = "SELECT max(ID) AS ID FROM course;";
	
	@Insert(insert)
	public int insert(CourseRequestDTO dto);
	
	@Select(selectAll)
	@Results(value ={
		@Result(column ="id", property="ID"),
		@Result(column ="name", property="NAME")
	})
	public ArrayList<CourseResponseDTO> selectAll();
	
	@Select(selectNames)
	public ArrayList<String> selectNames(); 

	
	@Select(selectLastId)
	public String getLastId();
	
	
}

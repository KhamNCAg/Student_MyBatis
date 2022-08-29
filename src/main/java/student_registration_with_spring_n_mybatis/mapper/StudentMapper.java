package student_registration_with_spring_n_mybatis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;

import student_registration_with_spring_n_mybatis.persistent.dto.StudentRequestDTO;
import student_registration_with_spring_n_mybatis.persistent.dto.StudentResponseDTO;

public interface StudentMapper {
	String insert = "INSERT INTO student(STUDENT_ID,STUDENT_NAME,DOB,GENDER,PHONE,EDUCATION)values"
			+ "(#{id},#{name},#{dob},#{gender},#{phone},#{education});";
	String selectLastId = "SELECT max(STUDENT_ID) AS ID FROM student;";
	String update = "UPDATE student SET STUDENT_NAME=#{name},DOB=#{dob},GENDER=#{gender},PHONE=#{phone},EDUCATION=#{education} where STUDENT_ID=#{id};";
	String selectOne = "select * from student where STUDENT_ID=#{id}";
	String selectAll = "SELECT STUDENT_ID,STUDENT_NAME FROM student;";
	String delete = "delete from student where STUDENT_ID=#{id}";
	String search ="SELECT DISTINCT s.STUDENT_ID FROM student AS s JOIN student_course AS sc "
			+ "ON s.STUDENT_ID = sc.STUDENT_ID JOIN course AS c ON sc.COURSE_ID = c.ID "
			+ "WHERE s.STUDENT_ID LIKE #{id} OR s.STUDENT_NAME LIKE #{name} OR c.NAME like #{course};";
	
	@Insert(insert)
	public int insert(StudentRequestDTO dto);
	
	@Update(update)
	public int update(StudentRequestDTO dto);
	
	@Select(selectAll)
	@Results(value = {
		@Result(column ="STUDENT_ID", property="id"),
		@Result(column ="STUDENT_NAME", property="name")
	})
	public ArrayList<StudentResponseDTO> selectAll();
	
	@Select(selectOne)
	@Results(value = {
		@Result(column ="STUDENT_ID", property="id"),
		@Result(column ="STUDENT_NAME", property="name"),
		@Result(column ="DOB", property="dob"),
		@Result(column ="GENDER", property="gender"),
		@Result(column ="PHONE", property="phone"),
		@Result(column ="EDUCATION", property="education")
	})
	public StudentResponseDTO selectOne(String id);
	
	@Delete(delete)
	public int delete(String id);
	
	@Select(search)
	public ArrayList<String> searchForTable(@Param(value = "id")String id,@Param(value = "name")String name,@Param(value = "course")String course);
	
	@Select(selectLastId)
	public String getLastId();
}

package student_registration_with_spring_n_mybatis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CoursesOfStudentMapper {
	String insert = "insert into student_course(STUDENT_ID,COURSE_ID) values(#{stu},#{cur});";
	String selectNameByStudentID = "SELECT c.NAME FROM student_course AS sc JOIN course AS c ON sc.COURSE_ID = c.ID WHERE sc.STUDENT_ID = #{id};";
	String delete = "DELETE FROM student_course WHERE STUDENT_ID = #{id};";
	String selectIdByName = "SELECT ID FROM course WHERE NAME = #{name};";
	
	@Select(selectIdByName)
	public String selectIdByName(String name);

	@Select(selectNameByStudentID)
	public ArrayList<String> selectNameByStuID(String id);
	
	@Insert(insert)
	public int insert(@Param(value = "stu")String stu,@Param(value = "cur")String cur);
	
	@Delete(delete)
	public int delete(String id);

}
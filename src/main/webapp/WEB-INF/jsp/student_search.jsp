<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/student_registration_with_spring_n_mybatis/style/test.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Search Student</title>
</head>
<body>
	<jsp:include page="/WEB-INF/layout/nav.jsp"/>
    <!-- <div id="testsidebar">Hello World </div> -->
<div class="container">
	<jsp:include page="/WEB-INF/layout/sidebar.jsp"/>

	
		     <div class="main_contents">
    <div id="sub_content">
      <form:form action="search_student" class="row g-3 mt-3 ms-2" method="post" modelAttribute="stu">
        <div class="col-auto">
          <label for="staticEmail2" class="visually-hidden">studentID</label>
          <form:input type="text"  class="form-control" id="staticEmail2" path="id" placeholder="Student ID"></form:input>
        </div>
        <div class="col-auto">
          <label for="inputPassword2" class="visually-hidden">studentName</label>
          <form:input type="text" class="form-control" id="inputPassword2" path="name" placeholder="Student Name"></form:input>
        </div>
        <div class="col-auto">
            <label for="inputPassword2" class="visually-hidden">Course</label>
            <form:input type="text" class="form-control" id="inputPassword2" path="course2find" placeholder="Course"></form:input>
          </div>
        <div class="col-auto">
          <form:button type="submit" class="btn btn-success mb-3">Search</form:button>
        </div>
        <div class="col-auto">
          <form:button type="reset" class="btn btn-secondary mb-3">Reset</form:button>
        </div>
      </form:form>
      
<div>
<h4 style="color: green">${sus}</h4>
      <table class="table table-striped" id="stduentTable">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course Name</th>
            <th scope="col">Details</th>
          </tr>
        </thead>
        <tbody>
          <tr>
          
          	<%!int i=1; %>
				<c:forEach items="${students}" var="data">
								<tr>
								<th scope="row"><%=i++ %></th>
									<td>${data.id}</td>
									<td>${data.name}</td>
									<td>
									<c:forEach items="${data.courses}" var="cur">
										${cur}									
									</c:forEach>
									</td>
									<td>
              							<a href="student_detail/${data.id}"><button type="submit" class="btn btn-secondary mb-2">See More</button></a> 
            						</td>
								</tr>
				</c:forEach><%i=1; %>
				    
                <tr>
        </tbody>
      </table>
    </div>
    </div>
</div>

</div>
        <jsp:include page="/WEB-INF/layout/footer.jsp"/>
        <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>

</body>
</html>
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
    
        <title>User Manage</title>

</head>
<body>
	<jsp:include page="/WEB-INF/layout/nav.jsp"/>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <jsp:include page="/WEB-INF/layout/sidebar.jsp"/>
      <div class="main_contents">
    <div id="sub_content">
        <form:form action="search_user" class="row g-3 mt-3 ms-2" method="post" modelAttribute="user">
            <div class="col-auto">
                <label for="staticEmail2" class="visually-hidden">User Id</label>
                <form:input type="text" class="form-control" id="staticEmail2" placeholder="User ID" path="id"></form:input>
            </div>
            <div class="col-auto">
                <label for="inputPassword2" class="visually-hidden">User Name</label>
                <form:input type="text" class="form-control" id="inputPassword2" placeholder="User Name" path="name"></form:input>
            </div>
    
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3">Search</button>
            </div>
            <div class="col-auto">
            <a href="/student_registration_with_spring_n_mybatis/setup_reg_user"><button type="button" class="btn btn-secondary ">Add</button></a>
    
            </div>
            <div class="col-auto">
                <button type="reset" class="btn btn-danger mb-3">Reset</button>
            </div>
        </form:form>
    <h4 style="color: green">${sus}</h4>
    <h4 style="color: green">${err}</h4>
        <table class="table table-striped" id="stduentTable">
            <thead>
                <tr>
                    
                    <th scope="col">User ID</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Details</th>
                </tr>
            </thead>
            <tbody>
				<c:forEach items="${urs}" var="data">
								<tr>
									<td>${data.id}</td>
									<td>${data.name}</td>
									<td> <a href="setup_update_user/${data.id}"><button type="button" class="btn btn-success">Update</button></a></td>
                					<td>
                					<!-- <button type="submit" class="btn btn-secondary mb-3" data-bs-toggle="modal" data-bs-target="#exampleModal">Delete</button> -->
                					<a href="delete_user/${data.id}"><button type="button" class="btn btn-secondary mb-3" data-bs-toggle="modal">Delete</button></a>
                					</td>
								</tr>
								<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
           						 <div class="modal-dialog modal-dialog-centered">
          					      <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                    <button type="button" class="btn btn-success col-md-2" onclick="window.location.href='delete_user/${data.id}';">OK</button>
    
                    </div>
            	    </div>
        	    	</div>
       				</div>
				</c:forEach>    
            </tbody>
        </table>
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
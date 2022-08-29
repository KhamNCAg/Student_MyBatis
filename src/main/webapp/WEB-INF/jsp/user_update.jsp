<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    
        <title>Update User</title>
</head>
<body>
    <jsp:include page="/WEB-INF/layout/nav.jsp"/>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <jsp:include page="/WEB-INF/layout/sidebar.jsp"/>
      <div class="main_contents">
    <div id="sub_content">
        <form:form action="/student_registration_with_spring_n_mybatis/update_user" method="post" modelAttribute="user">

        <h2 class="col-md-6 offset-md-2 mb-5 mt-4">User Update</h2>
        <h4 class="col-md-6 offset-md-2 mb-5 mt-4" style="color: red">${err}</h4>
        <form:input type="text" class="form-control" id="email" name="id" path="id" hidden="true"></form:input>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="email" class="col-md-2 col-form-label">Email</label>
            <div class="col-md-4">
            	<form:input type="email" class="form-control" id="email" path="email" required="required"></form:input>
            </div>
        </div>
        <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="Name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                	<form:input type="text" class="form-control" id="Name" path="name" required="required"></form:input>
                </div>
            </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="Passowrd" class="col-md-2 col-form-label">Passowrd</label>
            <div class="col-md-4">
	            <form:input type="password" class="form-control" id="Passowrd" path="password" required="required"></form:input>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="confirmPassword" class="col-md-2 col-form-label">Confirm Passowrd</label>
            <div class="col-md-4">
            	<form:input type="password" class="form-control" id="confirmPassword" path="cpassword" required="required"></form:input>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="userRole" class="col-md-2 col-form-label">User Role</label>
            <div class="col-md-4">
            	<form:select class="form-select" path="role" >
					<form:option value="${role}" label="" />
					<form:options items="${roleList}" />
				</form:select>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-4"></div>

            <div class="col-md-6">
               

                <button type="submit" class="btn btn-success col-md-2" data-bs-toggle="modal" >Update</button>
            <button type="button" class="btn btn-secondary col-md-2 " onclick="location.href = '/student_registration_with_spring/setup_search_user';">
                Back
            </button>
    

        </div>
        </form:form>
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
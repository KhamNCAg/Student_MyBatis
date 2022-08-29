<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/student_registration_with_spring_n_mybatis/style/test.css">

<title>Login</title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p>${err}</p>
          </div>
        </div>
        <form:form class="login-form" action="login" modelAttribute="usr" method="post" name="confirm" >
          <form:input type="text" path="id" placeholder="User ID" required="required"/>
          <form:input type="password" path="password" placeholder="Password" required="required"/>
          <button >login</button>
          <p class="message">Not registered? <a href="#">Create an account</a></p>
        </form:form>
      </div>
    </div>
</body>
</html>

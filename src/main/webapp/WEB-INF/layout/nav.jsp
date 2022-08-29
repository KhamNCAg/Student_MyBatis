<%@page import="java.text.SimpleDateFormat"%>
<%
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String date = formatter.format(java.util.Calendar.getInstance().getTime());
%>
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a href="/student_registration_with_spring_n_mybatis/menu"><h3>Student Registration</h3></a>
        </div>  
        <div class="col-md-6">
            <p>User: ${sessionScope.loginUser.id} ${sessionScope.loginUser.name}</p>
            <p>Current Date : <%=date%> </p>
        </div>  
        <div class="col-md-1" >
            <input type="button" class="btn-basic" id="lgnout-button" value="Log Out" onclick="location.href='/student_registration_with_spring_n_mybatis/logout'">
        </div>        
    </div>
</div>

</div>

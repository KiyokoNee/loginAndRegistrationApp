<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login & Registration</title>
	</head>
	<body>
			<h1>Welcome!</h1>
			<p>Join our growing community.</p>
			<div id="forms">
				<form:form modelAttribute="newUser">
					<h2>Register</h2>	
					<span class="errors"><form:errors path="userName" /></span>
					<div>
						<form:label path="userName">User Name: </form:label>
						<form:input path="userName" />
					</div>	
					<span class="errors"><form:errors path="email" /></span>
					<div>
						<form:label path="email">Email: </form:label>
						<form:input path="email" />
					</div>	
					<span class="errors"><form:errors path="password" /></span>
					<div>
						<form:label path="password">Password: </form:label>
						<form:input path="password" />
					</div>	
					<span class="errors"><form:errors path="confirm" /></span>
					<div>
						<form:label path="confirm">Confirm Password: </form:label>
						<form:input path="confirm" />
					</div>
					<button class="formButton" type="submit">Submit</button>
				</form:form>
				<form:form modelAttribute="newLogin">
					<h2>Login</h2>
					<span class="errors"><form:errors path="email" /></span>
					<div>
						<form:label path="email">Email: </form:label>
						<form:input path="email" />
					</div>	
					<span class="errors"><form:errors path="password" /></span>
					<div>
						<form:label path="password">Password: </form:label>
						<form:input path="password" />
					</div>
					<button class="formButton" type="submit">Submit</button>
				</form:form>
			</div>
	</body>
</html>
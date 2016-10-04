<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC | Agregar</title>
</head>
<body>
	<h2>Modificar un trabajadores</h2>
	<form:form action="edith" method="post">
		<input type="hidden" name="id" value="${model.id }" />
		<input type="text" name="nombres" placeholder="Nombre" value="${model.nombres }"/>  <br> <br>
		<input type="text" name="apellidos" placeholder="Apellidos" value="${model.apellidos }"/> </br> <br>
		<input type="text" name="edad" placeholder="Edad" value="${model.edad }"/> </br> <br>
		Cargo:
		<select name="selectPosition">
			<c:forEach items="${list}" var="list">
				<option value="${list.id }"> ${list.nombrecargo }</option>
			</c:forEach>
		</select> </br></br>
		<input type="submit" name="send" value="Agregar"/>
	</form:form>
</body>
</html>
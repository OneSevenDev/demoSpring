<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Spring MVC | Inicio</title>
</head>
<body>
	<h1>Lista de trabajadores</h1>
	<a href="${pageContext.request.contextPath}/agregar">Agregar alumno</a>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>NOMBRE</th>
				<th>APELLIDOS</th>
				<th>EDAD</th>
				<th>CARGO</th>
				<th>EDITAR</th>
				<th>ELIMINAR</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<th><c:out value="${list.id}"/></th>
					<th><c:out value="${list.nombres}"/></th>
					<th><c:out value="${list.apellidos}"/></th>
					<th><c:out value="${list.edad}"/></th>
					<th><c:out value="${list.cargo.nombrecargo}"/></th>
					<th><a href="${pageContext.request.contextPath}/editar?id=${list.id}">Editar</a></th>
					<th><a href="${pageContext.request.contextPath}/eliminar?id=${list.id}"
						onclick="return confirm('¿Esta seguro de eliminar?')">Eliminar</a></th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

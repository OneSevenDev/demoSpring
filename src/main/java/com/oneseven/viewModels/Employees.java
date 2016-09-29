package com.oneseven.viewModels;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.oneseven.models.Employee;
import com.oneseven.models.Position;

public class Employees {
	public Employees() {
		// TODO Auto-generated constructor stub
	}

	private static Employees _instance;

	public static Employees Instance() {
		if (_instance == null)
			_instance = new Employees();
		return _instance;
	}

	public ArrayList<Employee> listEmployee() throws Exception {
		Connection conn = null;
		Employee e = null;
		Position p = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
			conn = ConnectionMysql.Instance().ConnectMysql();
			callableStatement = conn.prepareCall("SELECT t.id,nombres,apellidos,edad,c.nombrecargo FROM trabajador t INNER JOIN cargo c ON t.id_cargo = c.id;");
			resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				e = new Employee();
				e.setId(resultSet.getInt("id"));
				e.setNombres(resultSet.getString("nombres"));
				e.setApellidos(resultSet.getString("apellidos"));
				e.setEdad(resultSet.getInt("edad"));

				// Se ingresa el nombre del cargo a la entidad de Position para
				// lograr insertarlos dentro de la entidad Employee
				p = new Position();
				p.setNombrecargo(resultSet.getString("nombrecargo"));
				e.setCargo(p);

				list.add(e);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			conn.close();
			callableStatement.close();
			resultSet.close();
		}
		return list;
	}
	public boolean InsertEmployee() throws Exception{
		Connection conn = null;
		CallableStatement callableStatement = null;
		Employee e = null;
		boolean result = false;
		try {
			conn = ConnectionMysql.Instance().ConnectMysql();
			callableStatement = conn.prepareCall("INSERT INTO trabajador(nombres,apellidos,edad,id_cargo) VALUES (?,?,?,?)");
			e = new Employee();
			callableStatement.setString(1, e.getNombres());
			callableStatement.setString(2, e.getApellidos());
			callableStatement.setInt(3, e.getEdad());
			callableStatement.setInt(4, e.getCargo().getId());
			result = callableStatement.execute();
		} catch (Exception ex) {
			throw ex;
		} finally {
			conn.close();
			callableStatement.close();
		}
		
		return result;
	}
}

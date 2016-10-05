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
			conn = ConnectionMysql.Instance().getConnection();
			callableStatement = conn.prepareCall(
					"SELECT t.id,nombres,apellidos,edad,c.id,c.nombrecargo FROM trabajador t INNER JOIN cargo c ON t.id_cargo = c.id;");
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
				p.setId(resultSet.getInt("c.id"));
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

	public boolean InsertEmployee(Employee e) throws Exception {
		Connection conn = null;
		CallableStatement statement = null;
		boolean result = false;
		try {
			conn = ConnectionMysql.Instance().getConnection();
			statement = conn.prepareCall("INSERT INTO trabajador(nombres,apellidos,edad,id_cargo) VALUES (?,?,?,?)");
			statement.setString(1, e.getNombres());
			statement.setString(2, e.getApellidos());
			statement.setInt(3, e.getEdad());
			statement.setInt(4, e.getCargo().getId());
			result = !statement.execute();
		} catch (Exception ex) {
			throw ex;
		} finally {
			conn.close();
			statement.close();
		}

		return result;
	}

	public boolean deleteEmployee(int id) throws Exception {
		Connection conn = null;
		CallableStatement statement = null;
		boolean result = false;
		try {
			conn = ConnectionMysql.Instance().getConnection();
			statement = conn.prepareCall("DELETE FROM trabajador WHERE id = ?");
			statement.setInt(1, id);
			result = !statement.execute();
		} catch (Exception ex) {
			throw ex;
		} finally {
			conn.close();
			statement.close();
		}
		return result;
	}

	public boolean EditEmployee(Employee e) throws Exception {
		Connection conn = null;
		CallableStatement statement = null;
		boolean result = false;
		try {
			conn = ConnectionMysql.Instance().getConnection();
			statement = conn
					.prepareCall("UPDATE trabajador SET nombres=?,apellidos=?,edad=?, id_cargo = ? WHERE id = ?");
			statement.setString(1, e.getNombres());
			statement.setString(2, e.getApellidos());
			statement.setInt(3, e.getEdad());
			statement.setInt(4, e.getCargo().getId());
			statement.setInt(5, e.getId());
			result = !statement.execute();
		} catch (Exception ex) {
			throw ex;
		} finally {
			conn.close();
			statement.close();
		}

		return result;
	}

	public Employee SearchEmployee(int id) throws Exception {
		Connection conn = null;
		Employee e = null;
		Position p = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		try {
			conn = ConnectionMysql.Instance().getConnection();
			//Enviando Consulta
			callableStatement = conn.prepareCall(
							"SELECT t.id,nombres,apellidos,edad,c.id,c.nombrecargo FROM trabajador t INNER JOIN cargo c ON t.id_cargo = c.id WHERE t.id = ?"
					);
			callableStatement.setInt(1, id);
			resultSet = callableStatement.executeQuery();

			while (resultSet.next()) {
				e = new Employee();
				e.setId(resultSet.getInt("t.id"));
				e.setNombres(resultSet.getString("nombres"));
				e.setApellidos(resultSet.getString("apellidos"));
				e.setEdad(resultSet.getInt("edad"));

				// Se ingresa el nombre del cargo a la entidad de Position para
				// lograr insertarlos dentro de la entidad Employee
				p = new Position();
				p.setId(resultSet.getInt("c.id"));
				p.setNombrecargo(resultSet.getString("nombrecargo"));
				e.setCargo(p);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			conn.close();
			callableStatement.close();
			resultSet.close();
		}
		return e;
	}
}

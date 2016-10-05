package com.oneseven.viewModels;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.oneseven.models.Position;

public class Positions {
	public Positions() {
		// TODO Auto-generated constructor stub
	}

	private static Positions _instance;

	public static Positions Instance() {
		if (_instance == null)
			_instance = new Positions();
		return _instance;
	}
	
	public ArrayList<Position> listPosition() throws Exception{
		ArrayList<Position> list = new ArrayList<Position>();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		Position p = null;
		try {
			connection = ConnectionMysql.Instance().getConnection();
			statement = connection.prepareCall("SELECT id, nombrecargo FROM cargo ORDER BY nombrecargo");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				p = new Position();
				p.setId(resultSet.getInt("id"));
				p.setNombrecargo(resultSet.getString("nombrecargo"));
				list.add(p);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return list;
	}
	public Position backPosition(int id) throws Exception {
		CallableStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		Position p = null;
		try {
			connection = ConnectionMysql.Instance().getConnection();
			statement = connection.prepareCall("SELECT id, nombrecargo FROM cargo WHERE id = ? ORDER BY nombrecargo");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				p = new Position();
				p.setId(resultSet.getInt("id"));
				p.setNombrecargo(resultSet.getString("nombrecargo"));
			}
		} catch (Exception ex) {
			throw ex;
		}
		return p;
	}
}

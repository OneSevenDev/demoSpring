package com.oneseven.models;

public class Employee {
	private int id;
    private String nombres;
    private String apellidos;
    private int edad;
    private Position cargo;
        
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		if(edad>0)
			this.edad = edad;
		else
			this.edad = 0;
	}
	public Position getCargo() {
		return cargo;
	}
	public void setCargo(Position cargo) {
		this.cargo = cargo;
	}
    
}

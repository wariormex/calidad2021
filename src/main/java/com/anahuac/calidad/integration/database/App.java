package com.anahuac.calidad.integration.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class App {
	
	public static void main(String[] args) {
		Alumno a = new Alumno("Alumno1","001",15,"hola@hola.com");
		Connection con;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:33060/pruebas_db","root","secret");
			
			PreparedStatement preparedStatement;
			preparedStatement = con.prepareStatement("insert INTO alumnos_tbl(id, nombre, email, edad) values(?,?,?,?)");
			
			preparedStatement.setString(1, a.getId());
			preparedStatement.setString(2, a.getNombre());
			preparedStatement.setString(3, a.getEmail());
			preparedStatement.setInt(4, a.getEdad());
			
			int result = preparedStatement.executeUpdate();
			System.out.println(">>Return: " + result);
			
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}

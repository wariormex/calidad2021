package com.anahuac.calidad.doubles.DAO;

public interface AlumnoDAO {
	public void addAlumno(Alumno a);
	public void deleteAlumno(Alumno a);
	public void updateEmail(Alumno a);
	public Alumno consultarAlumno(String id);
}

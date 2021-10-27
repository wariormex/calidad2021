package com.anahuac.calidad.doubles.DAO;

public interface AlumnoDAO {
	public boolean addAlumno(Alumno a);
	public boolean deleteAlumno(Alumno a);
	public boolean updateEmail(Alumno a);
	public Alumno consultarAlumno(String id);
}

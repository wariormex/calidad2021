package com.anahuac.calidad.doubles.DAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class FakeOracleAlumnoDAOTest {
	
	private FakeOracleAlumnoDAO dao;
	private HashMap<String, Alumno> alumnos;

	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(FakeOracleAlumnoDAO.class);
		 alumnos = new HashMap<String, Alumno>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addAlumnoTest() {
		int cuantosAntes = alumnos.size();
		System.out.println("Alumnos antes: " + cuantosAntes);
		Alumno alumno1 = new Alumno("Nombnre", "001", 20, "micorreo@hola.com");
		/*
		when(dao.addAlumno(any(Alumno.class))).thenAnswer(
				new Answer<Boolean>() {
					public Boolean answer(InvocationOnMock invocation) throws Throwable{
						Alumno arg = (Alumno) invocation.getArguments()[0];
						alumnos.put(anyString(),arg);
						System.out.println("Alumnos put: " + alumnos.size());
						return true;
			}
		}
		);
		*/
		
		doAnswer(new Answer() {
		      public Object answer(InvocationOnMock invocation) {
		    	  Alumno arg = (Alumno) invocation.getArguments()[0];
					alumnos.put(anyString(), arg);
		          return null;
		      }})
		  .when(dao).addAlumno(any(Alumno.class));
		
		dao.addAlumno(alumno1);
		int cuantosDesp = alumnos.size();
		System.out.println("Alumnos despues: " + cuantosDesp);
		assertThat(cuantosAntes+1,is(cuantosDesp));
	}

}

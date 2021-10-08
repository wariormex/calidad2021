package com.anahuac.calidad.doubles;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MockTest {
	Dependency dependency;
	@Before
	public void setUp() throws Exception {
		dependency = Mockito.mock(Dependency.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println("Resultado = " + dependency.addTwo(5));
		assertThat(dependency.getClassName(), is(nullValue()));
		//fail("Not yet implemented");
	}
	
	private void setearComportamiento() {
		//Definicon de comportamiento esperado
				when(dependency.getClassName()).thenReturn("Nombre de la clase");
	}
	
	@Test
	public void mockValueTest() {
		String resultadoEsperado = "Nombre de la clase";
		
		//Definicon de comportamiento esperado
		//when(dependency.getClassName()).thenReturn(resultadoEsperado);
		setearComportamiento();
		
		//Ejecucion del codigo
		String resultadoEjecucion = dependency.getClassName();
		
		//Verificacion
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void mockExceptionTest() {
		String resultadoEsperado = "Nombre de la clase";
		
		//Definicon de comportamiento esperado
		when(dependency.getClassName()).thenThrow(IllegalArgumentException.class);
		
		//Ejecucion del codigo
		dependency.getClassName();
		
		//Verificacion
		//assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test
	public void mockRealTest() {
		when(dependency.getClassName()).thenCallRealMethod();
		
		assertThat(dependency.getClassName(), is(dependency.getClass().getSimpleName()));
	}
	
	@Test
	public void mockArguentTest() {
		int resultadoEsperado = 10;
		//Definicon de comportamiento esperado
		when(dependency.addTwo(anyInt())).thenReturn(10);
		
		
		//Ejecucion del codigo
		int resultadoEjecucion = dependency.addTwo(0);
		
		//Verificacion
		assertThat(resultadoEsperado, is(resultadoEjecucion));
		assertThat(10,is(dependency.addTwo(59)));
	}
	
	@Test
	public void mockAnswerTest() {
		
		//Definicon de comportamiento esperado
		when(dependency.addTwo(anyInt())).thenAnswer(
				new Answer<Integer>() {
					public Integer answer(InvocationOnMock invocation) throws Throwable{
						int arg = (Integer) invocation.getArguments()[0];
						return arg + 10;
					}
				}
				);
		
		//Ejecucion del codigo
		int resultadoEjecucion = dependency.addTwo(0);
		
		//Verificacion
		assertThat(69,is(dependency.addTwo(59)));
		assertThat(39,is(dependency.addTwo(29)));
	}

}

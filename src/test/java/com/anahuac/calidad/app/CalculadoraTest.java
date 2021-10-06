package com.anahuac.calidad.app;

//import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.anahuac.calidad.app.Calculadora;


public class CalculadoraTest extends Object {

	private Calculadora calculadora;
	@Before
	public void setUp() throws Exception {
		calculadora = new Calculadora();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sumaTest() {
		double result = 4.0; //Resultado esperado
		//Ejercicio a ejecutar
		double resultTest = calculadora.suma(2.0f,2.0f);
		//Verificacion
		//assertEquals(result,resultTest,.01);
		//assertThat(result,is(equalTo(resultTest)));
		assertThat(result,is(closeTo(resultTest,0.01)));
	}
	
	@Test
	public void restaTest() {
		double result = 4.0; //Resultado esperado
		//Ejercicio a ejecutar
		double resultTest = calculadora.resta(6.0f,2.0f);
		//Verificacion
		//assertEquals(result,resultTest,.01);
		//assertThat(result,is(equalTo(resultTest)));
		assertThat(result,is(closeTo(resultTest,0.01)));
	}
	
	@Test
	public void divisionTest() {
		double result = 2.0f; //Resultado esperado
		//Ejercicio a ejecutar
		double resultTest = calculadora.division(4.0f,2.0f);
		//Verificacion
		//assertEquals(result,resultTest,.01);
		//assertThat(result,is(equalTo(resultTest)));
		//assertThat(result,is(not(closeTo(Double.POSITIVE_INFINITY,0.01))));
		assertThat(result,is(closeTo(resultTest,0.01)));
	}
	
	@Test(expected=ArithmeticException.class)
	public void divisionEntera() {
		double result = 2; //Resultado esperado
		//Ejercicio a ejecutar
		double resultTest = calculadora.divisionEntera(10,0);
		//Verificacion
		//assertEquals(result,resultTest,.01);
		//assertThat(result,is(equalTo(resultTest)));
		//assertThat(result,is(not(closeTo(Double.POSITIVE_INFINITY,0.01))));
		assertThat(result,is(resultTest));
	}
}

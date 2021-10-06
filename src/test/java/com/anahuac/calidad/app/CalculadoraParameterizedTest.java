package com.anahuac.calidad.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import com.anahuac.calidad.app.Calculadora;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class CalculadoraParameterizedTest {
	private double arg1;
	private double arg2;
	private double arg3;
	private Calculadora calculadora;
	
	public CalculadoraParameterizedTest(double arg1, double arg2, double arg3){
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
	}
	
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][] {
			{4,2,2},
			{6,3,2},
			{5,5,1},
			{10,5,2},
			{10,0,Double.POSITIVE_INFINITY},
			{0,0,Double.NaN}
		});
	}

	@Before
	public void setUp() throws Exception {
		calculadora = new Calculadora();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void divisionTest() {
		//Ejercicio a ejecutar
		double resultTest = calculadora.division(this.arg1,this.arg2);
		//Verificacion
		//assertEquals(result,resultTest,.01);
		//assertThat(result,is(equalTo(resultTest)));
		//assertThat(this.arg3,is(not(closeTo(Double.POSITIVE_INFINITY,0.01))));
		assertThat(this.arg3,is(resultTest));
	}

}

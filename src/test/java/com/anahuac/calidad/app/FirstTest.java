package com.anahuac.calidad.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FirstTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
	}

	@Test
	public void firstTest() {
		System.out.println("T1");
		fail("Not yet implemented");
	}
	
	@Test
	public void secondTest() {
		System.out.println("T2");
		fail("Not yet implemented");
	}
	
	@Test
	public void thirdTest() {
		System.out.println("T3");
		fail("Not yet implemented");
	}
	
	public void otherMethod() {
		System.out.println("Hola");
	}

}

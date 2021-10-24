package com.anahuac.calidad.tareaUnitTest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.anahuac.calidad.doubles.DAO.Alumno;
import com.anahuac.calidad.doubles.DAO.FakeOracleAlumnoDAO;

public class FakeOracleAccountDAOTest {
	
	private FakeOracleAccountDAO dao;
	private FakeAlertListenerDAO alertSys;
	private HashMap<String, Account> accounts;
	private HashMap<String, Double> transactionLog;
	Account account1;
	Account account2;
	Account account3;

	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(FakeOracleAccountDAO.class);
		alertSys = Mockito.mock(FakeAlertListenerDAO.class);
		accounts = new HashMap<String, Account>();
		account1 = new Account("Cuenta1", 1000, 1, alertSys);
		account2 = new Account("Cuenta2", 2000, 2, alertSys);
		account3 = new Account("Cuenta3", 3000, 3, alertSys);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addAccountsTest() {
		int cuantosAntes = accounts.size();
		System.out.println("Cuentas antes: " + cuantosAntes);
	
		doAnswer(new Answer() {
		      public Object answer(InvocationOnMock invocation) {
		    	  Account arg = (Account) invocation.getArguments()[0];
					accounts.put(Integer.toString(accounts.size()+1), arg);
		          return null;
		      }})
		  .when(dao).addAccount(any(Account.class));
		
		dao.addAccount(account1);
		dao.addAccount(account2);
		dao.addAccount(account3);
		int cuantosDesp = accounts.size();
		System.out.println("Cuentas despues: " + cuantosDesp);
		
		//Comprobacion creacion de las cuentas
		assertThat(cuantosAntes+3,is(cuantosDesp));
		//Comprobacion de zonas de las cuentas
		assertThat(accounts.get("1").zone,is(not(accounts.get("2").zone)));
		assertThat(accounts.get("2").zone,is(not(accounts.get("3").zone)));
		assertThat(accounts.get("3").zone,is(not(accounts.get("1").zone)));
		//Comprobacion de saldos de la cuentas
		assertThat(accounts.get("1").balance,is(not(accounts.get("2").balance)));
		assertThat(accounts.get("2").balance,is(not(accounts.get("3").balance)));
		assertThat(accounts.get("3").balance,is(not(accounts.get("1").balance)));
	}
	
	@Test
	public void balance100AlertTest() {
		accounts.put("1", account1);
		accounts.get("1").debit(901);
		
		/*
		doAnswer(new Answer() {
		      public Account answer(InvocationOnMock invocation) {
		    	  Account arg = (Account) invocation.getArguments()[0];
		          return accounts.get(arg);
		      }})
		  .when(dao).consultarAccount(anyString());
		*/
		
		verify(alertSys).sendAlert(accounts.get("1").holder+", your account balance is below 100");
	}
	
	@Test
	public void transactionTest() {
		transactionLog = new HashMap<String, Double>();
		//Cuenta a usar account1
		
		doAnswer(new Answer() {
		      public Object answer(InvocationOnMock invocation) {
		    	  double arg = (double) invocation.getArguments()[0];
		    	  transactionLog.put(Integer.toString(transactionLog.size()+1), arg);
		          return null;
		      }})
		  .when(dao).addBalance(anyDouble());
		
		doAnswer(new Answer() {
		      public Object answer(InvocationOnMock invocation) {
		    	  double arg = (double) invocation.getArguments()[0];
		    	  transactionLog.put(Integer.toString(transactionLog.size()+1), -arg);
		          return null;
		      }})
		  .when(dao).removeBalance(anyDouble());
		
		doAnswer(new Answer() {
		      public Double answer(InvocationOnMock invocation) {
		    	  double suma = 0;
		    	  for(int i = 0; i<transactionLog.size(); i++) {
		    		  suma += account1.getpercentCommission() * Math.abs(transactionLog.get(Integer.toString(i + 1)));
		    	  }
		    	  System.out.println(suma);
		    	  return suma;
		      }})
		  .when(dao).consultarCommissions();
		
		dao.removeBalance(200.0);
		dao.addBalance(100.0);
		assertThat(3.0,is(dao.consultarCommissions()));
	}

}

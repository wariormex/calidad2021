package com.anahuac.calidad.tareaUnitTest;

public interface AccountDAO {
	public void addAccount(Account a);
	public void deleteAccount(Account a);
	public void addBalance (double m);
	public void removeBalance (double m);
	public void updateZone(double z);
	public Account consultarAccount(String holder);
	public double consultarCommissions();
}

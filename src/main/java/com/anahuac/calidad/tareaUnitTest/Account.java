package com.anahuac.calidad.tareaUnitTest;
public class Account {
    
    int balance;
    //double commissionAccount;
    double percentCommission;
    double zone;
    String holder;
    AlertListener alerts;

    public Account(String holder, int initialBalance, double zone, AlertListener alerts){
        this.holder = holder;
        this.balance = initialBalance;
        //commissionAccount = 0;
        this.zone = zone;
        percentCommission = zone/100;
        this.alerts = alerts;
    }
    
    public int getBalance() {
        return this.balance;
    }
    
    public String getHolder(){
        return this.holder;
    }
    /*
    public double getCommissionAccount() {
    	return this.commissionAccount;
    }
    */
    public double getpercentCommission() {
    	return this.percentCommission;
    }

    void debit(int balance) {
    	//double commission = (balance*(zone/100));
    	//commissionAccount +=commission;
        this.balance -= (balance /*+ commission*/);
        if(this.balance < 100){
            this.alerts.sendAlert(this.holder+", your account balance is below 100");
        }
    }

    void credit(int balance) {
    	//double commission = (balance*(zone/100));
    	//commissionCuenta +=commission;
        this.balance += (balance /*- commission*/);
    }
    
    void setAlertListener(AlertListener listener){
        this.alerts = listener;
    }
    
}
package ebs.entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BillingUser {

	private int meter_no;
	private long unitsConsumed;
	private String billDate;
	private String connectionType;
	private double amount;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 
	
	public int getMeter_no() {
		return meter_no;
	}
	public void setMeter_no(int meter_no) {
		this.meter_no = meter_no;
	}
	public long getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(long unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String date) {
		//Date currentDate = new Date(date);
        this.billDate= date; 
        
	}
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}

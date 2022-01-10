package model;

public class Auction {
	private String entrepriseName;
	private String email;
	private long contactNo;
	private double offeringPrice;
	
	public Auction(String entrepriseName,String email,long contactNo,double offeringPrice) {
		this.entrepriseName = entrepriseName;
		this.email = email;
		this.contactNo = contactNo;
		this.offeringPrice = offeringPrice;
	}
	
	public String getEntrepriseName() {
		return this.entrepriseName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public long getContactNo() {
		return this.contactNo;
	}
	
	public double getOfferingPrice() {
		return this.offeringPrice;
	}
	
	@Override 
	public String toString() {
		return "Offeringprice - "+this.getOfferingPrice();
	}
}

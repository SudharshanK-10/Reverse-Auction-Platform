package model;

public class Seller {
	
	private String entrepriseName;
	private String email;
	private String password;
	private long regId;
	private String ownerName;
	private String address;
	private long contactNo;
	
	public Seller() {
		entrepriseName = "";
		email = "";
		password = "";
		regId = 0;
		ownerName = "";
		address = "";
		contactNo = 0;
	}
	
	public Seller(String email,String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEntrepriseName() {
		return entrepriseName;
	}
	
	public void setEntrepriseName(String entrepriseName) {
		this.entrepriseName = entrepriseName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getRegId() {
		return regId;
	}
	
	public void setRegId(long regId) {
		this.regId = regId;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public long getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	
	@Override
	public String toString() {
		String ans = "Entreprise Name - "+entrepriseName+"\n";
		ans += "Email - "+email+"\n";
		ans += "Password - "+password+"\n";
		ans += "Owner name - "+ownerName+"\n";
		ans += "Address - "+address+"\n";
		ans += "Contact no - "+contactNo+"\n";
		
		return ans;
	}
}

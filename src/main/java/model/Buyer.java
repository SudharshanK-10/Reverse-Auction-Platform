package model;

public class Buyer {
	
	private String name;
	private String email;
	private String password;
	private String address;
	private long contactNo;
	
	public Buyer() {
		name = "";
		email = "";
		password = "";
		address = "";
		contactNo = 0;
	}
	
	public Buyer(String email,String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
		String ans = "Name - "+name+"\n";
		ans += "Email - "+email+"\n";
		ans += "Password - "+password+"\n";
		ans += "Address - "+address+"\n";
		ans += "Contact no - "+contactNo+"\n";
		
		return ans;
	}
}

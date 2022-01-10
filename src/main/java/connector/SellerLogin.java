package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import model.*;


public class SellerLogin {
	public Seller AuthenticateCredentials(Seller seller) {
		
		System.out.println(seller.getEmail() + " --- "+seller.getPassword());
		
		//Authenticate the login credentials
		
		String pwd = seller.getPassword();
		//hashing the password
		try {
			String key = "Bar12345Bar12345"; // 128 bit key
			// Create key and cipher
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(pwd.getBytes());
			pwd = new String(encrypted);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT * FROM seller WHERE email = \""+seller.getEmail()+"\" AND password = \""+pwd+"\";";
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		boolean valid = false;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			if(rs.next()) {
				valid = true;
				seller.setEntrepriseName(rs.getString("entreprisename"));
				seller.setRegId(rs.getLong("regid"));
				seller.setOwnerName(rs.getString("ownername"));
				seller.setContactNo(rs.getLong("contactno"));
				seller.setAddress(rs.getString("address"));
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			
		}
		
		if(valid) {
			System.out.println("VALID SELLER!");
			return seller;
		}
		System.out.println("SELLER DOESN'T EXISTS!");
		return null;
	}
	
public boolean Register(Seller seller) {
		
		String pwd = seller.getPassword();
		//hashing the password
		try {
			String key = "Bar12345Bar12345"; // 128 bit key
			// Create key and cipher
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(pwd.getBytes());
			pwd = new String(encrypted);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO seller (entreprisename,email,password,ownername,address,contactno) ";
		sql += "VALUES (\""+seller.getEntrepriseName()+"\",\""+seller.getEmail()+"\",\""+pwd+"\",\"";	
		sql += seller.getOwnerName() + "\",\""+seller.getAddress()+"\",\""+seller.getContactNo()+"\");";
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
				
		try { 
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	public ArrayList<Item> getOrderRequests(Seller seller){
		ArrayList<Item> orderRequests = new ArrayList<Item>();
		
		String sql = "SELECT * FROM pendingorders WHERE ";
		sql += "status = 0 AND ";
		sql += "sellerEmail = \""+seller.getEmail()+"\";";
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		System.out.println(sql);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			while(rs.next()) {
				Item item = new Item(rs.getString("item"),rs.getInt("quantity"));
				item.setOrderID(rs.getInt("orderid"));
				item.setDateOfOrder(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dateoforder")));
				orderRequests.add(item);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(orderRequests);
		
		Collections.sort(orderRequests,new SortByDateOfOrder());
		return orderRequests;
	}
	
	public void acceptOrderRequest(int orderID, double offeringPrice, Seller seller) {
		
		String sql = "UPDATE pendingorders SET status = 1, price = "+offeringPrice;
		sql += " WHERE orderid = " + orderID + " AND sellerEmail = \"" + seller.getEmail() + "\";";
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
				
		try { 
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rejectOrderRequest(int orderID, Seller seller) {
		
		String sql = "DELETE FROM pendingorders WHERE orderid = "+orderID+" AND sellerEmail = \"" + seller.getEmail() + "\";";
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
				
		try { 
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Item> getPendingOrders(Seller s){
		ArrayList<Item> pendingOrders = new ArrayList<Item>();
		
		String sql = "SELECT * FROM pendingorders WHERE sellerEmail = "+"\""+s.getEmail()+"\"";
		sql += " AND status = 1;";
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			while(rs.next()) {
				Item item = new Item(rs.getString("item"),rs.getInt("quantity"));
				item.setOrderID(rs.getInt("orderid"));
				item.setDateOfOrder(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dateoforder")));
				pendingOrders.add(item);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Collections.sort(pendingOrders,new SortByDateOfOrder());
		
		return pendingOrders;
	}
	
	public void updateOfferingPrice(int orderID,double newOfferingPrice, String email) {
		String sql = "UPDATE pendingorders SET price = "+newOfferingPrice;
		sql += " WHERE orderid = "+orderID+" AND sellerEmail = \""+email+"\";";
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
				
		try { 
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendFeedback(String email,String subject,String feedback) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String date = formatter.format(new Date());
	    
	    String sql = "INSERT INTO feedback (date,email,subject,feedback) ";
		sql += "VALUES (\""+date+"\",\""+email+"\",\""+subject+"\",";	
		sql += "\""+feedback+"\");";
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

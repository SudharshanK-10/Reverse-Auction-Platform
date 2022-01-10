package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import model.*;
import java.util.*;
import java.text.*;

class SortByDateOfOrder implements Comparator<Item> {
	public int compare(Item a,Item b) {
		return b.getDateOfOrder().compareTo(a.getDateOfOrder());
	}
}

class SortByOfferingPrice implements Comparator<Auction> {
	public int compare(Auction a,Auction b) {
		return a.getOfferingPrice() > b.getOfferingPrice() ? 1 : -1;
	}
}

public class BuyerLogin {
	
	public Buyer AuthenticateCredentials(Buyer buyer) {
		
		System.out.println(buyer.getEmail() + " --- "+buyer.getPassword());
		
		//Authenticate the login credentials
		String pwd = buyer.getPassword();
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
		
		String sql = "SELECT * FROM buyer WHERE email = \""+buyer.getEmail()+"\" AND password = \""+pwd+"\";";
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
				buyer.setName(rs.getString("name"));
				buyer.setContactNo(rs.getLong("contactno"));
				buyer.setAddress(rs.getString("address"));
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
			System.out.println("VALID BUYER!");
			return buyer;
		}
		System.out.println("BUYER DOESN'T EXISTS!");
		return null;
	}
	
	public boolean Register(Buyer buyer) {
		
		String pwd = buyer.getPassword();
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
		
		String sql = "INSERT INTO buyer (name,email,address,contactno,password) ";
		sql += "VALUES (\""+buyer.getName()+"\",\""+buyer.getEmail()+"\",\""+buyer.getAddress()+"\",";	
		sql += buyer.getContactNo() + ",\""+pwd+"\");";
		
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
	
	public boolean sendOrderRequest(Item item,Buyer buyer) {
		//get all the sellers
		String sql = "SELECT * FROM seller;";
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		ArrayList<String> sellerEmails = new ArrayList<String>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			while(rs.next()) {
				sellerEmails.add(rs.getString("email"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String stringDate = formatter.format(item.getDateOfOrder()); 
	    
	    //insert the order into orders table
	    sql = "INSERT INTO orders (buyerEmail,item,quantity,dateoforder) VALUES (";
		sql += "\""+buyer.getEmail()+"\""+",";
		sql += "\""+item.getItemName()+"\""+",";
		sql += item.getQuantity()+",";
		sql += "\""+stringDate+"\"";
		sql += ");";
		
		System.out.println(sql);
		
		conn = null;
		p = null;
		rs = null;
		stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	    
	    //get the orderID for this particular order
	    sql = "SELECT MAX(orderid) AS orderid from orders;";
	    
	    System.out.println(sql);
		
		conn = null;
		p = null;
		rs = null;
		stmt = null;
	    
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			if(rs.next()) {
				item.setOrderID(rs.getInt("orderid"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		//send request to these sellers
		for(String se : sellerEmails) {
			sql = "INSERT INTO pendingorders (buyerEmail,sellerEmail,item,quantity,status,price,dateoforder,orderid) VALUES (";
			sql += "\""+buyer.getEmail()+"\""+",";
			sql += "\""+se+"\""+",";
			sql += "\""+item.getItemName()+"\""+",";
			sql += item.getQuantity()+",";
			sql += "FALSE,";
			sql += "0"+",";
			sql += "\""+stringDate+"\",";
			sql += item.getOrderID();
			sql += ");";
			
			System.out.println(sql);
			
			conn = null;
			p = null;
			rs = null;
			stmt = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");  
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Item> getMyOrders(Buyer buyer) {
		ArrayList<Item> myOrders = new ArrayList<Item>();
		
		String sql = "SELECT * FROM orders WHERE buyerEmail = ";
		sql += "\""+buyer.getEmail()+"\";";
		
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
				myOrders.add(item);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Collections.sort(myOrders,new SortByDateOfOrder());
		
		return myOrders;
	}
	
	public Item getItem(int orderID) {
		
		//get the Item of the auction
		Item item = null;
		String sql = "SELECT * FROM orders WHERE orderid = "+orderID+";";
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			if(rs.next()) {
				item = new Item(rs.getString("item"),rs.getInt("quantity"));
				item.setOrderID(rs.getInt("orderid"));
				item.setDateOfOrder(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dateoforder")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
	public ArrayList<Auction> getAuctionList(int orderID, Buyer b){
		ArrayList<Auction> auctionList = new ArrayList<Auction>();
		
		String sql = "SELECT * FROM pendingorders WHERE orderid = "+orderID;
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
				String email = rs.getString("sellerEmail");
				String innerSql = "SELECT * FROM seller WHERE email = \""+email+"\";";
				System.out.println(innerSql);
				
				p = conn.prepareStatement(innerSql);
				ResultSet innerRS = p.executeQuery();
				
				if(innerRS.next()) {
					Auction a = new Auction(innerRS.getString("entreprisename"),email,innerRS.getLong("contactno"),rs.getDouble("price"));
					auctionList.add(a);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Collections.sort(auctionList,new SortByOfferingPrice());
		System.out.println(auctionList);
		return auctionList;
	}
	
	public Buyer getBuyer(int orderID) {
		
		String email = "";
		
		//get the order info and extract the email
		String sql = "SELECT * FROM orders WHERE orderid = "+orderID+";";
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			if(rs.next()) {
				email = rs.getString("buyerEmail");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//get other information about the buyer
		sql = "SELECT * FROM buyer WHERE EMAIL = \""+email+"\";";
		
		 conn = null;
		 p = null;
		 rs = null;
		 stmt = null;
		
		Buyer b = new Buyer("","");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			if(rs.next()) {
				b.setName(rs.getString("NAME"));
				b.setAddress(rs.getString("ADDRESS"));
				b.setContactNo(rs.getLong("CONTACTNO"));
				b.setEmail(email);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	public void lockAuction(int orderID) {
		String sql = "UPDATE orders SET status = 1";
		sql += " WHERE orderid = "+orderID+";";
		
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
	
	public boolean isOrderOver(int orderID) {
		String sql = "SELECT * FROM orders WHERE orderid = "+orderID+";";
		
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverseauctionplatform?useSSL=false","root","root");
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("status") == 1) {
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
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
	
	public ArrayList<String> getTopSellers(){
		ArrayList<String> topSellers = new ArrayList<String>();
		
		String sql = "select item,COUNT(item) as count from orders group by item order by count desc;";
		
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
				if(rs.getString("count") == "0") {
					continue;
				}
				topSellers.add(rs.getString("item"));
				topSellers.add(rs.getString("count"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return topSellers;
	}
}

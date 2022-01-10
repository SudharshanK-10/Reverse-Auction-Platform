package model;

import java.util.*;

public class Item {
	private String itemName;
	private int quantity;
	private int orderID;
	private Date dateOfOrder;
	
	public Item() {
		this.itemName = "";
		this.quantity = 0;
	}
	
	public Item(String itemName,int quantity) {
		this.itemName = itemName;
		this.quantity = quantity;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	
	public Date getDateOfOrder() {
		return this.dateOfOrder;
	}
	
	@Override
	public int hashCode() {
	    return Integer.toString(orderID).hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return true;
	}
}

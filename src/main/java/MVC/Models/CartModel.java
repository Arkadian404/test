package MVC.Models;

import java.sql.Date;

public class CartModel {
	private int cartID;
	private int accountID;
	private Date purchaseDate; 
	private int status;
	public CartModel(int cartID, int accountID, Date purchaseDate, int status) {
		super();
		this.cartID = cartID;
		this.accountID = accountID;
		this.purchaseDate = purchaseDate;
		this.status = status;
	}
	public CartModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the cartID
	 */
	public int getCartID() {
		return cartID;
	}
	/**
	 * @param cartID the cartID to set
	 */
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}

package MVC.Models;

import java.sql.Date;

public class ReceiptModel {
	private int receiptID;
	private int cartID;
	private Date receiptDate;
	public ReceiptModel(int receiptID, int cartID, Date receiptDate) {
		super();
		this.receiptID = receiptID;
		this.cartID = cartID;
		this.receiptDate = receiptDate;
	}
	public ReceiptModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the receiptID
	 */
	public int getReceiptID() {
		return receiptID;
	}
	/**
	 * @param receiptID the receiptID to set
	 */
	public void setReceiptID(int receiptID) {
		this.receiptID = receiptID;
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
	 * @return the receiptDate
	 */
	public Date getReceiptDate() {
		return receiptDate;
	}
	/**
	 * @param receiptDate the receiptDate to set
	 */
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	
	
}

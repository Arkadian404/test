package MVC.Models;

import java.io.Serializable;
import java.sql.Date;

public class ProductModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int productID;
	private String productName;
	private int productAmount;
	private int productPrice;
	private String productDescription;
	private String productImage;
	private int productStatus;
	private int categoryID;
	public ProductModel() {}
	
	
	

	public ProductModel(int productID, String productName, int productAmount, int productPrice,
			String productDescription, String productImage, int productStatus, int categoryID) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productAmount = productAmount;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productImage = productImage;
		this.productStatus = productStatus;
		this.categoryID = categoryID;
	}




	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}




	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}




	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}




	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}




	/**
	 * @return the productAmount
	 */
	public int getProductAmount() {
		return productAmount;
	}




	/**
	 * @param productAmount the productAmount to set
	 */
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}




	/**
	 * @return the productPrice
	 */
	public int getProductPrice() {
		return productPrice;
	}




	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}




	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}




	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}




	/**
	 * @return the productImage
	 */
	public String getProductImage() {
		return productImage;
	}




	/**
	 * @param productImage the productImage to set
	 */
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}




	/**
	 * @return the productStatus
	 */
	public int getProductStatus() {
		return productStatus;
	}




	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}




	/**
	 * @return the categoryID
	 */
	public int getCategoryID() {
		return categoryID;
	}




	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}


	


	

}

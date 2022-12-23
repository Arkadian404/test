package MVC.Models;

public class CartDetailModel {
	private int cartDetailID;
	private ProductModel product;
	private int amount;
	private int price;
	private int cartID;
	private int productID;
	public CartDetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartDetailModel(ProductModel product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
		this.price = product.getProductPrice();
		this.productID = product.getCategoryID();
	}
	/**
	 * @return the cartDetailID
	 */
	public int getCartDetailID() {
		return cartDetailID;
	}
	/**
	 * @param cartDetailID the cartDetailID to set
	 */
	public void setCartDetailID(int cartDetailID) {
		this.cartDetailID = cartDetailID;
	}
	/**
	 * @return the product
	 */
	public ProductModel getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
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
	
	
	
}

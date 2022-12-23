package MVC.Models;

public class CategoryModel {
	private int categoryID;
	private String categoryName;
	private int status;
	public CategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryModel(int cateID, String cateName, int status) {
		super();
		this.categoryID = cateID;
		this.categoryName = cateName;
		this.status = status;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int cateID) {
		this.categoryID = cateID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String cateName) {
		this.categoryName = cateName;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CategoryModel [cateID=" + categoryID + ", cateName=" + categoryName + " , status="
				+ status + "]";
	}
	
}

package MVC.Services;

import java.util.List;

import MVC.Models.ProductModel;

public interface IProductWebServices {
	void insert(ProductModel product);
	void edit(ProductModel product);
	void delete(int id);
	
	void insert(ProductModel product);
	void edit(ProductModel product);
	void delete(int id);
	
	List<ProductModel> getTop4Product();
	List<ProductModel> selectAll();
	List<ProductModel> getAllByCateID(String id);
	//List<ProductModel> findAllPage(int index);
	ProductModel topProduct();
	ProductModel getProductByID(String id);
	ProductModel findByID(int id);
	int countAll();
	int countByCategoryID(int id);
	int countByProductNameSearch(String txt);
	List<ProductModel> searchByProductName(String txt, int index, int pageSize);
	List<ProductModel> pagingProduct(int index);
	List<ProductModel> pagingProductByCateID(int id, int index);
	List<ProductModel> select3LastProduct();
}

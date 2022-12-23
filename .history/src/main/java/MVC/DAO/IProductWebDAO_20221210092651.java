package MVC.DAO;

import java.util.List;

import MVC.Models.ProductModel;

public interface IProductWebDAO {
	void insert(ProductModel product);
	void edit(ProductModel product);
	void delete(int id);
	
	
	List<ProductModel> selectAll();
	List<ProductModel> getTop4Product();
	ProductModel topProduct();
	List<ProductModel> getAllByCateID(String cateID);
	ProductModel getProductByID(String id);
	ProductModel findByID(int id);
	//List<ProductModel> findAllPage(int index);
	int countAll();
	int countByProductNameSearch(String txt);
	int countByCategoryID(int id);
	List<ProductModel>searchByProductName(String txt, int index, int pageSize);
	List<ProductModel> pagingProduct(int index);
	List<ProductModel> pagingProductByCateID(int id, int index);
	List<ProductModel> select3LastProduct();
}

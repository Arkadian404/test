<<<<<<< HEAD
package MVC.Services.Impl;

import java.util.List;

import MVC.DAO.IProductWebDAO;
import MVC.DAO.Impl.ProductWebDAOImpl;
import MVC.Models.ProductModel;
import MVC.Services.IProductWebServices;

public class ProductWebServicesImpl implements IProductWebServices {

	IProductWebDAO productDAO = new ProductWebDAOImpl();

	@Override
	public List<ProductModel> getTop4Product() {
		// TODO Auto-generated method stub
		return productDAO.getTop4Product();
	}

	@Override
	public List<ProductModel> selectAll() {
		// TODO Auto-generated method stub
		return productDAO.selectAll();
	}

	@Override
	public ProductModel topProduct() {
		// TODO Auto-generated method stub
		return productDAO.topProduct();
	}

	@Override
	public List<ProductModel> getAllByCateID(String id) {
		// TODO Auto-generated method stub
		return productDAO.getAllByCateID(id);
	}

	@Override
	public ProductModel getProductByID(String id) {
		// TODO Auto-generated method stub
		return productDAO.getProductByID(id);
	}

	@Override
	public ProductModel findByID(int id) {
		return productDAO.findByID(id);

	}

	/*@Override
	public List<ProductModel> findAllPage(int index) {
		return productDAO.findAllPage(index);
	}*/

	@Override
	public int countAll() {
		return productDAO.countAll();
	}

	@Override
	public List<ProductModel> searchByProductName(String txt, int index, int pageSize) {
		// TODO Auto-generated method stub
		return productDAO.searchByProductName(txt, index, pageSize);
	}

	@Override
	public List<ProductModel> pagingProduct(int index) {
		// TODO Auto-generated method stub
		return productDAO.pagingProduct(index);
	}
	
	@Override
	public int countByCategoryID(int id) {
		// TODO Auto-generated method stub
		return productDAO.countByCategoryID(id);
	}
	
	@Override
	public List<ProductModel> pagingProductByCateID(int id, int index) {
		// TODO Auto-generated method stub
		return productDAO.pagingProductByCateID(id, index);
	}

	@Override
	public List<ProductModel> select3LastProduct() {
		// TODO Auto-generated method stub
		return productDAO.select3LastProduct();
	}

	@Override
	public void insert(ProductModel product) {
		productDAO.insert(product);
		
	}

	@Override
	public void edit(ProductModel product) {
		productDAO.edit(product);
		
	}

	@Override
	public void delete(int id) {
		productDAO.delete(id);
		
	}

	@Override
	public int countByProductNameSearch(String txt) {
		// TODO Auto-generated method stub
		return productDAO.countByProductNameSearch(txt);
	}
}
=======
package MVC.Services.Impl;

import java.util.List;

import MVC.DAO.IProductWebDAO;
import MVC.DAO.Impl.ProductWebDAOImpl;
import MVC.Models.ProductModel;
import MVC.Services.IProductWebServices;

public class ProductWebServicesImpl implements IProductWebServices {

	IProductWebDAO productDAO = new ProductWebDAOImpl();

	@Override
	public List<ProductModel> getTop4Product() {
		// TODO Auto-generated method stub
		return productDAO.getTop4Product();
	}

	@Override
	public List<ProductModel> selectAll() {
		// TODO Auto-generated method stub
		return productDAO.selectAll();
	}

	@Override
	public ProductModel topProduct() {
		// TODO Auto-generated method stub
		return productDAO.topProduct();
	}

	@Override
	public List<ProductModel> getAllByCateID(String id) {
		// TODO Auto-generated method stub
		return productDAO.getAllByCateID(id);
	}

	@Override
	public ProductModel getProductByID(String id) {
		// TODO Auto-generated method stub
		return productDAO.getProductByID(id);
	}

	@Override
	public ProductModel findByID(int id) {
		return productDAO.findByID(id);

	}

	/*@Override
	public List<ProductModel> findAllPage(int index) {
		return productDAO.findAllPage(index);
	}*/

	@Override
	public int countAll() {
		return productDAO.countAll();
	}

	@Override
	public List<ProductModel> searchByProductName(String txt) {
		// TODO Auto-generated method stub
		return productDAO.searchByProductName(txt);
	}

	@Override
	public List<ProductModel> pagingProduct(int index) {
		// TODO Auto-generated method stub
		return productDAO.pagingProduct(index);
	}
	
	@Override
	public int countByCategoryID(int id) {
		// TODO Auto-generated method stub
		return productDAO.countByCategoryID(id);
	}
	
	@Override
	public List<ProductModel> pagingProductByCateID(int id, int index) {
		// TODO Auto-generated method stub
		return productDAO.pagingProductByCateID(id, index);
	}

	@Override
	public List<ProductModel> select3LastProduct() {
		// TODO Auto-generated method stub
		return productDAO.select3LastProduct();
	}

	@Override
	public void insert(ProductModel product) {
		productDAO.insert(product);
		
	}

	@Override
	public void edit(ProductModel product) {
		productDAO.edit(product);
		
	}

	@Override
	public void delete(int id) {
		productDAO.delete(id);
		
	}
}
>>>>>>> upstream/master

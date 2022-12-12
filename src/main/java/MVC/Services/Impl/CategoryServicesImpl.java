package MVC.Services.Impl;

import java.io.File;
import java.util.List;

import MVC.DAO.ICategoryDAO;
import MVC.DAO.Impl.CategoryDAOImpl;
import MVC.Models.CategoryModel;
import MVC.Services.ICategoryServices;

public class CategoryServicesImpl  implements ICategoryServices {
	
	
	ICategoryDAO cateDAO = new CategoryDAOImpl();
	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub.
		return cateDAO.findAll();
	}
	@Override
	public CategoryModel get(int id) {
		// TODO Auto-generated method stub
		return cateDAO.get(id);
	}
	@Override
	public void insert(CategoryModel category) {
		cateDAO.insert(category);
	}
	@Override
	public void edit(CategoryModel newCategory) {
		cateDAO.edit(newCategory);
	}
	@Override
	public void delete(int id) {
		cateDAO.delete(id);
	}
	@Override
	public CategoryModel findByID(int id) {
		return cateDAO.findByID(id);
	}
	@Override
	public List<CategoryModel> findAllByCategoryID(String id) {
		// TODO Auto-generated method stub
		return cateDAO.findAllByCategoryID(id);
	}
	@Override
	public List<CategoryModel> pagingCategory(int index) {
		// TODO Auto-generated method stub
		return cateDAO.pagingCategory(index);
	}
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return cateDAO.count();
	}
	@Override
	public List<CategoryModel> searchByCategoryName(String txtSearch, int index, int pageSize) {
		// TODO Auto-generated method stub
		return cateDAO.searchByCategoryName(txtSearch, index, pageSize);
	}
	@Override
	public int countByCategoryNameSearch(String txtSearch) {
		// TODO Auto-generated method stub
		return cateDAO.countByCategoryNameSearch(txtSearch);
	}


}

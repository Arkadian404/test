package MVC.DAO;
import java.util.List;

import MVC.Models.CategoryModel;
public interface ICategoryDAO {
	CategoryModel get(int id);
	List<CategoryModel> findAll();
	List<CategoryModel> findAllByCategoryID(String id);
	void insert(CategoryModel category);
	void edit(CategoryModel category);
	void delete(int id);
	CategoryModel findByID(int id);
	int count();
	List<CategoryModel> pagingCategory(int index);
}

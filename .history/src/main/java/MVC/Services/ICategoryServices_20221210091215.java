<<<<<<< HEAD
package MVC.Services;

import java.util.List;

import MVC.Models.CategoryModel;

public interface ICategoryServices {
	CategoryModel get(int id);
	List<CategoryModel> findAll();
	List<CategoryModel> findAllByCategoryID(String id);
	void insert(CategoryModel category);
	void edit(CategoryModel category);
	void delete(int id);
	CategoryModel findByID(int id);
	List<CategoryModel> pagingCategory(int index);
	int count();
}
=======
package MVC.Services;

import java.util.List;

import MVC.Models.CategoryModel;

public interface ICategoryServices {
	CategoryModel get(int id);
	List<CategoryModel> findAll();
	List<CategoryModel> findAllByCategoryID(String id);
	void insert(CategoryModel category);
	void edit(CategoryModel category);
	void delete(int id);
	CategoryModel findByID(int id);
}
>>>>>>> upstream/master

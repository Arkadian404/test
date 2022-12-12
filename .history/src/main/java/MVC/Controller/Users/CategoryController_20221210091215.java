<<<<<<< HEAD
package MVC.Controller.Users;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MVC.Models.CategoryModel;
import MVC.Models.ProductModel;
import MVC.Services.ICategoryServices;
import MVC.Services.IProductWebServices;
import MVC.Services.Impl.CategoryServicesImpl;
import MVC.Services.Impl.ProductWebServicesImpl;

@WebServlet(urlPatterns = { "/category" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// Khoi tao service
		IProductWebServices productService = new ProductWebServicesImpl();
		ICategoryServices categoryService = new CategoryServicesImpl();
		String cateID = req.getParameter("categoryID");
		String indexPage = req.getParameter("index");
		if (indexPage == null) {
			indexPage = "1";
		}
		
		int index = Integer.parseInt(indexPage);
		int categoryID = Integer.parseInt(cateID);
		List<CategoryModel> categoryList = categoryService.findAll();
		List<CategoryModel> categoryListID = categoryService.findAllByCategoryID(cateID);
		List<ProductModel> productList = productService.selectAll();

		
		ProductModel topProduct = productService.topProduct();

		//khi mo product mac dinh
		if ("0".equals(cateID)) {
			int count = productService.countAll();

			int endPage = count / 3;
			if (count % 3 != 0) {
				endPage++;
			}
			List<ProductModel> productListPage = productService.pagingProduct(index);// phan trang theo 3 sp
			req.setAttribute("endPage", endPage);
			req.setAttribute("list", productListPage);
		} // khi an vao cac nut phan trang voi index co san
		else {
			int count = productService.countByCategoryID(categoryID);
			int endPage = count / 3;
			if (count % 3 != 0) {
				endPage++;
			}
			List<ProductModel> productListCateID = productService.pagingProductByCateID(categoryID, index);
			req.setAttribute("list", productListCateID);
			//List<ProductModel> productListID = productService.getAllByCateID(cateID);
			//req.setAttribute("list", productListID);
			req.setAttribute("endPage", endPage);

		}

		req.setAttribute("listCate", categoryList);
		req.setAttribute("top", topProduct);
		req.setAttribute("tagActive", categoryID); // active khi cateID product = cateID category
		req.setAttribute("index", index);
		RequestDispatcher rd = req.getRequestDispatcher("views/web/category.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
=======
package MVC.Controller.Users;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MVC.Models.CategoryModel;
import MVC.Models.ProductModel;
import MVC.Services.ICategoryServices;
import MVC.Services.IProductWebServices;
import MVC.Services.Impl.CategoryServicesImpl;
import MVC.Services.Impl.ProductWebServicesImpl;

@WebServlet(urlPatterns = { "/category" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// Khoi tao service
		IProductWebServices productService = new ProductWebServicesImpl();
		ICategoryServices categoryService = new CategoryServicesImpl();
		String cateID = req.getParameter("cateID");
		String indexPage = req.getParameter("index");
		if (indexPage == null) {
			indexPage = "1";
		}
		
		int index = Integer.parseInt(indexPage);
		int cateIDInt = Integer.parseInt(cateID);
		List<CategoryModel> categoryList = categoryService.findAll();
		List<CategoryModel> categoryListID = categoryService.findAllByCategoryID(cateID);
		List<ProductModel> productList = productService.selectAll();

		List<ProductModel> productListID = productService.getAllByCateID(cateID);
		ProductModel topProduct = productService.topProduct();

		//khi mo product mac dinh
		if ("0".equals(cateID)) {
			int count = productService.countAll();

			int endPage = count / 3;
			if (count % 3 != 0) {
				endPage++;
			}
			List<ProductModel> productListPage = productService.pagingProduct(index);// phan trang theo 3 sp
			req.setAttribute("endPage", endPage);
			req.setAttribute("list", productListPage);
		} // khi an vao cac nut phan trang voi index co san
		else {
			int count = productService.countByCategoryID(cateIDInt);
			int endPage = count / 3;
			if (count % 3 != 0) {
				endPage++;
			}
			List<ProductModel> productListCateID = productService.pagingProductByCateID(cateIDInt, index);
			req.setAttribute("list", productListCateID);
			req.setAttribute("endPage", endPage);

		}

		req.setAttribute("listCate", categoryList);
		req.setAttribute("top", topProduct);
		req.setAttribute("tagActive", cateID); // active khi cateID product = cateID category
		req.setAttribute("tag", index);
		RequestDispatcher rd = req.getRequestDispatcher("views/web/category.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
>>>>>>> upstream/master

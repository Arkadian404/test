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

@WebServlet(urlPatterns = {"/search"})
public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		
		IProductWebServices productService = new ProductWebServicesImpl();
		ICategoryServices categoryService = new CategoryServicesImpl();
		
		String txtSearch = req.getParameter("txt");
		String cateID = req.getParameter("cateID");
		
		List<ProductModel> productSearch = productService.searchByProductName(cateID);
		List<CategoryModel> categoryList = categoryService.findAll();

 		ProductModel topProduct = productService.topProduct();
 		
 		req.setAttribute("txtSeach", txtSearch);
 		req.setAttribute("list", productSearch);
 		req.setAttribute("listCate", categoryList);
 		req.setAttribute("top", topProduct);
		
		RequestDispatcher rd = req.getRequestDispatcher("views/web/category.jsp");
		rd.forward(req, resp);
 		
		
	}
}

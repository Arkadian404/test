package MVC.Common;

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

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		//Khoi tao service
		IProductWebServices productService = new ProductWebServicesImpl();
		ICategoryServices categoryService = new CategoryServicesImpl();
		//Thuc thi cac service
		List<CategoryModel> categoryList = categoryService.findAll();
		List<ProductModel> top4 = productService.getTop4Product();
 		List<ProductModel> productList = productService.select3LastProduct();
 		ProductModel topProduct = productService.topProduct();
		//Thiet lap du lieu de call tren JSP
 		req.setAttribute("listCate", categoryList);
		req.setAttribute("top4product", top4);
		req.setAttribute("list", productList);
		req.setAttribute("top", topProduct);
		RequestDispatcher rd = req.getRequestDispatcher("views/web/home.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}

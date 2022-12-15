package MVC.Controller.Seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MVC.Models.CategoryModel;
import MVC.Models.ProductModel;
import MVC.Services.ICategoryServices;
import MVC.Services.IProductWebServices;
import MVC.Services.Impl.CategoryServicesImpl;
import MVC.Services.Impl.ProductWebServicesImpl;
@WebServlet(urlPatterns= {"/seller/home"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IProductWebServices productService =  new ProductWebServicesImpl();
	ICategoryServices categoryService = new CategoryServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		int sellerId = (int)session.getAttribute("sellerId");
		System.out.println(sellerId+"\n");
		List<CategoryModel> sellerCategory = categoryService.findAllCategoryBySellerID(sellerId);
		List<ProductModel> sellerTop4Product  = productService.sellerTop4Product(sellerId);
		List<ProductModel> sellerLastProduct = productService.seller3LastProduct(sellerId);
		ProductModel sellerTopProduct = productService.sellerTopProduct(sellerId);
		System.out.println(sellerTopProduct.getProductName());
		for(int i=0;i<sellerTop4Product.size();i++)
			System.out.println(sellerTop4Product.get(i).getProductName()+"\n");
		req.setAttribute("sellerTop4Product", sellerTop4Product);
		req.setAttribute("sellerLastProduct", sellerLastProduct);
		req.setAttribute("sellerTopProduct", sellerTopProduct);
		req.setAttribute("sellerCategory", sellerCategory);
		req.getRequestDispatcher("/views/seller/home.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}

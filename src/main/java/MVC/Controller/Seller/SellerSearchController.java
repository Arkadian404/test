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


@WebServlet({"/seller/category/search", "/seller/product/search"})
public class SellerSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if (action.equalsIgnoreCase("category")) {
			doPost_Category(req, resp);
		} else if (action.equalsIgnoreCase("product")) {
			doPost_Product(req, resp);
		} 
	}
	
	
	protected void doPost_Category(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		int sellerId = (int)session.getAttribute("sellerId");
		try {

			ICategoryServices categoryService = new CategoryServicesImpl();

			String txtSearch = req.getParameter("txtSearch");
			String indexPage = req.getParameter("index");
			if(indexPage==null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = categoryService.countByCategoryNameSearch(txtSearch, sellerId);
			int pageSize = 3;
			int endPage = count/pageSize;
			if(count%pageSize!=0) {
				endPage++;
			}
			List<CategoryModel> sellerCategoryList = categoryService.searchByCategoryName(txtSearch, sellerId, index, pageSize);
			System.out.print(count+"\n"+index+"\n"+txtSearch+"\n");
			req.setAttribute("txtSearch", txtSearch);
			req.setAttribute("sellerCategoryList", sellerCategoryList);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.getRequestDispatcher("/views/seller/category/search.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Product(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		int sellerId = (int)session.getAttribute("sellerId");
		try {
			IProductWebServices productService = new ProductWebServicesImpl();

			String txtSearch = req.getParameter("txtSearch");
			String indexPage = req.getParameter("index");
			if(indexPage==null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = productService.countByProductNameSearch(txtSearch, sellerId);
			int pageSize = 3;
			int endPage = count/pageSize;
			if(count%pageSize!=0) {
				endPage++;
			}
			List<ProductModel> sellerProductList = productService.searchByProductName(txtSearch, sellerId, index, pageSize);
			System.out.print(count+"\n"+index+"\n"+txtSearch+"\n");
			req.setAttribute("txtSearch", txtSearch);
			req.setAttribute("sellerProductList", sellerProductList);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.getRequestDispatcher("/views/seller/product/search.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}

}

package MVC.Controller.Admin;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MVC.Models.AccountModel;
import MVC.Models.CategoryModel;
import MVC.Models.ProductModel;
import MVC.Models.StaffModel;
import MVC.Services.IAccountServices;
import MVC.Services.ICategoryServices;
import MVC.Services.IProductWebServices;
import MVC.Services.IStaffServices;
import MVC.Services.Impl.AccountServicesImpl;
import MVC.Services.Impl.CategoryServicesImpl;
import MVC.Services.Impl.ProductWebServicesImpl;
import MVC.Services.Impl.StaffServicesImpl;
@WebServlet(urlPatterns= {"/admin/category/search", "/admin/product/search", "/admin/account/search", "/admin/staff/search"})
public class AdminSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	
	protected void doPost_Category(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("utf-8");

			ICategoryServices categoryService = new CategoryServicesImpl();

			String txtSearch = req.getParameter("txtSearch");
			String indexPage = req.getParameter("index");
			if(indexPage==null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = categoryService.countByCategoryNameSearch(txtSearch);
			int pageSize = 3;
			int endPage = count/pageSize;
			if(count%pageSize!=0) {
				endPage++;
			}
			List<CategoryModel> categoryList = categoryService.searchByCategoryName(txtSearch, index, pageSize);
			System.out.print(count+"\n"+index+"\n"+txtSearch+"\n");
			req.setAttribute("txtSearch", txtSearch);
			req.setAttribute("categoryList", categoryList);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.getRequestDispatcher("/views/admin/category/search-category.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Product(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("utf-8");

			IProductWebServices productService = new ProductWebServicesImpl();

			String txtSearch = req.getParameter("txtSearch");
			String indexPage = req.getParameter("index");
			if(indexPage==null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = productService.countByProductNameSearch(txtSearch);
			int pageSize = 3;
			int endPage = count/pageSize;
			if(count%pageSize!=0) {
				endPage++;
			}
			List<ProductModel> productList = productService.searchByProductName(txtSearch, index, pageSize);
			System.out.print(count+"\n"+index+"\n"+txtSearch+"\n");
			req.setAttribute("txtSearch", txtSearch);
			req.setAttribute("productList", productList);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.getRequestDispatcher("/views/admin/product/search-product.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Account(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("utf-8");

			IAccountServices accountService = new AccountServicesImpl();

			String txtSearch = req.getParameter("txtSearch");
			String indexPage = req.getParameter("index");
			if(indexPage==null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = accountService.countByAccountNameSearch(txtSearch);
			int pageSize = 3;
			int endPage = count/pageSize;
			if(count%pageSize!=0) {
				endPage++;
			}
			List<AccountModel> accountList = accountService.searchByAccountName(txtSearch, index, pageSize);
			System.out.print(count+"\n"+index+"\n"+txtSearch+"\n");
			req.setAttribute("txtSearch", txtSearch);
			req.setAttribute("accountList", accountList);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.getRequestDispatcher("/views/admin/account/search-account.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Staff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("utf-8");

			IStaffServices staffService = new StaffServicesImpl();

			String txtSearch = req.getParameter("txtSearch");
			String indexPage = req.getParameter("index");
			if(indexPage==null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = staffService.countByStaffNameSearch(txtSearch);
			int pageSize = 3;
			int endPage = count/pageSize;
			if(count%pageSize!=0) {
				endPage++;
			}
			List<StaffModel> accountList = staffService.searchByStaffName(txtSearch, index, pageSize);
			System.out.print(count+"\n"+index+"\n"+txtSearch+"\n");
			req.setAttribute("txtSearch", txtSearch);
			req.setAttribute("accountList", accountList);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.getRequestDispatcher("/views/admin/staff/search-staff.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
		} else if (action.equalsIgnoreCase("account")) {
			doPost_Account(req, resp);
		} else if(action.equals("staff")) {
			doPost_Staff(req, resp);
		}
	}
}

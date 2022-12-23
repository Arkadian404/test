package MVC.Common;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet(urlPatterns = { "/search" })
public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			req.setCharacterEncoding("utf-8");

			IProductWebServices productService = new ProductWebServicesImpl();
			ICategoryServices categoryService = new CategoryServicesImpl();

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
			List<ProductModel> productSearch = productService.searchByProductName(txtSearch, index, pageSize);
			List<CategoryModel> categoryList = categoryService.findAll();
			ProductModel topProduct = productService.topProduct();
			
			System.out.print(count);
			req.setAttribute("txtSearch", txtSearch);
			req.setAttribute("list", productSearch);
			req.setAttribute("listCate", categoryList);
			req.setAttribute("top", topProduct);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.getRequestDispatcher("/views/search.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

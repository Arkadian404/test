package MVC.Controller.Seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import MVC.Models.CategoryModel;
import MVC.Services.ICategoryServices;
import MVC.Services.Impl.CategoryServicesImpl;

@WebServlet(urlPatterns= {"/seller/category/list"})
public class SellerCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICategoryServices categoryService = new CategoryServicesImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if (action == null) {
			doGet_All(req, resp);
		} else {
			if (action.equalsIgnoreCase("find")) {
				doGet_Find(req, resp);
			}
		}
	}

	protected void doGet_Find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		CategoryModel category;
		try {
			int categoryID = Integer.parseInt(req.getParameter("id"));
			category = categoryService.findByID(categoryID);
			Gson gson = new Gson();
			PrintWriter writer = resp.getWriter();
			writer.print(gson.toJson(category));
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet_All(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			int sellerId = (int) session.getAttribute("sellerId");
			String indexPage = req.getParameter("index");
			if(indexPage == null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = categoryService.countAllBySellerID(index);
			int endPage = count/3;
			if(count%3!=0) {
				endPage++;
			}
			List<CategoryModel> sellerCategoryList = categoryService.pagingCategoryBySellerID(sellerId, index);
			req.setAttribute("sellerCategoryList", sellerCategoryList);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.getRequestDispatcher("/views/seller/category/list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if (action.equalsIgnoreCase("create")) {
			doPost_Create(req, resp);
		} else if (action.equalsIgnoreCase("delete")) {
			doPost_Delete(req, resp);
		} else if (action.equalsIgnoreCase("update")) {
			doPost_Update(req, resp);
		}
	}

	protected void doPost_Create(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CategoryModel category = new CategoryModel();
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			category.setCategoryName(req.getParameter("categoryName"));
			category.setStatus(Integer.parseInt(req.getParameter("status")));
			categoryService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/seller/category/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost_Update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			int categoryID = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = new CategoryModel();
			category = categoryService.findByID(categoryID);
			category.setCategoryName(req.getParameter("categoryName"));
			category.setStatus(Integer.parseInt(req.getParameter("status")));
			categoryService.edit(category);
			resp.sendRedirect(req.getContextPath() + "/seller/category/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost_Delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int categoryID = Integer.parseInt(req.getParameter("id"));
			categoryService.delete(categoryID);
			resp.sendRedirect(req.getContextPath() + "/seller/category/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

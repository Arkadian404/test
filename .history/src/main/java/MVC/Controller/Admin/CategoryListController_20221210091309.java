package MVC.Controllers.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import MVC.Models.CategoryModel;
import MVC.Services.ICategoryServices;
import MVC.Services.Impl.CategoryServicesImpl;

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICategoryServices categoryService = new CategoryServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*List<CategoryModel> cateList = cateService.findAll();
		req.setAttribute("categoryList", cateList);
		RequestDispatcher rq = req.getRequestDispatcher("/views/admin/category/list-category.jsp");
		rq.forward(req, resp);*/
		
		String action = req.getParameter("action");
		if(action == null) {
			doGet_All(req,resp);
		}else {
			if(action.equalsIgnoreCase("find")) {
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
			Gson gson =new Gson();
			PrintWriter writer = resp.getWriter();
			writer.print(gson.toJson(category));
			writer.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet_All(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CategoryModel> categoryList = categoryService.findAll();
		req.setAttribute("categoryList", categoryList);
		req.getRequestDispatcher("/views/admin/category/list-category.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action.equalsIgnoreCase("create")) {
			doPost_Create(req, resp);
		} else if(action.equalsIgnoreCase("delete")) {
			doPost_Delete(req, resp);
		}else if(action.equalsIgnoreCase("update")) {
			doPost_Update(req,resp);
		}
	}
	
	protected void doPost_Create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryModel category = new CategoryModel();
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			category.setCategoryName(req.getParameter("categoryName"));
			category.setStatus(Integer.parseInt(req.getParameter("status")));
			categoryService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int categoryID = Integer.parseInt(req.getParameter("id"));
			categoryService.delete(categoryID);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

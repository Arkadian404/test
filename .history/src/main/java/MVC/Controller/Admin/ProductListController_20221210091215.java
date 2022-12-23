<<<<<<< HEAD:src/main/java/MVC/Controller/Admin/ProductListController.java
package MVC.Controller.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import MVC.Models.ProductModel;
import MVC.Services.IProductWebServices;
import MVC.Services.Impl.ProductWebServicesImpl;

@WebServlet(urlPatterns = { "/admin/product/list" })
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IProductWebServices productService = new ProductWebServicesImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		ProductModel product;
		try {
			int productID = Integer.parseInt(req.getParameter("id"));
			product = productService.findByID(productID);
			Gson gson = new Gson();
			PrintWriter writer = resp.getWriter();
			writer.print(gson.toJson(product));
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet_All(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String indexPage = req.getParameter("index");
			if(indexPage == null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = productService.countAll();
			int endPage = count/3;
			if(count%3!=0) {
				endPage++;
			}
			List<ProductModel> productList = productService.pagingProduct(index);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.setAttribute("productList", productList);
			req.getRequestDispatcher("/views/admin/product/list-product.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action.equalsIgnoreCase("create")) {
			doPost_Create(req, resp);
		} else if (action.equalsIgnoreCase("delete")) {
			doPost_Delete(req, resp);
		} else if (action.equalsIgnoreCase("update")) {
			doPost_Update(req, resp);
		}
	}
	
	protected void doPost_Create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductModel product = new ProductModel();
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			product.setProductName(req.getParameter("productName"));
			product.setProductAmount(Integer.parseInt(req.getParameter("productAmount")));
			product.setProductPrice(Integer.parseInt(req.getParameter("productPrice")));
			product.setProductDescription(req.getParameter("productDescription"));
			product.setProductImage(req.getParameter("productImage"));
			product.setProductStatus(Integer.parseInt(req.getParameter("productStatus")));
			product.setCategoryID(Integer.parseInt(req.getParameter("categoryID")));
			productService.insert(product);
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			int productID = Integer.parseInt(req.getParameter("id"));
			ProductModel product = new ProductModel();
			product = productService.findByID(productID);
			product.setProductName(req.getParameter("productName"));
			product.setProductAmount(Integer.parseInt(req.getParameter("productAmount")));
			product.setProductPrice(Integer.parseInt(req.getParameter("productPrice")));
			product.setProductDescription(req.getParameter("productDescription"));
			product.setProductImage(req.getParameter("productImage"));
			product.setProductStatus(Integer.parseInt(req.getParameter("productStatus")));
			product.setCategoryID(Integer.parseInt(req.getParameter("categoryID")));
			productService.edit(product);
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int productID = Integer.parseInt(req.getParameter("id"));
			productService.delete(productID);
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
=======
package MVC.Controllers.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import MVC.Models.ProductModel;
import MVC.Services.IProductWebServices;
import MVC.Services.Impl.ProductWebServicesImpl;

@WebServlet(urlPatterns = { "/admin/product/list" })
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IProductWebServices productService = new ProductWebServicesImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		ProductModel product;
		try {
			int productID = Integer.parseInt(req.getParameter("id"));
			product = productService.findByID(productID);
			Gson gson = new Gson();
			PrintWriter writer = resp.getWriter();
			writer.print(gson.toJson(product));
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet_All(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String indexPage = req.getParameter("index");
			if(indexPage == null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = productService.countAll();
			int endPage = count/3;
			if(count%3!=0) {
				endPage++;
			}
			List<ProductModel> productList = productService.pagingProduct(index);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.setAttribute("productList", productList);
			req.getRequestDispatcher("/views/admin/product/list-product.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action.equalsIgnoreCase("create")) {
			doPost_Create(req, resp);
		} else if (action.equalsIgnoreCase("delete")) {
			doPost_Delete(req, resp);
		} else if (action.equalsIgnoreCase("update")) {
			doPost_Update(req, resp);
		}
	}
	
	protected void doPost_Create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductModel product = new ProductModel();
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			product.setProductName(req.getParameter("productName"));
			product.setProductAmount(Integer.parseInt(req.getParameter("productAmount")));
			product.setProductPrice(Integer.parseInt(req.getParameter("productPrice")));
			product.setProductDescription(req.getParameter("productDescription"));
			product.setProductImage(req.getParameter("productImage"));
			product.setProductStatus(Integer.parseInt(req.getParameter("productStatus")));
			product.setCategoryID(Integer.parseInt(req.getParameter("categoryID")));
			productService.insert(product);
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			int productID = Integer.parseInt(req.getParameter("id"));
			ProductModel product = new ProductModel();
			product = productService.findByID(productID);
			product.setProductName(req.getParameter("productName"));
			product.setProductAmount(Integer.parseInt(req.getParameter("productAmount")));
			product.setProductPrice(Integer.parseInt(req.getParameter("productPrice")));
			product.setProductDescription(req.getParameter("productDescription"));
			product.setProductImage(req.getParameter("productImage"));
			product.setProductStatus(Integer.parseInt(req.getParameter("productStatus")));
			product.setCategoryID(Integer.parseInt(req.getParameter("categoryID")));
			productService.edit(product);
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int productID = Integer.parseInt(req.getParameter("id"));
			productService.delete(productID);
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
>>>>>>> upstream/master:src/main/java/MVC/Controllers/Admin/ProductListController.java

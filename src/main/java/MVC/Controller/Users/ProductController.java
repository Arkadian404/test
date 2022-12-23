package MVC.Controller.Users;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MVC.Models.CartDetailModel;
import MVC.Models.CategoryModel;
import MVC.Models.ProductModel;
import MVC.Services.ICategoryServices;
import MVC.Services.IProductWebServices;
import MVC.Services.Impl.CategoryServicesImpl;
import MVC.Services.Impl.ProductWebServicesImpl;

@WebServlet(urlPatterns= {"/product"})
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String productID = req.getParameter("productID");
		//Khoi tao service
		IProductWebServices productService = new ProductWebServicesImpl();
		ICategoryServices categoryService = new CategoryServicesImpl();
		//Thuc thi cac service
		List<CategoryModel> categoryList = categoryService.findAll();
 		List<ProductModel> productList = productService.selectAll();
 		ProductModel getProductByID = productService.getProductByID(productID);
 		
		
		//lay so luong hang nay trong kho
		
		HttpSession session = req.getSession();
		List<CartDetailModel> cart = (List<CartDetailModel>) session.getAttribute("cart");
		int productAmountInCart;
		if(cart != null)
		{
			int productIndexInCart = isExisting(Integer.parseInt(productID), cart);
			//chỉ lấy quantity ra chứa không thay đổi quantity product trong cart
			if (productIndexInCart == -1) {
				productAmountInCart = 1;
			} else {
				productAmountInCart = cart.get(productIndexInCart).getAmount();
			}			
		} else {
			productAmountInCart = 1;
		}

		req.setAttribute("productAmountInCart", productAmountInCart);
		//Thiet lap du lieu de call tren JSP
 		req.setAttribute("listCate", categoryList);
 		//req.setAttribute("categoryByID", categoryListID.get(1));
		req.setAttribute("list", productList);
		req.setAttribute("productByID", getProductByID);
		RequestDispatcher rd = req.getRequestDispatcher("views/web/product.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
	private int isExisting(int productId, List<CartDetailModel> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getProductID() == productId) {
				return i;
			}
		}
		return -1;
	}
	
}

package MVC.Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MVC.Models.CartDetailModel;
import MVC.Models.ProductModel;
import MVC.Services.IProductWebServices;
import MVC.Services.Impl.ProductWebServicesImpl;
@WebServlet(urlPatterns= {"/cart"})
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IProductWebServices productService = new ProductWebServicesImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		if (action == null) {
			showCartItems(req, resp);
		} else {
			if (action.equalsIgnoreCase("addToCart")) {
				addToCart(req, resp);
			} else if (action.equalsIgnoreCase("remove")) {
				remove(req, resp);
			} else if (action.equalsIgnoreCase("updateToCart")) {
				updateToCart(req,resp);
			}
		}
	}
	
	protected void showCartItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/web/cart.jsp").forward(req, resp);
	}
	
	
	protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("cart") == null) { //tạo cart mới có 1 product duy nhất
			List<CartDetailModel> cart = new ArrayList<CartDetailModel>();
			int productID = Integer.parseInt(req.getParameter("productID"));
			cart.add(new CartDetailModel(productService.findByID(productID), 1));
			session.setAttribute("cart", cart);
		} else { //ép kiểu
			List<CartDetailModel> cart = (List<CartDetailModel>) session.getAttribute("cart");
			int productId = Integer.parseInt(req.getParameter("productID"));
			int productIndexInCart = isExisting(productId, cart);
			if (productIndexInCart == -1) {
				cart.add(new CartDetailModel(productService.findByID(productId), 1));
			} else {
				ProductModel product = cart.get(productIndexInCart).getProduct();
				//lam service lay so luong hang trong kho
				int productStock = 50;
				int amount = cart.get(productIndexInCart).getAmount() + 1;
				if (amount <= productStock)
				{
					cart.get(productIndexInCart).setAmount(amount);
				} else {
					req.setAttribute("outOfProduct", true);
					amount -= 1;
					cart.get(productIndexInCart).setAmount(amount);
				}
				
			}
			session.setAttribute("cart", cart);
			req.setAttribute("cart", cart);
		}
		resp.sendRedirect(req.getContextPath() + "/views/web/cart.jsp");
	}
	
	private void updateToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("cart") == null) { //tạo cart mới có 1 product duy nhất
			List<CartDetailModel> cart = new ArrayList<CartDetailModel>();
			int productID = Integer.parseInt(req.getParameter("productID"));
			int productAmount = Integer.parseInt(req.getParameter("productAmount"));
			cart.add(new CartDetailModel(productService.findByID(productID), productAmount));
			session.setAttribute("cart", cart);
		} else { //ép kiểu
			List<CartDetailModel> cart = (List<CartDetailModel>) session.getAttribute("cart");
			int productID = Integer.parseInt(req.getParameter("productID"));
			int productAmount= Integer.parseInt(req.getParameter("productAmount"));
			int productIndexInCart = isExisting(productID, cart);
			if (productIndexInCart == -1) {
				cart.add(new CartDetailModel(productService.findByID(productID), productAmount));
			} else {
				/* int quantity = cart.get(productIndexInCart).getQuantity() + 1; */
				int amount = productAmount;
				cart.get(productIndexInCart).setAmount(amount);
			}
			session.setAttribute("cart", cart);
			req.setAttribute("cart", cart);
		}
		resp.sendRedirect(req.getContextPath() + "/views/web/cart.jsp");
	}

	protected void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		List<CartDetailModel> cart = (List<CartDetailModel>) session.getAttribute("cart");
		int productID = Integer.parseInt(req.getParameter("productID"));
		int productIndexInCart = isExisting(productID, cart);
		cart.remove(productIndexInCart);
		session.setAttribute("cart", cart);
		resp.sendRedirect(req.getContextPath() + "/views/web/cart.jsp");
	}
	
	protected int isExisting(int productId, List<CartDetailModel> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getProductID() == productId) {
				return i;
			}
		}
		return -1;
	}

	
	
	
}

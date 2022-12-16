package MVC.Common;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MVC.Models.CartDetailModel;
import MVC.Models.CartModel;
import MVC.Services.IAccountServices;
import MVC.Services.Impl.AccountServicesImpl;


@WebServlet(urlPatterns= {"/order"})
public class OrderController extends HttpServlet{
	static final long serialVersionUID = 1L;
	IAccountServices accountService = new AccountServicesImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		List<CartDetailModel> cart = (List<CartDetailModel>) session.getAttribute("cart");
		req.setAttribute("cart", cart);
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/order.jsp");
		rd.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//form cos them truong dia chi nhan hang va so dien thoai
				//bang cart cung co them 2 truong nay
				//lay cart ra tạo 1 cart với status = 0 chưa được duyệt với createDate là hiện tịa
				resp.setContentType("text/html");
				resp.setCharacterEncoding("utf-8");
				HttpSession session = req.getSession();
				List<CartDetailModel> cart = (List<CartDetailModel>) session.getAttribute("cart");
				
				String username = (String) session.getAttribute("username"); 
				//userId có thể là 0(mặc định) nếu chưa login(xem UserDAOImpl) vì username = null
				int userId = accountService.findAccountId(username);		
				System.out.println("username hiện tại: " + username);
				System.out.println("userId hiện tại: " + userId);
		        java.util.Date date = new java.util.Date();
		        Date datePurchase = new Date(date.getTime());
		        String phoneNumber = req.getParameter("phoneNumber");
		        String address = req.getParameter("address");
		        
				
				CartModel cartToInsert = new CartModel();
				cartToInsert.setAccountID(userId);
				cartToInsert.setPurchaseDate(datePurchase);
				//cartToInsert.setPhoneNumber(phoneNumber); chua xong
				//cartToInsert.setAddress(address); chua xong
				System.out.println("Thông tin cart vừa insert: " + cartToInsert);
				//khi insert mặc định status = 0
				//cartService.insertNewCart(cartToInsert); chua xong
				//nếu 2 bảng Cart và CartItem không có id tăng tự động thì cartService.insertNewCart(cartToInsert) bị lỗi vì không cung cấp id
				//lay id của cart vừa tạo(lấy top 1 với createDate giảm dần)
				//int cartInsertedId = cartService.findCartId(userId, phoneNumber, address);
				//System.out.println("Thông tin id của cart vừa insert: " + cartInsertedId);
				
				//Duyệt qua từng cartModel tạo cartItem và gán cartId là id cart vừa tạo
				/*for(CartItemModel cartItem : cart) {
					//các trường quantity unitPrice productId đã được tạo trong CartController khi gọi constructor 
					//của CartItemModel
					cartItem.setCartId(cartInsertedId);
					System.out.println("Thông tin của các cart item trong cart vừa insert: " + cartItem);
					cartItemService.insertToCart(cartItem);			
				}*/
				
				//xóa cart trong session
				session.removeAttribute("cart");
				resp.sendRedirect(req.getContextPath() + "/home");
	}
	
}

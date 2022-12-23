package MVC.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MVC.Models.CartModel;
import MVC.Models.ReceiptModel;
import MVC.Services.IAccountServices;
import MVC.Services.ICartServices;
import MVC.Services.IReceiptServices;
import MVC.Services.Impl.AccountServicesImpl;
import MVC.Services.Impl.CartServicesImpl;
import MVC.Services.Impl.ReceiptServicesImpl;

@WebServlet(urlPatterns = { "/receipt/list" })
public class ReceiptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICartServices cartService = new CartServicesImpl();
	IAccountServices accountService = new AccountServicesImpl();
	IReceiptServices receiptService = new ReceiptServicesImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		int userId = accountService.findAccountId(username);
		List<CartModel> waitingCartsOfUser = cartService.findAllWaitingOfUser(userId);
		List<CartModel> validCartsOfUser = cartService.findAllValidOfUser(userId);
		List<CartModel> checkOutedCartsOfUser = cartService.findAllCheckOutedOfUser(userId);
		List<ReceiptModel> receiptsOfUser = receiptService.findAllOfCheckOutedCarts(checkOutedCartsOfUser);

		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		req.setAttribute("waitingCartsOfUser", waitingCartsOfUser);
		req.setAttribute("validCartsOfUser", validCartsOfUser);
		req.setAttribute("receiptsOfUser", receiptsOfUser);

		RequestDispatcher rd = req.getRequestDispatcher("/views/web/receipt.jsp");
		rd.forward(req, resp);
	}
}
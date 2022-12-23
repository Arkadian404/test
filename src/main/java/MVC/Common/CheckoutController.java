package MVC.Common;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MVC.Models.ReceiptModel;
import MVC.Services.ICartServices;
import MVC.Services.IReceiptServices;
import MVC.Services.Impl.CartServicesImpl;
import MVC.Services.Impl.ReceiptServicesImpl;

@WebServlet(urlPatterns= {"/order/checkout"})
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICartServices cartService = new CartServicesImpl();
	IReceiptServices receiptService = new ReceiptServicesImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cartId = Integer.parseInt(req.getParameter("cartID"));
		java.util.Date utilDate = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("purchaseDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date buyDate = new Date(utilDate.getTime());
		ReceiptModel receiptToInsert = new ReceiptModel();
		receiptToInsert.setReceiptDate(buyDate);
		receiptToInsert.setCartID(cartId);
		
		receiptService.insert(receiptToInsert);
		cartService.changeStatusToCheckedOut(cartId);
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		RequestDispatcher rd = req.getRequestDispatcher("/receipt/list");
		rd.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
